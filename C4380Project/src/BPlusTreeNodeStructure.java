//This code is for structure purpose only.Details and Errors would be ignored
public class BPlusTreeNodeStructure {

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
	//Insert for leaf node only

	//Check the leaf node space
		///If not full, insert directly
		//If full , split into a subnode that use a median as root while split the left into two
		//leaf node, meanwhile change the nodePosn of each new leaf node
	public BPlueTreeNode insert(IntNode obj){
		//Pointer that need to be returned
		BPlueTreeNode res = null;
		//base case - check the spacing - if space is enough - insert directly
		if(this.nodeNum<this.order*2){
			//less than 2*order => at least 1 in posn extra in elements
			this.insertElement(IntNode obj);
			return this;
		}
		//Recursive Case - the space is not enouth, split the node and upload to parents
		else{
			//Make a new IntNode array that contains all elements inside.
				//Move all elements into this array
			IntNode[] tempInt = mergeTwoIntNodeArr(this.elements,obj);
			//Need to be splited in parents node.
			int medianPosn = (2*order+1 )/2;
			IntNode medianObj = tempInt[medianPosn];
				//Fist part - start => medianPosn-1
				IntNode[] first = new IntNode[2*order];
				//Second part - medianPosn => end
				IntNode[] second = new IntNode[2*order];
				// Fill the Lists
				int count = 0;
				for(IntNode i:tempInt){
					if(count <medianPosn){
						first[count] = tempInt[count];
					}
					else{
						second[count-median] = tempInt[counts];
					}
					count++;
				}
				//Make up a New SubNode
				//Root of subnode
				BPlueTreeNode temp = new BPlueTreeNode(this.order,this.nodePosn,this.parents);
				//right child of sub node	- leaf node
				BPlueTreeNode tempRightLeaf = null;
				//left child of sub node - leaf node -- should keep the original one
					//modify the original node so that we do not need to connect with prev node
					this.setElements(first);
					this.setNext(tempRightLeaf);
					this.setParents(temp);
				// BPlueTreeNode tempLeftLeaf = new BPlueTreeNode(this.order,0,temp,tempRightLeaf,null);
				//Continus right child
					//make right right node content
				tempRightLeaf = BPlueTreeNode(this.order,1,temp,null,this);
					//Fill right elements in
				tempRightLeaf.setElements(second);
				//Root
					//Make a new Index for the sub node root
				Int[] tempIndexs = new Int[order*2];
					// assign the searchKey of median to the subnode root
				tempIndexs[0] = medianObj.getsearchKey;
					//Set medians(int array - new index) to subnode's Root
				temp.setIndexs = tempIndexs;
					//Set it's left and right ponters
						// Make a new nextlevel array
				BPlueTreeNode tempNextLevel = new BPlueTreeNode[order*2+1];
						//Assign left
				tempNextLevel[0] = this;
						//Assign right
				tempNextLevel[1] = tempRightLeaf;
			//FInally, Call parents for insert the new node
			//Check parents is null - if only one node in whole structure
			if(this.parents != null){
				res = this.parents.insert(temp);
			}
			else{//if parents is null, this node will be the new node
				res = this;
			}
			return res;
		}
	}
	//Insert the element to the leaf node only
	public void insertElement(IntNode obj){
		//first find the posn that need to be insert
		int posn = BinarySearch(IntNode obj);
		this.elements[posn] = obj;
		this.nodeNum++;
	}
	//Merge A IntNode Arr with a IntNode Obj - Return a new IntNode arr with length+1
	public IntNode[] mergeTwoIntNodeArr(IntNode[] arr1,IntNode obj){

	}
	//Search the correct or appoimate location for an IntNode object
	public int BinarySearch(IntNode obi){
		return 0;
	}
	//insert for non-leaf node
	public BPlueTreeNode insert(BPlusTreeNode obj){
		BPlueTreeNode res = null;
		//base case - check the spacing - if space is enouth
		if(this.indexs<2*order){
			res = insertBPNode(obj);
		}
		//Recursive Case - split & make a new BPlueTreeNode and upload
		else{

		}
	}
	//Used for build the basic insertion of non-leaf node
		//Find the correct spot,add the elements
		//NEED to fix the child's posn
	public BPlueTreeNode insertBPNode(BPlueTreeNode subtree){
		//move afterwards indexs and pointers - make 1 spot for target
		//The POSN should be obj's POSN
		int POSN = obj.nodePosn;
		this.indexs = moveBack(this.indexs,POSN);
		this.nextlevel = moveBack(this.nextlevel,POSN);
		//Put the target right on spot
		this.indexs[posn] = subtree.indexs[0];
		//put left child into target 's left - Even it's original one,assigned again
		this.nextlevel[posn] = subtree.nextlevel[0];
		//put right child into target's right
		this.nextlevel[posn+1] = subtree.nextlevel[1];
		this.nextlevel[posn+1].nodePosn = posn+1;
		return this;
	}
	//make the empty spon avaliablity
	public int[] moveBack(int[] temp, int posn){
		int[] arr = new int[2*this.order];
		int count = 0;
		for(int i : temp){
			if(count==posn){
				count++;
			}
			arr[count] = i;
			count++;
		}
		return arr;
	}
	public BPlueTreeNode[] moveBack(BPlueTreeNode[] temp,int posn){
		BPlueTreeNode[] arr = new BPlueTreeNode[2*this.order+1];
		int count = 0;
		for(BPlueTreeNode i : temp){
			if(count == posn){
				count++;
			}
			arr[count] = i;
			count++;
		}
		return arr;
	}
	//Search the correct or apporimate location for an number of an BPlueTreeNode
	public int BinarySearch(BPlusTreeNode obi){

	}
}
