package applications;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import exceptions.TreeException;
import utilities.*;

public class WordTracker<E> {

	String binary_file = "res/repository.ser"; 
	BSTReferencedBased bst = new BSTReferencedBased();

	public WordTracker(String input_file, String report_file, String option) throws FileNotFoundException, TreeException, ClassNotFoundException {
		super();
		
		File file = new File(binary_file);
		if (file.exists())
		{
			deserializeBSTreeFromFile(binary_file);
		}

		wordTracker(input_file);
		//serializeBSTreeToFile(binary_file); 
		
		if (report_file != null && !report_file.equals("") )
		{
			PrintStream out = new PrintStream(new FileOutputStream(report_file));
			System.setOut(out);
		}
		
		BSTreeNode<E> node;
		for (Iterator<E> it = bst.inorderIterator(); it.hasNext();) {
			String word = (String) it.next();
			node = bst.search(word);
			ArrayList<String> value = node.getValue();
			
			if (option.equals("pf")) {
				System.out.println(word.toUpperCase());
				System.out.println("-".repeat(20));
				for(int i = 0; i < value.size(); i++)
				{
					System.out.println(value.get(i).split(",")[0]); // filename
				}
				System.out.println();
			}
			else if (option.equals("pl"))
			{
				System.out.println(word.toUpperCase());
				System.out.println("-".repeat(20));
				for(int i = 0; i < value.size(); i++)
				{
					System.out.println(value.get(i).split(",")[0]); // filename
					System.out.println("- at lines [" + value.get(i).substring(value.get(i).indexOf(',') + 1) + "]\n");
				}
				System.out.println();
			}
			else // option.equals("po")
			{
				int occurrence = 0;
				System.out.println(word.toUpperCase());
				System.out.println("-".repeat(20));
				for(int i = 0; i < value.size(); i++)
				{
					occurrence += value.get(i).substring(value.get(i).indexOf(',') + 1).split(",").length;
					System.out.println(value.get(i).split(",")[0]); // filename
					System.out.println("- at lines [" + value.get(i).substring(value.get(i).indexOf(',') + 1) + "]");
				}
				System.out.println( " (" + occurrence + " occurrences)");
				System.out.println();
			}
		}	
	}

	/**
	 * Build a bst using provided text file
	 * @param input_file provided text file which is used to create a bst
	 * @throws FileNotFoundException if provided text file doesn't exist
	 * @throws TreeException if the tree is empty
	 */
	public void wordTracker(String input_file) throws FileNotFoundException, TreeException {

		int line_number = 0;

		Scanner in = new Scanner(new File(input_file));
		
		while (in.hasNextLine())
		{
			String line = in.nextLine();
			
			line_number++;
			
			String[] words = line.replaceAll("[^a-zA-Z ]", "").split(" ");
			
			for (String word : words)
			{
				if (! word.equals("")) {
					word = word.toLowerCase();
					BSTreeNode<E> node;
	
					if (bst.isEmpty() || bst.search(word) == null) //the tree is empty or this node doesn't exist
					{
						bst.add(word);
						node = bst.search(word);
						ArrayList<String> v = new ArrayList<String>();
						v.add(input_file + "," + line_number);
						node.setValue(v);
						//node.setValue(input_file + "," + line_number);
					}
					else // this node exists
					{
						node = bst.search(word);
						ArrayList<String> record = node.getValue(); // find it's value which is an arraylist format
						boolean isUpdate = false;
						
						for (int i = 0; i < record.size(); i++)
						{
							if (input_file.equals(record.get(i).split(",")[0]))	// does the filename exist in arrayList? Yes
							{
								String[] s = record.get(i).split(",");
								if (! s[s.length - 1].equals(Integer.toString(line_number))) // on the different line
								{
									record.set(i, record.get(i) + "," + line_number);	// add line_number at the end
								}
								isUpdate = true;
							}
						}
						
						if (! isUpdate) { // the filename doesn't exist
							node.getValue().add(input_file + "," + line_number);
						}
					}
				}
			}
		}
		
		/*
		BSTreeNode<E> node;
		for (Iterator<E> it = bst.inorderIterator(); it.hasNext();) {
			String word = (String) it.next();
			node = bst.search(word);
			ArrayList<String> value = node.getValue();
		    System.out.println(word + ", " + value);
		}	
		System.exit(0);
		*/		
	}
	
	/**
	 * Store bst to a binary file
	 * @param binary_file the destination to store a bst
	 * @throws TreeException if the tree is empty
	 */
	public void serializeBSTreeToFile(String binary_file) throws TreeException
	{
		try 
		{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(binary_file));
			
			for (Iterator<E> it = bst.inorderIterator(); it.hasNext();) {
				String word = (String) it.next();
				BSTreeNode node = bst.search(word);
				oos.writeObject(node);
			}
			oos.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Restore a bst from a binary file
	 * @param binary_file the file stores a bst in binary format
	 * @throws ClassNotFoundException if the class is not found
	 */
	public void deserializeBSTreeFromFile(String binary_file)
	{
		try 
		{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(binary_file));
			
			BSTreeNode node = null;
			try {
				while ((ois.readObject()) != null)
				{
					node = (BSTreeNode) ois.readObject();
					String word = (String)node.getNode();
					bst.add(word);
					
					try 
					{
						bst.search(word).setValue(node.getValue());
					} 
					catch 
					(TreeException e) 
					{
						e.printStackTrace();
					}
					
					System.out.println(node.getNode() + ", " + node.getValue());					
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			ois.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			//e.printStackTrace();
			// the end of file
		}
	}
	
}
