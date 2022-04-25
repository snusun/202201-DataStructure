public class HeapSort {
    static int call=0;

    public void heapsort(int[] A, int n){
        // build array A[1...n] to heap
        for(int i=n/2; i>=0; i--){
            System.out.println("build");
            percolateDown(A, i, n);
        }
        // delete one by one
        for(int size=n; size>1; size--){
            // exchange
            int temp = A[1];
            A[1] = A[size];
            A[size]  = temp;
            percolateDown(A, 1, size-1);
        }
    }

    private void percolateDown(int[] A, int i, int n){
        System.out.println(++call);
        System.out.println("size: " + n);
        int child = 2*i;
        int rightChild = 2*i + 1;
        if(child <= n) {
            if(rightChild<=n && A[child] < A[rightChild]){
                child = rightChild;
            }
            if(A[i] < A[child]) {
                int temp = A[i];
                A[i] = A[child];
                A[child]  = temp;
                percolateDown(A, child, n);
            }
        }
    }
}
