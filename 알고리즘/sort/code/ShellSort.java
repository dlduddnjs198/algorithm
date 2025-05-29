import java.util.*;

/**
 * 셸 정렬 (Shell Sort)
 *
 * ✅ 개요
 * - 삽입 정렬을 확장한 정렬 알고리즘
 * - 일정한 간격(gap)을 두고 떨어진 요소들을 부분 삽입 정렬하고,
 *   점차 간격을 줄여가며 전체를 정렬하는 방식
 * - 간격(gap) = N/2 → N/4 → ... → 1 로 줄어들며 반복
 *
 * ✅ 작동 방식
 * - 초기엔 먼 거리의 요소를 비교하고 정렬 → 전반적인 배열 상태 개선
 * - gap이 줄어들수록 삽입 정렬에 가까워짐 (거의 정렬된 배열 → 효율 ↑)
 * - 마지막 gap이 1일 때, 일반적인 삽입 정렬 수행
 *
 * ✅ 시간 복잡도
 * - 최악: O(N²)
 * - 평균: O(N^1.25) ~ O(N^1.5) (gap 선택에 따라 달라짐)
 * - **Hibbard, Knuth, Sedgewick** 등 다양한 gap 수열 제안됨
 *
 * ✅ 공간 복잡도
 * - O(1) (제자리 정렬, 추가 메모리 없음)
 *
 * ✅ 장점
 * - 삽입 정렬보다 훨씬 빠름
 * - 구현 간단 + 정렬된 정도가 높을수록 빠름
 * - 비교적 안정적 성능을 가짐 (특히 작은 배열에 적합)
 *
 * ✅ 단점
 * - 안정 정렬이 아님 (같은 값의 상대 순서가 바뀔 수 있음)
 * - 최적의 gap 수열 선택이 정해져 있지 않음 (수학적 연구 대상)
 *
 * ✅ 사용 예
 * - 정렬 대상이 작거나 메모리가 제한적일 때 유용
 */

public class Main {

    private static final Random random = new Random();

    private static final int INSERTION_SORT_THRESHOLD = 10; // 삽입 정렬로 전환할 임계값

    public static void main(String[] args) {
        int[] arr = {8, 4, 1, 6, 3, 9, 2, 5, 7};

        shellSort(arr);

        System.out.println(Arrays.toString(arr));
    }

    /**
     * 🔹 셸 정렬 함수
     *
     * - gap을 점차 줄여가며 부분 삽입 정렬 수행
     * - 일반적으로 gap = gap / 2 사용 (Knuth 수열도 자주 사용됨)
     */
    private static void shellSort(int[] arr) {
        int n = arr.length;

        // 초기 간격 설정 (여기선 단순히 n/2, n/4, ..., 1 방식 사용)
        for(int gap = n/2; gap > 0; gap /= 2){

            // 각 부분 배열에 대해 삽입 정렬 수행
            for(int i= gap; i<n;i++){
                int key = arr[i];
                int j = i;

                // gap만큼 떨어진 요소와 비교하며 정렬
                while(j >= gap && arr[j-gap] > key){
                    arr[j] = arr[j-gap];
                    j -= gap;
                }

                arr[j] = key;
            }
        }
    }
}