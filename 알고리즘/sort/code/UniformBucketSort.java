import java.util.*;

/**
 * Uniform Bucket Sort (균일 분포 버킷 정렬)
 *
 * ✅ 개요
 * - 데이터를 [0.0, 1.0)과 같이 균일하게 분포된 수라고 가정하고
 *   일정 구간마다 "버킷"을 나누고, 각 버킷 내부를 정렬한 후 합친다.
 *
 * ✅ 동작 방식
 * 1. 입력 배열의 길이에 비례하는 개수의 버킷 생성
 * 2. 각 값을 적절한 버킷에 분배
 * 3. 각 버킷 내 데이터를 정렬 (보통 삽입 정렬)
 * 4. 버킷들을 앞에서부터 차례로 합쳐서 정렬된 결과 생성
 *
 * ✅ 시간 복잡도
 * - 평균: O(N + N²/K + K), 최적일 경우 O(N)
 * - 최악: O(N²) (모든 값이 한 버킷에 몰리는 경우)
 *
 * ✅ 사용 예
 * - 값이 실수이고, **[0.0, 1.0)** 또는 범위를 정규화할 수 있을 때
 * - 시험 점수, 소수 확률, 데이터가 비교적 균일 분포라고 확신이 있을 때
 */

public class Main {

    public static void main(String[] args) {
        // 정규화된 소수 배열 (0.0 이상 1.0 미만)
        double[] arr = {0.78, 0.17, 0.39, 0.26, 0.72, 0.94, 0.21, 0.12, 0.23, 0.68};

        bucketSort(arr);

        System.out.println(Arrays.toString(arr));
    }

    /**
     * 🔹 Uniform Bucket Sort 본체
     */
    public static void bucketSort(double[] arr) {
        int n = arr.length;
        if (n <= 0) return;

        // 1. 버킷 개수는 입력 크기만큼 생성
        List<List<Double>> buckets = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            buckets.add(new ArrayList<>());
        }

        // 2. 각 요소를 적절한 버킷에 분배
        for (double value : arr) {
            int idx = (int)(value * n);  // [0, 1) 범위 기준으로 인덱스 계산
            buckets.get(idx).add(value);
        }

        // 3. 각 버킷 내부 정렬 (삽입 정렬 사용)
        for (List<Double> bucket : buckets) {
            insertionSort(bucket);
        }

        // 4. 버킷을 차례로 합치기
        int index = 0;
        for (List<Double> bucket : buckets) {
            for (double value : bucket) {
                arr[index++] = value;
            }
        }
    }

    /**
     * 🔸 삽입 정렬: 버킷 내부 정렬에 적합 (데이터 양이 적음)
     */
    private static void insertionSort(List<Double> list) {
        for (int i = 1; i < list.size(); i++) {
            double key = list.get(i);
            int j = i - 1;
            // 뒤에서부터 앞쪽으로 이동하면서 key보다 큰 값 이동
            while (j >= 0 && list.get(j) > key) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }
}
