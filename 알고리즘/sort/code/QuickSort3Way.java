import java.util.*;

/**
 * 🟨 퀵 정렬 (Quick Sort) - 3-Way Partitioning
 *
 * ✅ 개요
 * - 피벗을 기준으로 3개 구간으로 나누는 퀵 정렬 방식
 *   ① pivot보다 작은 영역
 *   ② pivot과 같은 영역
 *   ③ pivot보다 큰 영역
 *
 * ✅ 특징
 * - 중복이 많은 배열에서 매우 효율적
 * - 기존 퀵 정렬보다 분할 성능이 더 안정적
 *
 * ✅ 시간 복잡도
 * - 평균: O(N log N)
 * - 최악: O(N²)
 *
 * ✅ 공간 복잡도
 * - O(log N) (재귀 호출 스택)
 *
 * ✅ 장점
 * - 중복 데이터에 매우 효과적
 * - 파티션 효율성이 좋아 비교 횟수 감소
 *
 * ❌ 단점
 * - 코드 복잡도가 다소 증가
 *
 * ✅ 사용 예
 * - 중복 값이 많은 데이터 정렬
 */

public class Main {

    private static final Random random = new Random();

    public static void main(String[] args) {
        int[] arr = new int[]{4, 5, 1, 3, 2, 8, 6, 7};

        quickSort3Way(arr, 0, arr.length-1);

        System.out.println(Arrays.toString(arr));
    }

    /**
     * 🔹 3-Way QuickSort
     *
     * - 피벗을 기준으로 < / = / > 3개 영역으로 나눈다
     */
    private static void quickSort3Way(int[] arr, int left, int right){
        if(left >= right) return;

        // 랜덤 피벗 선택
        int pivotIndex = left + random.nextInt(right - left + 1);
        int pivot = arr[pivotIndex];
        swap(arr, left, pivotIndex);

        int leftArea = left; // < pivot
        int i = left + 1; // 탐색 인덱스
        int rightArea = right; // > pivot

        while(i <= rightArea){
            if(arr[i] < pivot){
                swap(arr, leftArea++, i++); // 현재 값이 피벗보다 작으면 왼쪽 영역으로 보내기
            }else if(arr[i] > pivot){
                swap(arr, i, rightArea--); // 현재 값이 피벗보다 크면 오른쪽 영역으로 보내기
            }else{
                i++; // pivot과 같으면 그대로
            }
        }


        // 분할된 세 구간 각각 설정
        quickSort3Way(arr, left, leftArea-1);
        quickSort3Way(arr, rightArea+1, right);

    }

    private static void swap(int[] arr, int a, int b) {
        if (a != b) {
            int temp = arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
        }
    }
}