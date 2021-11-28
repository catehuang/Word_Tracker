package utilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import exceptions.TreeException;

public class BSTReferencedBased<E extends Comparable<? super E>> implements BSTreeADT<E> 
{
	private BSTreeNode<E> root;
	int counter = 0;

	public BSTReferencedBased() 
	{
		this.root = null;
	}

	public BSTReferencedBased(E node) 
	{
		this.root = new BSTreeNode(node);
	}

	@Override
	public BSTreeNode<E> getRoot() throws TreeException 
	{

		if (this.root == null)
		{
			throw new TreeException("The Binary Search Tree is empty");
		}
		else
		{
			return root;
		}
	}
	
	@Override
	public int getHeight() 
	{
		return calHeight(root);
	}
	
	public int calHeight(BSTreeNode<E> node) // number of level with nodes
	{
		if (node == null)
		{
			return 0; // ppt said the height is 0 for empty tree
		}

        int lefth = calHeight(node.getLeft());
        
        int righth = calHeight(node.getRight());

        if (lefth > righth )
        {
            return (lefth + 1);
        }    
        else
        {
            return (righth + 1);
        }
	}

	@Override
	public int size() 
	{
		return calSize(root);
	}
	
	public int calSize(BSTreeNode<E> node)
	{
		if (node == null)
		{
			return 0;
		}
		
		counter++;
		calSize(node.getRight());
		calSize(node.getLeft());

		return counter;
	}
	
	@Override
	public boolean isEmpty() 
	{
		if (this.root == null)
		{
			return true;
		}

		return false;
	}

	@Override
	public void clear() 
	{
		this.root = null;
	}

	@Override
	public boolean contains(E entry) throws TreeException 
	{		
		if (search(entry) == null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	@Override
	public BSTreeNode<E> search(E entry) throws TreeException 
	{
		if (isEmpty())
		{
			throw new TreeException("The Binary Search Tree is empty");
		}
		
		if (getRoot().getNode() == entry)
		{
			return this.root;
		}
		
		BSTreeNode<E> prev = null;
		BSTreeNode<E> tmp = root;
		
		while (tmp != null)
		{
			if (entry.compareTo(tmp.getNode()) == 0)
			{
				return tmp;
			}
			else if (entry.compareTo(tmp.getNode()) > 0)
			{
				prev = tmp;
				tmp = tmp.getRight();
			}
			else
			{
				prev = tmp;
				tmp = tmp.getLeft();
			}
		}
		
		if (entry.compareTo(prev.getNode()) == 0)
		{
			return prev;
		}
		else
		{
			return null;
		}
	}

	@Override
	public boolean add(E newEntry) throws NullPointerException {
		
		if (newEntry == null)
		{
			throw new NullPointerException();
		}
		
		BSTreeNode<E> newNode = new BSTreeNode<E>(newEntry);
		
		if (root == null) 
		{
			root = newNode;
			return true;
		}

		// traversing to find a place
		BSTreeNode<E> prev = null;
		BSTreeNode<E> tmp = root;
		
		while (tmp != null)
		{
			if (newNode.getNode().compareTo(root.getNode()) == 0)
			{
				return false; // BST doesn't allow duplicates
			} 
			else if (newNode.getNode().compareTo(tmp.getNode()) > 0) // node > root
			{
				prev = tmp;
				tmp = tmp.getRight();
			}
			else // node < root
			{
				prev = tmp;
				tmp = tmp.getLeft();
			}
		}
		
		if (newNode.getNode().compareTo(prev.getNode()) > 0)
		{
			prev.setRight(newNode);
			return true;
		}
		else if (newNode.getNode().compareTo(prev.getNode()) < 0)
		{
			prev.setLeft(newNode);
			return true;
		}
		else
		{
			return false; // BST doesn't allow duplicates
		}
	}

	@Override
	public Iterator<E> inorderIterator() {
		
		return new InorderIterator();
	}

	//inner class InorderIterator
	private class InorderIterator implements Iterator<E>{ 
		private ArrayList<E> list = new ArrayList<>(); //the list to store the element
		private int current = 0; //the current element/position in the list
		
		public InorderIterator() {
			inorder();
		}
		
		//inorder traversal from the root
		private void inorder() {
			inorder(root);
		}
		
		//inorder traversal from a subtree
		private void inorder(BSTreeNode<E> root) {
			if(root == null) return;
			inorder(root.getLeft()); //L
			list.add(root.getNode()); //V
			inorder(root.getRight()); //R
		}
		
		@Override
		public boolean hasNext() {
			if(current < list.size())
				return true;
			return false;
		}
		
		@Override
		public E next() {
			return list.get(current++); //get the current element and move to the next
		}

	}

	@Override
	public Iterator<E> preorderIterator() {
		return null;
	}

	@Override
	public Iterator<E> postorderIterator() {
		
		return null;
	}

}
