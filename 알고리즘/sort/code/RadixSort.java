import java.util.*;

/**
 * 📊 기수 정렬 (Radix Sort) - LSD 방식 (가장 낮은 자리수부터 정렬)
 *
 * ✅ 개요
 * - 자릿수(1의 자리 → 10의 자리 → 100의 자리...)를 기준으로
 *   **Counting Sort를 여러 번 반복**하여 정렬하는 비교 없는 정렬
 * - 정수에 특화되며, **범위가 크더라도** 정렬이 가능하다 (단, 자릿수 적어야 효율적)
 *
 * ✅ LSD (Least Significant Digit) 방식
 * - 가장 오른쪽 자리(1의 자리)부터 왼쪽으로 이동하며 정렬
 * - Counting Sort와 달리 **자릿수 기준으로 안정 정렬 반복**한다
 *
 * ✅ 사용 예
 * - **양수만 존재하며 범위가 매우 큰 정수 배열** (예: 최대 1억)
 * - **비교 연산이 비효율적인 상황**에서 효율적 (예: 자릿수 기반 정렬)
 * - **문자열, 주민번호, 전화번호처럼 길이가 고정된 숫자 문자열** 정렬
 * - **정수형 ID, 우편번호, 날짜(숫자형태) 정렬** 등에도 효과적
 * - 실제로 **데이터베이스 인덱싱, 디지털 이미지 처리, 통계처리 등**에서 사용됨
 *
 * ✅ 시간 복잡도
 * - O(D × (N + K))
 *   (D: 최대 자릿수, N: 원소 개수, K: 기수 = 보통 10)
 *
 * ✅ 공간 복잡도
 * - O(N + K)
 *
 * ✅ 조건
 * - 정수 데이터만 사용 가능
 * - **음수는 처리 불가 → 따로 분리해야 함**
 *
 * ✅ 장점
 * - 정렬 범위가 넓을수록 Counting Sort보다 효율적
 * - **안정 정렬** (동일 값의 순서 보장)
 *
 * ✅ 단점
 * - 자릿수가 너무 크면 느림
 * - 음수 미지원 (양수/음수 나눠서 정렬해야)
 */

public class Main {

    public static void main(String[] args) {
        int[] arr = {8, 4, 1, 6, 3, 9, 2, 5, 7};

        radixSort(arr);

        System.out.println(Arrays.toString(arr));
    }

    private static void radixSort(int[] arr) {
        if(arr.length == 0) return;

        // 1. 정렬 대상 중 최댓값을 구함 → 이 수의 자릿수만큼 반복 예정
        int max = Integer.MIN_VALUE;
        for(int i = 0 ; i<arr.length;i++){
            max = Math.max(max, arr[i]);
        }

        // 2. 자릿수별로 Counting Sort를 수행 (1, 10, 100, ...)
        for(int exp = 1;max/exp>0;exp*=10){
            countingSortByDigit(arr, exp);
        }
    }

    /**
     * 🧮 자릿수 기준 Counting Sort (안정 정렬)
     *
     * @param arr 정렬할 배열
     * @param exp 자릿수 (1 → 10 → 100 ...)
     */
    private static void countingSortByDigit(int[] arr, int exp){
        int n = arr.length;
        int[] output = new int[n]; // 정렬 결과 저장
        int[] count = new int[10]; // 0~9 자릿수 등장 횟수

        // 1. count 배열에 자릿수 값 세기
        for(int num : arr){
            int digit = (num / exp) % 10;
            count[digit]++;
        }

        // 2. 누적합
        for(int i=1;i<n;i++){
            count[i] += count[i-1];
        }

        // 3. 안정 정렬을 위해 뒤에서부터 넣기
        for(int i=n-1;i>=0;i--){
            int num = (arr[i]/exp) % 10;
            int pos = count[num]-1; // 실제 인덱스
            output[pos] = num;
            count[num]--; // 해당 자릿수 인덱스 감소
        }

        // 4. 결과 복사
        System.arraycopy(output, 0, arr, 0, n);

    }


}