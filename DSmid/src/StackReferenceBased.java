import java.util.Stack;

public class StackReferenceBased {
    private Node top;
    public StackReferenceBased(){
        top = null;
    }
    public boolean isEmpty(){
        return top==null;
    }
    public void push(Object newItem){
        top = new Node(newItem, top); // 생성자 변경함
        System.out.println("push " + newItem);
    }
    public Object pop(){
        if(!isEmpty()){
            Node temp = top;
            //top = top.getNext();
            // add
            top = top.getPrev();

            System.out.println("pop " + temp.getItem());
            return temp.getItem();
        } else {
            System.out.println("stack is empty");
            return null;
        }
    }
    public void popAll(){
        top = null;
    }
    public Object peek(){
        if(!isEmpty()) {
            System.out.println("peek " + top.getItem());
            return top.getItem();
        }
        else {
            System.out.println("stack is empty");
            return null;
        }
    }
}

class Node{
    private Object item;
    private Node next;

    // add
    private Node prev;

    public Node(Object newItem){
        item = newItem;
        next = null;
    }
    /*
    public Node(Object newItem, Node nextNode){
        item = newItem;
        next = nextNode;
    }*/

    // add
    public Node(Object newItem, Node prevNode){
        item = newItem;
        prev = prevNode;
    }

    public Object getItem(){
        return item;
    }
    public void setItem(Object item){
        this.item = item;
    }
    public Node getNext(){
        return next;
    }
    public void setNext(Node next) {
        this.next = next;
    }

    // add
    public Node getPrev(){
        return prev;
    }

    public void setPrev(Node prev){
        this.prev = prev;
    }
}
