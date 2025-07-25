## 📘 문자열 탐색 알고리즘 정리

---

## 🔹 1. KMP (Knuth-Morris-Pratt)

### ✅ 개요

- KMP 알고리즘은 **텍스트 문자열(T) 안에서 패턴 문자열(P)이 어디에 등장하는지** 찾기 위한 알고리즘입니다. 
- 문자열을 **앞에서부터 한 글자씩 비교**하면서 일치하지 않을 때, **이미 비교한 정보를 재활용해서 다시 처음부터 비교하지 않도록 최적화된 방식**입니다. 
- 예를 들어, "abababca"라는 텍스트에서 "abca"를 찾을 때, 단순 비교는 겹치는 부분도 처음부터 다시 보지만, KMP는 그걸 피합니다.

### 🧠 핵심 아이디어

1. 부분 일치 테이블(PI 배열, failure function)
- 이 테이블은 패턴 P에서 i까지의 접두사와 접미사가 얼마나 일치하는지를 저장합니다.
- **예: 패턴 "abab"**

| i (인덱스) | P\[0..i] | 접두사들       | 접미사들       | 공통 접두사=접미사 | pi\[i] |
| ------- | -------- | ---------- | ---------- | ---------- | ------ |
| 0       | a        | -          | -          | 없음         | 0      |
| 1       | ab       | a          | b          | 없음         | 0      | 
| 2       | aba      | a, ab      | ba, a      | a          | 1      |
| 3       | abab     | a, ab, aba | bab, ab, b | ab         | 2      |
→ pi = [0, 0, 1, 2]<br>
**📎 정리된 의미:**
- pi[3] = 2는 무슨 뜻이냐면?
  - P[0..3]인 "abab"의 가장 긴 접두사 겸 접미사가 "ab"이고
  - 그 길이가 2이기 때문에 pi[3] = 2라는 것임.


2. 불일치 발생 시 점프 전략
- 일반적인 문자열 탐색은 불일치 발생 시 다시 텍스트를 한 칸 옮겨 처음부터 비교합니다. 
- KMP는 이미 비교한 패턴의 정보를 활용해 중복 비교를 건너뜁니다. 
- **예시:<br>**

```text
텍스트 T : a b a b a b c a
인덱스   : 0 1 2 3 4 5 6 7
패턴 P  : a b a b c
pi 배열 : 0 0 1 2 0
```
**비교 흐름**

| T 인덱스 | P 인덱스 | 비교 내용  | 결과 | 행동                           |
| ----- | ----- | ------ | -- | ---------------------------- |
| 0     | 0     | a == a | ✅  | 둘 다 +1                       |
| 1     | 1     | b == b | ✅  | 둘 다 +1                       |
| 2     | 2     | a == a | ✅  | 둘 다 +1                       |
| 3     | 3     | b == b | ✅  | 둘 다 +1                       |
| 4     | 4     | a ≠ c  | ❌  | pi\[3] = 2 → P 인덱스 2부터 다시    |
| 4     | 2     | a == a | ✅  | 둘 다 +1                       |
| 5     | 3     | b == b | ✅  | 둘 다 +1                       |
| 6     | 4     | c == c | ✅  | 패턴 끝 → 매치 발견 (T에서 시작 위치 = 2) |


→ pi[3] = 2이므로 불일치 발생 시, 패턴의 2번째 인덱스부터 비교 재개.

### ⏱ 시간 복잡도

* **전처리 (부분 일치 테이블)**: O(m)
* **탐색**: O(n)
* → 전체 O(n + m)

** n은 전체 텍스트(T)의 길이 — 예: "abababca"는 n = 8<br>
** m은 찾고자 하는 패턴(P)의 길이 — 예: "abab"은 m = 4

### 📄 Java 핵심 코드 (부분)

```java
int[] buildPi(String pattern) {
    int[] pi = new int[pattern.length()];
    int j = 0;
    for (int i = 1; i < pattern.length(); i++) {
        while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
            j = pi[j - 1];
        }
        if (pattern.charAt(i) == pattern.charAt(j)) {
            pi[i] = ++j;
        }
    }
    return pi;
}
```
[예시코드(링크)](../code/KMP.java)

### 주로 사용하는 상황
| 항목         | 내용                                                                              |
| ---------- | ------------------------------------------------------------------------------- |
| **사용 시점**  | - **패턴이 한 개**이고, 효율적으로 검색해야 할 때<br>- **문자 직접 비교가 안정적인 상황**에서 사용                 |
| **주요 특징**  | - 실패해도 비교했던 정보를 기반으로 점프 가능 (pi 배열)<br>- 텍스트를 한 글자씩 스캔하면서 패턴과 직접 비교함             |
| **장점**     | - 최악의 경우에도 O(n + m)의 시간 복잡도 보장<br>- 문자열 직접 비교 기반이라 **충돌 없음**                    |
| **단점**     | - **하나의 패턴에만 최적화**되어 있음<br>- 전처리로 pi 배열을 만들어야 함                                 |
| **적합한 상황** | - 긴 텍스트에서 **단일 패턴**을 효율적으로 찾고 싶을 때<br>- 충돌이 있으면 안 되는 경우 (예: DNA, 정확한 보안 텍스트 검색) |


---

## 🔹 2. Rabin-Karp

### ✅ 개요

* 문자열을 **해시값으로 바꿔서** 비교하는 방식입니다.
* 여러 패턴을 동시에 검색할 수 있는 장점도 있습니다.
* 문자열을 일일이 비교하지 않고, 해시값으로만 비교하면 속도가 훨씬 빨라지는 것이 핵심입니다.

### 🧠 핵심 아이디어

#### 🔹 문자열 비교를 빠르게 하려면?

보통 문자열에서 패턴을 찾으려면, 매 위치에서 문자열을 하나하나 직접 비교해야 합니다.
하지만 **Rabin-Karp는 문자열을 일단 숫자(해시값)으로 바꿔서 비교**하기 때문에 이 과정을 훨씬 빠르게 만듭니다.

#### 🔹 어떻게 숫자로 바꾸나?

예를 들어 `"abc"`라는 문자열을 해시값으로 바꾼다면 다음처럼 처리합니다:

```
"abc" → 'a'*31² + 'b'*31¹ + 'c'*31⁰
     → 97*961 + 98*31 + 99
     → 93136
```

> 여기서 31은 보통 소수를 사용하여 충돌을 줄이는 값입니다. 실제 구현에서는 `mod`도 함께 써서 오버플로우를 방지합니다.

#### 🔹 슬라이딩 윈도우와 롤링 해시

텍스트에서 길이 `m`짜리 문자열을 **한 칸씩 밀면서** (슬라이딩) 계속 해시값을 비교합니다.

예시:

* 텍스트: `"abcdabc"` → 길이 `n = 7`
* 패턴: `"abc"` → 길이 `m = 3`

텍스트에서 `"abc"`의 위치를 찾는다면 다음 구간의 해시를 검사합니다:

| 슬라이딩 위치 | 서브스트링 | 해시값 비교 대상              |
| ------- | ----- | ---------------------- |
| 0\~2    | `abc` | ✅ 같음 → 실제 문자열 비교 → 일치! |
| 1\~3    | `bcd` | ❌ 다름                   |
| 2\~4    | `cda` | ❌ 다름                   |
| 3\~5    | `dab` | ❌ 다름                   |
| 4\~6    | `abc` | ✅ 같음 → 실제 문자열 비교 → 일치! |

> 문자열 전체를 다시 해시로 계산하면 비효율적이므로, **이전 해시값을 이용해 다음 해시값을 O(1)에 계산**하는 "롤링 해시" 기법을 씁니다.

#### 🔹 해시값을 어떻게 갱신하나?

기존 해시값에서:

* 앞 문자를 제거하고,
* 뒤에 새 문자를 추가하면 됩니다.

예를 들어 `"abc"`의 해시 → `"bcd"`의 해시로 바꾸기:

```
"abc"의 해시: H₁ = 'a'*31² + 'b'*31¹ + 'c'*31⁰
"bcd"의 해시: H₂ = ('b'*31² + 'c'*31¹ + 'd'*31⁰)

H₂ = (H₁ - 'a'*31²) * 31 + 'd'
```

이렇게 하면 O(1)에 새로운 해시값을 계산할 수 있습니다.

#### 🔹 충돌 처리 (중요)

해시값이 같다고 해서 무조건 같은 문자열은 아닙니다.
예를 들어 `"abc"`와 `"acb"`가 같은 해시값을 가질 수도 있습니다. (이런 경우를 해시 **충돌**이라 함)

그래서:

> **해시값이 같으면** → **실제 문자열도 한 번 더 비교해서 최종 확인**합니다.

---

* 문자열의 해시값을 계산해두고, **슬라이딩 윈도우**로 이동하며 해시값만 비교합니다.
* 충돌이 발생하면 문자열도 직접 확인합니다.

### ⏱ 시간 복잡도

* 평균: O(n + m)
* 최악: O(nm) (충돌이 매우 많을 경우)

** n은 전체 텍스트(T)의 길이 — 예: "abababca"는 n = 8<br>
** m은 찾고자 하는 패턴(P)의 길이 — 예: "abab"은 m = 4<br>
** base는 해시 계산에 사용하는 진수 (보통 31 또는 101 등 소수 사용)<br>
** mod는 오버플로우 방지 및 충돌 감소용 (보통 큰 소수 사용)

### 📄 Java 핵심 코드 (부분)

```java
long hash(String s) {
    long h = 0;
    for (char c : s.toCharArray()) {
        h = h * 31 + c;
    }
    return h;
}
```
[예시코드(링크)](../code/RabinKarp.java)

### 주로 사용되는 상황
| 항목         | 내용                                                                               |
| ---------- | -------------------------------------------------------------------------------- |
| **사용 시점**  | - **패턴이 여러 개** 있을 때 효과적<br>- 문자열을 직접 비교하는 것이 부담스러운 경우                            |
| **주요 특징**  | - 문자열을 해시값으로 변환하여 비교<br>- **슬라이딩 윈도우 + 롤링 해시** 사용                                |
| **장점**     | - 여러 패턴의 해시값을 미리 계산해서 **동시에 여러 패턴 탐색 가능**<br>- 해시값 계산을 통한 빠른 비교                  |
| **단점**     | - 해시 충돌 발생 가능성 → **충돌 발생 시 문자열 재확인 필요**<br>- 충돌이 많으면 성능 저하 가능 (최악 O(nm))         |
| **적합한 상황** | - **여러 패턴을 동시에 찾고 싶을 때** (예: 검열 시스템, 금지어 필터)<br>- 텍스트가 매우 길고 비교 성능을 빠르게 처리해야 할 때 |


---

## 🔹 3. Z-Algorithm

### ✅ 개요

* Z-Algorithm은 문자열의 각 위치에서 시작하는 부분 문자열이 문자열의 접두사(prefix)와 얼마나 길게 일치하는지를 계산하는 알고리즘이다.
* `Z[i]`는 `i`번째 인덱스에서 시작하는 문자열이 접두사와 **최대 몇 글자까지 일치하는지**를 의미한다.
* 이를 통해 문자열 내 반복되는 패턴 탐지, 문자열 검색, 최소 주기 구하기 등에 활용된다.

### 🧠 핵심 아이디어

Z배열은 다음과 같이 정의된다:

* `Z[i]`는 문자열의 **0번째 인덱스부터 시작하는 접두사**와, `i`번째 인덱스부터 시작하는 부분 문자열이 **앞에서부터 몇 글자까지 일치하는지**를 저장한다.
* Z[i]는 S[0:] (문자열의 처음부터)와 S[i:] (i번째 인덱스부터 시작하는 부분 문자열)가 앞에서부터 몇 글자까지 일치하는지를 나타낸다. 
* 즉, Z[i] = 최대 k이면 S[0..k-1] == S[i..i+k-1]이다.
* 예를 들어, 문자열 `S = "aabcaabxaaaz"` 가 있을 때:

```
S      = a a b c a a b x a a a z
index  = 0 1 2 3 4 5 6 7 8 9 10 11
Z      = 0 1 0 0 3 1 0 0 2 1  0  0
```
#### 🔸 Z\[4] = ?

* S\[0:] → `aabcaabxaaaz`
* S\[4:] → `aabxaaaz`

앞에서부터 비교:

```
a a b c a a b x a a a z
        ↑
        a a b x ...
        ↑
        a a b   (3글자 같음)
```

→ `'a'=='a'`, `'a'=='a'`, `'b'=='b'` → OK
→ `'c' != 'x'` → 다름
→ **Z\[4] = 3**


#### 윈도우 활용 전략

Z-Algorithm은 두 포인터 **\[L, R]** 윈도우를 사용한다:

* `L`과 `R`은 현재까지 발견된 가장 오른쪽으로 확장된 Z-box를 나타낸다. 즉, 접두사와 일치하는 구간이다.
* 현재 인덱스 `i`가 `R`의 바깥이면 새로 비교하여 `Z[i]`를 계산한다.
* `i`가 `L`과 `R` 사이에 있다면, 기존 Z값을 재활용한다:

  * 이때 `Z[i - L]`의 값이 `R - i + 1`보다 작다면 그대로 사용
  * 크거나 같다면 다시 직접 비교하여 확장

✅ **예시**

문자열 `S = "aabcaabxaaaz"` 에 대해 Z배열을 구하는 도중,

* `L = 4`, `R = 6`일 때 (즉, `"aab"` 일치 중)
* 현재 인덱스 `i = 5`라면:

  * `Z[i - L] = Z[1] = 1`
  * `R - i + 1 = 2`

→ `1 < 2` 이므로, **Z\[5] = 1**로 그대로 사용

* 반면, `i = 6`이라면:

  * `Z[i - L] = Z[2] = 0`
  * `R - i + 1 = 1`

→ `0 < 1` → 역시 기존 값을 그대로 사용

하지만,

* `i = 8`에서 `L = 4`, `R = 6`인 상황이면 `i > R`이므로 Z-box를 벗어났으므로,
  → **새로 직접 비교**하여 `Z[8] = 2`를 계산한다.

#### 예시로 보는 흐름

문자열: `aabcaabxaaaz`

* `Z[1] = 1` → `S[1:] = abcaab...`와 `S[0:] = aabcaab...` 중, 접두사 'a'까지만 일치 → 1
* `Z[4] = 3` → `S[4:] = aabx...`는 접두사 `aab`와 일치 → 3
* `Z[8] = 2` → `S[8:] = aaz`는 접두사 `aa`와 일치 → 2

시각화:

```text
S      =  a a b c a a b x a a a z
Z[4]   →        a a b  (← 접두사 aab와 일치 → 길이 3)
Z[8]   →                a a     (← 접두사 aa와 일치 → 길이 2)
```

---

### ⏱ 시간 복잡도

* O(n)
  * `L`, `R` 윈도우 덕분에 중복 비교를 피하고, 전체 문자열을 최대 한 번만 순회함

### 💻 Java 핵심 코드 (부분)

```java
int[] z = new int[s.length()];
int left = 0, right = 0;

for (int i = 1; i < s.length(); i++) {
    if (i <= right) {
        z[i] = Math.min(right - i + 1, z[i - left]);
    }
    while (i + z[i] < s.length() && s.charAt(z[i]) == s.charAt(i + z[i])) {
        z[i]++;
    }
    if (i + z[i] - 1 > right) {
        left = i;
        right = i + z[i] - 1;
    }
}
```
[예시코드(링크)](../code/ZAlgorithm.java)

---

### 🛠 주로 사용되는 상황

* **문자열 검색**: `"패턴$텍스트"` 형태로 Z배열을 구하면, Z값이 패턴 길이와 같은 위치가 검색 결과임
* **반복 패턴 탐지**: 접두사-부분 문자열 간 일치를 이용해 문자열의 주기성 판단
* **최소 주기 계산**: 문자열의 길이 n과 Z\[n - k]를 이용해 반복 단위 추출
* **Suffix Automaton/Array 최적화의 사전 연산**

---

## 🔹 4. Trie (접두사 트리)

### ✅ 개요

* Trie는 **여러 개의 문자열을 효율적으로 저장하고, 공통된 접두사를 빠르게 탐색**할 수 있는 **트리 기반 자료구조**이다.
* 자동완성, 사전, 금지어 필터링, 접두사 기반 검색 등에 사용된다.
* 각 노드는 **문자 하나**를 나타내며, 루트에서 특정 노드까지의 경로는 어떤 단어의 접두사가 된다.
* 단어의 끝을 `isEnd` 플래그로 구분하여, 완전한 단어 여부를 식별할 수 있다.

### 🧠 핵심 아이디어

* Trie는 각 노드마다 자식 노드들을 **문자 → 노드** 형태의 맵으로 관리한다.
* 루트에서부터 한 글자씩 내려가며 문자열을 삽입하거나 탐색한다.
* 삽입이나 탐색에 걸리는 시간은 \*\*문자열의 길이에 비례 (O(L))\*\*하며, 데이터 수(n)와 무관하다.

### ✅ 예시

`["apple", "app", "bat"]`를 Trie에 삽입하면 다음과 같은 구조가 된다:

```
(root)
 ├─ a
 │  └─ p
 │     └─ p (isEnd=true)
 │        └─ l
 │           └─ e (isEnd=true)
 └─ b
    └─ a
       └─ t (isEnd=true)
```

* `"app"`은 하나의 단어이면서 `"apple"`의 접두사이기도 하다.
* `"bat"`는 완전히 별개의 경로를 가진다.

### 🔍 동작 방식

#### 🔸 삽입 (Insert)

1. 루트에서 시작한다.
2. 한 글자씩 탐색하면서 자식 노드가 없으면 새로 생성한다.
3. 마지막 글자 노드에 `isEnd = true`를 표시한다.

#### 🔸 탐색 (Search)

1. 루트에서 시작한다.
2. 한 글자씩 자식 노드를 따라간다.
3. 마지막 노드에 도달했을 때 `isEnd == true`면 해당 단어가 존재하는 것.

#### 🔸 접두사 검사 (startsWith)

1. 단어 전체를 검색하지 않고 중간에 끊겨도 된다.
2. 단지 해당 경로가 존재하는지만 확인하면 되므로, `isEnd` 체크는 필요 없다.

### ⏱ 시간 복잡도

| 연산     | 시간 복잡도 |
| ------ | ------ |
| 삽입     | O(L)   |
| 탐색     | O(L)   |
| 접두사 검사 | O(L)   |

(*L = 단어의 길이*)

*총 데이터 수(n)가 많아도 문자열 길이만큼만 비교하면 되므로 매우 효율적이다.*

### 📄 Java 핵심 코드 (부분)

```java
class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isEnd = false;
}

void insert(String word) {
    TrieNode node = root;
    for (char c : word.toCharArray()) {
        node = node.children.computeIfAbsent(c, k -> new TrieNode());
    }
    node.isEnd = true;
}
```
[예시코드(링크)](../code/Trie.java)

---

## 🔹 5. Suffix Array + LCP

### ✅ 개요

* 문자열의 **모든 접미사를 정렬한 배열**이 Suffix Array입니다.
* **LCP(Longest Common Prefix) 배열**은 인접한 접미사 간의 접두사 일치 길이입니다.

### 🧠 핵심 아이디어 (완전 입문자용 설명)

#### 🔸 접미사(Suffix)란?

문자열 `S = "banana"`가 있을 때, **모든 접미사**는 다음과 같다:

| 인덱스 | 접미사    |
| --- | ------ |
| 0   | banana |
| 1   | anana  |
| 2   | nana   |
| 3   | ana    |
| 4   | na     |
| 5   | a      |

→ **접미사란, 문자열의 한 위치부터 끝까지 잘라낸 부분 문자열**이다.

---

#### 🔸 Suffix Array란?

위 접미사들을 **사전순(lexicographical order)** 으로 정렬하면:

| 정렬 순서 | 인덱스 | 접미사    |
| ----- | --- | ------ |
| 1st   | 5   | a      |
| 2nd   | 3   | ana    |
| 3rd   | 1   | anana  |
| 4th   | 0   | banana |
| 5th   | 4   | na     |
| 6th   | 2   | nana   |

→ 이때의 Suffix Array는:
**`[5, 3, 1, 0, 4, 2]`**
(즉, 사전순으로 정렬한 접미사들이 **원래 문자열에서 시작된 인덱스**만 저장)<br>
** **왜 접미사를 정렬하는가?<br>**
**사전순 정렬은 문자열이 앞에서부터 최대한 많이 같을수록 더 가까이 정렬되기 때문**이다.


---

#### 🔸 LCP Array란?

\*\*LCP\[i]\*\*는 **Suffix Array의 i번째 접미사와 i-1번째 접미사 사이의 접두사 일치 길이**이다.<br>
**정렬된 접미사들 "인접한 것들끼리" 접두사 얼마나 겹치는지만 확인
→ 이게 LCP[i] = 접미사[i]와 접미사[i-1]의 접두사 길이이다.**

| 위치 | 접미사1   | 접미사2   | 공통 접두사 | 길이 |
| -- | ------ | ------ | ------ | -- |
|    |        | a      | —      | 0  |
| 1  | a      | ana    | "a"    | 1  |
| 2  | ana    | anana  | "ana"  | 3  |
| 3  | anana  | banana | —      | 0  |
| 4  | banana | na     | —      | 0  |
| 5  | na     | nana   | "na"   | 2  |

→ 결과:
**`LCP = [0, 1, 3, 0, 0, 2]`**

(LCP\[0]은 정의상 항상 0)

---

#### 🔸 시각화 요약

```text
Suffix Array 순서:
[5]  a
[3]  ana        → 공통 접두사 "a" (길이 1)
[1]  anana      → 공통 접두사 "ana" (길이 3)
[0]  banana     → 공통 없음 (길이 0)
[4]  na         → 공통 없음 (길이 0)
[2]  nana       → 공통 접두사 "na" (길이 2)

LCP:      [0, 1, 3, 0, 0, 2]
SuffixIdx:[5, 3, 1, 0, 4, 2]
```

---

### 🤔 왜 중요한가?

* **사전순 정렬**이 되어 있기 때문에, **문자열 내 부분 문자열을 빠르게 검색하거나 순서대로 탐색 가능**
* LCP를 이용하면 **중복된 접두사들을 빠르게 파악**하여 문자열 문제를 더 효율적으로 풀 수 있음

#### 🔸 응용력 높은 이유
* **중복 부분 문자열 계산**: `총 가능한 서로 다른 부분 문자열 개수`를 구할 때 사용됨
* **가장 긴 중복 부분 문자열**: LCP 배열 중 최대값
* **사전순 k번째 부분 문자열**: Suffix Array + LCP로 누적하며 탐색

### ⏱ 시간 복잡도

* Suffix Array: O(n log n)
* LCP: O(n)

### 📄 Java 핵심 코드 (부분)

```java
// Suffix Array 만들기 (접미사 정렬)
Arrays.sort(order, (a, b) -> S.substring(a).compareTo(S.substring(b)));

// LCP 계산 (접두사 몇 글자 일치하는지)
while (S.charAt(i + k) == S.charAt(j + k)) k++;
```
[예시코드(링크)](../code/SuffixArrayLCP.java)

### 🛠 주요 활용

| 활용처                 | 설명                                   |
| ------------------- | ------------------------------------ |
| **중복 부분 문자열**       | LCP 배열의 최대값 → 가장 긴 중복 부분 문자열 길이      |
| **사전순 K번째 부분 문자열**  | Suffix Array로 모든 부분 문자열을 순서대로 나열 가능  |
| **서로 다른 부분 문자열 개수** | n(n+1)/2 - ΣLCP → 중복 제거한 전체 부분 문자열 수 |
| **문자열 압축**          | 중복된 접두사 제거 시 비용 최소화 가능               |
| **플레이그라운드 문제**      | 접미사 관련 퀴즈(접미사 길이, 유일성, k번째 접미사 등)    |

#### 🔹 1. **가장 긴 중복 부분 문자열 찾기**

**문제:**

> 문자열 S에서 **중복된 부분 문자열 중 가장 긴 것의 길이**를 구하시오.

**풀이 흐름:**

* Suffix Array를 만들면, **비슷한 접미사들이 나란히 정렬됨**
* LCP 배열을 보면, 서로 인접한 접미사들의 **공통 접두사 길이**가 들어 있음
* → **가장 큰 LCP 값**이 = 가장 긴 중복 부분 문자열의 길이

**예)** `S = "banana"` → `LCP = [0, 1, 3, 0, 0, 2]` → 최대값은 `3` → `"ana"`

---

#### 🔹 2. **서로 다른 부분 문자열 개수 세기**

**문제:**

> 문자열 `S`에서 가능한 모든 **서로 다른 부분 문자열의 개수**는 몇 개인가?

**풀이 흐름:**

1. 문자열의 총 가능한 부분 문자열 수는 `n * (n+1) / 2`
2. 하지만 접미사 간 **중복되는 접두사들**은 빼야 함 → 이게 LCP다!
3. 공식:

   ```text
   Total = Σ (S.length - suffixArray[i])  
   Distinct = Total - Σ(LCP)
   ```

**예)**
`banana`는 전체 21개의 부분 문자열 중,
`LCP`가 `[0, 1, 3, 0, 0, 2]` → 총 6을 빼면
**21 - 6 = 15개가 서로 다른 부분 문자열**

---

#### 🔹 3. **사전순 K번째 부분 문자열 구하기**

**문제:**

> 문자열 `S`에서 **사전순으로 정렬된 모든 부분 문자열 중 K번째는?**

**풀이 흐름:**

* 접미사를 정렬한 Suffix Array를 기준으로 보면,
  `S[i:]`에서 가능한 모든 접두사들이 **사전순 부분 문자열**이 됨
* 각 접미사에서 나오는 부분 문자열 수 = `S.length - suffixArray[i]`
* 하지만 중복되는 부분 문자열이 있음 → LCP만큼 제외해야 함
* → 누적 카운트를 하며 K번째 위치에 있는 부분 문자열을 탐색

---

#### 🔹 4. **문자열 압축 문제**

**문제:**

> 중복되는 부분 문자열을 인코딩해서 문자열 길이를 줄이시오.

**Suffix Array의 역할:**

* 정렬된 접미사들을 보면 어떤 접미사들이 얼마나 겹치는지를 알 수 있음
* LCP를 통해 **중복되는 길이**를 알 수 있어, **압축할 수 있는 패턴 범위** 파악에 사용

---

## 🔹 5. Manacher’s Algorithm

### ✅ 개요

* Manacher’s Algorithm은 문자열에서 **가장 긴 팰린드롬(회문) 부분 문자열**을 **O(n)** 시간에 찾는 알고리즘이다.
* 보통 "가장 긴 팰린드롬을 찾아라"는 문제를 일반적으로 하면 **O(n²)** 시간이 걸리지만,
  Manacher는 **문자열의 대칭성과 중심 확장을 활용**해서 이를 선형 시간으로 줄인다.

### 🧠 핵심 아이디어

#### 1. 전처리: 문자열 변형 (짝수/홀수 회문 통일)

* 원래 문자열: `"abba"` → 짝수 회문 (중앙 없음)
* `"racecar"` → 홀수 회문 (중앙 있음)

이 둘을 한꺼번에 다루기 위해, **모든 문자 사이에 특수 문자 `#`를 삽입**한다:

```
입력:   "abba"
변형:   "#a#b#b#a#"
```

* 이러면 모든 회문은 **홀수 길이로 통일**된다.
* 그리고 회문의 중심은 항상 문자(혹은 `#`) 위에 있으므로 일관된 처리가 가능하다.

#### 2. P 배열: 각 위치에서의 **팰린드롬 반지름 길이**

* `P[i]`는 **i를 중심으로 한 가장 긴 회문의 반지름 길이**를 의미한다.
  (즉, `S[i - P[i]]`부터 `S[i + P[i]]`까지가 팰린드롬)

#### 3. 대칭성 이용한 최적화

* 특정 중심 `C`, 오른쪽 끝 `R`의 팰린드롬을 유지하며,

* 현재 인덱스 `i`가 `R` 안에 포함돼 있으면:

  → `mirror = 2*C - i` (i의 반사 위치)
  → `P[i]`는 최소한 `P[mirror]` 혹은 `R - i` 중 작은 값만큼 보장된다.

* 이후 `S[i - P[i] - 1]`과 `S[i + P[i] + 1]`를 비교하며 확장

### ⏱ 시간 복잡도

* 전처리 변환: O(n)
* 메인 루프 (중심 확장): O(n)
* **총합: O(n)**
  → 각 문자에 대해 중심 확장은 **한 번씩만 확장 성공하므로** O(n)에 수렴함

### 🔍 예시 흐름 (입력: `"abacdfgdcaba"`)

1. 전처리:
   `"abacdfgdcaba"` → `"#a#b#a#c#d#f#g#d#c#a#b#a#"`

2. `P[i]` 배열 생성 과정:

* `P[0] = 0`
* `P[1] = 1` → `"a"` (중심 확장됨)
* `P[2] = 0`
* ...
* `P[12] = 1` → `"cdc"` (중심이 `d`)
* ...
* `P[20] = 3` → `"aba"` (길이 3)
* ...
* 가장 큰 `P[i]`를 기준으로 팰린드롬 복원

### 📌 예시 정리

```text
입력 문자열: abacdfgdcaba
전처리 문자열: #a#b#a#c#d#f#g#d#c#a#b#a#

P 배열 (정확 예시): [0,1,0,3,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,3,0,1,0,1,0]

최대 P[i] = 3 → 중심 i = 3 또는 19
→ 시작 인덱스 = (3 - 3) / 2 = 0 또는 (19 - 3) / 2 = 8
→ 길이 = 2 * 3 - 1 = 5
→ 결과 = S.substring(0, 5) = "abaca" 또는 S.substring(8, 13) = "abaca"
→ 실제로는 두 군데 `"aba"`가 잡힌 것. 이건 회문 `"aba"`를 기준으로 잡힌 반지름 3임
```
[예시코드(링크)](../code/Manacher.java)

---

### 🛠 사용 시나리오

| 문제 유형      | 설명                         |
| ---------- | -------------------------- |
| 가장 긴 회문 찾기 | 문자열 전체에서 가장 긴 팰린드롬을 찾을 때   |
| 회문 개수 세기   | 각 중심별 회문 길이를 이용해서 전체 개수 계산 |
| 실시간 회문 판별  | 빠르게 특정 구간이 회문인지 판단 (응용)    |

---

## 📌 정리 요약표

| 알고리즘               | 목적        | 시간 복잡도                | 특징               |
| ------------------ | --------- | --------------------- | ---------------- |
| KMP                | 단일 패턴 검색  | O(n + m)              | 실패 테이블 이용        |
| Rabin-Karp         | 해시 기반 검색  | 평균 O(n + m), 최악 O(nm) | 다중 검색도 가능        |
| Z-Algorithm        | 접두사 기반 매칭 | O(n)                  | 전체 문자열의 반복 구조 탐지 |
| Trie               | 문자열 사전 구축 | O(L)                  | 자동완성, 접두사 검색     |
| Suffix Array + LCP | 정렬/중복 분석  | O(n log n) + O(n)     | 중복/정렬 처리에 강함     |
| Manacher           | 회문 탐색     | O(n)                  | 짝/홀 회문 모두 탐지     |

