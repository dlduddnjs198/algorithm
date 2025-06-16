# 📘 이진 탐색 (Binary Search)

## ✅ 이진 탐색이란?

* **정렬된 배열이나 탐색 범위에서** 원하는 값을 빠르게 찾는 알고리즘.
* **시간 복잡도: O(log N)**

### 🔍 핵심 아이디어

* 가운데 값을 기준으로 탐색 범위를 절반씩 줄여 나감.
* 항상 **정렬된 상태**가 전제 조건.

```text
예: [1, 3, 5, 7, 9, 11] 에서 5를 찾을 때
1. 중간값 5 → 정답
```

---

## ✅ 기본 구조 (Java 예시)

```java
int binarySearch(int[] arr, int target) {
    int left = 0;
    int right = arr.length - 1;

    while (left <= right) {
        int mid = (left + right) / 2;
        if (arr[mid] == target) return mid;
        else if (arr[mid] < target) left = mid + 1;
        else right = mid - 1;
    }
    return -1; // 찾지 못한 경우
}
```
[BinarySearch.java](../code/BinarySearch.java)

---

## 🧠 언제 사용하는가?

| 조건                | 사용 여부            |
| ----------------- | ---------------- |
| 배열/리스트가 정렬되어 있는가? | ✅ YES            |
| 빠르게 값을 찾고 싶은가?    | ✅ YES            |
| 최댓값/최솟값을 찾고 싶은가?  | ✅ YES → 파라메트릭 서치 |

---

## 🧩 응용 1: 파라메트릭 서치 (Parametric Search)

### ✅ 정의

* **조건을 만족하는 가장 큰 값 or 가장 작은 값**을 찾는 형태의 이진 탐색
* 일반적으로 `true/false`를 반환하는 **조건 함수**와 함께 사용

### 📘 예제 문제

* 특정 용량의 DVD에 N개의 곡을 저장할 수 있는 최소 DVD 크기?
* K개의 사람에게 물건을 나눠주는 최소 최대값?

### ✅ 구조 예시

```java
int binarySearchParametric() {
    int left = 최소 가능한 값;
    int right = 최대 가능한 값;
    int answer = right;

    while (left <= right) {
        int mid = (left + right) / 2;
        if (조건(mid)) {
            answer = mid; // 조건 만족 시 더 작은 값 시도
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }
    return answer;
}
```
[파라매트릭 서치 예제(떡 자르기)](../code/BinarySearchParametric.java)

---

## 🧩 응용 2: 지수 탐색 (Exponential Search)

### ✅ 정의

* 크기를 알 수 없는 배열에서 유효한 범위를 먼저 찾고, 그 안에서 이진 탐색을 수행하는 방법

### ✅ 사용 예

* 무한한 입력 스트림에서 탐색할 때
* **일반적인 코딩테스트 단계에서는 이걸 쓸 일은 거의 없을 것이다.**

### ✅ 구조

- 이진 탐색은 탐색 범위가 정해져 있을 때 쓰는 방식이다.<br>
- 하지만 범위를 모르는 경우(특히 무한 배열 또는 길이를 모르는 정렬된 리스트)에는 이진 탐색을 곧바로 쓰기 어려움.

그래서 이렇게 한다:
1. 지수적으로 커지는 간격으로 범위를 확장:<br>
arr[1], arr[2], arr[4], arr[8], arr[16]... 처럼<br>
원하는 값보다 큰 지점이 나올 때까지 증가.

2. 그 범위 사이에서 이진 탐색 수행.

즉, "범위를 지수적으로 빠르게 찾고, 그 안에서 이진 탐색" = Exponential Search라는 것이다.

---

## 🧩 응용 3: 삼진 탐색 (Ternary Search)

### ✅ 정의

* **함수가 단조 증가/감소 혹은 봉우리 형태**일 때, 최댓값/최솟값을 찾기 위한 탐색
* 범위를 **1/3 단위로 나눠서** 비교
* 어떤 점을 기준으로 증가하다가 감소하거나 / 감소하다가 증가하는 형태의 함수에만 사용 가능!
* 일반적인 코딩테스트 상황에서는 왠만하면 쓸일이 없어보인다.

### ✅ 사용 예

* 어떤 수 `x`에 대해 `f(x)`의 최솟값을 찾을 때 (ex. 거리, 시간 등 함수 최소화 문제)

### ✅ 구조 (double로 연속값 찾는 경우)

```java
double ternarySearch(double left, double right) {
    while (right - left > 1e-6) {
        double m1 = (2 * left + right) / 3;
        double m2 = (left + 2 * right) / 3;

        if (f(m1) < f(m2)) right = m2;
        else left = m1;
    }
    return (left + right) / 2;
}
```
[삼진 탐색 예시](../code/TenarySearch.java)

---

# 📌 정리

| 이름       | 조건         | 목적             | 시간복잡도    | 특징        |
| -------- | ---------- | -------------- | -------- | --------- |
| 이진 탐색    | 정렬된 배열     | 특정 값 탐색        | O(log N) | 가장 기본     |
| 파라메트릭 서치 | 이진 결정 조건   | 조건 만족하는 최소/최대값 | O(log N) | 최적화 문제 해결 |
| 지수 탐색    | 범위 모를 때    | 유효 범위 내 탐색     | O(log N) | 선형 + 로그   |
| 삼진 탐색    | 함수 최솟값/최댓값 | 최적 지점 찾기       | O(log N) | 봉우리, 단조함수 |

---

## ✅ 대표적인 이진 탐색 유형 정리

### 📌 1. 정렬된 배열에서 값 찾기

* `Arrays.binarySearch()` 또는 직접 구현 가능
* 값이 존재하는지 확인하거나 인덱스(정확한 답)를 찾음

### 📌 2. Lower Bound / Upper Bound 찾기

* **Lower Bound**: `target 이상이 처음 나오는 위치`
* **Upper Bound**: `target 초과가 처음 나오는 위치`

### 📌 3. 파라메트릭 서치 (Parametric Search)

* **답이 될 수 있는 값을 이진 탐색**으로 찾는 것
* ex) 최소한의 최대값, 최대한의 최소값, 조건을 만족하는 최소/최대 값 등
* 일반적으로 조건 판별 함수가 필요함
* **일반 이진 탐색은 "존재하는 값"을 찾는 거고, 파라메트릭 서치는 "조건을 만족하는 최적의 수치"를 찾는 것이다.**

**예시(반대도 가능)**
```java
while (left <= right) {
    int mid = (left + right) / 2;
    if (조건(mid)) {
        right = mid - 1; // 조건 만족: 더 작은 값도 가능?
        ans = mid;
    } else {
        left = mid + 1; // 조건 불만족: 더 큰 값으로 이동
    }
}
```

### 📌 4. 이분 매칭 문제

* 배열이 아닌, **탐색 공간이 "답"이 될 수 있는 수의 범위**인 경우
* 예: 징검다리 건너기, 공유기 설치 문제 등

## 📚 연습 추천

* 백준 1654: 랜선 자르기 (파라메트릭 서치)
* 백준 2805: 나무 자르기
* 백준 1300: K번째 수

---

