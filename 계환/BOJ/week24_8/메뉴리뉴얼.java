package BOJ.week24_8;

import java.util.*;

public class 메뉴리뉴얼 {
    List<String> answerList = new ArrayList<>();
    Map<String, Integer> hashMap = new HashMap<>();

    public String[] solution(String[] orders, int[] course) {
        // 1. 각 order 정렬
        for(int i = 0; i < orders.length; i++){
            char[] arr = orders[i].toCharArray();
            Arrays.sort(arr);
            orders[i] = String.valueOf(arr);
        }
        // 2. 각 order를 기준으로 courseLength 만큼의 조합 만들기
        for(int courseLength: course){
            for(String order : orders){
                combination("", order, courseLength);
            }
            // 3. 가장 많은 조합 answer에 저장
            if(!hashMap.isEmpty()){
                List<Integer> countList = new ArrayList<>(hashMap.values());
                int max = Collections.max(countList);
                if(max > 1){
                    for(String key : hashMap.keySet()){
                        if(hashMap.get(key) == max){
                            answerList.add(key);
                        }
                    }
                }
                hashMap.clear();
            }
        }
        Collections.sort(answerList);
        String[] answer = new String[answerList.size()];
        for(int i = 0; i < answer.length; i++){
            answer[i] = answerList.get(i);
        }
        return answer;
    }
    public void combination(String order, String others, int count){
        // 탈출조건
        if(count == 0){
            hashMap.put(order, hashMap.getOrDefault(order, 0) + 1);
        }
        // 실행동작 - 0부터 length 까지의 조합
        for(int i = 0; i < others.length(); i++){
            combination(order + others.charAt(i), others.substring(i+1), count-1);
        }
    }
}
