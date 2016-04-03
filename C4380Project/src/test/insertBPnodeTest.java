package test;

import static org.junit.Assert.*;

import org.junit.Test;

import BPlusTreeNode.BPlusTreeNode;
import BPlusTreeNode.IntNode;

//This Unit test is test for non - leaf insertion's Basic Case
public class insertBPnodeTest {

	@Test
	//Test nnon - leaf insertion basic case
	public void test() {
		int order = 5;
		//Setup a subtree with filled leaf node
		// Build root - non leaf
		BPlusTreeNode temp = new BPlusTreeNode(order,0,null);
		BPlusTreeNode left = null;
		// Right Child
		BPlusTreeNode right = new BPlusTreeNode(order,0,temp,null,left);
		//elements
		IntNode[] nodes = new IntNode[2*order];
		IntNode[] nodesleft = new IntNode[2*order];
		for(int i = 0;i<2*order;i++){
			nodes[i] = new IntNode(3);
		}
		right.setElements(nodes); // All 1
		right.setNodeNum(2*order);
		// Left Child
		left = new BPlusTreeNode(order,0,temp,right,null);
		for(int i = 0;i<2*order;i++){
			nodesleft[i] = new IntNode(1);	//All 2
		}
		left.setElements(nodesleft);
		left.setNodeNum(2*order);
		
		right.setPrev(left);
		BPlusTreeNode[] levels = new BPlusTreeNode[2*order+1];
		levels[0] = left;
		levels[1] = right;
		temp.setNextlevels(levels);
		int[] tempInt = new int[2*order];
		tempInt[0] = 2;

		temp.setNodeNum(1);
		temp.setNodePosn(0);
		temp.setIndexs(tempInt);


		//Start insert
		left.insert(new IntNode(1));
		System.out.println(temp.toString());

	}

}
