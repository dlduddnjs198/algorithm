import java.util.*;

/**
 * 퀵 정렬 (Quick Sort) - Hoare 분할 방식
 *
 * ✅ 개요
 * - 퀵 정렬의 원조이자 C.A.R. Hoare가 1960년에 제안한 정렬 방식
 * - 피벗 기준으로 양쪽 포인터가 서로 교차할 때까지 이동하며 분할
 * - 일반적으로 **Lomuto보다 비교/교환 횟수가 적어 성능이 더 좋음**
 *
 * ✅ Hoare 분할 방식
 * - 피벗은 보통 **가운데 값** 또는 **왼쪽 값**
 * - 두 포인터 i(left), j(right)를 양쪽 끝에서 시작해서
 *   → i는 피벗보다 크거나 같은 값에서 멈춤
 *   → j는 피벗보다 작거나 같은 값에서 멈춤
 * - i < j면 arr[i] ↔ arr[j] 교환 후 계속 진행
 * - i ≥ j가 되면 종료하고, **j를 기준으로 분할**
 *
 * ✅ 시간 복잡도
 * - 평균: O(N log N)
 * - 최악: O(N²) (정렬된 배열에 대해)
 *
 * ✅ 공간 복잡도
 * - O(log N) (재귀 호출 스택)
 *
 * ✅ 장점
 * - 비교/교환 횟수 적음 → 실전 성능 우수
 * - 정렬된 배열, 중복 많은 배열에도 효과적
 *
 * ✅ 사용 예
 * - 성능이 중요한 일반 정렬기 (예: Python/C++ 라이브러리)
 */

public class Main {

    public static void main(String[] args) {
        int[] arr = new int[]{4, 5, 1, 3, 2, 8, 6, 7};

        quickSortRecursive(arr, 0, arr.length-1);

        System.out.println(Arrays.toString(arr));
    }

    // 재귀적으로 정렬
    private static void quickSortRecursive(int[] arr, int left, int right){
        if(left >= right) return; // 기저 조건

        // 분할 기준 : Hoare 방식은 반환값이 pivot 위치가 아니다.
        int p = partition(arr, left, right);

        // 주의: 피벗은 고정되지 않음 → j 기준으로 나누기
        quickSortRecursive(arr, left, p);
        quickSortRecursive(arr, p+1, right);
    }

    /**
     * 🔹 Hoare 분할 함수
     *
     * - 피벗: arr[(left + right) / 2] (가운데 값)
     * - i는 왼쪽부터 → 피벗보다 크거나 같은 값에서 멈춤
     * - j는 오른쪽부터 → 피벗보다 작거나 같은 값에서 멈춤
     * - i < j이면 swap, i ≥ j면 종료하고 j 반환
     */
    private static int partition(int[] arr, int left, int right){
        int pivot = arr[(left + right)/2]; // 가운데 값을 피벗으로 삼는다.
        int i = left-1;
        int j = right+1;

        while(true){
            // 왼쪽부터 피벗보다 크거나 같은 값 찾기
            do {
                i++;
            } while(arr[i] < pivot);

            // 오른쪽부터 피벗보다 작거나 같은 값 찾기
            do {
                j--;
            } while (arr[j] > pivot);

            //포인터가 교차하면 종료
            if(i >= j) return j;

            // 교차 전이면 교환
            swap(arr, i, j);
        }
    }

    // 값 교환
    private static void swap(int[] arr, int a, int b){
        if(a!=b){
            int temp = arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
        }
    }
}