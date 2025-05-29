import java.io.*;
import java.util.*;

/**
 * 🧮 선택 정렬 (Selection Sort)
 *
 * - 각 회전마다 가장 작은(또는 가장 큰) 값을 찾아서 제자리로 이동
 * - 비교는 많이 하지만, 교환(swap)은 각 회전당 1번만 일어남
 * - 단순하고 안정성이 없는 정렬 방식 (Unstable)
 * - 정렬된 부분과 미정렬 부분을 구분하며 앞에서부터 채워나감
 *
 * 시간복잡도: O(n²) 항상
 * 공간복잡도: O(1) (제자리 정렬)
 */

public class Main {

    public static void main(String[] args) {
        int[] arr = new int[]{4, 5, 1, 3, 2, 8, 6, 7};

        selectionSort(arr);  // 오름차순

        System.out.println(Arrays.toString(arr));
    }

    // 🟦 [1] 선택 정렬 - 오름차순
    private static void selectionSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;

            // 현재 위치 이후에서 가장 작은 값을 찾는다
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }

            // 가장 작은 값과 현재 위치의 값을 교환
            if (i != minIdx) {
                int temp = arr[i];
                arr[i] = arr[minIdx];
                arr[minIdx] = temp;
            }
        }
    }
}