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

    // 힙 정렬 함수
    private static void heapSort(int[] arr) {
        int n = arr.length;

        // 1. 최대 힙 만들기 (Bottom-up 방식) -> 루트가 항상 가장 큰 값이 됨
        // 부모 i의 자식 인덱스(0-based index 기준):
        // - 왼쪽 자식: 2 * i + 1
        // - 오른쪽 자식: 2 * i + 2
        // 리프 노드는 자식이 없으니 heapify할 필요 없다.
        // i >= n/2 인 인덱스들은 리프 노드가 된다.
        // 따라서 heapify 시작 지점은 n/2 - 1부터다.(이게 가장 마지막 부모 노드이기 때문)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // 2. 루트(최대값)를 끝으로 이동 + 힙 크기 줄이며 재정렬
        // 힙 정렬은 이렇게 동작함:
        // (1). 가장 큰 값을 루트(0번)**에 위치시킴
        // (2). 그걸 **배열 맨 뒤(n-1)**로 보냄
        // (3). 남은 부분만 다시 힙 구조로 만듦 (크기 줄여가며) 그럼 정렬이 된다.
        for(int i=n-1;i>0;i--){
            swap(arr, 0, i); // 최대값을 끝으로 보내기
            heapify(arr, i, 0); // 줄어든 힙에 대해 다시 최대 힙 구성
        }

    }

    /**
     * 🔹 heapify(): 힙 속성을 유지하도록 재정렬
     * - i: 현재 부모 노드의 인덱스
     * - n: 현재 힙의 크기
     * - 자식 노드와 비교하여 가장 큰 값과 swap하고 재귀 호출
     */
    private static void heapify(int[] arr, int n, int i){
        int largest = i; // 루트
        int left = 2 * i + 1; // 왼쪽 자식
        int right = 2 * i + 2; // 오른쪽 자식

        // 왼쪽 자식이 루트보다 크면 largest 업데이트
        if(left < n && arr[left] > arr[largest]){
            largest = left;
        }

        // 오른쪽 자식이 루트보다 크면 largest 업데이트
        if(right < n && arr[right] > arr[largest]){
            largest = right;
        }

        // 가장 큰 값이 루트가 아니면 교환 + 재귀 호출
        if(largest != i){
            swap(arr, i, largest);
            // 전체 트리를 만드는 게 아니라, 특정 서브트리 안에서 문제 생긴 걸 해결하는 보정 작업이다.
            // 바뀐 위치에 대해 다시 heapify
            heapify(arr, n, largest);
        }
    }

    // 값 교환 함수
    private static void swap(int[] arr, int a, int b) {
        if (a != b) {
            int temp = arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
        }
    }
}