import java.util.*;

/**
 * 📌 삽입 정렬 (Insertion Sort)
 *
 * ▸ 정의: 현재 값을 왼쪽 정렬된 구간에 삽입하면서 정렬하는 알고리즘
 * ▸ 시간복잡도: 최선 O(n), 평균/최악 O(n²), 공간복잡도 O(1)
 * ▸ 정렬 안정성: ✅ Stable (같은 값의 순서 유지)
 *
 * ▸ 장점:
 *   - 거의 정렬된 배열에 매우 빠름
 *   - 구현 간단, 내부 정렬 (추가 메모리 X)
 *
 * ▸ 사용 상황:
 *   - 입력이 거의 정렬된 경우
 *   - 실시간으로 들어오는 데이터를 정렬할 때 (온라인 정렬)
 */
public class Main {

    public static void main(String[] args) {
        int[] arr = new int[]{4, 5, 1, 3, 2, 8, 6, 7};

        insertionSort(arr);  // 기본 삽입 정렬
//        binaryInsertionSort(arr); // 이진 삽입 정렬

        System.out.println(Arrays.toString(arr));
    }

    // 🟦 [1] 삽입 정렬 - 기본형
    //
    // - 앞쪽 구간은 정렬되어 있다고 가정하고
    // - 현재 값을 적절한 위치까지 왼쪽으로 밀어넣는 방식
    // - 정렬이 거의 되어 있을수록 빠름 (최선 O(n))
    //
    // 시간복잡도:
    // - 최선: O(n) (이미 정렬되어 있을 경우)
    // - 평균 / 최악: O(n²)
    private static void insertionSort(int[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            // 앞에서부터 key보다 큰 값을 한 칸씩 오른쪽으로 밀기
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            // 올바른 위치에 key 삽입
            arr[j + 1] = key;
        }
    }

    // 🟦 [2] 삽입 정렬 - 이진 삽입 정렬
    //
    // - 삽입 위치를 이진 탐색으로 찾음 → 비교 횟수 줄이기
    // - 삽입 자체는 O(n) 그대로, 전체 시간복잡도는 여전히 O(n²)
    private static void binaryInsertionSort(int[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int key = arr[i];

            // 이진 탐색으로 삽입 위치 찾기
            int insertPos = binarySearch(arr, 0, i - 1, key);

            // 삽입 위치 이후 요소들 오른쪽으로 한 칸씩 밀기
            for (int j = i - 1; j >= insertPos; j--) {
                arr[j + 1] = arr[j];
            }

            arr[insertPos] = key;
        }
    }

    // key를 삽입할 위치를 이진 탐색으로 찾기
    private static int binarySearch(int[] arr, int left, int right, int key) {
        while (left <= right) {
            int mid = (left + right) / 2;

            if (arr[mid] <= key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left; // 삽입할 위치
    }

}