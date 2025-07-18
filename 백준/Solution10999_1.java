import java.io.*;
import java.util.*;

// 10999 구간 합 구하기 2
public class Main {

    static int N; // 원소의 개수 (1-based 인덱싱)
    static long[] tree1; // 구간 덧셈 처리를 위한 첫 번째 펜윅 트리 (기울기 저장용)
    static long[] tree2; // 누적합 보정을 위한 두 번째 펜윅 트리 (절편 저장용)

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 첫 줄 입력: N = 배열 크기, M = 업데이트 쿼리 개수, K = 합 구하기 쿼리 개수
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // BIT는 1-based이므로 크기 +2로 선언
        tree1 = new long[N + 2];
        tree2 = new long[N + 2];

        // 원본 배열 입력받고 초기 세팅 (1-based)
        long[] origin = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            origin[i] = Long.parseLong(br.readLine());
            // 각 값들을 rangeAdd로 [i, i] 구간에 더함 → 초기 세팅
            rangeAdd(i, i, origin[i]);
        }

        // M+K개의 쿼리 수행
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());

            if (a == 1) {
                // 구간 업데이트 쿼리 (b부터 c까지 d를 더함)
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                long d = Long.parseLong(st.nextToken());
                rangeAdd(b, c, d);
            } else {
                // 구간 합 출력 쿼리
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                sb.append(rangeSum(b, c)).append("\n");
            }
        }

        // 결과 출력
        System.out.println(sb.toString());
    }

    // ✅ 구간 [left, right]에 num을 더함
    private static void rangeAdd(int left, int right, long num) {
        // tree1은 기울기 처리용: [left ~ right] 구간에 기울기 +num
        update(tree1, left, num);         // 시작점부터 기울기 증가
        update(tree1, right + 1, -num);   // 끝 다음부터 기울기 감소 → 그 뒤는 영향 없게

        // tree2는 누적합 보정용 절편 처리: query(tree1, i) * i - query(tree2, i) 구조 보정
        update(tree2, left, num * (left - 1));
        update(tree2, right + 1, -num * right);
    }

    // ✅ 구간 [left, right]의 합을 반환
    private static long rangeSum(int left, int right) {
        // 구간 합은 [1 ~ right]까지 누적합 - [1 ~ left-1]까지 누적합
        return prefixSum(right) - prefixSum(left - 1);
    }

    // ✅ [1, i]까지의 누적합을 반환
    static long prefixSum(int i) {
        // 핵심 공식:
        // prefixSum(i) = tree1의 누적합 * i - tree2의 누적합
        // 이 수식은 아래와 같은 수학적 유도에서 나옴:
        // S[i] = a₁ + a₂ + ... + aᵢ
        //      = (기울기 * 개수) - 절편 보정값
        return query(tree1, i) * i - query(tree2, i);
    }

    // ✅ BIT에 값 업데이트 (i 위치에 x를 더함)
    private static void update(long[] tree, int i, long x) {
        while (i < tree.length) {
            tree[i] += x;
            // i += i & -i : 펜윅 트리에서 다음 노드로 이동
            i += (i & -i);
        }
    }

    // ✅ BIT로 i까지의 누적합을 구함
    private static long query(long[] tree, int i) {
        long sum = 0;
        while (i > 0) {
            sum += tree[i];
            // i -= i & -i : 펜윅 트리에서 부모 노드로 이동
            i -= (i & -i);
        }
        return sum;
    }
}
// 펜윅트리 2개(누적합) -> 세그먼트 트리도 가능
// 시간복잡도
// 초기화	O(N log N)
// 구간 업데이트	O(log N)
// 구간 합 쿼리	O(log N)
//
// → 전체 연산은 최대 O((M+K) * log N) → 빠르게 동작합니다.