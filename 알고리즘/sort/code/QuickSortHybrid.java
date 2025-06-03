import java.util.*;

/**
 * 🟦 하이브리드 퀵 정렬 (Hybrid Quick Sort)
 *
 * ✅ 개요
 * - 퀵 정렬의 빠른 평균 성능 + 삽입 정렬의 작은 배열에서의 효율성 결합
 * - 일정 크기 이하 배열은 삽입 정렬로 처리하여 **퀵 정렬의 단점 보완**
 *
 * ✅ 전환 기준
 * - 배열 크기가 INSERTION_SORT_THRESHOLD 이하일 경우 삽입 정렬로 전환
 *
 * ✅ 시간 복잡도
 * - 평균: O(N log N)
 * - 최악: O(N²) (퀵 정렬 특성 그대로, 피벗에 따라 발생)
 *
 * ✅ 공간 복잡도
 * - O(log N) (재귀 호출 스택)
 *
 * ✅ 장점
 * - **작은 배열에 효율적**
 * - **캐시 적중률**이 높음 (삽입 정렬은 인접 메모리를 순차적으로 접근)
 * - **실전에서 가장 많이 사용되는 정렬 방식**
 *
 * ✅ 사용 예
 * - Java Arrays.sort(int[]) (primitive type) 내부 구현 (Dual-Pivot QuickSort + Insertion)
 * - C++ std::sort 내부 구현 (Introsort 계열)
 */

public class Main {

    private static final Random random = new Random();

    private static final int INSERTION_SORT_THRESHOLD = 10; // 삽입 정렬로 전환할 임계값

    public static void main(String[] args) {
        int[] arr = new int[]{4, 5, 1, 3, 2, 8, 6, 7};

        quickSortHybrid(arr, 0, arr.length-1);

        System.out.println(Arrays.toString(arr));
    }

    /**
     * 🔹 하이브리드 퀵 정렬 (Hybrid Quick Sort)
     * - 퀵 정렬 수행 중, 정렬 구간이 작아지면 삽입 정렬로 전환
     */
    private static void qucickSortHybrid(int[] arr, int left, int right){
        // 구간 크기가 작으면 삽입 정렬로 전환
        if (right - left + 1 <= INSERTION_SORT_THRESHOLD) {
            insertionSort(arr, left, right);
            return;
        }

        // Lomuto 분할 방식 사용
        int pivotIndex = partition(arr, left, right);

        int leftArea = left; // < pivot
        int i = left + 1; // 탐색 인덱스
        int rightArea = right; // > pivot

        // 피벗을 기준으로 좌우 재귀 정렬
        hybridQuickSort(arr, left, pivotIndex - 1);
        hybridQuickSort(arr, pivotIndex + 1, right);
    }

    /**
     * 🔹 Lomuto 분할 방식
     * - 피벗: 가장 오른쪽 요소
     * - 왼쪽은 피벗보다 작거나 같은 값, 오른쪽은 큰 값으로 분할
     */
    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right]; // 피벗 선택: 가장 오른쪽 값
        int i = left - 1; // i는 작거나 같은 값을 넣을 자리의 직전 위치

        for(int j=left; j<right; j++){
            if(arr[j] <= pivot){
                i++; // 작거나 같은 값 발견하면 i 증가
                swap(arr, i, j); // i에 해당하는 위치에 해당 값을 옮긴다.
            }
        }

        // 최종적으로 피벗을 i+1 위치에 배치
        swap(arr, i+1, right);
        return i+1; // 피벗의 최종 위치 반환
    }

    /**
     * 🔹 삽입 정렬 (Insertion Sort)
     * - 작은 구간에 대해 O(N^2) 정렬이지만, 실제론 빠름
     * - 이유: 비교 횟수 적고, 캐시 효율성이 높음
     */
    private static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;

            // 앞에서부터 비교하며 삽입 위치 탐색
            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j]; // 뒤로 밀기
                j--;
            }
            arr[j + 1] = key; // 알맞은 위치에 삽입
        }
    }

    private static void swap(int[] arr, int a, int b) {
        if (a != b) {
            int temp = arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
        }
    }
}