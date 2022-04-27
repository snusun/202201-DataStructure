import javax.swing.plaf.SplitPaneUI;
import java.util.Dictionary;

public class DoublyLinkedList {
    private BidirectionalNode head;
    private int numItems;
    public DoublyLinkedList(){
        numItems=0;
        head = new BidirectionalNode(10);
        head.next = head;
        head.prev = head;
    }
    public void add(int x){
        System.out.println("x: " + x);
        BidirectionalNode prevNode = head;
        int num=0;
        while(x < prevNode.item && num <= numItems){ // 등호 없애도 성립?
            System.out.println("while");
            //if(prevNode==head) break;
            prevNode = prevNode.next;
            num++;
        }
        prevNode = prevNode.prev;
        BidirectionalNode newNode = new BidirectionalNode(x, prevNode, prevNode.next);
        newNode.next.prev = newNode;
        prevNode.next= newNode;
        numItems++;
    }

    public void print(){
        BidirectionalNode curr= head.next;
        for(int i=0; i<numItems; i++){
            System.out.println(curr.item);
            curr=curr.next;
        }
    }
}

class BidirectionalNode{
    public int item;
    public BidirectionalNode prev;
    public BidirectionalNode next;
    public BidirectionalNode(int newItem){
        item=newItem;
        prev = next = null;
    }
    public BidirectionalNode(int newItem, BidirectionalNode prevNode, BidirectionalNode nextNode){
        item = newItem;
        prev = prevNode;
        next = nextNode;
    }
}
