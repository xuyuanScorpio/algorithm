package com.algorithm.heap.solution.effectiveTimer;

public class RunSchedule {

    public static final RunSchedule instance = new RunSchedule();

    public final long getCurrentTime(){
        return System.currentTimeMillis();
    }

    public void runTask(long runtime, Runnable task){

        long currentTime;

        while (true){
            currentTime = this.getCurrentTime();
            long waitTime = runtime - currentTime;

            if(waitTime > 0){
                try {
                    long startTime = System.currentTimeMillis();
                    System.out.println("开始：" + startTime);
                    Thread.sleep(waitTime);
                    long endTime = System.currentTimeMillis();
                    System.out.println("结束" + (endTime - startTime));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                break;
            }
        }
        task.run();
    }

}
