import java.util.*;

public class Main {

    // BASE는 문자들의 위치 가중치를 주기 위한 기반 숫자 (자바 String.hashCode()와 동일하게 31 사용)
    private static final int BASE = 31;

    // MOD는 해시값이 너무 커지는 걸 방지하고 충돌을 줄이기 위한 큰 소수
    private static final int MOD = 1_000_000_007;

    // 테스트용 main 함수
    public static void main(String[] args) {
        String text = "abcdabcabcabc";
        List<String> patterns = Arrays.asList("abc", "bca", "cab");

        Map<String, List<Integer>> result = multiPatternSearch(text, patterns);
        for (String pattern : result.keySet()) {
            System.out.println("패턴 [" + pattern + "] 위치: " + result.get(pattern));
        }
    }

    /**
     * Rabin-Karp 알고리즘을 이용해 여러 패턴을 텍스트 내에서 동시에 찾습니다.
     * 문자열을 해시값으로 변환하여 비교하기 때문에 여러 패턴 비교 시 유리합니다.
     */
    private static Map<String, List<Integer>> multiPatternSearch(String text, List<String> patterns){
        Map<String, List<Integer>> result = new HashMap<>(); // 결과 저장용

        int n = text.length();

        // 패턴 길이별로 그룹핑: {길이 -> {해시값 -> 패턴리스트}}
        Map<Integer, Map<Integer, List<String>>> patternGroups = new HashMap<>();

        // [1] 모든 패턴에 대해 해시값을 계산하고 그룹화
        for(String pattern : patterns){
            int m = pattern.length();
            int hash = 0;
            for(int i=0;i<m;i++){
                hash = (hash * BASE + pattern.charAt(i)) % MOD;
            }
            // 그룹화
            if(!patternGroups.containsKey(m)) patternGroups.put(m, new HashMap<>());
            if(!patternGroups.get(m).containsKey(hash)) patternGroups.get(m).put(hash, new ArrayList<>());
            patternGroups.get(m).get(hash).add(pattern);
        }

        // [2] 각 패턴 길이 그룹에 대해 텍스트를 슬라이딩하며 검사
        for(int m : patternGroups.keySet()){
            Map<Integer, List<String>> hashToPattern = patternGroups.get(m); // 해당 길이의 해시 그룹

            if (n < m) continue; // 텍스트보다 긴 패턴은 건너뜀

            int windowHash = 0; // 현재 슬라이딩 윈도우(길이 m)의 해시값
            int highBase = 1; // 윈도우의 왼쪽 문자 제거 시 사용할 값: BASE^(m-1)

            // highBase 계산: BASE^(m-1) % MOD
            // → 왜 필요한가? 슬라이딩 할 때 맨 왼쪽 문자의 가중치를 제거하기 위해
            // 예: abc에서 a 제거 → 해시값에서 a × BASE^(2) 만큼 빼야 하는데 여기서 2를 구해둔다는 뜻임
            for (int i = 0; i < m - 1; i++) {
                highBase = (int)((long)highBase * BASE % MOD);
            }

            // 초기 윈도우 해시값 계산: text[0 ~ m-1] 구간
            for(int i=0;i<m;i++){
                windowHash = (int)(((long)windowHash * BASE + text.charAt(i)) % MOD);
            }

            // 슬라이딩 윈도우로 텍스트 전체 탐색 시작
            for(int i=0;i<=n-m;i++){
                // 현재 윈도우 해시값과 일치하는 패턴이 있는 경우
                if(hashToPattern.containsKey(windowHash)){
                    String window = text.substring(i, i+m); // 현재 슬라이딩 윈도우 구간의 실제 문자열

                    // 해시가 일치한 모든 패턴과 실제 문자열 비교(해시 충돌 방지를 위함)
                    for(String pattern : patterns){
                        if(window.equals(pattern)){ // 실제 문자열도 일치해야 진짜 매칭
                            result.putIfAbsent(pattern, new ArrayList<>());
                            result.get(pattern).add(i);
                        }
                    }
                }

                // 윈도우 오른쪽으로 한 칸 이동 (다음 인덱스로 슬라이딩)
                if(i <n-m){
                    // 1️⃣ 왼쪽 문자 제거
                    // windowHash에서 왼쪽 문자(charAt(i)) × highBase 제거
                    windowHash = (int)((long)windowHash - (long)text.charAt(i) * highBase % MOD); // 여기서 음수가 나올수도 있음
                    if(windowHash < 0) windowHash += MOD; // 음수 방지 (mod 연산 특성)

                    // 2️⃣ 오른쪽 문자 추가
                    // windowHash * BASE + 새 문자(charAt(i + m)) 추가
                    windowHash = (int)(((long)windowHash * BASE + text.charAt(i + m)) % MOD);
                }
            }


        }


    }
}