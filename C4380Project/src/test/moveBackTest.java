package test;

import static org.junit.Assert.*;

import org.junit.Test;

import BPlusTreeNode.BPlusTreeNode;
import BPlusTreeNode.IntNode;

public class moveBackTest {

	@Test
	public void test() {
		//A leaf Node
		BPlusTreeNode ts = new BPlusTreeNode(5,3,null,null,null);
		IntNode[] leaf = new IntNode[2*5];
		ts.setNodeNum(3);
		for(int i = 0 ; i <3 ; i++){
			leaf[i] = new IntNode(i);
		}

		ts.setElements(leaf);
		for(int i = 0; i<ts.getElements().length && ts.getElements()[i] != null;i++){
			System.out.println(ts.getElements()[i].getSearchKey()+"--");
		}
		ts.moveBack(1, leaf[0]);
		for(int i = 0; i<ts.getElements().length && ts.getElements()[i] != null;i++){
			System.out.println(ts.getElements()[i].getSearchKey()+"++");
		}
	}

}
