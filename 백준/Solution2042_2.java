import java.io.*;
import java.util.*;

// 2042 구간 합 구하기
public class Main {

    static long[] tree; // 세그먼트 트리 배열
    static long[] arr;  // 실제 값 저장 배열
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken()); // 수의 개수
        int M = Integer.parseInt(st.nextToken()); // 변경 횟수
        int K = Integer.parseInt(st.nextToken()); // 구간 합 질의 횟수

        arr = new long[N + 1];

        // 세그먼트 트리의 크기 = 2^(높이+1)
        int height = (int)Math.ceil(Math.log(N) / Math.log(2));
        int treeSize = 1 << (height + 1); // 2^(height + 1)
        tree = new long[treeSize];

        // 원본 배열 입력
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        // 트리 초기화
        init(1, 1, N);

        // 명령 처리
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());

            int cmd = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (cmd == 1) {
                // b번째 수를 c로 변경
                update(1, 1, N, b, c - arr[b]); // 차이만큼 업데이트
                arr[b] = c;
            } else if (cmd == 2) {
                // b~c 구간 합 출력
                sb.append(sum(1, 1, N, b, (int)c)).append("\n");
            }
        }

        System.out.println(sb.toString());
    }

    /**
     * 세그먼트 트리 초기화 함수
     * @param node: 현재 트리 노드 번호
     * @param start: 현재 구간의 시작
     * @param end: 현재 구간의 끝
     */
    static long init(int node, int start, int end) {
        if (start == end) {
            return tree[node] = arr[start];
        }

        int mid = (start + end) / 2;
        return tree[node] = init(node * 2, start, mid)
                + init(node * 2 + 1, mid + 1, end);
    }

    /**
     * 특정 위치의 값을 갱신하는 함수
     * @param node: 현재 트리 노드 번호
     * @param start: 현재 구간 시작
     * @param end: 현재 구간 끝
     * @param idx: 변경할 인덱스
     * @param diff: 변경된 값과 기존 값의 차이
     */
    static void update(int node, int start, int end, int idx, long diff) {
        // idx가 범위 밖이면 return
        if (idx < start || idx > end) return;

        // 현재 노드에 diff 반영
        tree[node] += diff;

        if (start == end) return; // 리프 노드라면 종료

        int mid = (start + end) / 2;
        update(node * 2, start, mid, idx, diff);         // 왼쪽 자식
        update(node * 2 + 1, mid + 1, end, idx, diff);   // 오른쪽 자식
    }

    /**
     * 구간 합을 구하는 함수
     * @param node: 현재 트리 노드 번호
     * @param start: 현재 구간 시작
     * @param end: 현재 구간 끝
     * @param left: 질의 구간 시작
     * @param right: 질의 구간 끝
     * @return 해당 구간의 합
     */
    static long sum(int node, int start, int end, int left, int right) {
        // 구간이 전혀 겹치지 않는 경우
        if (right < start || left > end) return 0;

        // 구간이 완전히 포함되는 경우
        if (left <= start && end <= right) return tree[node];

        // 일부 겹치는 경우
        int mid = (start + end) / 2;
        return sum(node * 2, start, mid, left, right)
                + sum(node * 2 + 1, mid + 1, end, left, right);
    }
}
// 세그먼트 트리(누적합) -> 펜윅 트리도 가능
// 시간복잡도
// 초기화 (init)	O(N)
// 갱신 (update)	O(log N)
// 질의 (sum)	O(log N)