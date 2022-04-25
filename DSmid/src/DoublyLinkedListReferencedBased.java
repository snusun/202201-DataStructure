public class DoublyLinkedListReferencedBased {
    private DNode headNode;
    private int numItems;
    public DoublyLinkedListReferencedBased(){
        numItems = 0;
        // dummy head 생성
        headNode = new DNode(null);
        headNode.setNext(headNode);
        headNode.setPrev(headNode);
    }
    public void add(int item){
        DNode curr = headNode.getNext();
        if(curr.getItem()==null){
            DNode newNode= new DNode(item, headNode, headNode);
            return;
        }
        while(curr.getNext()!=null){
            if((int)curr.getItem()>(int)item){
                DNode newNode = new DNode(item, curr.getPrev(), curr);
                curr.setPrev(newNode);
                curr.getPrev().setNext(newNode);
                numItems++;
            }
            curr=curr.getNext();
        }
        DNode newNode= new DNode(item, curr, null);
        curr.setNext(newNode);
    }
    public void print(){
        DNode curr = headNode.getNext();
        while (curr!=null){
            System.out.println(curr.getItem());
            curr = curr.getNext();
        }
    }
}

class DNode {
    private Object item;
    private DNode prev;
    private DNode next;
    public DNode(Object newItem){
        item = newItem;
        prev = next = null;
    }
    public DNode(Object newItem, DNode prevNode, DNode nextNode){
        item = newItem;
        prev = prevNode;
        next = nextNode;
    }
    public Object getItem(){
        return item;
    }
    public DNode getNext(){
        return next;
    }
    public DNode getPrev(){
        return prev;
    }
    public void setNext(DNode next){
        this.next = next;
    }
    public void setPrev(DNode prev){
        this.prev = prev;
    }
}