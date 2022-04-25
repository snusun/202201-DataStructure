
public class Main {
    static int[] A =  {0, 1, 3, 6, 3, 7, 9, 2, 8, 4, 7, 2, 4, 6, 8, 5}; //{10, 9, 8, 8, 8, 5, 4, 3}; //{10, 9, 8, 8, 8, 5, 4, 3, 2, 1};
    static int[] B = {0, 3, 2, 1};
    //static String prefixOperation = "*-+ABC";//"*+AB+CD";

    public static void main(String[] args) {

        // 2021 5번
        // switch merge sort
        /*
        SwitchMerge switchMerge = new SwitchMerge();

        for(int n: A){
            System.out.print(n + " ");
        }
        System.out.println();

        switchMerge.switchingMergeSort(A);

        for(int n: A){
            System.out.print(n + " ");
        }
        System.out.println();
        */

        // 2019 1번
        // the number of call of fib
        /*
        Fibonacci fibonacci = new Fibonacci();
        fibonacci.fib(5);
         */

        // 2019 2번
        // the number of call of heap sort percolate down
        /*
        HeapSort heapSort = new HeapSort();

        heapSort.heapsort(A, A.length);
        for(int n: A){
            System.out.print(n + " ");
        }
         */

        // 2019 3번
        // prefix to postfix
        /*
        String prefixOperation = "*-+ABC";
        Prefix prefix = new Prefix();
        System.out.println(prefix.toPostfix(prefixOperation));
         */

        // 2019 5번
        // hanoi tower has 4 tower
        /*
        HanoiTower hanoiTower = new HanoiTower();
        //hanoiTower.move(3, "A", "B", "C");
        hanoiTower.move4(10, "A", "B", "C", "D");
        */

        // 2019 6번
        /*
        RecursiveBubbleSort recursiveBubbleSort = new RecursiveBubbleSort();
        for(int n: A){
            System.out.print(n + " ");
        }
        //recursiveBubbleSort.bubbleSort(A, A.length);
        recursiveBubbleSort.recursiveBubble(A, A.length);
        for(int n: A){
            System.out.print(n + " ");
        }
         */

        // 2018 8번
        /*
        StackReferenceBased stack = new StackReferenceBased();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.peek();
        stack.pop();
        stack.pop();
        stack.peek(); // 2
        stack.push(5);
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
         */

        // 2016 5번
        /*
        MergeSort mergeSort = new MergeSort();
        int cnt=0;
        mergeSort.originMergesort(0, A.length-1, A, cnt);
        for(int n: A){
            System.out.print(n + " ");
        }*/

        // 2014 4번
        // double-ended queue
        /*
        QueueArrayBased queue = new QueueArrayBased();

        queue.dequeue();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.dequeue();
        queue.dequeue();
        queue.enqueue(4);

        queue.removeBack();
        queue.insertFront(1);
        queue.insertFront(2);
        queue.insertFront(3);
        queue.removeBack();
        queue.removeBack();
        queue.insertFront(4);
        Object[] q = queue.getItems();
        for(Object n: q){
            System.out.print(n + " ");
        }
        */

        // 2014 6번
        /*
        HanoiTower hanoiTower = new HanoiTower();
        hanoiTower.move3(10, "A", "B", "C");
         */

        // 2012 6번
        // 에러 오졌다리
        /*
        System.out.println("hi");
        DoublyLinkedListReferencedBased doublyLinkedList = new DoublyLinkedListReferencedBased();
        doublyLinkedList.add(1);
        doublyLinkedList.add(2);
        doublyLinkedList.add(4);
        doublyLinkedList.print();
        doublyLinkedList.add(3);
        doublyLinkedList.add(2);
        doublyLinkedList.add(5);
        doublyLinkedList.print();
        System.out.println("hi");
         */

        // 2011 2번
        /*
        HanoiTower hanoiTower = new HanoiTower();
        hanoiTower.move2(7, "A", "B", "C");
         */

        // 2011 4번
        Postfix postfix = new Postfix();
        postfix.evaluate(new char[]{'3', '4', '*', '2', '5', '*', '+'});
    }
}
