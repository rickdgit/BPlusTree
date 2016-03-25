package test;
import static org.junit.Assert.*;
import org.junit.Test;

import BPlusTreeNode.BPlusTreeNode;
import BPlusTreeNode.IntNode;

//This test case supporsts to test insertElement method and toString 
public class insertElementTest {

	@Test
	public void test() {

		BPlusTreeNode testNode = new BPlusTreeNode(4,0,null,null,null);
		IntNode toBeInsert = new IntNode(5);
//		System.out.println(toBeInsert.toString());
		
		
		//insert empty list 
		testNode.insertElement(toBeInsert);
		System.out.println(testNode.nodeToString());
		
		
		//insert to non-empty list
		testNode.insertElement(new IntNode(2));
		System.out.println(testNode.nodeToString());
		
		//insert to non-empty list
		testNode.insertElement(new IntNode(9));
		System.out.println(testNode.nodeToString());
		
		
		//insert to non-empty list
		testNode.insertElement(new IntNode(1));
		System.out.println(testNode.nodeToString());
		
		
		//insert to non-empty list
		testNode.insertElement(new IntNode(1));
		System.out.println(testNode.nodeToString());
		//insert to non-empty list
		testNode.insertElement(new IntNode(1));
		System.out.println(testNode.nodeToString());
		//insert to non-empty list
		testNode.insertElement(new IntNode(1));
		System.out.println(testNode.nodeToString());
		
		System.out.println(testNode.getNodeNum());
		
		//insert to non-empty list
		testNode.insertElement(new IntNode(9));
		System.out.println(testNode.nodeToString());
	}

}
