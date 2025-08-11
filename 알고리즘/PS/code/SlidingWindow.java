/**
 * ✅ SlidingWindow.java
 *
 * ✅ 개념 설명:
 * 슬라이딩 윈도우(Sliding Window)는 배열이나 문자열에서
 * 일정 크기의 구간(윈도우)을 유지하면서 효율적으로 문제를 푸는 기법입니다.
 *
 * 주로 **연속된 부분 구간의 최댓값/최솟값/합** 등을 구할 때 사용합니다.
 *
 * ✅ 시간 복잡도:
 * - O(N) — 배열 전체를 한 번만 순회하면서 구간을 갱신
 *
 * ✅ 자주 등장하는 문제 유형:
 * - 연속된 K개의 수의 최대합 / 최소합
 * - 특정 조건을 만족하는 최장/최단 부분 수열
 * - 문자열 내 연속된 패턴 분석 등
 */

import java.util.*;

public class SlidingWindow {

    /**
     * ✅ 고정 길이 K에 대해, 연속된 부분 배열의 최대 합을 구하는 메서드
     * 예) arr = {2, 1, 5, 1, 3, 2}, k = 3 → 최대합은 9 (5+1+3)
     */
    public static int maxSumOfKLengthWindow(int[] arr, int k) {
        int n = arr.length;

        // 예외 처리: 배열 길이보다 k가 크면 불가능
        if (n < k) {
            System.out.println("Window size k is larger than array length.");
            return -1;
        }

        int maxSum = 0;
        int windowSum = 0;

        // 초기 윈도우 설정: 처음 k개 합
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }
        maxSum = windowSum; // 초기값으로 설정

        // 오른쪽으로 윈도우 슬라이드
        for (int i = k; i < n; i++) {
            // 윈도우에서 왼쪽 값 제거, 오른쪽 값 추가
            windowSum = windowSum - arr[i - k] + arr[i];
            maxSum = Math.max(maxSum, windowSum); // 최대값 갱신
        }

        return maxSum;
    }

    /**
     * ✅ 메인 함수: 테스트용 코드
     */
    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 1, 3, 2};
        int k = 3;

        int result = maxSumOfKLengthWindow(arr, k);
        System.out.println("Maximum sum of length " + k + " window: " + result);
    }
}
