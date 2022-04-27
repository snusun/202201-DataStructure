//public class DoublyLinkedListReferencedBased {
//    private DNode headNode;
//    private int numItems;
//    public DoublyLinkedListReferencedBased(){
//        numItems = 0;
//        // dummy head 생성
//        headNode = new DNode(-1);
//        headNode.setNext(headNode);
//        headNode.setPrev(headNode);
//    }
//
//    public void add(int item){
//        DNode prevNode = headNode;
//        System.out.println(item);
//        int cnt=0;
//        while (item > (int) prevNode.getItem() && cnt<=numItems) {
//            System.out.println(item + " " + (int) prevNode.getItem());
//            prevNode = prevNode.getNext();
//            cnt++;
//        }
//        prevNode = prevNode.getNext();
//        DNode newNode = new DNode(item, prevNode, prevNode.getNext());
//        newNode.getNext().setPrev(newNode);
//        prevNode.setNext(newNode);
//        numItems++;
//
//        //DNode newNode = new DNode(item);
//
//    }
//    public void print(){
//        DNode curr = headNode.getNext();
//        for(int i=0; i<numItems; i++){
//            System.out.println(curr.getItem());
//            curr = curr.getNext();
//        }
//    }
//}
//
class DNode {
    public Integer item;
    public DNode prev;
    public DNode next;
    public DNode(Integer newItem){
        item = newItem;
        prev = next = null;
    }
    public DNode(Integer newItem, DNode prevNode, DNode nextNode){
        item = newItem;
        prev = prevNode;
        next = nextNode;
    }
//    public Object getItem(){
//        return item;
//    }
//    public DNode getNext(){
//        return next;
//    }
//    public DNode getPrev(){
//        return prev;
//    }
//    public void setNext(DNode next){
//        this.next = next;
//    }
//    public void setPrev(DNode prev){
//        this.prev = prev;
//    }
}