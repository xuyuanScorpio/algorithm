package com.algorithm.heap.solution.statisticLogsKeys;


import com.algorithm.heap.solution.statisticLogsKeys.domain.Key;

import java.util.*;

public class CountTopN {

    public static final CountTopN INSTANCE = new CountTopN();

    private Map<String, Integer> appearMap = new HashMap<String, Integer>(10240);

    // 比较器对象
    private Comparator<Key> comp = (o1, o2) -> {
        if(o1.getAppearNum() > o2.getAppearNum()){
            return 1;
        }else if(o1.getAppearNum() < o2.getAppearNum()){
            return -1;
        }
        return 0;
    };

    public void dataClear(){
        appearMap.clear();
    }

    public void putData(String data){
        Integer appearNum = appearMap.get(data);

        if(null == appearNum){
            appearNum = 0;
        }
        appearNum++;
        appearMap.put(data, appearNum);
    }

    public Key[] getTopN(int n){

        PriorityQueue<Key> topN = new PriorityQueue<>(n, comp);

        Iterator<java.util.Map.Entry<String, Integer>> iterator = appearMap.entrySet().iterator();

        Map.Entry<String, Integer> entry;

        while(iterator.hasNext()){
            entry = iterator.next();

            if(topN.size() < n){
                topN.offer(new Key(entry.getKey(), entry.getValue()));
            }else {
                // 如果当前数据比大顶堆的堆顶大，则加入，否则丢弃
                if(topN.peek().getAppearNum() < entry.getValue()){
                    topN.poll();
                    topN.offer(new Key(entry.getKey(), entry.getValue()));
                }
            }
        }

        Key[] result = new Key[n];
        topN.toArray(result);
        return result;
    }

    public Key[] getFinalTopN(List<Key[]> list, int n){

        PriorityQueue<Key> topN = new PriorityQueue<>(n, comp);

        for(Key[] keys : list){
            for(Key key : keys){
                if(key == null){
                    break;
                }

                if(topN.size() < n){
                    topN.offer(new Key(key.getKey(), key.getAppearNum()));
                }else {
                    if(topN.peek().getAppearNum() < key.getAppearNum()){
                        topN.poll();
                        topN.offer(key);
                    }
                }
            }
        }
        Key[] result = new Key[n];
        topN.toArray(result);

        return result;
    }



}
