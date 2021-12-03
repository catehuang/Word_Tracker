package utilities;

import java.io.Serializable;

public class BSTreeNode <E> implements Serializable
{
	private static final long serialVersionUID = -1852863289091407289L;

	private BSTreeNode<E> left;
	private BSTreeNode<E> right;
	private E node;
	private String value;

	public BSTreeNode() 
	{
		this.node = null;
		this.left = null;
		this.right = null;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public BSTreeNode(E node) 
	{
		this.node = node;
		this.left = null;
		this.right = null;
	}
	
	public BSTreeNode(E node, BSTreeNode<E> left, BSTreeNode<E> right) {
		this.left = left;
		this.right = right;
		this.node = node;
	}

	public E getNode() {
		return node;
	}

	public void setNode(E node) {
		this.node = node;
	}

	public BSTreeNode<E> getLeft() {
		return left;
	}

	public void setLeft(BSTreeNode<E> left) {
		this.left = left;
	}

	public BSTreeNode<E> getRight() {
		return right;
	}

	public void setRight(BSTreeNode<E> right) {
		this.right = right;
	}

	
}
