package test;

import static org.junit.Assert.*;
import BPlusTreeNode.BPlusTreeNode;
import BPlusTreeNode.IntNode;
import org.junit.Test;

public class insertRecursionTest {

	@Test
	public void test() {
		int order = 5;
		//Setup a subtree with filled leaf node
		// Build root - non leaf
		BPlusTreeNode temp = new BPlusTreeNode(order,0,null);


		//FIll All - leafs are not connected each in this case
		int i = 0;
		while(i<(2*order+1)){

			//indexs
			if(i<2*order){
				temp.getIndexs()[i]=i;
			}
			//nextlevels
			BPlusTreeNode next = new BPlusTreeNode(order,i,temp,null,null);
			next.setNodeNum(2*order);
			next.setNodePosn(i);
			int j =0;
			while(j<2*order){
				next.getElements()[j] =	new IntNode(i);
				j++;
			}
			temp.getNextlevels()[i] = next;
			i++;
		}
		temp.setNodeNum(2*order);
		temp.setNodePosn(0);
		
		
		//Start insert
		IntNode ne = new IntNode(0);
		BPlusTreeNode test = temp.getNextlevels()[0];
		test.insert(ne);
		System.out.println(test.toString());

	}

}
