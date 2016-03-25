package test;

import static org.junit.Assert.*;

import org.junit.Test;

import BPlusTreeNode.BPlusTreeNode;
import BPlusTreeNode.IntNode;
public class binarySearchTest {

	@Test
	public void test() {
		//Setup BPlusTreeNode
		BPlusTreeNode ts = new BPlusTreeNode(5,3,null,null,null);
		IntNode[] leaf = new IntNode[2*5];
		ts.setNodeNum(5);
		for(int i = 2 ; i <7 ; i++){
			leaf[i-2] = new IntNode(i);
		}
//		leaf[0] = new IntNode(1);
		ts.setElements(leaf);
		for(int i = 0; i<ts.getElements().length && ts.getElements()[i] != null;i++){
			System.out.println(ts.getElements()[i].getSearchKey()+"--");
		}
		//Done Setup
		
		//Search 1, if duplicate, return the posn of 0
		System.out.println(ts.binarySearch(new IntNode(1))+"++"+ts.getElements()[ts.binarySearch(new IntNode(1))]);
		//Search 4, if duplicate, return the posn of 3
		System.out.println(ts.binarySearch(new IntNode(4))+"++"+ts.getElements()[ts.binarySearch(new IntNode(4))]);
		//Search 7, if duplicate, return the posn of 5 -> the last one + 1
		System.out.println(ts.binarySearch(new IntNode(7))+"++"+ts.getElements()[ts.binarySearch(new IntNode(7))]);
	}

}
