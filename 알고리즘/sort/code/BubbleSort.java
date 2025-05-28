import java.io.*;
import java.util.*;

/**
 * 🧮 버블 정렬 (Bubble Sort) - 자바 구현
 *
 * 📌 개념:
 * - 인접한 두 원소를 비교하여 잘못된 순서일 경우 스왑(swap)
 * - 한 번의 회전(Pass)이 끝나면 가장 큰 값이 맨 뒤로 이동
 * - 매 회전마다 정렬 대상 구간이 하나씩 줄어듦
 * - 총 n-1회의 회전으로 정렬을 완료 (n: 배열 길이)
 *
 * 📌 시간복잡도:
 * - 최선: O(n)      → 이미 정렬된 경우 (개선형에서만 가능)
 * - 평균: O(n²)
 * - 최악: O(n²)
 *
 * 📌 공간복잡도:
 * - O(1) → 제자리 정렬 (in-place)
 **/
public class Main {

    public static void main(String[] args){
        int[] arr = new int[]{4, 5, 1, 3, 2, 8, 6, 7};
        bubbleSort(arr);
//        bubbleSort2(arr);
//        bubbleSort3(arr);

        System.out.println(Arrays.toString(arr));
    }

    // 🟦 [1] 기본 버블 정렬
    //
    // - 모든 인접한 원소 쌍을 비교하여 큰 값을 뒤로 보냄
    // - 각 패스(회전)마다 가장 큰 값이 맨 뒤로 가게 됨
    // - 총 n-1 회전, 매 회전마다 n-1-i 만큼 비교
    //
    // 시간복잡도: 항상 O(n²), 정렬 여부 상관 없음
    private static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) { // 총 n-1회 반복 (회전 횟수)
            for (int j = 0; j < n - 1 - i; j++) { // 아직 정렬 안된 부분만 비교
                if (arr[j] > arr[j + 1]) {
                    // 두 인접한 원소의 크기 순서가 잘못되었으면 스왑
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // 🟦 [2] 개선형 버블 정렬 (swapped 플래그 사용)
    //
    // - 스왑이 한 번도 발생하지 않은 경우 → 정렬 완료로 판단 → 조기 종료
    // - 최선 시간복잡도 O(n), 평균/최악은 O(n²)
    // - "이미 정렬된 배열"에 특히 효과적
    public static void bubbleSort2(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false; // 이번 회전에서 스왑이 발생했는지 확인

            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    // 크기 순서가 틀리면 스왑 수행
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    swapped = true; // 스왑 발생 기록
                }
            }

            if (!swapped) break; // 스왑이 없으면 → 이미 정렬됨 → 종료
        }
    }

    // 🟦 [3] 고급 개선형 버블 정렬 (마지막 교환 위치 추적)
    //
    // - 각 회전에서 마지막으로 스왑이 발생한 위치를 추적하여
    //   다음 회전에서 그 이후 구간은 비교하지 않음
    // - 비교 범위를 자동으로 줄여주므로 효율적
    // - "부분 정렬된 배열"에도 빠르게 동작
    public static void bubbleSort3(int[] arr) {
        int n = arr.length;
        int newN;

        do {
            newN = 0; // 이번 회전에서 마지막으로 스왑이 발생한 인덱스

            for (int i = 1; i < n; i++) {
                if (arr[i - 1] > arr[i]) {
                    // 스왑 필요 → 값 교환
                    int temp = arr[i];
                    arr[i] = arr[i - 1];
                    arr[i - 1] = temp;

                    newN = i; // 마지막으로 스왑된 위치 기록
                }
            }

            n = newN; // 다음 회전에서는 여기까지만 비교

        } while (newN > 0); // 스왑이 한 번이라도 발생했다면 계속 반복
    }


}