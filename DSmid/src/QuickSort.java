public class QuickSort {
    public void quickSort(int[] A, int p, int r){
        if(p<r){
            int q = partition(A, p, r);
            quickSort(A, p, q-1);
            quickSort(A, q+1, r);
        }
    }

    public int partition(int[] A, int first, int last){
        int pivot = A[last];
        int lastS1 = first-1;

        for(int firstUnknown=first; firstUnknown<last; ++firstUnknown){
            if(A[firstUnknown]<=pivot){
                ++lastS1;
                int temp=A[firstUnknown];
                A[firstUnknown]  = A[lastS1];
                A[lastS1] = temp;
            }
        }
        int temp=A[last];
        A[last]  = A[lastS1+1];
        A[lastS1+1] = temp;
        return lastS1+1;
    }

    public void quickSortWithMerge(int[] A, int p, int r){
        if(p<r){
            int pivot = partition(A, p, r);
            System.out.println(pivot);
            quickSortWithMerge(A, p, pivot-1);
            originMergesort(pivot+1, r, A);
        }
    }

    public void mergeWithQuick(int[] A, int p, int r){
        if(p < r){
            int mid = (p+r)/2;
            mergeWithQuick(A, p, mid);
            quickSort(A, mid+1, r);
            merge(A, p, mid, r);
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
