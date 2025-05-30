import java.util.*;

/**
 * 병합 정렬 (Merge Sort) - Top-Down 방식 (재귀 호출 기반)
 *
 * ✅ 특징
 * - 분할 정복(Divide and Conquer) 방식
 * - 배열을 반으로 나눈 뒤, 정렬하며 병합
 * - 안정 정렬 (Stable Sort)
 *
 * ✅ 시간 복잡도: O(N log N) (항상 일정)
 * ✅ 공간 복잡도: O(N) (임시 배열 필요)
 *
 * ✅ 장점: 데이터 분포와 무관하게 안정적 성능
 * ✅ 단점: 추가 메모리 사용
 *
 * ✅ 사용 예: 대규모 데이터 정렬, 안정 정렬이 필요한 경우
 */
public class Main {

    public static void main(String[] args) {
        int[] arr = new int[]{4, 5, 1, 3, 2, 8, 6, 7};

        mergeSort(arr);

        System.out.println(Arrays.toString(arr));
    }


    // 외부에서 호출하는 메서드: 병합 정렬을 시작하는 진입점
    private static void mergeSort(int[] arr){
        // 정렬에 사용할 임시 배열을 한 번만 생성 (재귀 호출마다 새로 만들지 않도록)
        int[] temp = new int[arr.length];

        // 재귀적으로 분할 및 정렬 시작 (왼쪽 인덱스, 오른쪽 인덱스)
        mergeSortRecursive(arr, temp, 0, arr.length-1);
    }

    // 재귀적으로 배열을 반씩 분할하고 병합 정렬하는 함수
    private static void mergeSortRecursive(int[] arr, int[] temp, int left, int right){
        // 배열이 하나의 원소만 남으면 더 이상 분할이 불가능하므로 종료
        if(left >= right) return;

        // 배열을 두 부분으로 나누기 위한 중간 인덱스
        int mid = (left + right) / 2;

        // 왼쪽 절반을 정렬
        mergeSortRecursive(arr, temp, left, mid);
        // 오른쪽 절반을 정렬
        mergeSortRecursive(arr, temp, mid+1, right);

        // 정렬된 두 배열을 병합
        merge(arr, temp, left, mid, right);
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