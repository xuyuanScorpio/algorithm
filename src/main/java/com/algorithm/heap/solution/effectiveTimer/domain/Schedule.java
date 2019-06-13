package com.algorithm.heap.solution.effectiveTimer.domain;

public class Schedule {

    private long runTime;

    private Runnable runnable;

    public Schedule(long runTime, Runnable runnable) {
        this.runTime = runTime;
        this.runnable = runnable;
    }

    public long getRunTime() {
        return runTime;
    }

    public void setRunTime(long runTime) {
        this.runTime = runTime;
    }

    public Runnable getRunnable() {
        return runnable;
    }

    public void setRunnable(Runnable runnable) {
        this.runnable = runnable;
    }
}
