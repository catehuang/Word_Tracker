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
import utilities.*;

public class WordTracker<E> {

	String binary_file = "res/repository.ser"; 
	BSTReferencedBased bst = new BSTReferencedBased();

	public WordTracker(String input_file) throws FileNotFoundException, TreeException, ClassNotFoundException {
		super();
		
		File file = new File(binary_file);
		if (file.exists())
		{
			deserializeBSTreeFromFile(binary_file);
		}
		else
		{
			wordTracker(input_file);
			serializeBSTreeToFile(binary_file);
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
			
			String[] words = line.replaceAll("[^a-zA-Z \']", "").split(" ");
			
			for (String word : words)
			{
				word = word.toLowerCase();
				BSTreeNode<E> node;

				if (bst.isEmpty() || bst.search(word) == null)
				{
					bst.add(word);
					node = bst.search(word);
					node.setValue(input_file + "," + line_number);
				}
				else
				{
					node = bst.search(word);
					String value = node.getValue();
					String[] value_array = value.split(",");
					int max_length =  value_array.length;

					if (input_file.equals(value_array[0])) //same file
					{

						if (! value_array[max_length - 1].equals(Integer.toString(line_number))) // on the different line
						{
							node.setValue(value + "," + line_number);
						}
					}
					else //different file
					{
						node.setValue(input_file + "," + line_number);
					}
					
				}
			}
		}
		
		/*
		
		BSTreeNode<E> node;
		for (Iterator<E> it = bst.inorderIterator(); it.hasNext();) {
			String word = (String) it.next();
			node = bst.search(word);
			String value = node.getValue();
		    System.out.println(word + ", " + value);
		}	
		
		/* Use HashMap
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
