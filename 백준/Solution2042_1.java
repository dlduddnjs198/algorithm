import java.io.*;
import java.util.*;

// 2042 구간 합 구하기
public class Main {

    static long[] tree; // 펜윅 트리 (구간 합을 빠르게 계산하기 위한 트리)
    static long[] arr;  // 실제 원본 배열 (업데이트 시 차이를 계산하기 위해 저장)

    public static void main(String[] args) throws Exception {
        // 입력을 빠르게 받기 위한 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 공백 기준으로 입력 나누기 위한 StringTokenizer
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 출력 효율을 위한 StringBuilder
        StringBuilder sb = new StringBuilder();

        // 수의 개수 N, 수 변경 횟수 M, 구간 합 질의 횟수 K
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        tree = new long[N + 1]; // 인덱스 1부터 사용 (BIT는 1-based index)
        arr = new long[N + 1];  // 원본 배열도 1-based index

        // N개의 숫자를 입력받고 트리에 초기값 반영
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine()); // 실제 값 저장
            update(i, arr[i]); // 트리에 누적 반영
        }

        // M + K번의 명령 처리
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken()); // 명령 유형 (1: 업데이트, 2: 구간 합)
            long b = Long.parseLong(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {
                // a == 1: b번째 수를 c로 변경
                long diff = c - arr[(int) b]; // 기존 값과의 차이 계산
                update((int) b, diff);        // 펜윅 트리에 diff만큼 갱신
                arr[(int) b] = c;             // 원본 배열도 갱신
            } else {
                // a == 2: b번째 수부터 c번째 수까지의 구간 합 계산
                sb.append(rangeQuery((int) b, (int) c)).append("\n");
            }
        }

        // 결과 출력
        System.out.println(sb.toString());
    }

    /**
     * 특정 위치 i에 값 value를 더하는 함수
     * BIT의 핵심 원리인 i += (i & -i)를 사용해 연관된 노드에 모두 반영
     */
    private static void update(int i, long value) {
        while (i < tree.length) {
            tree[i] += value;        // 현재 노드에 값 추가
            i += (i & -i);           // 다음 업데이트할 노드로 이동
        }
    }

    /**
     * 1번부터 i번까지의 누적 합을 구하는 함수
     */
    private static long query(int i) {
        long sum = 0;

        while (i > 0) {
            sum += tree[i];         // 현재 위치의 값 누적
            i -= (i & -i);          // 부모 노드로 이동
        }

        return sum;
    }

    /**
     * [left, right] 구간 합을 계산하는 함수
     * 누적합을 이용하여 query(right) - query(left - 1)로 계산
     */
    private static long rangeQuery(int left, int right) {
        return query(right) - query(left - 1);
    }
}
// 펜윅트리(누적합) -> 세그먼트 트리도 가능
// 시간복잡도
// 초기 트리 구축	O(N log N)
// 업데이트/쿼리	O(log N)
// 총 시간	O((M + K) log N) → ✅ 충분히 빠름 (N ≤ 1,000,000)