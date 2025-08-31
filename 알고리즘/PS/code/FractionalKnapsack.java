import java.util.Arrays;
import java.util.Comparator;

/**
 * 💡 [Fractional Knapsack 문제 설명]
 *
 * - 각 물건은 쪼갤 수 있다. 즉, 일부만 선택해서 배낭에 넣을 수 있다.
 * - 물건의 무게와 가치가 주어졌을 때, 가방에 넣을 수 있는 **최대 가치**를 구하는 문제.
 * - 배낭의 최대 무게(W)를 넘지 않도록 주의.
 *
 * [적용 예시]
 * - 리소스를 부분적으로 사용할 수 있는 상황 (예: 기름, 금, 쌀, 액체 등)
 *
 * [접근 방식]
 * - **Greedy (탐욕법)** 사용
 * - 단위 무게당 가치(value/weight)가 높은 것부터 먼저 넣는다.
 * - 가방에 여유가 있다면 전부 넣고, 공간이 부족하면 **쪼개서 일부만 넣는다.**
 *
 * [시간복잡도]
 * - 정렬: O(N log N)
 * - 선택: O(N)
 * → 전체 시간복잡도: **O(N log N)**
 */
public class FractionalKnapsack {

    // 물건 클래스 정의
    static class Item {
        int weight;
        int value;

        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        // 단위 무게당 가치 계산 (value / weight)
        public double getRatio() {
            return (double) value / weight;
        }
    }

    public static void main(String[] args) {
        int N = 3;         // 물건 개수
        int W = 50;        // 가방 최대 무게

        // 물건 리스트 초기화
        Item[] items = new Item[N];
        items[0] = new Item(10, 60);  // 무게 10, 가치 60
        items[1] = new Item(20, 100); // 무게 20, 가치 100
        items[2] = new Item(30, 120); // 무게 30, 가치 120

        // 💡 단위 무게당 가치 기준으로 내림차순 정렬
        Arrays.sort(items, Comparator.comparingDouble(Item::getRatio).reversed());

        double totalValue = 0.0; // 결과: 최대 가치
        int remainingCapacity = W; // 남은 가방 용량

        for (Item item : items) {
            if (remainingCapacity == 0) break; // 가방이 꽉 차면 종료

            if (item.weight <= remainingCapacity) {
                // 💡 전부 담을 수 있다면 전부 담기
                totalValue += item.value;
                remainingCapacity -= item.weight;
            } else {
                // 💡 가방에 다 안 들어가면 일부만 담기 (분할)
                double fraction = (double) remainingCapacity / item.weight;
                totalValue += item.value * fraction;
                remainingCapacity = 0; // 가방 꽉 참
            }
        }

        System.out.printf("🧳 가방에 담을 수 있는 최대 가치: %.2f\n", totalValue);
    }
}
