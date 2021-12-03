package applications;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.Scanner;

import exceptions.TreeException;
import utilities.BSTreeNode;

public class Driver {

	public static void main(String[] args) throws FileNotFoundException, TreeException, ClassNotFoundException {		
		String input_file = "res/textfile.txt";
		String output_file = "";
		
		if (args.length == 0)
		{
			System.out.println("Usage: java - jar Tracker.jar textfile.txt");			
			System.out.println("Other mutually exclusive options:");			
			System.out.println("-pf to print in alphabetic order all words along with the corresponding list of files in which the words occur");
			System.out.println("-pl to print in alphabetic order all words along with the corresponding list of files and numbers of the lines in which the word occur");
			System.out.println("-po to print in alphabetic order all words along with the corresponding list of files, numbers of the lines in which the word occur and the frequency of occurrence of the words.");
			System.out.println("-f filename to redirect the report in the previous step to a spefified file");
		}
		else
		{
			int pf = 0;
			int pl = 0;
			int po = 0;
			boolean redirect = false;
			int ordinalNumber = 0;
			
			for (int i = 0; i < args.length; i++)
			{
				if (args[i].equalsIgnoreCase("-pf"))
				{
					pf = 1;
				}
				else if (args[i].equalsIgnoreCase("-pl"))
				{
					pl = 1;
				}
				else if (args[i].equalsIgnoreCase("-po"))
				{
					po = 1;
				}
				else if (args[i].equalsIgnoreCase("-f"))
				{
					redirect = true;
					ordinalNumber = i;
				}
				else
				{
					if (i == ordinalNumber + 1)
					{
						output_file += args[i];
					}	
				}
			}
		
			if (pf + pl + po > 1)
			{
				System.out.println("Error. Only one option can be assigned.");
			}
			else
			{
				new WordTracker(input_file);
			}
		}
	}
}