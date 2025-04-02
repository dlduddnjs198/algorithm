import java.io.*;
import java.util.*;

// 통계학
// 산술평균 : N개의 수들의 합을 N으로 나눈 값
// 중앙값 : N개의 수들을 증가하는 순서로 나열했을 경우 그 중앙에 위치하는 값
// 최빈값 : N개의 수들 중 가장 많이 나타나는 값
// 범위 : N개의 수들 중 최댓값과 최솟값의 차이
// 정렬 안써도 됨
// O(NlogN) -> O(N)까지 가능
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        int[] arr = new int[N];
        int[] cnt = new int[8001];  // -4000 ~ 4000 → +4000 offset
        int sum = 0;

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            sum += arr[i];
            cnt[arr[i] + 4000]++;
        }

        Arrays.sort(arr);

        // 산술평균
        sb.append(Math.round((double) sum / N)).append("\n");

        // 중앙값
        sb.append(arr[N / 2]).append("\n");

        // 최빈값
        int maxFreq = 0;
        List<Integer> modeList = new ArrayList<>();
        for (int i = 0; i < cnt.length; i++) {
            if (cnt[i] > 0) {
                if (cnt[i] > maxFreq) {
                    maxFreq = cnt[i];
                    modeList.clear();
                    modeList.add(i - 4000);
                } else if (cnt[i] == maxFreq) {
                    modeList.add(i - 4000);
                }
            }
        }

        // 여러 개면 두 번째로 작은 값 출력
        Collections.sort(modeList);
        sb.append(modeList.size() >= 2 ? modeList.get(1) : modeList.get(0)).append("\n");

        // 범위
        sb.append(arr[N - 1] - arr[0]).append("\n");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}

