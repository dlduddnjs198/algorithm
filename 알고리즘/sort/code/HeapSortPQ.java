import java.util.*;

/**
 * 🏗️ 힙 정렬 (Heap Sort)
 *
 * ✅ 개요
 * - 완전 이진 트리 기반의 **최대 힙(Max Heap)**을 이용한 정렬 알고리즘
 * - 힙의 특성을 활용해 가장 큰 값을 루트에 위치시키고 정렬
 *
 * ✅ 핵심 로직
 * 1. 주어진 배열을 **최대 힙(Max Heap)**으로 만든다
 * 2. 힙의 루트(최대값)를 배열 끝으로 보내고 힙 크기를 줄인다
 * 3. 힙 속성을 유지하도록 **heapify**(재정렬) 한다
 * 4. 위 과정을 반복해 정렬 완료
 *
 * ✅ 시간 복잡도
 * - 최악/최선/평균: O(N log N)
 *
 * ✅ 공간 복잡도
 * - O(1) (제자리 정렬 - in-place)
 *
 * ✅ 장점
 * - 최악의 경우에도 O(N log N)
 * - 재귀 사용 없이 구현 가능 (비재귀적 힙 구성)
 *
 * ✅ 단점
 * - 퀵 정렬보다 일반적으로 느리다 (캐시 친화성 낮음)
 * - 안정 정렬이 아니다 (같은 값의 상대 순서가 바뀔 수 있음)
 */

public class Main {

    public static void main(String[] args) {
        int[] arr = {8, 4, 1, 6, 3, 9, 2, 5, 7};

        heapSort(arr);

        System.out.println(Arrays.toString(arr));
    }

    // 힙 정렬 함수 (PriorityQueue 활용)
    private static void heapSort(int[] arr) {
        // 1. 우선순위 큐 생성 (Min Heap)
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // 2. 배열의 모든 요소를 힙에 삽입
        for(int num : arr) {
            pq.offer(num); // 삽입 시 자동으로 힙 구조 유지됨
        }

        // 3. 힙에서 하나씩 꺼내서 배열에 다시 채움 → 오름차순 정렬 완료
        for(int i=0;i<arr.length;i++){
            arr[i] = pq.poll(); // 가장 작은 값부터 꺼냄
        }
    }
}