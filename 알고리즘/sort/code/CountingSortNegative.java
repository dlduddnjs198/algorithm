import java.util.*;

/**
 * 🧮 카운팅 정렬 (Counting Sort) - 음수 포함 확장형
 *
 * ✅ 개요
 * - 정수 배열에 음수와 양수가 섞여 있어도 동작하도록 확장한 Counting Sort
 * - 기본 Counting Sort는 인덱스를 그대로 사용할 수 있는 양수만 처리 가능
 * - 따라서, 음수도 배열 인덱스로 매핑할 수 있도록 "offset" 기법을 사용함
 *
 * ✅ 시간복잡도: O(N + K) (N: 데이터 수, K: 최댓값-최솟값+1 범위)
 * ✅ 공간복잡도: O(N + K)
 *
 * ✅ 특징
 * - 안정 정렬 (같은 값의 상대적 순서를 유지함)
 * - 값의 범위가 작고 정수형일 때 최적의 성능
 * - 값이 퍼져 있거나 실수/문자열에는 부적합
 */

public class Main {

    public static void main(String[] args) {
        int[] arr = {8, 4, 1, 6, 3, 9, 2, 5, 7};

        countingSortNegative(arr);

        System.out.println(Arrays.toString(arr));
    }

    private static void countingSortNegative(int[] arr) {
        if(arr.length == 0) return;

        // ✅ 1. 배열에서 최솟값과 최댓값을 찾아서 범위(Range)를 구함
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int num : arr){
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        // 예: arr = [-7, -5, -2, -2, 0, 1, 3, 4, 5]
        // → min = -7, max = 5 → range = 5 - (-7) + 1 = 13
        int range = max - min + 1;

        // ✅ 2. 각 숫자의 등장 횟수를 세는 count 배열 생성
        // count[i]는 (i + min)이라는 값을 얼마나 봤는지를 저장
        int[] count = new int[range];
        for(int num : arr){
            count[num - min]++; // offset 보정: 예를 들어 -2 → count[5]
        }

        // ✅ 3. 누적합 배열 만들기
        // → count[i]는 해당 값이 정렬 배열에서 어디 위치에 들어가는지를 결정함
        for(int i = 1;i<range;i++){
            count[i] += count[i-1]; // 예: count[5] = 4 → -2는 정렬된 배열에서 인덱스 3에 들어가야 함
        }

        // ✅ 4. 안정 정렬을 위한 output 배열 생성
        int[] output = new int[arr.length];

        // ✅ 5. 원본 배열을 뒤에서부터 순회하면서 정렬 위치에 삽입
        for(int i = arr.length-1; i>=0;i--){
            int num = arr[i]; // 원소 값
            int pos = count[num - min] - 1; // 정렬된 위치 : 누적합 - 1
            output[pos] = num; // output 배열의 정확한 위치에 삽입
            count[num - min]--; // 해당 값 다음번 위치를 위해 감소
        }

        // 6. output 내용 arr에 옮기기
    }


}