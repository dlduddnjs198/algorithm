import java.util.*;

/**
 * 병합 정렬 (Merge Sort) + 이진 삽입 정렬 최적화
 *
 * ✅ 개요
 * - 작은 범위에서는 삽입 정렬이 Merge Sort보다 빠르기 때문에,
 *   일정 구간 이하일 경우 Insertion Sort로 정렬하고,
 *   그 이후 Bottom-Up 방식으로 병합 정렬을 진행함
 * - 실전에서는 Timsort가 이 아이디어를 기반으로 훨씬 더 복잡한 최적화를 수행함
 *
 * ✅ 시간 복잡도:
 * - 평균: O(N log N)
 * - 최선: O(N) (이미 거의 정렬된 경우)
 * - 최악: O(N log N)
 *
 * ✅ 장점:
 * - 실제 데이터에서는 일반 Merge Sort보다 빠름
 * - 작은 배열 구간에서는 삽입 정렬이 빠르기 때문에 하이브리드 전략이 효과적
 *
 * ✅ 사용 예:
 * - 정렬된 데이터가 포함된 실전 상황
 * - Timsort의 동작 원리 이해용 구현
 */
public class Main {

    public static void main(String[] args) {
        int[] arr = new int[]{4, 5, 1, 3, 2, 8, 6, 7};

        mergeSortWithBinaryInsertion(arr);

        System.out.println(Arrays.toString(arr));
    }

    private static void mergeSortWithBinaryInsertion(int[] arr) {
        int n = arr.length;
        int threshold = 16; // 이 크기 이하는 삽입 정렬로 진행

        // 🔹 Step 1: threshold 단위로 잘라서 각 구간을 Insertion Sort로 정렬
        for(int i=0 ; i < n ; i+=threshold){
            binaryInsertionSort(arr, i, Math.min(i+threshold-1, n-1));
        }

        // 🔹 Step 2: Bottom-Up 방식 Merge Sort로 병합
        int[] temp = new int[n];
        for(int size=threshold;size<n;size*=2){
            for(int left=0;left<n-size;left+=2*size){
                int mid = left + size - 1;
                int right = Math.min(left + 2*size-1,n-1);
                merge(arr, temp, left, mid, right);
            }
        }
    }

    /**
     * 🔹 Binary Insertion Sort
     *
     * - 삽입할 위치를 이진 탐색으로 찾아 효율적으로 정렬
     * - 이동 비용은 동일하지만, 비교 횟수가 줄어들어 성능 향상
     */
    private static void binaryInsertionSort(int[] arr, int start, int end){
        for(int i=start+1;i<=end;i++){
            int key = arr[i];

            // 이진 탐색으로 key가 삽입될 위치 찾기
            int insertPos = binarySearch(arr, start, i-1, key);

            // [pos ~ i-1]까지 한 칸씩 뒤로 밀기
            for(int j = i-1; j >= insertPos;j--){
                arr[j+1] =arr[j];
            }

            // key 삽입
            arr[insertPos] = key;
        }
    }

    /**
     * 🔹 Binary Search Position
     *
     * - arr[left ~ right] 구간에서 key가 들어갈 첫 위치 반환
     * - 중복 값이 있다면 중복 앞쪽에 삽입 (안정성 유지)
     */
    private static int binarySearch(int[] arr, int left, int right, int key){
        while(left <= right){
            int mid = (left + right) /2;

            if(arr[mid] <= key) {
                left = mid + 1; // key보다 작거나 같으면 오른쪽으로
            }else {
                right = mid - 1; // key보다 작을 위치를 더 왼쪽에서 찾음
            }

        }
        return left; // key를 삽입할 위치
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