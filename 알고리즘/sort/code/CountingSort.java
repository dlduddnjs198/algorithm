import java.util.*;

/**
 * 🧮 카운팅 정렬 (Counting Sort)
 *
 * ✅ 개요
 * - 정수 기반의 **비교하지 않는 정렬 알고리즘**
 * - "원소의 크기 범위(K)"가 작을 때 빠르게 정렬할 수 있음
 * - 배열 인덱스를 직접 활용해 등장 횟수 기반으로 정렬
 *
 * ✅ 핵심 로직
 * 1. 주어진 배열의 최대값을 찾고, 그 크기만큼의 count 배열 생성
 * 2. count[num]에 각 값의 등장 횟수 저장
 * 3. count 배열을 누적합 형태로 변환 → 값을 정렬할 위치를 알 수 있게 됨
 * 4. 원본 배열을 역순으로 순회하면서 각 원소를 **output 배열의 올바른 위치에 삽입**
 * 5. output 배열을 원본 배열로 복사
 *
 * ✅ 시간 복잡도
 * - O(N + K) (N: 배열 길이, K: 값의 범위)
 *
 * ✅ 공간 복잡도
 * - O(N + K)
 *
 * ✅ 장점
 * - 매우 빠름 (조건만 맞으면)
 * - 안정 정렬 가능 (같은 값의 순서 유지 가능)
 * 정수형 데이터 + 범위 작음 + 데이터 양 많음 + 안정 정렬 필요 → 최고의 선택
 *
 * ✅ 단점
 * - 정수형 데이터에만 사용 가능
 * - 값의 범위(K)가 크면 메모리 낭비 심함
 */

public class Main {

    public static void main(String[] args) {
        int[] arr = {8, 4, 1, 6, 3, 9, 2, 5, 7};

        countingSort(arr);

        System.out.println(Arrays.toString(arr));
    }

    private static void countingSort(int[] arr) {
        if(arr.length == 0) return;

        // 1. 최댓값 찾기(값의 범위 계산용)
        int max = Integer.MIN_VALUE;
        for(int i=0;i<arr.length;i++){
            max = Math.max(max, arr[i]);
        }

        // 2. 각 값의 빈도 수를 저장할 count 배열 생성
        int[] count = new int[max+1];
        for(int num : arr) {
            count[num]++;
        }

        // 3. 누적합을 통해 각 값이 들어갈 "최종 위치" 결정
        for(int i=1;i<=max;i++){
            count[i] += count[i-1];
        }

        // 4. 출력요 임시 배열 생성 (안정 정렬을 위해 뒤에서부터 순회)
        int[] output = new int[arr.length];
        for(int i=arr.length-1;i>=0;i--){
            int num = arr[i];
            // count[num]: num 이하의 숫자가 몇 개 있는지 알려줌 (누적합)
            // 예를 들어, count[3] = 5라면,
            // → 1, 2, 3까지 총 5개가 있다는 뜻임
            // 즉, "숫자 3은 정렬된 배열에서 인덱스 4(=5-1)에 들어가야 한다" 는 의미이다.
            int pos = count[num] - 1;
            output[pos] = num;
            count[num]--;
            // 이 과정에서 count 배열은 더 이상 누적합의 의미를 유지하지 않는다.
        }


    }


}