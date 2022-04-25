public class QueueArrayBased {
    final int MAX_QUEUE = 50;
    private Object items[];
    private int front, back, numItems;

    public QueueArrayBased(){
        items = new Object[MAX_QUEUE];
        front = 0;
        back = MAX_QUEUE - 1;
        numItems = 0;
    }

    public boolean isEmpty(){
        return numItems == 0;
    }

    public boolean isFull(){
        return numItems==MAX_QUEUE;
    }

    public void enqueue(Object newItem){
        if(!isFull()){
            back = (back + 1) % MAX_QUEUE;
            items[back] = newItem;
            ++numItems;
        } else {
            System.out.println("queue is full");
        }
    }

    public Object dequeue(){
        if(!isEmpty()){
            Object queueFront = items[front];
            front = (front+1) % MAX_QUEUE;
            --numItems;
            return queueFront;
        } else {
            System.out.println("queue is empty");
            return null;
        }
    }

    public void insertFront(Object newItem){
        if(!isFull()){
            if(front==0) front = MAX_QUEUE - 1;
            else front -= 1;
            items[front] = newItem;
            ++numItems;
        } else {
            System.out.println("queue is full");
        }
    }

    public Object removeBack(){
        if(!isEmpty()){
            Object queueBack = items[front];
            back = (back==0) ? MAX_QUEUE-1: back-1;
            --numItems;
            return queueBack;
        } else {
            System.out.println("queue is empty");
            return null;
        }
    }

    public Object[] getItems(){
        return items;
    }
}
