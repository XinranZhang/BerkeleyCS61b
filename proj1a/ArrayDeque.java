/* add and remove must take constant time, except during resizing operations.
get and size must take constant time.
The starting size of your array should be 8.
The amount of memory that your program uses at any given time must be proportional to 
the number of items. For example, if you add 10,000 items to the Deque, and then remove 9,999 items, 
you shouldn't still be using an array of length 10,000ish. For arrays of length 16 or more, 
your usage factor should always be at least 25%. For smaller arrays, 
your usage factor can be arbitrarily low.**/

public class ArrayDeque<Item> {

	private Item[] items;
	private int size;

	public ArrayDeque() {
		items = (Item[]) new Object[8];
		size = 0;
	}
  
  /*
  
  first = 0
  size = 3
  1 2 3 x x x x x
  
  4 1 2 3 x x x x
  
  first = 6
  1 2 3 6 x x 5 4
  
  addFirst(4)
  addFirst(5)
  addLast(6)
  
  */
  
  
  //compute the index immediately "before" a given index
  //private int minusOne(int index) {
  	
  //}

	//resizes the underlying array to the target capacity
	private void resize(int capacity){
		Item[] a = (Item[]) new Object[capacity];
		System.arraycopy(items, 0, a, 0, size);
		items = a;
	}

	//not sure if this is correct ****
	public void addFirst(Item x) {
		if (size == items.length) {
			resize(size * 2);
		}
    	for (int i = size; i > 0; i--) {
      		items[i] = items[i-1];
    	}	
		items[0] = x;
		size++;
	}

	public void addLast(Item x) {
		if (size == items.length) {
			resize(size * 2);
		}
		items[size++] = x;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}


	//Prints the items in the Deque from first to last, separated by a space.
	public void printDeque() {
		for (int i = 0; i < size; i ++) {
			System.out.print(items[i] + " ");
		}
	}

	public Item getFirst() {
    	if (size == 0) {
      		return null;
    	}
		return items[0];
	}

	//Removes and returns the item at the front of the Deque. If no such item exists, returns null. ****
	public Item removeFirst() {
		if (size == 0) {
			return null;
		}
		Item x = getFirst();

    for (i = 0; i < size; i++) {
    	items[i] = items[i+1];
    }
    
		size--;
		return x;
	}

	public Item getLast() {
    	if (size == 0) {
      		return null;
    }
		return items[size - 1];
	}

	public Item removeLast() {
    	if (size == 0) {
    		return null;
    	}
		Item x = getLast();
		size--;
		return x;
	}

	public Item get(int index) {
		return items[index];
	}
}