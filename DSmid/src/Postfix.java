public class Postfix {
    public int evaluate(char[] operation){
        QueueArrayBased queue = new QueueArrayBased();
        StackReferenceBased stack = new StackReferenceBased();
        for(char c: operation){
            queue.enqueue(c);
        }
        while (!queue.isEmpty()){
            char ch = (char) queue.dequeue();
            if(Character.isDigit(ch)){
                stack.push(ch-'0');
            } else {
                int n2 = (int) stack.pop();
                int n1 = (int) stack.pop();
                if(ch=='+') {
                    stack.push(n1 + n2);
                } else if(ch=='-') {
                    stack.push(n1-n2);
                } else if(ch=='*') {
                    stack.push(n1 * n2);
                }
            }

        }
        return (int) stack.pop();


        /*while (!stack.isEmpty()){
            queue.enqueue(stack.pop());
        }

        while (!queue.isEmpty()){
            stack.push(queue.dequeue());
        }*/



        /*while (!queue.isEmpty()){

        }*/
    }
}

/*
class Node{
    private Object item;
    private Node next;
    public Node(Object newItem){
        item = newItem;
        next = null;
    }
    public Node(Object newItem, Node nextNode){
        item = newItem;
        next = nextNode;
    }
    public Object getItem(){
        return item;
    }
    public void setItem(Object newItem){
        item = newItem;
    }
    public void setNext(Node newNode){
        next = newNode;
    }
    public Node getNext(){
        return next;
    }
}
*/

/*

class QueueArrayBased{
    final int MAX_QUEUE = 100;
    private Object items[];
    private int front, back, numItems;

    public QueueArrayBased(){
        items = new Object[MAX_QUEUE];
        front = 0;
        back = MAX_QUEUE - 1;
        numItems = 0;
    }
    public boolean isEmpty(){
        return numItems==0;
    }
}*/
