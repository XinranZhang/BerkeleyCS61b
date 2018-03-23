public interface Deque<Item> {

    void addFirst(Item x);
    void addLast(Item x);
    int size();
    Item removeFirst();
    Item removeLast();
    Item get(int index);
    Item getRecursive(int index);
    boolean isEmpty();
    void printDeque();
    Item getLast();
    Item getFirst();

}
