import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    
    private int N;        // number of elements on list
    private Node pre;     // sentinel before first item
    private Node post;    // sentinel after last item
    
    // construct an empty deque
    public Deque()  {
        pre  = new Node();
        post = new Node();
        pre.next = post;
        post.prev = pre;
    }
    
    // linked list node helper data type
    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }
    
    
    public boolean isEmpty() {
        return N == 0;
    }
    public int size() {
        return N;
    }
    
    
    // insert the item at the end
    public void addLast(Item item) {
        if (item == null) throw new java.lang.NullPointerException();
        Node last = post.prev;
        Node x = new Node();
        x.item = item;
        x.next = post;
        x.prev = last;
        post.prev = x;
        last.next = x;
        N++;
    }
    
    // insert the item at the end
    public void addFirst(Item item) {
        if (item == null) throw new java.lang.NullPointerException();
        Node first = pre.next;
        
        Node x = new Node();
        x.item = item;
        x.next = first;
        x.prev = pre;
        
        pre.next = x;
        first.prev = x;
        N++;
    }
    
    // delete and return the item at the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        
        Node x = pre.next;
        
        pre.next = x.next;
        pre.next.prev = pre;
        
        x.next = null;
        x.prev = null;
        N--;
        
        return x.item;
    }
    
    // delete and return the item at the end
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        
        Node x = post.prev;
        
        post.prev = x.prev;
        post.prev.next = post;
        
        x.next = null;
        x.prev = null;
        N--;
        
        return x.item;
    }
    
    // return an iterator over items in order from front to end
    public Iterator<Item> iterator()  { 
       return new DoublyLinkedListIterator(); 
    }
    
     // assumes no calls to DoublyLinkedList.add() during iteration
    private class DoublyLinkedListIterator implements Iterator<Item> {
        // the node that is returned by next()
        private Node current      = pre.next;  
      
        public boolean hasNext()  {
            return current != post;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
        
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }

    }
    
     /**
     * Unit tests the <tt>ResizingArrayQueue</tt> data type.
     */
    public static void main(String[] args) {
        Deque<String> q = new Deque<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            StdOut.println(" ::: " + item);
            if (!item.equals("-")) q.addLast(item);
            else if (!q.isEmpty()) StdOut.print(q.removeLast() + " ");
            
            for (String s : q) {
                StdOut.println(" --- " + s);
            }
        }
        StdOut.println("(" + q.size() + " left on queue)");
    }
}