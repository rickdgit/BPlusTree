package test;
import BPlusTreeNode.BPlusTreeNode;
import BPlusTreeNode.IntNode;
import static org.junit.Assert.*;

import org.junit.Test;

public class BPlusTreeInsertTest {

	@Test
	/*This Code used for test B Plus Tree insertion Process
	 * There are four test case specifically:
	 * basic case - insert from an empty leaf Node
	 * basic case - insert node to a full leaf Node - recursively and generate its parents
	 * Recursive Case - insert node to non - leaf node
	 * Recursive Case - insert node to a full leaf node and re-insert to a full non- leaf node
	 */
	public void test() {
		//Starts frin Basic Case - insert from a empty leaf
		int order = 5;

		BPlusTreeNode root = new BPlusTreeNode(order);
		IntNode node = new IntNode(5);

		int i = 0;
		while(i<2*order){
			System.out.println("Basic - left "+i);
			root = root.insert(new IntNode(i));
			i++;
		}

		System.out.println(root.toString());

		//Basic Case - Recursive
		root = root.insert(new IntNode(10));
		System.out.println("Basic Rec\n "+root.toString());
		i = 0;
		while(i<2*order){
			root = root.overAllInsert(new IntNode(i+10));
			i++;
		}
		//Recursive - insert - basic case
		root.insert(new IntNode(10));
		i = 0;
		while(i<2*order){
			root = root.overAllInsert(new IntNode(i+20));
			i++;
		}
		System.out.println("Rec Basic "+root.toString());
		//Revursive - non - leaf recur

	}

}
