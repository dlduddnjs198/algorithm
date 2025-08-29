import java.util.*;

// 펜윅 트리(Fenwick Tree, Binary Indexed Tree(BIT))
// 시간복잡도
// 단일 값 업데이트 - O(log N)
// 누적합 계산 - O(log N)
// 구간합 계산 - O(log N)

// 핵심은 i & -i인데 이건
// i에서 가장 낮은(가장 오른쪽에 있는) 1비트만 남기는 연산이다.
public class Main {

    static class FenwickTree {
        // 트리 배열: 내부적으로 트리 역할을 함 (1-based 인덱스 사용)
        private int[] tree;
        private int size;

        // 생성자: 원소 개수 n에 대해 트리 배열을 초기화
        public FenwickTree(int n) {
            this.size = n;
            this.tree = new int[n + 1]; // 인덱스 1부터 시작하기 위해 n+1 크기로 설정
        }


        /**
         * ✅ 원소를 업데이트하는 함수 (add 방식)
         * A[i] += value 를 수행할 때 사용함
         *
         * @param i      업데이트할 원소의 위치 (1-based index)
         * @param value  더할 값
         */
        public void update(int i, int value) {
            // i += (i & -i)를 반복하면서 관련된 구간에 모두 반영
            while (i <= size) {
                tree[i] += value;

                // i의 마지막 1비트를 더해서 다음 노드로 이동
                // i가 포함된 구간을 커버하는 상위 노드(더 큰 범위를 담당하는 노드)로 이동하는 것이다.
                i += (i & -i);
            }
        }

        /**
         * ✅ 누적합을 계산하는 함수 (prefix sum)
         * A[1] ~ A[i]까지의 합을 구함
         *
         * @param i   구간의 끝 인덱스
         * @return    누적합 A[1] + A[2] + ... + A[i]
         */
        public int query(int i) {
            int sum = 0;

            // i -= (i & -i)를 반복하면서 관련된 구간의 합을 모두 더함
            while (i > 0) {
                sum += tree[i];

                // i의 마지막 1비트를 빼서 하위 노드(더 작은 범위를 담당하는 노드)로 이동
                i -= (i & -i);
            }

            return sum;
        }

        /**
         * ✅ 구간 합 구하기 (A[left] ~ A[right])
         *
         * @param left   구간 시작 (1-based index)
         * @param right  구간 끝
         * @return       A[left] + A[left+1] + ... + A[right]
         */
        public int rangeQuery(int left, int right) {
            return query(right) - query(left - 1);
        }

        /**
         * ✅ 디버깅용: 내부 tree 배열 출력
         */
        public void printTree() {
            for (int i = 1; i <= size; i++) {
                System.out.print(tree[i] + " ");
            }
            System.out.println();
        }
    }

    // 예시 사용
    public static void main(String[] args) {
        int[] arr = {0, 3, 2, -1, 6, 5, 4, -3, 3, 7, 2, 3}; // 인덱스 0은 무시
        int n = arr.length - 1;

        FenwickTree ft = new FenwickTree(n);

        // 트리에 arr의 값을 초기 반영
        for (int i = 1; i <= n; i++) {
            ft.update(i, arr[i]);
        }

        System.out.println("A[1] ~ A[5] 합: " + ft.query(5));       // 3 + 2 + (-1) + 6 + 5 = 15
        System.out.println("A[3] ~ A[7] 합: " + ft.rangeQuery(3, 7)); // (-1) + 6 + 5 + 4 + (-3) = 11
    }

}
