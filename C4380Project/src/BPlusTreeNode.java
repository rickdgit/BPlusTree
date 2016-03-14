
public class BPlusTreeNode {
	
	private BPlusTreeNode parents,next,prev;
	private IntNode[] elements ; //used for leaf - store <SearchKey,Address> pair data
	private int[] indexs;
	private BPlusTreeNode[] nextlevels;
	private int order,nodeNum,nodePosn;// nodeNum is total number of elements or index inside of this TreeNode  nodePosn is the current posn of this node in parents 
	//root
	public BPlusTreeNode(int order){
		this.setOrder(order);
		this.setNodeNum(0);
		this.setNodePosn(0);
		this.setParents(null);
		this.setNext(null);
		this.setPrev(null);
		this.setElements(null);
		this.setNextlevels(null);
		this.setIndexs(new int[order*2]);
		
	}
	//middle node
	public BPlusTreeNode(int order,int nodePosn,BPlusTreeNode parents){
		this.order = order;
		this.nodePosn = nodePosn;
		this.parents = parents;
		this.nodeNum = 0;
		this.next = null;
		this.prev = null;
		this.nextlevels = new BPlusTreeNode[order*2];
		this.elements = null;
		this.setIndexs(new int[order*2]);
	}
	//leaf
	public BPlusTreeNode(int order,int nodePosn,BPlusTreeNode parents,BPlusTreeNode next,BPlusTreeNode prev){
		this.setIndexs(null);
		this.nextlevels = null;
		this.elements = new IntNode[order*2];
		this.next = next;
		this.prev = prev;
		this.parents = parents;
		this.order = order;
		this.nodeNum = 0;
		this.nodePosn = nodePosn;
		
		
	}
	public BPlusTreeNode getPrev() {
		return prev;
	}
	public void setPrev(BPlusTreeNode prev) {
		this.prev = prev;
	}
	public BPlusTreeNode getNext() {
		return next;
	}
	public void setNext(BPlusTreeNode next) {
		this.next = next;
	}
	public BPlusTreeNode getParents() {
		return parents;
	}
	public void setParents(BPlusTreeNode parents) {
		this.parents = parents;
	}
	public IntNode[] getElements() {
		return elements;
	}
	public void setElements(IntNode[] elements) {
		this.elements = elements;
	}
	public BPlusTreeNode[] getNextlevels() {
		return nextlevels;
	}
	public void setNextlevels(BPlusTreeNode[] nextlevels) {
		this.nextlevels = nextlevels;
	}
	public int getNodePosn() {
		return nodePosn;
	}
	public void setNodePosn(int nodePosn) {
		this.nodePosn = nodePosn;
	}
	public int getNodeNum() {
		return nodeNum;
	}
	public void setNodeNum(int nodeNum) {
		this.nodeNum = nodeNum;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public int[] getIndexs() {
		return indexs;
	}
	public void setIndexs(int[] indexs) {
		this.indexs = indexs;
	}

}
