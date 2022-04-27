public class ListReferenceBased {
    private Node head;
    private int numItems;
    //constructor
    public ListReferenceBased(){
        numItems = 0;
        head = null;
    }
    //operations
    public boolean isEmpty(){return numItems==0;}
    public int size(){return numItems;}
    private Node find(int index){
        // return reference to i-th node
        Node curr = head;
        //System.out.println("find: " + index);
        for(int i=1; i<index; i++){
            //System.out.println("i: " + i);
            curr = curr.getNext();
        }
        return curr;
    }
    public Object get(int index){
        if(index>=1 && index <= numItems){
            Node curr = find(index);
            Object dataItem = curr.getItem();
            return dataItem;
        } else {
            System.out.println("invalid index");
            return null;
        }
    }
    public void remove(int index){
        if(index >= 1 && index <= numItems){
            if(index==1) head = head.getNext();
            else {
                Node prev = find(index-1);
                Node curr = prev.getNext();
                prev.setNext(curr.getNext());
            }
            numItems--;
        } else {
            System.out.println("invalid index");
        }
    }
    public void add(int index, Object item){
        if(index >= 1 && index <= numItems+1){
            if(index==1){
                Node newNode = new Node(item, head);
                head = newNode;
            } else {
                Node prev = find(index-1);
                Node newNode = new Node(item, prev.getNext());
                prev.setNext(newNode);
            }
            numItems++;
        } else {
            System.out.println("invalid index");
        }
    }
}
