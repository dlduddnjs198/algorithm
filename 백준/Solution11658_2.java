import java.io.*;
import java.util.*;

// 11658 구간 합 구하기 3
public class Main {
    static int N, M;                 // N: 배열 크기, M: 쿼리 개수
    static int[][] arr;             // 원본 배열
    static long[][] tree;           // 2차원 세그먼트 트리 (트리의 각 노드는 x,y 구간의 합 저장)

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // N: 배열 크기, M: 쿼리 수
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 배열은 1-indexed 로 사용
        arr = new int[N + 1][N + 1];
        tree = new long[4 * N][4 * N]; // 세그먼트 트리는 4배 크기로 잡는다 (최악의 경우 대비)

        // 초기 입력 값을 받아서 트리에 삽입
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                updateX(1, 1, N, i, j, arr[i][j]);  // 트리에 (i,j) 위치에 값 삽입
            }
        }

        // 쿼리 처리
        for (int q = 0; q < M; q++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());

            if (w == 0) {
                // 0 x y c: (x,y)의 값을 c로 바꾼다
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int diff = c - arr[x][y];           // 차이 계산
                arr[x][y] = c;                      // 원본 배열 갱신
                updateX(1, 1, N, x, y, diff);       // 트리에 변화량만큼 반영
            } else {
                // 1 x1 y1 x2 y2: (x1,y1) ~ (x2,y2) 사각형 영역의 합을 구한다
                int x1 = Integer.parseInt(st.nextToken());
                int y1 = Integer.parseInt(st.nextToken());
                int x2 = Integer.parseInt(st.nextToken());
                int y2 = Integer.parseInt(st.nextToken());
                long result = queryX(1, 1, N, x1, y1, x2, y2); // 질의
                sb.append(result).append("\n");
            }
        }

        System.out.println(sb);
    }

    // x축 트리에서 update 진행
    // nodeX: 현재 노드 번호, lx~rx: 현재 노드가 담당하는 x 범위
    // x,y: 갱신할 좌표, val: 변화량
    private static void updateX(int nodeX, int lx, int rx, int x, int y, int val){
        if(x < lx || x > rx) return; // x 범위 밖이면 리턴

        updateY(nodeX, 1, 1, N, y, val); // 현재 x에 해당하는 y축 세그먼트 트리 업데이트

        // 자식 노드들에도 반영
        if(lx != rx){
            int mx = (lx + rx) / 2;
            updateX(nodeX * 2, lx, mx, x, y, val);
            updateX(nodeX * 2 + 1, mx + 1, rx, x, y, val);
        }
    }

    // y축 트리에서 update 진행
    // nodeX: 해당 x축 노드 번호 (x를 고정한 상태에서 y축 갱신)
    // nodeY: 현재 y축 노드 번호, ly~ry: 현재 노드가 담당하는 y 범위
    private static void updateY(int nodeX, int nodeY, int ly, int ry, int y, int val){
        if(y < ly || y > ry) return; // y 범위 밖이면 리턴

        tree[nodeX][nodeY] += val; // 변화량만큼 트리 노드에 반영

        if(ly != ry){
            int my = (ly + ry) / 2;
            updateY(nodeX, nodeY * 2, ly, my, y, val);         // 왼쪽 y자식
            updateY(nodeX, nodeY * 2 + 1, my + 1, ry, y, val); // 오른쪽 y자식
        }
    }

    // x축 기준 쿼리 (x1~x2 범위 내 y축 구간을 질의)
    private static long queryX(int nodeX, int lx, int rx, int x1, int y1, int x2, int y2){
        if(rx < x1 || lx > x2) return 0; // 범위 밖

        if(x1 <= lx && rx <= x2){
            return queryY(nodeX, 1, 1, N, y1, y2); // y축 트리 질의
        }

        int mx = (lx + rx) / 2;
        long left = queryX(nodeX * 2, lx, mx, x1, y1, x2, y2);
        long right = queryX(nodeX * 2 + 1, mx + 1, rx, x1, y1, x2, y2);
        return left + right;
    }

    // y축 기준 쿼리 (y1~y2 구간의 합을 구한다)
    private static long queryY(int nodeX, int nodeY, int ly, int ry, int y1, int y2) {
        if(ry < y1 || ly > y2) return 0; // 범위 밖

        if(y1 <= ly && ry <= y2){
            return tree[nodeX][nodeY]; // 이 노드의 합이 완전히 포함됨
        }

        int my = (ly + ry) / 2;
        long left = queryY(nodeX, nodeY * 2, ly, my, y1, y2);
        long right = queryY(nodeX, nodeY * 2 + 1, my + 1, ry, y1, y2);
        return left + right;
    }
}
// 2차원 세그먼트 트리
// 시간복잡도
// update(x, y, val)	O(log N * log N)
// query(x1, y1, x2, y2)	O(log N * log N)
// 공간복잡도 	O(N²) (트리 포함)