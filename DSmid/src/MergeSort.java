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

    public void originMergesort(int p, int r, int[] A/*, int cnt*/){
        //cnt++;
        //System.out.println(cnt);
        if(p<r){
            int q = (p+r)/2;
            originMergesort(p, q, A);
            originMergesort(q+1, r, A);
            merge(A, p, q, r);
        }
        //cnt--;
        //return cnt;
    }

    private void merge(int[] A, int p, int q, int r){
        int i=p; int j=q+1; int t=0;
        int[] temp = new int[A.length];
        while(i<=q && j<=r){
            if(A[i] <= A[j]){
                temp[t++] = A[i++];
            } else {
                temp[t++] = A[j++];
            }
        }
        while(i<=q) { // 왼쪽 부분 배열이 남은 경우
            temp[t++] = A[i++];
        }
        while(j<=r) { // 오른쪽 부분 배열이 남은 경우
            temp[t++] = A[j++];
        }
        i = p; t = 0;
        while(i<=r){
            A[i++] = temp[t++];
        }
    }
}
