package com.algorithm.heap.solution.effectiveTimer;

import com.algorithm.heap.solution.effectiveTimer.domain.Schedule;

import java.util.PriorityQueue;

public class ScheduleManager {

    public static final ScheduleManager INSTANCE = new ScheduleManager();

    // 使用小顶堆来存储任务队列
    private PriorityQueue<Schedule> queue = new PriorityQueue<>(8, (o1, o2) -> {
        if(o1.getRunTime() > o2.getRunTime()){
            return 1;
        }else if(o1.getRunTime() < o2.getRunTime()){
            return -1;
        }
        return 0;
    });


    public void delayRun(long time, Runnable runnable){
        queue.offer(new Schedule(time, runnable));
        Schedule schedule = queue.poll();
        //
    }

}
