package Test;
import static org.junit.Assert.*;
import org.junit.Test;

import BPlusTreeNode.BPlusTreeNode;
import BPlusTreeNode.IntNode;

//This test case supporsts to test insertElement method and toString 
public class insertElementTest {

	@Test
	public void test() {
		BPlusTreeNode testNode = new BPlusTreeNode(4,0,null,null,null);
		IntNode[] elements = new IntNode[4];
		IntNode toBeInsert = new IntNode(5);
		System.out.println(toBeInsert.toString());
		testNode.insertElement(toBeInsert);
		System.out.println(testNode.nodeToString());
		
	}

}
