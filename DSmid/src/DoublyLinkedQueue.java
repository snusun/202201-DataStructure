public class DoublyLinkedQueue {
    private DNode head;

    public DoublyLinkedQueue(){
        head = new DNode(null);
        head.prev = head;
        head.next = head;
    }
    public boolean isEmpty(){
        //return head.getItem() == null;
        return head.next == head;
    }
    public void enqueue(Integer newItem){// append
        DNode preNode = head.prev;
        DNode newNode = new DNode(newItem, preNode, head);
        preNode.next = newNode;
        head.prev = newNode;
//        if(isEmpty()){
//            head = new DNode(newItem);
//            System.out.println("head: " + head.getItem());
//            head.setPrev(head);
//            head.setNext(head);
//        } else {
//            DNode newNode = new DNode(newItem, head.getPrev() , head);
//            head.setPrev(newNode);
//            head.getPrev().setNext(newNode);
//            //System.out.println(head.getItem());
//        }
//        System.out.println("en: " + newItem);
    }
    public Integer dequeue(){
        if(!isEmpty()){
            DNode removeNode = head.next;
            removeNode.prev.next = removeNode.next;
            removeNode.next.prev = removeNode.prev;
            return removeNode.item;
        } else {
            System.out.println("is empty");
            return 0;
        }

//        if(!isEmpty()){
//            DNode de = head;
//            head.getPrev().getPrev().setNext(head.getPrev().getNext());
//            head.getPrev().getNext().setPrev(head.getPrev().getPrev());
//            head = head.getNext();
//            System.out.println("de: "+ de.getItem());
//            return (Integer) de.getItem();
//        }
//        else {
//            System.out.println("dequeue is empty");
//            return null;
//        }
    }

    public void printDequeue(){
        if(isEmpty()) System.out.println("empty");
        else {
            DNode curr = head.next;
            while(curr!=head){
                System.out.print(curr.item + " ");
                curr = curr.next;
            }
        }
    }
}
