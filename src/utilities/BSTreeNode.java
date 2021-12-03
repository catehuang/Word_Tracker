package utilities;

import java.io.Serializable;

public class BSTreeNode <E> implements Serializable
{
	private static final long serialVersionUID = -1852863289091407289L;

	private BSTreeNode<E> left;
	private BSTreeNode<E> right;
	private E node;
	private String value;

	/**
	 * Create node
	 */
	public BSTreeNode() 
	{
		this.node = null;
		this.left = null;
		this.right = null;
		this.value = "";
	}
	
	/**
	 * Get the stored essential information - filenames and line numbers
	 * @return value the value in string format
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Set a value in string format storing filenames and line numbers
	 * @param value the value for a node
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Create a node with specified key
	 * @param node the key or word
	 */
	public BSTreeNode(E node) 
	{
		this.node = node;
		this.left = null;
		this.right = null;
		this.value = "";
	}
	
	/**
	 * Create a node with specified key node, left and right nodes
	 * @param node the specified key or word
	 * @param left specified left subnode
	 * @param right specified right subnode
	 */
	public BSTreeNode(E node, BSTreeNode<E> left, BSTreeNode<E> right) {
		this.left = left;
		this.right = right;
		this.node = node;
	}

	/**
	 * Get the key of node
	 * @return the node
	 */
	public E getNode() {
		return node;
	}

	/**
	 * Set the key of a node
	 * @param node sppecified node
	 */
	public void setNode(E node) {
		this.node = node;
	}

	/**
	 * Get the left subnode
	 * @return the bstree node
	 */
	public BSTreeNode<E> getLeft() {
		return left;
	}
	
	/**
	 * Set the left subnode
	 * @param left the left bstree node
	 */
	public void setLeft(BSTreeNode<E> left) {
		this.left = left;
	}

	/**
	 * Get the right subnode
	 * @return the bstree node
	 */
	public BSTreeNode<E> getRight() {
		return right;
	}

	/**
	 * Set the right subnode
	 * @param right the right bstree node
	 */
	public void setRight(BSTreeNode<E> right) {
		this.right = right;
	}

	
}
