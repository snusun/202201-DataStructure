
public class Main {
    static int[] A = {0, 1, 3, 6, 3, 7, 9, 2, 8, 4, 7, 2, 4, 6, 8, 5}; //{10, 9, 8, 8, 8, 5, 4, 3, 2, 1};
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
        HanoiTower hanoiTower = new HanoiTower();
        //hanoiTower.move(3, "A", "B", "C");
        hanoiTower.move4(10, "A", "B", "C", "D");

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
    }
}
