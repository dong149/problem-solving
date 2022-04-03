package algorithm.sort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class Sort {

    int[] arr;
    int[] expectArr;

    @BeforeEach
    void setUp() {
        arr = new int[]{5, 4, 3, 2, 1};
        expectArr = new int[]{1, 2, 3, 4, 5};
    }

    @Test
    void bubbleSortTest() {
        bubbleSort(arr);
        assertThat(arr).containsExactly(expectArr);
    }

    private void bubbleSort(int[] arr) {
        int temp = 0;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n - i; j++) {
                if (arr[j - 1] > arr[j]) {
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    @Test
    void selectionSortTest() {
        selectionSort(arr);
        assertThat(arr).containsExactly(expectArr);
    }

    private void selectionSort(int[] arr) {
        int indexMin, temp;
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            indexMin = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[indexMin]) {
                    indexMin = j;
                }
            }

            temp = arr[indexMin];
            arr[indexMin] = arr[i];
            arr[i] = temp;
        }
    }

    @Test
    void insertionSortTest() {
        insertionSort(arr);
        assertThat(arr).containsExactly(expectArr);
    }

    private void insertionSort(int[] arr) {
        int n = arr.length;
        for (int idx = 1; idx < n; idx++) {
            int temp = arr[idx];
            int prev = idx - 1;
            while (prev >= 0 && arr[prev] > temp) {
                arr[prev + 1] = arr[prev];
                prev--;
            }
            arr[prev + 1] = temp;
        }
    }

    @Test
    void mergeSortTest() {
        mergeSort(arr, 0, arr.length - 1);
        assertThat(arr).containsExactly(expectArr);
    }

    private void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private void merge(int[] arr, int left, int mid, int right) {
        int[] leftArr = Arrays.copyOfRange(arr, left, mid + 1);
        int[] rightArr = Arrays.copyOfRange(arr, mid + 1, right + 1);

        int i = 0, j = 0, k = left;
        int leftSize = leftArr.length;
        int rightSize = rightArr.length;

        while (i < leftSize && j < rightSize) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i++];
            } else {
                arr[k] = rightArr[j++];
            }
            k++;
        }

        while (i < leftSize) {
            arr[k++] = leftArr[i++];
        }

        while (j < rightSize) {
            arr[k++] = rightArr[j++];
        }
    }

    @Test
    void quickSortTest() {
        quickSort(arr, 0, arr.length - 1);
        assertThat(arr).containsExactly(expectArr);
    }

    private void quickSort(int[] arr, int left, int right) {
        if(left >= right) return;

        int pivot = partition(arr,left,right);

        quickSort(arr,left,pivot-1);
        quickSort(arr,pivot+1,right);
    }

    private int partition(int[] arr, int left, int right) {
        int pivot = arr[left];
        int i=left, j = right;
        while(i<j) {
            while(pivot < arr[j]) {
                j--;
            }
            while(i < j && pivot >= arr[i]) {
                i++;
            }
            int temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
        }
        arr[left] = arr[i];
        arr[i] = pivot;

        return i;
    }
}
