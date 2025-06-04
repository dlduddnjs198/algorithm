import java.util.*;

/**
 * 병합 정렬 (Merge Sort) - In-Place 방식 (추가 메모리 없이 병합)
 *
 * ✅ 개요
 * - 일반 병합 정렬은 임시 배열을 사용해서 병합하는데,
 *   이 구현은 추가 메모리 없이 배열 내부에서 직접 병합을 수행
 * - 공간 복잡도를 O(N) → O(1)로 줄이는 대신, 시간 복잡도는 악화됨
 *
 * ✅ 병합 방식
 * - 두 정렬된 부분 [left ~ mid], [mid+1 ~ right]을 병합할 때
 * - arr[i] > arr[j]인 경우 arr[j]를 앞에 끼워넣고,
 *   arr[i ~ j-1]를 한 칸씩 뒤로 밀어서 정렬 유지
 *
 * ✅ 시간 복잡도: O(N log² N) (밀어내기 때문에 log N 번마다 O(N))
 * ✅ 공간 복잡도: O(1)
 *
 * ✅ 장점
 * - 메모리를 매우 아껴야 하는 환경에서 유용함
 *
 * ✅ 단점
 * - 성능이 매우 떨어짐 (실전에서는 거의 안 씀)
 * - 구현이 복잡하고, 캐시 효율도 나쁨
 */
public class Main {

    public static void main(String[] args) {
        int[] arr = new int[]{4, 5, 1, 3, 2, 8, 6, 7};

        mergeSortInPlace(arr, 0, arr.length-1);

        System.out.println(Arrays.toString(arr));
    }

    private static void mergeSortInPlace(int[] arr, int left, int right){
        if(left >= right) return; // 더 이상 쪼갤 수 없을 경우 리턴

        int mid = (left + right) / 2;

        // 왼쪽, 오른쪽 각각 정렬
        mergeSortInPlace(arr, left, mid);
        mergeSortInPlace(arr, mid + 1, right);

        // 추가 공간 없이 직접 병합
        mergeInPlace(arr, left, mid, right);
    }

    /**
     * 🔹 In-Place Merge
     *
     * - arr[left ~ mid]와 arr[mid+1 ~ right]는 이미 정렬되어 있다고 가정
     * - 두 포인터 i, j를 이용하여 병합 수행
     * - arr[i] > arr[j]이면, arr[j]를 앞으로 끼워넣고,
     *   중간 값들을 한 칸씩 뒤로 밀어 공간 확보
     */
    private static void mergeInPlace(int[] arr, int left, int mid, int right){
        int i = left;
        int j = mid + 1;

        // 두 포인터가 각 서브배열을 순회
        while(i <= mid && j <= right) {
            if(arr[i] <= arr[j]){
                // 정렬 순서가 올바르면 그냥 i 증가
                i++;
            }else {
                // arr[j]가 더 작으면 이 값을 arr[i] 앞에 넣어야 함
                int value = arr[j];
                int index = j;

                // arr[i ~ j-1]까지 오른쪽으로 한 칸씩 이동
                while(index > i){
                    arr[index] = arr[index - 1];
                    index--;
                }

                // arr[i] 자리에 arr[j] 삽입
                arr[i] = value;

                // 포인터 갱신
                i++;
                mid++; // mid도 늘려야 정렬 경계를 보존(InPlace라 그렇다.)
                j++;
            }
        }
    }



}