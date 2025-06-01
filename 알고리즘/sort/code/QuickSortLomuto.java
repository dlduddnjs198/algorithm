import java.util.*;

/**
 * 퀵 정렬 (Quick Sort) - Lomuto 분할 방식 (기본형)
 *
 * ✅ 개요
 * - 대표적인 분할 정복(Divide and Conquer) 기반 정렬 알고리즘
 * - 배열에서 피벗을 하나 정하고, 그 피벗보다 작은 값은 왼쪽, 큰 값은 오른쪽으로 보내서 분할
 * - 그 뒤 왼쪽과 오른쪽 구간을 재귀적으로 다시 정렬
 *
 * ✅ Lomuto 분할 방식
 * - 피벗을 **오른쪽 끝**으로 선택(현재 처리중인 공간의 오른쪽 끝)
 * - i는 작거나 같은 값이 쌓일 구간의 끝, j는 현재 비교 중인 인덱스
 * - arr[j]가 피벗보다 작거나 같으면 i를 증가시키고 arr[i]와 arr[j]를 교환
 * - 마지막엔 피벗과 arr[i+1]을 교환하여 피벗의 최종 위치 결정
 *
 * ✅ 시간 복잡도
 * - 평균: O(N log N)
 * - 최악: O(N²) (정렬된 배열에 대해)
 *
 * ✅ 공간 복잡도
 * - O(log N) (재귀 호출 스택)
 *
 * ✅ 특징
 * - 불안정 정렬 (같은 값의 순서 보장되지 않음)
 * - 비교 기반 정렬 중 평균적으로 가장 빠름
 */

public class Main {

    public static void main(String[] args) {
        int[] arr = new int[]{4, 5, 1, 3, 2, 8, 6, 7};

        quickSortRecursive(arr, 0, arr.length-1);

        System.out.println(Arrays.toString(arr));
    }

    // 재귀적으로 왼쪽, 오른쪽 구간을 정렬
    private static void quickSortRecursive(int[] arr, int left, int right) {
        if(left >= right) return; // 기저 조건 : 하나 이하의 요소는 정렬이 필요 없다.

        // 분할: 피벗 기준으로 배열을 나누고, 피벗의 최종 위치 반환
        int pivot = partition(arr, left, right);

        // 피벗 기준으로 왼쪽, 오른쪽 재귀 정렬(피벗 자체는 제외)
        quickSortRecursive(arr, left, pivot - 1);
        quickSortRecursive(arr, pivot + 1, right);

    }

    /**
     * 🔹 Lomuto 분할 알고리즘
     *
     * - 피벗: arr[right] (현재 처리중인 공간의 가장 오른쪽 값)
     * - i: 피벗보다 작거나 같은 값을 쌓을 마지막 위치
     * - j: 현재 비교 중인 위치
     * - arr[j] <= pivot일 경우 i를 증가시키고, arr[i]와 arr[j]를 교환
     * - 마지막에 피벗과 arr[i+1]을 교환하여 피벗의 위치를 확정
     */
    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right]; // 피벗 선택: 가장 오른쪽 값
        int i = left - 1; // i는 작거나 같은 값을 넣을 자리의 직전 위치

        for(int j=left; j<right; j++){
            if(arr[j] <= pivot){
                i++; // 작거나 같은 값 발견하면 i 증가
                swap(arr, i, j); // i에 해당하는 위치에 해당 값을 옮긴다.
            }
        }

        // 최종적으로 피벗을 i+1 위치에 배치
        swap(arr, i+1, right);
        return i+1; // 피벗의 최종 위치 반환
    }

    private static void swap(int[] arr, int a, int b){
        if(a!=b){
            int temp = arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
        }
    }
}