public class RecursiveBubbleSort {
    public void bubbleSort(int[] A, int n){
        for(int last = n-1; last > 0; last--){
            for(int i=0; i<last; i++){
                if(A[i] > A[i+1]) {
                    int temp = A[i];
                    A[i] = A[i+1];
                    A[i+1] = temp;
                }
            }
        }
    }

    public void recursiveBubble(int[] A, int n){
        if(n==1) return;

        for(int i=0; i<n-1; i++){
            if(A[i] > A[i+1]) {
                int temp = A[i];
                A[i] = A[i+1];
                A[i+1] = temp;
            }
        }
        recursiveBubble(A, n-1);
    }
}
