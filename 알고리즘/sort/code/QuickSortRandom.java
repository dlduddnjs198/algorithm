import java.util.*;

/**
 * 🟦 퀵 정렬 (Quick Sort) - Hoare 분할 방식 + 랜덤 피벗 선택
 *
 * ✅ 개요
 * - Hoare 분할 방식에 피벗을 **무작위로 선택**하여 편향 분할 방지
 * - 정렬된 배열, 같은 값이 많은 배열에서도 **평균 시간 복잡도를 안정화**
 *
 * ✅ 특징
 * - 피벗을 (left ~ right) 중 랜덤으로 선택
 * - 선택된 피벗을 가운데 인덱스로 옮기고, Hoare 방식으로 분할
 *
 * ✅ 시간 복잡도
 * - 평균: O(N log N)
 * - 최악: O(N²) → 무작위 피벗으로 발생 가능성 낮춤
 *
 * ✅ 공간 복잡도
 * - O(log N) (재귀 호출 스택)
 *
 * ✅ 장점
 * - 특정 입력에 의한 성능 저하 방지
 * - 빠르고 안정적 (실전에서 자주 쓰임)
 *
 * ❌ 단점
 * - 난수 생성 비용 (무시 가능 수준)
 * - 재현 가능한 결과가 필요할 경우 주의
 *
 * ✅ 사용 예
 * - 대규모 무작위 정렬
 * - 정렬된 배열, 중복 많은 입력에서도 안정적인 성능 보장
 */

public class Main {

    private static final Random random = new Random();

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
        int pivotIndex = left + random.nextInt(right-left+1);
        int pivot = arr[pivotIndex]; // 가운데 값을 피벗으로 삼는다.

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