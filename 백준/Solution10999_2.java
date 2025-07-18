import java.io.*;
import java.util.*;

// 10999 구간 합 구하기 2
public class Main {

    static int N;
    static long[] tree; // 세그먼트 트리: 구간 합을 저장
    static long[] lazy; // Lazy 트리: 아직 반영되지 않은 구간 업데이트 값을 저장

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // N: 원소 개수, M: 구간 업데이트 횟수, K: 구간 합 질의 횟수
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 세그먼트 트리 배열은 4배 크기로 할당 (최악의 경우 대비)
        tree = new long[N * 4];
        lazy = new long[N * 4];

        long[] arr = new long[N+1];
        // 1-based indexing 사용
        for(int i=1;i<=N;i++){
            arr[i] = Long.parseLong(br.readLine());
        }

        // 초기 세그먼트 트리 구성
        build(1, 1, N, arr);

        // 총 M + K번의 명령 처리
        for(int i=0;i<M+K;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());

            if(a==1){
                // 1 b c d : 구간 [b, c]에 d를 더한다.
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                long d = Long.parseLong(st.nextToken());
                update(1, 1, N, b, c, d);
            }else{
                // 2 b c : 구간 [b, c]의 합을 출력한다.
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                sb.append(query(1, 1, N, b, c)).append("\n");
            }
        }

        // 결과 출력
        System.out.println(sb.toString());
    }

    /**
     * 세그먼트 트리 초기 구성 함수
     * @param node 현재 노드 번호
     * @param start 이 노드가 담당하는 구간의 시작
     * @param end 이 노드가 담당하는 구간의 끝
     * @param arr 원본 배열
     */
    private static void build(int node, int start, int end, long[] arr){
        if(start == end){
            // 리프 노드: 실제 원소 값 저장
            tree[node] = arr[start];
            return;
        }

        int mid = (start + end) / 2;
        build(node * 2, start, mid, arr);         // 왼쪽 자식 트리 생성
        build(node * 2 + 1, mid + 1, end, arr);   // 오른쪽 자식 트리 생성

        // 현재 노드 값은 자식 노드들의 합
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    /**
     * 구간 [left, right]에 val를 더하는 함수 (Lazy Propagation 사용)
     */
    private static void update(int node, int start, int end, int left, int right, long val){
        propagate(node, start, end); // 현재 노드의 lazy 값이 있으면 먼저 반영

        if(end < left || start > right) return; // 완전히 벗어난 경우는 무시

        if(start >= left && end <= right){
            // 완전히 포함된 경우: lazy 값만 누적시키고 바로 propagate
            lazy[node] += val;
            propagate(node, start, end);
            return;
        }

        // 일부만 겹치는 경우 → 자식 노드로 분기
        int mid = (start + end) / 2;
        update(node * 2, start, mid, left, right, val);        // 왼쪽 자식 업데이트
        update(node * 2 + 1, mid + 1, end, left, right, val);  // 오른쪽 자식 업데이트

        // 자식 노드들의 합으로 현재 노드 갱신
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    /**
     * Lazy 값이 남아있는 노드의 실제 값을 세그먼트 트리에 반영
     */
    private static void propagate(int node, int start, int end){
        if(lazy[node] != 0){
            // 구간 크기만큼 lazy 값을 현재 노드의 누적합에 더함
            tree[node] += (end - start + 1) * lazy[node];

            if(start != end){
                // 리프 노드가 아닌 경우 → 자식에게 lazy 값 전달
                lazy[node * 2] += lazy[node];
                lazy[node * 2 + 1] += lazy[node];
            }

            // 현재 노드의 lazy는 반영 완료 → 초기화
            lazy[node] = 0;
        }
    }

    /**
     * 구간 [left, right]의 누적합을 구하는 함수
     */
    private static long query(int node, int start, int end, int left, int right) {
        propagate(node, start, end); // lazy 값이 있으면 먼저 반영

        if(end < left || start > right) return 0; // 완전히 벗어남
        if(start >= left && end <= right) return tree[node]; // 완전히 포함된 경우

        // 일부만 겹치는 경우 → 자식 노드 탐색
        int mid = (start + end) / 2;
        long leftSum = query(node * 2, start, mid, left, right);
        long rightSum = query(node * 2 + 1, mid + 1, end, left, right);

        return leftSum + rightSum;
    }
}
// 세그먼트 트리(지연 업데이트 적용)
// 시간복잡도
// rangeAdd(l, r, x)	O(log N)
// rangeSum(l, r)	O(log N)
// 공간복잡도	O(4N)