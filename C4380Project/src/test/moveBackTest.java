package test;

import static org.junit.Assert.*;

import org.junit.Test;

import BPlusTreeNode.BPlusTreeNode;
import BPlusTreeNode.IntNode;

public class moveBackTest {

	@Test
	public void test() {
		int order = 5;
		//A leaf Node
		BPlusTreeNode ts = new BPlusTreeNode(order,3,null,null,null);
		IntNode[] leaf = new IntNode[2*order];
		ts.setNodeNum(3);
		for(int i = 0 ; i <3 ; i++){
			leaf[i] = new IntNode(i);
		}
		//Test IntNode 
		ts.setElements(leaf);
		for(int i = 0; i<ts.getElements().length && ts.getElements()[i] != null;i++){
			System.out.println(ts.getElements()[i].getSearchKey()+"--");
		}
		ts.moveBack(1, leaf[0]);
		for(int i = 0; i<ts.getElements().length && ts.getElements()[i] != null;i++){
			System.out.println(ts.getElements()[i].getSearchKey()+"++");
		}
		
		//Test BPlusTreeNode

		//make the nextlevels avaliable
		BPlusTreeNode[] nextlevel = new BPlusTreeNode[2*order+1];
		int[] index = new int[2*order];
		//make another leaf node - doesn't mater what inside
		//keep at least 1 spot 
		int k = 0;
		for(int i = 0;i < 6 ; i++){
			index[i] = i;
			nextlevel[i] = new BPlusTreeNode(order,i,null,null,null);
			k++;
		}
		ts.setNodeNum(k);
		ts.setNextlevels(nextlevel);
		//test move back - obj type matters but content does not matter 
		ts.moveBack(2, new BPlusTreeNode(order));
		
		//Test int 
		ts.setIndexs(index);
		ts.moveBack(2, 2);
		System.out.println();
	}

}
