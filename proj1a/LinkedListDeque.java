public class LinkedListDeque<Item> {

	private class StuffNode {
		public Item item;
		public StuffNode next;
		public StuffNode prev;

		public StuffNode(Item i, StuffNode n, StuffNode p) {
			item = i;
			next = n;
			prev = p;
		}
	}

	private int size;
	private StuffNode first;

	public LinkedListDeque() {
		first = null;
		size = 0;
	}

	public LinkedListDeque(Item x) {
		first = new StuffNode(x, null, null);
    	first.next = first;
    	first.prev = first;
		size = 1;
	} 
	
	public void addFirst(Item x) {
		size++;
		if (size == 1) {
			first = new StuffNode(x, null, null);
			first.next = first;
			first.prev = first;
		}
		first.prev = new StuffNode(x, first, first.prev);
    	first = first.prev;
    	first.prev.next = first;
	}

  //?????*******
	public void addLast(Item x) {
    	size++;
    	if (size == 1) {
			first = new StuffNode(x, null, null);
			first.next = first;
			first.prev = first;
		}
    	first.prev = new StuffNode(x, first, first.prev);
    	first.prev.prev.next = first.prev;
	}

	public boolean isEmpty() {
    	return size == 0;
	}

	public int size() {
		return size;
	}

  // Prints the items in the Deque from first to last, separated by a space.
	public void printDeque() {
    StuffNode p = first;
    for (int i = 0; i < size; i++) {
    	System.out.print(p.item + " ");
      p = p.next;
    }	
	}

//Removes and returns the item at the front of the Deque. If no such item exists, returns null.****
	public Item removeFirst() {
		if (size == 0) {
			return null;
		}
    	size--;
    	StuffNode p = first;
    	if (size == 0) {
      		first = null;
      		return p.item;
    	}
    
    	first.prev.next = first.next;
    	first.next.prev = first.prev;
		first = first.next;
    	return p.item;
	}

//Removes and returns the item at the back of the Deque. If no such item exists, returns null
//not sure if this is correct lol *****
	public Item removeLast() {
		if (size == 0) {
			return null;
		}
	  	size--;
		StuffNode p = first.prev;
    
    	if (size == 0) {
      	first = null;
      return p.item;
    	}
	
    	first.prev.prev.next = first;
    	first.prev = first.prev.prev;
    	return p.item;
    
	}
//Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. 
//If no such item exists, returns null. Must not alter the deque! ***** iterate through list for loop
	public Item get(int index) {
		if (index >= size) {
			return null;
		}

		StuffNode p = first;
		for (int i = 0; i < index; i++) {
			p = p.next;
		}
		return p.item;
	}

//Same as get, but uses recursion ****
	public Item getRecursive(int index) {
		if (index >= size) {
			return null;
		} 
    	if (index == 0) {
      		return first.item;
    	}
    	first = first.next;
    	Item item = getRecursive(index - 1);
    	first = first.prev;
    	return item;
		}
}