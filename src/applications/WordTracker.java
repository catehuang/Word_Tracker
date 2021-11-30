package applications;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import exceptions.TreeException;
import utilities.BSTReferencedBased;

public class WordTracker {

	public <E> WordTracker() throws FileNotFoundException, TreeException {

		String input_file = "res/textfile.txt";
		
		int line_number = 0;
		
		BSTReferencedBased bst = new BSTReferencedBased();
		
		HashMap<String, String> h = new HashMap<>();
		
		Scanner in = new Scanner(new File(input_file));
		
		while (in.hasNextLine())
		{
			String line = in.nextLine();
			
			line_number++;
			
			String[] words = line.replaceAll("[^a-zA-Z \']", "").split(" ");
			
			for (String word : words)
			{
				word = word.toLowerCase();
				
				if (bst.isEmpty() || bst.search(word) == null)
				{
					bst.add(word);
					h.put(word, input_file + "," + line_number);
				}
				else
				{
					h.replace(word, h.get(word) + "," + line_number);
				}
			}
		}
		
		for (String i : h.keySet())
		{
			System.out.println(i + " : " + h.get(i));
		}
	}

	


}
