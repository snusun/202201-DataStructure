public class MergeSort {

    public void switchingMergeSort(int[] A){
        int[] B = new int[A.length];
        // 1
        System.arraycopy(A, 0, B, 0, A.length);
        //
        msort(0, A.length-1, A, B);
    }

    private void msort(int p, int r, int[] A, int[] B){
        if(p<r){
            int q = (p+r)/2;
            // 2
            msort(p, q, B, A);
            msort(q+1, r, B, A);
            switchingMerge(p, q, r, B, A);
            //
        }
    }

    private void switchingMerge(int p, int q, int r, int[] C, int[] D){
        int i=p; int j=q+1; int t=p;
        while(i<=q && j<=r){
            if(C[i] <= C[j]){
                D[t++] = C[i++];
            } else {
                D[t++] = C[j++];
            }
        }
        while(i<=q) { // 왼쪽 부분 배열이 남은 경우
            D[t++] = C[i++];
        }
        while(j<=r) { // 오른쪽 부분 배열이 남은 경우
            D[t++] = C[j++];
        }
    }
}
