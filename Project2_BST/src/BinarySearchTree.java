import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {
	private static class Node<AnyType> {
		private AnyType data;
		private Node<AnyType> parent;
		private Node<AnyType> left;
		private Node<AnyType> right;

		public Node(AnyType d, Node<AnyType> p, Node<AnyType> l, Node<AnyType> r) {
			setData(d);
			setParent(p);
			setLeft(l);
			setRight(r);
		}

		public AnyType getData() {
			return data;
		}

		public Node<AnyType> getParent() {
			return parent;
		}

		public Node<AnyType> getLeft() {
			return left;
		}

		public Node<AnyType> getRight() {
			return right;
		}

		public void setData(AnyType d) {
			data = d;
		}

		public void setParent(Node<AnyType> p) {
			parent = p;
		}

		public void setLeft(Node<AnyType> l) {
			left = l;
		}

		public void setRight(Node<AnyType> r) {
			right = r;
		}
	}

	private Node<AnyType> root;

	public BinarySearchTree() {
		makeEmpty();
	}

	public void makeEmpty() {
		root = null;
	}

	public boolean isEmpty() {
		return (root == null);
	}

	public boolean contains(AnyType v) {
		return contains(v, root);
	}

	private boolean contains(AnyType v, Node<AnyType> t) {
		if (t == null)
			return false;

		int compareResult = v.compareTo(t.getData());

		if (compareResult < 0)
			return contains(v, t.getLeft());
		else if (compareResult > 0)
			return contains(v, t.getRight());
		else
			return true;
	}

	public AnyType findMin() throws IllegalStateException {
		if (isEmpty())
			throw new IllegalStateException("Search Tree is empty.");

		Node<AnyType> minNode = findMin(root);
		return minNode.getData();
	}

	private Node<AnyType> findMin(Node<AnyType> t) {
		if (t == null)
			return null;
		else if (t.getLeft() == null)
			return t;

		return findMin(t.getLeft());
	}

	public AnyType findMax() throws IllegalStateException {
		if (isEmpty())
			throw new IllegalStateException("Search Tree is empty.");

		Node<AnyType> maxNode = findMax(root);
		return maxNode.getData();
	}

	private Node<AnyType> findMax(Node<AnyType> t) {
		if (t == null)
			return null;
		else if (t.getRight() == null)
			return t;

		return findMax(t.getRight());
	}

	public void insert(AnyType value) {
		int compareResult = 0;//create integer for comparison
		Node<AnyType> parent = root;//create parent Node
		if (root == null) {//no root
			root = new Node<AnyType>(value, null, null, null);//insert this value as new root
			return;
		}
		while (true) {
			compareResult = value.compareTo(parent.getData());//compare the insert value to the parent value
			if (compareResult < 0) {//if insert value < parent value
				if (parent.getLeft() == null) {//if parent has no left child
					Node<AnyType> newNode = new Node<AnyType>(value, parent, null, null);//create a node with insert value					
					parent.setLeft(newNode);// insert the node to parent's left
					break;
				}
				parent = parent.getLeft();//parent has left child, continue the loop
			} else if (compareResult > 0) {//if insert value > parent value
				if (parent.getRight() == null) {//if parent has no right child
					Node<AnyType> newNode = new Node<AnyType>(value, parent, null, null);//create a node with insert value
					parent.setRight(newNode);// insert the node to parent's right
					break;
				}
				parent = parent.getRight();//parent has right child, continue the loop

			} else {
				;// equal current node, do nothing
				break;
			}
		}
	}

	public void remove(AnyType value) {
		AnyType removeValue = value;//the value to be removed
		Node<AnyType> currentNode = root;//create currentNode point to root
		int compareResult = 0;//integer to get compare result
		while (currentNode != null) {
			compareResult = removeValue.compareTo(currentNode.getData());//compare the value to be removed to currentNode value
			if (compareResult < 0) {//if remove value < currentNode value
				currentNode = currentNode.getLeft();//assign currentNode to its left,continue to find the node to remove
			} else if (compareResult > 0) {//if remove value > currentNode value
				currentNode = currentNode.getRight();//assign currentNode to its right, continue to find the node to remove
			} else {
				if (currentNode.getLeft() != null && currentNode.getRight() != null) {//remove value equal currentNode value, root has left child and right child
					Node<AnyType> minNodeInRight = findMin(currentNode.getRight());//find the right child with minimum value 
					AnyType minNodeValue = minNodeInRight.getData();//get the value of that node
					currentNode.setData(minNodeValue);//change the value of root to the value got
					currentNode = currentNode.getRight();//set currentNode to the right of root
					removeValue = minNodeValue;//assign remove value to the minimum value got before, and continue to remove that node
				} else {//root only has left child or right child
					Node<AnyType> parent = currentNode.getParent();//create node of currentNode's parent
					Node<AnyType> child = (currentNode.getLeft() != null) ? currentNode.getLeft()//create node of either left child or right child
							: currentNode.getRight();
					if (child != null)//if currentNode has child
						child.setParent(parent);//set currentNode's child to its parent's child
					if (parent == null) {//the child's parent(currentNode) has no parent
						root = child;//set that child to root
					} else {
						if (currentNode.getData().compareTo(parent.getData()) < 0) {//if currentNode is the left child of its parent 
							parent.setLeft(child);//set currentNode's child to currentNode's parent's left child
						} else {
							parent.setRight(child);//set currentNode's child as currentNode's parent's right child
						}
					}
					currentNode = null;
					break;
				}
			}
		}

	}

	public void printTree() {
		int height = getTreeHeight(root);//get the Height of the tree

		printNodes(Collections.singletonList(root), 1, height);//print tree

	}

	private void printNodes(List<Node<AnyType>> nodes, int currentLevel, int height) {
		boolean hasData = false;
		//go through the nodes ,if there's child ,continue the process
		for (Iterator<Node<AnyType>> iterator = nodes.iterator(); iterator.hasNext();) {
			Node<AnyType> node = iterator.next();
			if (node != null) {
				hasData = true;
				break;
			}
		}
		if (!hasData) {
			return; // done
		}

		// calculate spaces using formula
		int floor = height - currentLevel;
		int leadSpaces = 5 * ((int) Math.pow(2, (floor)) - 1);//spaces at front
		int betweenSpaces = 5 * ((int) Math.pow(2, (floor + 1)) + -1);//spaces between two nodes
        //print the front spaces
		printSpaces(leadSpaces);
        //create array list to get the next level nodes, if the node has child, get the child value, if not, set child to null
		List<Node<AnyType>> nextLevelNodes = new ArrayList<Node<AnyType>>();
		for (Node<AnyType> node : nodes) {
			printNodeData(node);//print according to the format
			if (node != null) {//has child
				nextLevelNodes.add(node.getLeft());
				nextLevelNodes.add(node.getRight());
			} else {//has no child
				nextLevelNodes.add(null);
				nextLevelNodes.add(null);
			}
        //print the spaces between two nodes
			printSpaces(betweenSpaces);
		}
		System.out.println("");
		//print next level nodes
		printNodes(nextLevelNodes, currentLevel + 1, height);

	}

	private int getTreeHeight(Node<AnyType> node) {//get height of tree
		if (node == null)//no node, height is 0
			return 0;

		return Math.max(getTreeHeight(node.getLeft()), getTreeHeight(node.getRight())) + 1;

	}

	private void printSpaces(int count) {//print space
		for (int i = 0; i < count; i++) {
			System.out.print(" ");
		}
	}

	private void printNodeData(Node<AnyType> node) {//format for printing
		if (node == null) {
			System.out.print("     ");
		} else {
			String data = node.getData().toString();
			int len = data.length();
			switch (len) {
			case 1://1 digit 
				System.out.print("  " + data + "  ");
				break;
			case 2://1 signed digit or 2 digit
				System.out.print(" " + data + "  ");
				break;
			case 3://2 signed digit or 3 digit
				System.out.print(" " + data + " ");
				break;
			case 4://3 signed digit or 4 digit
				System.out.print(data + " ");
				break;

			default:
				System.out.print(data);
				break;
			}
		}
	}

	public Iterator<AnyType> iterator() {
		LinkedList<AnyType> snapshot = new LinkedList<>();

		inOrderTraversal(root, snapshot);
		return snapshot.iterator();
	}

	private void inOrderTraversal(Node<AnyType> t, LinkedList<AnyType> l) {
		if (t != null) {
			inOrderTraversal(t.getLeft(), l);
			l.add(t.getData());
			inOrderTraversal(t.getRight(), l);
		}
	}
}
