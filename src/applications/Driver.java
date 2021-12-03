package applications;

import java.io.FileNotFoundException;
import exceptions.TreeException;

public class Driver {

	public static void main(String[] args) throws FileNotFoundException, TreeException, ClassNotFoundException {
		String text_file = "res/textfile.txt";
		String report_file = "";

		String option = "";
		int file_arg = 0;
		int key_arg = 0;
		boolean valid = true;

		for (int i = 0; i < args.length; i++) {
			if (args[i].equalsIgnoreCase("-pf")) {
				if (option.equals("")) {
					option = "pf";
				} else {
					System.out.println("Error. Only one option can be assigned.");
					valid = false;
				}
			} else if (args[i].equalsIgnoreCase("-pl")) {
				if (option.equals("")) {
					option = "pl";
				} else {
					System.out.println("Error. Only one option can be assigned.");
					valid = false;
				}
			} else if (args[i].equalsIgnoreCase("-po")) {
				if (option.equals("")) {
					option = "po";
				} else {
					System.out.println("Error. Only one option can be assigned.");
					valid = false;
				}
			} else if (args[i].equalsIgnoreCase("-f")) {
				file_arg = i;
			} else {
				if (i == file_arg + 1) {
					report_file += args[i];
				}
			}
		}

		if (valid != true) {
			message();
		} else {

			// System.out.println(report_file);
			// System.out.println(option);
			new WordTracker(text_file, report_file, option);
		}
	}

	private static void message() {
		System.out.printf("\tjava -jar Tracker.jar [-pf/pl/po] [-f report.txt]\n");
		System.out.printf("\t[] means optional");
		System.out.println("Mutually exclusive options (default is po):");
		System.out.printf(
				"\t-pf to print in alphabetic order all words along with the corresponding list of files in which the words occur\n");
		System.out.printf(
				"\t-pl to print in alphabetic order all words along with the corresponding list of files and numbers of the lines in which the word occur\n");
		System.out.printf(
				"\t-po to print in alphabetic order all words along with the corresponding list of files, numbers of the lines in which the word occur and the frequency of occurrence of the words.\n");
		System.out.println("Other options:");
		System.out.printf("\t-f filename to redirect the report in the previous step to a specified file\n");
	}
}