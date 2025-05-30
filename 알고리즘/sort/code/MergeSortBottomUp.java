import java.util.*;

/**
 * 병합 정렬 (Merge Sort) - Bottom-Up 방식 (반복문 기반)
 *
 * ✅ 개요
 * - Top-Down과 달리 재귀 없이 반복문으로 구현하는 병합 정렬
 * - 작은 크기(1)부터 시작해 2, 4, 8... 단위로 병합하며 정렬
 * - 동일한 시간복잡도를 가지면서 스택 사용을 피할 수 있음
 *
 * ✅ 시간 복잡도: O(N log N)
 * ✅ 공간 복잡도: O(N)
 *
 * ✅ 장점: 스택 오버플로우 걱정 없이 안정적인 정렬
 * ✅ 단점: 구현이 약간 더 복잡하고, 매우 작은 배열에서는 비효율적일 수 있음
 *
 * ✅ 사용 예: 재귀 호출이 제한된 환경, 대규모 정렬 작업
 */
public class Main {

    public static void main(String[] args) {
        int[] arr = new int[]{4, 5, 1, 3, 2, 8, 6, 7};

        mergeSortBottomUp(arr);

        System.out.println(Arrays.toString(arr));
    }

    private static void mergeSortBottomUp(int[] arr) {
        int n = arr.length;
        int[] temp = new int[n]; // 병합에 사용할 임시 배열

        // size: 병합할 하위 배열의 크기 (1->2->4->8 ...)
        for(int size = 1 ; size < n ; size *= 2){

            // left: 현재 병합할 두 블록 중 왼쪽 블록의 시작 인덱스
            for(int left = 0; left < n - size; left+=2 * size){
                int mid = left + size - 1; // 왼쪽 블록의 끝 인덱스
                int right = Math.min(left + 2 * size - 1, n - 1); // 오른쪽 블록의 끝 인덱스(넘지 않도록)

                // 두 정렬된 블록을 병합
                merge(arr, temp, left, mid, right);
            }
        }
    }

    // // 정렬된 두 부분 (left ~ mid, mid+1 ~ right)을 하나로 병합하는 함수
    private static void merge(int[] arr, int[] temp, int left, int mid, int right){
        int i = left; // 왼쪽 배열 시작 인덱스
        int j = mid + 1; // 오른쪽 배열 시작 인덱스
        int k = left; // 임시 배열에 값을 채워나갈 인덱스

        // 왼쪽과 오른쪽 배열의 값을 비교하면서 더 작은 값을 temp에 복사
        while(i <= mid && j <= right){
            if(arr[i] <= arr[j]){
                temp[k++] = arr[i++];
            }else{
                temp[k++] = arr[j++];
            }
        }

        // 왼쪽 배열에 남은 값이 있으면 모두 복사
        while(i <= mid){
            temp[k++] = arr[i++];
        }

        // 오른쪽 배열에 남은 값이 있으면 모두 복사
        while(j <= right) {
            temp[k++] = arr[j++];
        }

        // temp 배열에 병합된 결과를 원래 배열 arr에 복사
        for(int t=left; t <= right; t++){
            arr[t] = temp[t];
        }
    }

}