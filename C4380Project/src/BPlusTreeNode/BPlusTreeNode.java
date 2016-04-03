package BPlusTreeNode;

//Using Order as the minimal number of data in a bucket
public class BPlusTreeNode {

	private BPlusTreeNode parents,next,prev;//nodes connections
	private IntNode[] elements ; //used for leaf - store <SearchKey,Address> pair data
	private int[] indexs; //used for store the index
	private BPlusTreeNode[] nextlevels; //used for connect to next level leaf
	// nodeNum is total number of elements or index inside of this TreeNode
	// nodePosn is the current posn of this node in parents
	private int order,nodeNum,nodePosn;
	//root - for init use
	public BPlusTreeNode(int order){
		this.setOrder(order);
		this.setNodeNum(0);
		this.setNodePosn(0);
		this.setParents(null);
		this.setNext(null);
		this.setPrev(null);
		this.setElements(new IntNode[2*order]);
		// this.setNextlevels(new BPlusTreeNode[order*2+1]);
		// this.setIndexs(new int[order*2]);
		this.setNextlevels(null);
		this.setIndexs(null);

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
	public BPlusTreeNode insert(IntNode obj){
		//The return that need to return
		BPlusTreeNode res = null;
		res = this.parents;
		//Base case
			//Element or index could be inserted successfully
		if(this.nodeNum<(2*order)){
			this.insertElement(obj);
			res = this;
		}
		//Recursive Case
			//No space for insert, need to be split then recursively call parents insert
		else{
			//In this case, all elements are full, we need to expand one node to two node
			//by making a new node with two child
			IntNode[] tempInt = mergeTwoIntNode(this.elements,obj);
			int meadianPosn = (2*order+1)/2;
			IntNode medianObj = tempInt[meadianPosn];

			//FIrst part - 0 => medianPosn -1
			IntNode[] first = new IntNode[2*order];
			//Second part - medianPosn => end
			IntNode[] second = new IntNode[2*order];

			int count = 0;
			int firstlength = 0, secondlength = 0;
			//Fill the list
			for(IntNode i : tempInt){
				if(count<meadianPosn){
					//Fisrt part
					first[count] = i;
					firstlength++;
				}
				else{
					second[count - meadianPosn] = i;
					secondlength++;
				}
				count++;
			}
			//Start make new node - Root
			BPlusTreeNode  temp = new BPlusTreeNode(this.order,this.nodePosn,this.parents);
			//Declare right child
			BPlusTreeNode tempRightLeaf = new BPlusTreeNode(this.order,1,this.parents,null,this);
			//make left child - modify current node
			this.setElements(first);
			this.setNext(tempRightLeaf);
			this.setParents(temp);
			this.setNodeNum(firstlength);
			//Make right child - updatetempRightLeaf;
			tempRightLeaf.setElements(second);
			tempRightLeaf.setNodeNum(secondlength);
			tempRightLeaf.setParents(temp);

			//Start setup root
			int[] tempIndexs = new int[order*2];
			//make median as first index
			tempIndexs[0] = tempInt[meadianPosn].getSearchKey();
			//merge tempIndexs into index
			temp.setIndexs(tempIndexs);
			//Set nextlevels
			BPlusTreeNode[] tempNextLevel = new BPlusTreeNode[2*order+1];
			//Assign left
			tempNextLevel[0] = this;
			//Assign Right
			tempNextLevel[1] = tempRightLeaf;
			temp.setNextlevels(tempNextLevel);
			temp.setNodeNum(1);
			//Check parents avaliable - if not, this ndoe is the new one
			if(res != null){
				res = this.parents.parents.insert(temp);
			}
			else{
				res = temp;
			}
//			return res;
		}
		return res;
	}
	//Insert the element(an IntNode) to current leaf(space avaliable)
	public void insertElement(IntNode obj){
		int posn = binarySearch(obj);
		//if elemetns is not empty ,need to move back
		if( this.elements[0]!=null){
			this.moveBack(posn,obj);
		}
		this.elements[posn] = obj;
		this.nodeNum++;
	}
	// Add obj to IntNode[] arr and return the new array
	public IntNode[] mergeTwoIntNode(IntNode[] arr, IntNode obj){
		IntNode[] temp = new IntNode[arr.length+1];
		int i = 0,posn = binarySearch(obj);
		while(i < temp.length){
			if(i == posn){
				temp[i] = obj;
				i++;
			}
			else if(i < posn){
				temp[i] = this.elements[i];
				i++;
			}
			else{
				temp[i] = this.elements[i-1];
				i++;

			}
		}
		arr = temp;
		return arr;
	}
	//Used for search the corrent or appoimate location for an IntNode object
	//return value should be the one just bigger than target
	//Which means return value should be moved back
	//if duplicate, return the next posn of duplication, eg {1,2,4,5} => return 3 if search 4
	public int binarySearch(IntNode obj){
		int res = 0;
		int toBeInserted = obj.getSearchKey();
		//Using loop instead of recursion to write a BSearch
		//If empty, insert directy
		if(this.elements[0] == null){
			// this.elements[0] = obj;
			res = 0;
		}
		//First iteration : use linear serch instead of BSearch
		else{
			// int start = 0;
			// int end = this.elements.length;
			// int mid = 0;
			// //Stop condition
			// //arr[mid] = obj => find the target
			// while(arr[mid]!= obj || start != end){
			//
			// }
			if(this.elements[0].compareTo(obj) == 1){
				res = 0;
			}
			else{
				for(int i = 0; i < this.elements.length; i++){
					//aescding order in this array
					if(this.elements[i]!=null &&
					(this.elements[i].compareTo(obj) == -1
					|| this.elements[i].compareTo(obj) == 0)){
						//loop all the smaller than obj elements,keep the last index of them
						//res as the one in front of obj
						res = i;
					}
				}
				res++;
			}
		}
		return res;
	}
	//Insertation methond for insert the non-leaf node
	//IF spece avaliable - use base case otherwise use recursice case
	public BPlusTreeNode insert(BPlusTreeNode obj){
		BPlusTreeNode res = null;
		//Basic case: if this node's indexs is not empty, insert directly by calling insertBPNode methond
		if(this.nodeNum < order*2){
			res = insertBPNode(obj);
		}
		//Recursive case: if this node's indexs is full,build a new subtree and insert for parents
		else{
			//This non-leaf node is full - split and make a new node and insert to parents
			
		}


		return res;

	}
	//Used for non-leaf node's base case insertation
	public BPlusTreeNode insertBPNode(BPlusTreeNode subtree){
		//put the subtree into current non-leaf node
		//subtree's indexs[0] should be in this indexs's subtree.posn - we need to call moveBack
		//subtree's left child stays where it is, add rightchild to posn+1's location
		int posn = subtree.getNodePosn();
		//Make spot avaliable for indexs and node
		this.moveBack(posn,this.indexs[0]);
		this.moveBack(posn+1,this.nextlevels[0]);
		this.nodeNum++;
		//assign the new node
		//For indexs
		this.indexs[posn] = subtree.getIndexs()[0];
		//For right child
		this.nextlevels[posn+1] = subtree.getNextlevels()[1];
		//correct posn
		this.nextlevels[posn+1].setNodePosn(posn+1);
		// Update right child's next
		if(this.nodeNum!= posn){
			//right isn't last element of nodes
			this.nextlevels[posn+1].setNext(this.nextlevels[posn+2]);
		}
		//Otheerwise is null
		//Left child stays where it was
		this.nextlevels[posn].setNodePosn(posn);
		return this;
	}
	//Make the empty pson avaliable
	// Object[] - input array which need to be moved

	public void moveBack(int posn,Object obj){
		//Used for IntNode(Elements) / int(indexs) / BPlusTreeNode(nextlevels);
		if(obj instanceof IntNode){
			//In the condition that need to move IntNode element - insertation basic case
			//add the object in to array

			for(int i = this.nodeNum ; i > posn ;i--){
				this.elements[i] = this.elements[i-1];
			}
		}
		else if(obj instanceof BPlusTreeNode){
			//Used for moveback basic non-leaf insert case
			//move nextlevels with an empty avaliable spot

			//starts from nodeNum+1 since nextlevels is 2*order+1
			for(int i = this.nodeNum+1;i>posn;i--){
				this.nextlevels[i] = this.nextlevels[i-1];
			}
		}
		else if(obj instanceof Integer){
			//Used for moveback basic non-leaf case
			//move indexs with an empty spot
			for(int i = this.nodeNum ; i > posn ;i--){
				this.indexs[i] = this.indexs[i-1];
			}
		}
		else{

		}
	}
	public String toString(){
		String res = "";
		if(this.elements != null){
			res += "This is a leaf node \n";
			for(int i = 0; i < this.nodeNum ; i++){
				res += this.elements[i].toString();
			}
		}
		else{
			res += "This is a non - leaf node\n";
			for ( int i=0; i<this.nodeNum;i++){
				res+= this.nextlevels[i].toString()+"\n";
				res += "Index :"+ i+"\n";
			}
			res += "The last node\n";
			res += this.nextlevels[nodeNum]+"\n";
		}
		return res;
	}
	public String nodeToString(){
		String rs = "";
		//Means it is leaf
		if(this.elements != null){
			//Loop and print out all the elements
			rs += "This is an leaf \n";
			for(int i = 0; i<this.elements.length && this.elements[i] != null;i++){
				rs+=this.elements[i].toString()+" ";
			}
		}else{
		//Means it is non - leaf node
		//Print out INT
			rs += "This is an non-leaf";
			for(int i : this.indexs){
				rs += i+"";
			}
		}
		return rs;
	}

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
