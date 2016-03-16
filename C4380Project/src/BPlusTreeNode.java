
//Using Order as the minimal number of data in a bucket
public class BPlusTreeNode {
	
	private BPlusTreeNode parents,next,prev;//nodes connections
	private IntNode[] elements ; //used for leaf - store <SearchKey,Address> pair data
	private int[] indexs; //used for store the index
	private BPlusTreeNode[] nextlevels; //used for connect to next level leaf
	// nodeNum is total number of elements or index inside of this TreeNode 
	// nodePosn is the current posn of this node in parents 
	private int order,nodeNum,nodePosn; 
	//root
	public BPlusTreeNode(int order){
		this.setOrder(order);
		this.setNodeNum(0);
		this.setNodePosn(0);
		this.setParents(null);
		this.setNext(null);
		this.setPrev(null);
		this.setElements(null);
		this.setNextlevels(new BPlusTreeNode[order*2+1]);
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
		this.nextlevels = new BPlusTreeNode[order*2+1];
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
	public void insert(int searchKey){
		
		
		//Base case
			//Element or index could be inserted successfully
		if(this.nodeNum<=(2*order-1)){
			
		}
		//Recursive Case
			//No space for insert, need to be split then recursively call parents insert
		else{
		
			int median = findMedian(this,searchKey);
		}
	}
	public int findMedian(BPlusTreeNode bp,int searchKey){
		//Check it is a leaf node or not
		if(bp.getIndexs() == null){
			
		}
		else{
			
		}
		
		
		return bp.getNodeNum()+searchKey;
	}
	//Used for search the location of searchKey OR used for search the apporixmate location of searchKey
	public int binarySearch(int[] arr,int searchKey){
		int res = 0;
		
		return res;
	}
	//Used for get inserted values's left& right nodes
	
	public void delete(int searchKey){
		
	}
	public BPlusTreeNode search(int searchKey){
		return null;
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
