import java.io.*;
import java.util.*;

public class SortingTest {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            boolean isRandom = false;    // 입력받은 배열이 난수인가 아닌가?
            int[] value;    // 입력 받을 숫자들의 배열
            String nums = br.readLine();    // 첫 줄을 입력 받음
            if (nums.charAt(0) == 'r') {
                // 난수일 경우
                isRandom = true;    // 난수임을 표시

                String[] nums_arg = nums.split(" ");

                int numsize = Integer.parseInt(nums_arg[1]);    // 총 갯수
                int rminimum = Integer.parseInt(nums_arg[2]);    // 최소값
                int rmaximum = Integer.parseInt(nums_arg[3]);    // 최대값

                Random rand = new Random();    // 난수 인스턴스를 생성한다.

                value = new int[numsize];    // 배열을 생성한다.
                for (int i = 0; i < value.length; i++)    // 각각의 배열에 난수를 생성하여 대입
                    value[i] = rand.nextInt(rmaximum - rminimum + 1) + rminimum;
            } else {
                // 난수가 아닐 경우
                int numsize = Integer.parseInt(nums);

                value = new int[numsize];    // 배열을 생성한다.
                for (int i = 0; i < value.length; i++)    // 한줄씩 입력받아 배열원소로 대입
                    value[i] = Integer.parseInt(br.readLine());
            }

            // 숫자 입력을 다 받았으므로 정렬 방법을 받아 그에 맞는 정렬을 수행한다.
            while (true) {
                int[] newvalue = (int[]) value.clone();    // 원래 값의 보호를 위해 복사본을 생성한다.

                String command = br.readLine();

                long t = System.currentTimeMillis();
                switch (command.charAt(0)) {
                    case 'B':    // Bubble Sort
                        newvalue = DoBubbleSort(newvalue);
                        break;
                    case 'I':    // Insertion Sort
                        newvalue = DoInsertionSort(newvalue);
                        break;
                    case 'H':    // Heap Sort
                        newvalue = DoHeapSort(newvalue);
                        break;
                    case 'M':    // Merge Sort
                        newvalue = DoMergeSort(newvalue);
                        break;
                    case 'Q':    // Quick Sort
                        newvalue = DoQuickSort(newvalue);
                        break;
                    case 'R':    // Radix Sort
                        newvalue = DoRadixSort(newvalue);
                        break;
                    case 'X':
                        return;    // 프로그램을 종료한다.
                    default:
                        throw new IOException("잘못된 정렬 방법을 입력했습니다.");
                }
                if (isRandom) {
                    // 난수일 경우 수행시간을 출력한다.
                    System.out.println((System.currentTimeMillis() - t) + " ms");
                } else {
                    // 난수가 아닐 경우 정렬된 결과값을 출력한다.
                    for (int i = 0; i < newvalue.length; i++) {
                        System.out.println(newvalue[i]);
                    }
                }

            }
        } catch (IOException e) {
            System.out.println("입력이 잘못되었습니다. 오류 : " + e.toString());
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    private static int[] DoBubbleSort(int[] value) {
        // TODO : Bubble Sort 를 구현하라.
        // value는 정렬안된 숫자들의 배열이며 value.length 는 배열의 크기가 된다.
        // 결과로 정렬된 배열은 리턴해 주어야 하며, 두가지 방법이 있으므로 잘 생각해서 사용할것.
        // 주어진 value 배열에서 안의 값만을 바꾸고 value를 다시 리턴하거나
        // 같은 크기의 새로운 배열을 만들어 그 배열을 리턴할 수도 있다.
        int n = value.length;
        for (int last = n - 1; last > 0; last--) {
            for (int i = 0; i < last; i++) {
                if (value[i] > value[i + 1]) {
                    int temp = value[i];
                    value[i] = value[i + 1];
                    value[i + 1] = temp;
                }
            }
        }
        return (value);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    private static int[] DoInsertionSort(int[] value) {
        // TODO : Insertion Sort 를 구현하라.
        int n = value.length;
        for (int i = 1; i < n; i++) {
            int j = i - 1;
            int newItem = value[i];
            while (0 <= j && newItem < value[j]) {
                value[j + 1] = value[j];
                j--;
            }
            value[j + 1] = newItem;
        }
        return (value);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    private static int[] DoHeapSort(int[] value) {
        // TODO : Heap Sort 를 구현하라.
        Heap heap = new Heap(value, value.length);
        heap.heapSort();

        return (value);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    private static int[] DoMergeSort(int[] value) {
        // TODO : Merge Sort 를 구현하라.
        mergeSort(value, 0, value.length - 1);
        return (value);
    }

    private static void mergeSort(int[] value, int p, int r) {
        if (p < r) {
            int q = (p + r) / 2;
            mergeSort(value, p, q);
            mergeSort(value, q + 1, r);
            merge(value, p, q, r);
        }
    }

    private static void merge(int[] value, int p, int q, int r) {
        int i = p, j = q + 1, t = 0;
        int[] subArr = new int[r + 1 - p];

        while (i <= q && j <= r) {
            if (value[i] < value[j]) {
                subArr[t++] = value[i++];
            } else {
                subArr[t++] = value[j++];
            }
        }
        while (i <= q) {
            subArr[t++] = value[i++];
        }
        while (j <= r) {
            subArr[t++] = value[j++];
        }
        System.arraycopy(subArr, 0, value, p, r + 1 - p);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////
    private static int[] DoQuickSort(int[] value) {
        // TODO : Quick Sort 를 구현하라.
        quickSort(value, 0, value.length - 1);
        return (value);
    }

    private static void quickSort(int[] value, int p, int r) {
        if (p < r) {
            int q = partition(value, p, r);
            quickSort(value, p, q - 1);
            quickSort(value, q + 1, r);
        }
    }

    private static int partition(int[] value, int p, int r) {
        int x = value[r];
        int i = p - 1;
        for (int j = p; j < r; j++) {
            if (value[j] < x) {
                i++;
                int temp1 = value[i];
                value[i] = value[j];
                value[j] = temp1;
            }
        }
        int temp2 = value[i + 1];
        value[i + 1] = value[r];
        value[r] = temp2;
        return i + 1;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    private static int[] DoRadixSort(int[] value) {
        // TODO : Radix Sort 를 구현하라.
        int negCnt = 0;
        for (int val : value) {
            if (val < 0) negCnt++;
        }
        int[] negValue = new int[negCnt];
        int[] posValue = new int[value.length - negCnt];

        int p = 0;
        int n = 0;
        for (int j : value) {
            if (j >= 0) posValue[p++] = j;
            else negValue[n++] = j;
        }

        radixSort(negValue);
        radixSort(posValue);

        for (int i = 0; i < negValue.length; i++) {
            value[i] = negValue[negValue.length - 1 - i];
        }
        System.arraycopy(posValue, 0, value, negValue.length, posValue.length);

        return (value);
    }

    private static void radixSort(int[] value) {
        int[] sortArr = new int[value.length];
        int queSize, maxLen = 0;
        int len;
        int idx;

        Map<Integer, Queue<Integer>> map = new HashMap<>();

        for (int l : value) {
            len = (int) Math.log10(Math.abs(l)) + 1;
            if (maxLen < len) {
                maxLen = len;
            }
        }

        for (int j = 0; j < 10; j++) {
            map.put(j, new LinkedList<>());
        }

        for (int i = 0; i < maxLen; i++) {
            for (int j = 0; j < 10; j++) {
                map.get(j).clear();
            }

            for (int j = value.length - 1; j >= 0; j--) {
                map.get((int) (Math.abs(value[j]) / Math.pow(10, i) % 10)).add(value[j]);
            }

            idx = value.length - 1;
            for (int j = 9; j >= 0; j--) {

                queSize = map.get(j).size();
                for (int qIdx = 0; qIdx < queSize; qIdx++) {
                    sortArr[idx] = map.get(j).poll();
                    idx--;
                }
            }

            System.arraycopy(sortArr, 0, value, 0, value.length);
        }
    }
}

class Heap {
    int[] value;
    int numItems;

    Heap(int[] value, int numItems){
        this.value = value;
        this.numItems = numItems;
    }

    int deleteMax(){
        int max = value[0];
        value[0] = value[numItems-1];
        numItems--;
        percolateDown(0);
        return max;
    }

    void percolateDown(int k){
        int child = 2*k+1;
        int right = 2*k+2;
        if(child<=numItems-1){
            if(right<=numItems-1 && value[child] < value[right]){
                child = right;
            }
            if(value[k] < value[child]){
                int temp = value[k];
                value[k] = value[child];
                value[child] = temp;
                percolateDown(child);
            }
        }
    }

    void buildHeap(){
        for(int i=(numItems-2)/2; i>=0; i--){
            percolateDown(i);
        }
    }

    void heapSort(){
        buildHeap();
        for(int i=numItems-1; i>0; i--){
            value[i] = deleteMax();
        }
    }
}