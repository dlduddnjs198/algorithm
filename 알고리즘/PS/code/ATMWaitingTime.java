/**
 * ATMWaitingTime.java
 * ------------------------------------------
 * ✅ 문제 설명:
 * - ATM 앞에 줄 서 있는 사람들의 인출 시간이 주어졌을 때,
 *   각 사람이 기다린 시간의 총합이 최소가 되도록 줄을 세우는 문제입니다.
 * - 즉, 각 사람이 기다리는 시간의 총합을 최소화해야 합니다.
 *
 * ✅ 핵심 아이디어:
 * - 정렬이 핵심입니다. 인출 시간이 짧은 사람을 먼저 처리하면
 *   뒤에 있는 사람들의 대기 시간을 줄일 수 있습니다.
 * - 따라서 **작은 인출 시간 순으로 정렬**한 뒤, **누적합을 계산**합니다.
 *
 * ✅ 시간복잡도:
 * - 정렬: O(N log N)
 * - 누적합 계산: O(N)
 * - 전체: **O(N log N)**
 *
 * ✅ 입력 예:
 *   5
 *   3 1 4 3 2
 * ✅ 출력 예:
 *   32
 *
 * ✅ 설명:
 *   정렬된 순서: 1 2 3 3 4
 *   각 사람의 대기 시간: 1, (1+2), (1+2+3), ...
 *   총합 = 1 + 3 + 6 + 9 + 13 = 32
 */

import java.util.*;

public class ATMWaitingTime {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 사람 수 입력
        int N = sc.nextInt();
        int[] times = new int[N];

        // 각 사람의 인출 시간 입력
        for (int i = 0; i < N; i++) {
            times[i] = sc.nextInt();
        }

        // 🔹 인출 시간이 짧은 사람이 먼저 오도록 오름차순 정렬
        Arrays.sort(times);

        int totalWaitingTime = 0; // 전체 기다린 시간의 총합
        int accumulated = 0;      // 현재까지의 누적 시간

        // 🔹 정렬된 인출 시간 순으로 순회하면서 누적합 계산
        for (int time : times) {
            accumulated += time;         // 현재 사람의 인출 시간 누적
            totalWaitingTime += accumulated; // 전체 누적 시간에 더함
        }

        // 결과 출력
        System.out.println(totalWaitingTime);
    }
}
