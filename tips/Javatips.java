/**
 * ✅ javatips.java
 *
 * ✅ 목적:
 * - 코딩테스트 대비 Java 문법과 컬렉션 활용 팁을 종합 정리한 파일입니다.
 * - 주요 클래스 정의, 정렬 방법, 자료구조 선언 및 유용한 메서드들을 예시로 제공합니다.
 */

import java.util.*;

public class Javatips {
 
    /**
     * ✅ 1. static class 만들고 Comparable 구현
     * - 우선순위 정렬이나 정렬 기준이 필요한 객체 정의 시 유용함
     * - Comparable<T>을 implements 하고 compareTo 메서드를 오버라이딩
     */
    static class Person implements Comparable<Person> {
        String name;
        int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        // 나이 오름차순 정렬
        public int compareTo(Person other) {
            return this.age - other.age;
        }
    }

    /**
     * ✅ 2. 다양한 정렬 방법 예시
     */
    public static void sortingExamples() {
        // 기본 타입 정렬
        int[] arr = {5, 2, 8, 1, 3};
        Arrays.sort(arr); // 오름차순
        System.out.println("Arrays.sort(): " + Arrays.toString(arr));

        // 내림차순 정렬 (Integer 배열로 박싱 필요)
        Integer[] arrDesc = {5, 2, 8, 1, 3};
        Arrays.sort(arrDesc, Collections.reverseOrder());
        System.out.println("내림차순: " + Arrays.toString(arrDesc));

        // List 정렬
        List<String> words = new ArrayList<>(List.of("banana", "apple", "cherry"));
        words.sort(Comparator.naturalOrder()); // 오름차순
        words.sort(Comparator.reverseOrder()); // 내림차순

        // 객체 정렬
        List<Person> people = new ArrayList<>(List.of(
                new Person("Alice", 30),
                new Person("Bob", 25),
                new Person("Charlie", 35)
        ));

        // 나이 오름차순 (compareTo 사용)
        Collections.sort(people);

        // 다른 기준으로 정렬 (람다식 사용)
        people.sort(Comparator.comparing(person -> person.name)); // 이름 오름차순
        people.sort(Comparator.comparingInt(person -> person.age)); // 나이 오름차순
        people.sort(Comparator.comparingInt((Person p) -> p.age).reversed()); // 나이 내림차순
    }

    /**
     * ✅ 3. 주요 Java 자료구조 선언 예시와 사용처
     */
    public static void containerExamples() {
        // ✅ List: 순서 있는 데이터, 중복 허용
        List<Integer> list = new ArrayList<>();
        list.add(1); list.add(2);

        // ✅ Set: 중복 없는 데이터 집합 (순서 없음)
        Set<String> set = new HashSet<>();
        set.add("apple"); set.add("banana");

        // ✅ Map: 키-값 쌍 저장 (빠른 조회)
        Map<String, Integer> map = new HashMap<>();
        map.put("Alice", 90);
        map.put("Bob", 85);

        // ✅ Deque: 양쪽에서 삽입/삭제 가능 (Stack/Queue 대체)
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(1); // 앞에 삽입
        deque.addLast(2);  // 뒤에 삽입
        deque.removeFirst();

        // ✅ PriorityQueue: 우선순위 큐 (기본은 오름차순)
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(3); pq.add(1); pq.add(2); // poll() 시 1 → 2 → 3

        // 내림차순 우선순위 큐
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    }

    /**
     * ✅ 4. 자주 쓰는 Collections 함수들 예시
     */
    public static void usefulFunctions() {
        // ✅ putIfAbsent: 키가 없을 때만 put
        Map<String, Integer> map = new HashMap<>();
        map.putIfAbsent("apple", 1); // apple이 없으면 추가
        map.putIfAbsent("apple", 100); // 이미 있으므로 무시

        // ✅ comparingInt: 정렬 기준 지정 시 사용
        List<Person> people = new ArrayList<>();
        people.add(new Person("Zoe", 40));
        people.add(new Person("Alex", 20));

        // List - contains, removeIf
        boolean hasApple = list.contains(5);
        list.removeIf(x -> x % 2 == 0); // 짝수 제거

        // 나이 기준 오름차순
        people.sort(Comparator.comparingInt(p -> p.age));

        // ✅ getOrDefault: 값이 없을 경우 기본값 제공
        int score = map.getOrDefault("banana", 0);

        // ✅ containsKey / containsValue
        boolean hasApple = map.containsKey("apple");
        boolean has100 = map.containsValue(100);

        // ✅ removeIf: 조건에 맞는 요소 제거
        people.removeIf(p -> p.age > 30); // 나이가 30 넘는 사람 제거
    }

    public static void main(String[] args) {
        sortingExamples();
        containerExamples();
        usefulFunctions();
    }
}
