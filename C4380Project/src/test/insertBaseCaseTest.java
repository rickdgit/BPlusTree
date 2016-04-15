package test;

import static org.junit.Assert.*;

import org.junit.Test;

import BPlusTreeNode.BPlusTreeNode;
import BPlusTreeNode.IntNode;

public class insertBaseCaseTest {

	@Test
	public void test() {

		//Setup BPlusTreeNode
		BPlusTreeNode ts = new BPlusTreeNode(5,3,null,null,null);
		for(int i = 0; i<ts.getElements().length && ts.getElements()[i] != null;i++){
			System.out.println(ts.getElements()[i].getSearchKey()+"--");
		}
		//Done Setup
		
	//Basic Case Test
		//insert to an empty node
		IntNode obj = new IntNode(5);
		ts.insert(obj);

		//insert front 
		obj = new IntNode(0);
		ts.insert(obj);
		
		//insert duplicate 
		obj = new IntNode(5);
		ts.insert(obj);

		//insert till full
		obj = new IntNode(5);
		ts.insert(obj);
		ts.insert(obj);
		ts.insert(obj);
		ts.insert(obj);
		ts.insert(obj);
		ts.insert(obj);
		ts.insert(obj);
		
//		System.out.println(ts.toString());
		
//		for(int i = 0; i<ts.getElements().length && ts.getElements()[i] != null;i++){
//			System.out.println(ts.getElements()[i].getSearchKey()+"--");
//		}
	//Basic Case Done
	//Recursive Case 
		BPlusTreeNode head = null;
		head = ts.insert(obj);
		String res = head.toString();
		System.out.println(res);

	}

}
