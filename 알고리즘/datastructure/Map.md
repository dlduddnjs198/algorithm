# 🗺️ Map 자료구조 정리 (HashMap, LinkedHashMap, TreeMap)

`Map`은 **키-값(key-value) 쌍으로 데이터를 저장**하는 자료구조입니다.  
`Set`이 "값이 있는가?"에 집중한다면, `Map`은 "특정 키에 어떤 값을 대응시킬까?"에 집중합니다.

Java에서 가장 많이 쓰이는 Map 구현체는 다음과 같습니다:

- `HashMap`
- `LinkedHashMap`
- `TreeMap`

---

## 📌 Map 공통 특징

- Key는 중복 불가, Value는 중복 가능
- Key를 통해 Value에 빠르게 접근할 수 있음
### **자주 사용되는 함수**  
- **map.put("apple", 3);** → 키-값 쌍 추가 또는 수정, 이미 존재하면 **값을 덮어씀**
- **map.putIfAbsent("apple", 3);** → 키가 없을 때만 추가. 이미 있으면 무시(기존 값 덮어쓰기 방지)
- **map.get("apple");** → 키에 해당하는 값 반환 (없으면 null)  
- **map.getOrDefault("apple", 0);** → 값이 있으면 반환, 없으면 **기본값(0)** 반환(null 체크 없이 안전하게 사용 가능)
- **map.containsKey("apple");** → 키 존재 여부 확인(값과 상관없이 키 기준) 
- **map.containsValue(3);** → 값 존재 여부 확인(비효율적이라 자주 안씀)
- **map.remove("apple");** → 해당 키-값 쌍 제거, 없으면 아무일도 일어나지 않음
- **map.remove("apple", 3);** → **key와 value가 정확히 일치**할 경우에만 제거 (boolean 반환)
- **map.replace("apple", 3);** → 키가 존재할 경우에만 값을 바꿈 (없으면 무시)
- **map.replace("apple", 3, 4);** → "apple"의 현재 값이 3일 때만 4로 교체
- **map.size();** → 항목 수  
- **map.isEmpty();** → 비었는지 확인  
- **map.clear();** → 모두 제거  
- **map.keySet(), map.values(), map.entrySet()** → 순회용 뷰 제공
### **자주 사용되는 함수(TreeMap 전용)**
- **map.firstKey();** → 가장 작은 key 반환
- **map.lastKey();** → 가장 큰 key 반환
- **map.ceilingKey(5);** → 5 이상 중 가장 작은 key 반환 (lower_bound)
- **map.floorKey(5);** → 5 이하 중 가장 큰 key 반환 (upper_bound 대체용)
- **map.higherKey(5);** → 5 초과 중 가장 작은 key 반환
- **map.lowerKey(5);** → 5 미만 중 가장 큰 key 반환
- **map.subMap(3, 8);** → key가 3 이상 8 미만인 부분 Map 반환
- **map.headMap(5);** → key가 5 미만인 항목들만 포함된 Map 반환
- **map.tailMap(5);** → key가 5 이상인 항목들만 포함된 Map 반환
- **map.descendingKeySet();** → key들을 내림차순 정렬된 Set 형태로 반환
- **map.descendingMap();** → 전체 Map을 내림차순 정렬된 형태로 반환

---

## 1️⃣ HashMap

### ✅ 개요
- 가장 기본적인 Map 구현체
- 내부적으로 **해시 테이블**을 사용
- Key의 순서와 정렬은 **보장되지 않음**

### 🛠️ 시간 복잡도
| 연산 | 평균 시간 | 최악 시간 |
|------|-----------|-----------|
| put/get/remove | O(1) | O(n) |

※ 최악의 경우는 해시 충돌이 많이 발생했을 때

### 📎 사용 예시
- 빈도 수 카운팅 (문자열, 정수 등)
- 빠른 키-값 조회
- 예: `"a"가 몇 번 등장했는가?`

### 💡 예제 코드
```java
Map<String, Integer> map = new HashMap<>();
map.put("apple", 3);
map.put("banana", 2);
map.put("apple", 5); // 기존 값 덮어씀

System.out.println(map.get("apple")); // 5
System.out.println(map.containsKey("banana")); // true
````

---

## 2️⃣ LinkedHashMap

### ✅ 개요

* HashMap의 기능에 **입력 순서 유지 기능**을 추가한 구조
* 내부적으로 해시 테이블 + 이중 연결 리스트 사용
* 순회 시 **입력 순서 또는 접근 순서**를 유지할 수 있음

### 🛠️ 시간 복잡도

| 연산             | 평균 시간 |
| -------------- | ----- |
| put/get/remove | O(1)  |

### 📎 사용 예시

* **순서 보존이 필요한 캐시 구조**
* **LRU Cache** 구현 (accessOrder 설정)

### 💡 예제 코드

```java
Map<String, Integer> map = new LinkedHashMap<>();
map.put("a", 1);
map.put("b", 2);
map.put("c", 3);

System.out.println(map); // {a=1, b=2, c=3}
```

#### 💡 LRU Cache 구현 예시

```java
Map<Integer, Integer> lru = new LinkedHashMap<>(16, 0.75f, true);
lru.put(1, 100);
lru.put(2, 200);
lru.get(1); // 1이 가장 최근에 접근된 키가 됨
```

---

## 3️⃣ TreeMap

### ✅ 개요

* 키를 **자동 정렬**하여 저장하는 Map
* 내부적으로 **이진 탐색 트리 (Red-Black Tree)** 사용
* 기본 정렬은 키의 자연 순서(오름차순) -> 바꿀 수 있다.
* TreeMap에서만 사용할 수 있는 부분 Map을 만드는 함수는 반환값이 본래 TreeMap과 연결되어있다.

### 🛠️ 시간 복잡도

| 연산             | 시간 복잡도   |
| -------------- | -------- |
| put/get/remove | O(log n) |

### 📎 사용 예시

* 정렬된 키 탐색
* **범위 기반 연산** (`floorKey`, `ceilingKey`, `subMap` 등)

### 💡 예제 코드

```java
Map<Integer, String> map = new TreeMap<>();
map.put(3, "C");
map.put(1, "A");
map.put(2, "B");

System.out.println(map); // {1=A, 2=B, 3=C}
System.out.println(((TreeMap<Integer, String>) map).ceilingKey(2)); // 2
System.out.println(((TreeMap<Integer, String>) map).floorKey(2));   // 2
```

---

## 🧠 정리 비교

| 자료구조            | 내부 구조       | 순서 보장     | 자동 정렬     | 탐색 속도    | 특이점              |
| --------------- | ----------- | --------- | --------- | -------- | ---------------- |
| `HashMap`       | 해시 테이블      | ❌ 없음      | ❌ 없음      | O(1) 평균  | 가장 빠른 일반적인 Map   |
| `LinkedHashMap` | 해시 + 연결 리스트 | ✅ 입력/접근 순 | ❌ 없음      | O(1) 평균  | 순서 보존, LRU 구현 가능 |
| `TreeMap`       | 이진 탐색 트리    | ✅ 키 정렬 순  | ✅ 키 자동 정렬 | O(log n) | 범위 탐색, 정렬 탐색 특화  |

---

## 🧾 결론

* **정렬 필요 없음, 빠른 탐색** → `HashMap`
* **순서 유지가 필요하거나 캐시 구현** → `LinkedHashMap`
* **정렬된 키 탐색, 범위 기반 탐색 필요** → `TreeMap`

---

> 💡 `Map`은 코딩 테스트에서 매우 자주 쓰이며,
> 특히 `HashMap`은 빈도수 세기, 인덱스 저장, 쌍 정보 저장 등에서 필수입니다.
> `TreeMap`은 정렬 기반 탐색이 필요할 때 이분 탐색 대안으로 등장합니다.
