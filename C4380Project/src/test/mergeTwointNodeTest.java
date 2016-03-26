package test;

import static org.junit.Assert.*;
import BPlusTreeNode.BPlusTreeNode;
import BPlusTreeNode.IntNode;

import org.junit.Test;

public class mergeTwointNodeTest {

	@Test
	public void test() {
		//Setup BPlusTreeNode
		BPlusTreeNode ts = new BPlusTreeNode(5,3,null,null,null);
		IntNode[] leaf = new IntNode[2*5];
		ts.setNodeNum(5);
		for(int i = 0 ; i <10 ; i++){
			leaf[i] = new IntNode(i);
		}
		ts.setElements(leaf);
		for(int i = 0; i<ts.getElements().length && ts.getElements()[i] != null;i++){
			System.out.println(ts.getElements()[i].getSearchKey()+"--");
		}
		//Done Setup
		IntNode obj = new IntNode(3);
		ts.setElements(ts.mergeTwoIntNode(ts.getElements(), obj));
		for(int i = 0; i<ts.getElements().length && ts.getElements()[i] != null;i++){
			System.out.println(ts.getElements()[i].getSearchKey()+"++");
		}	
		
	}

}
