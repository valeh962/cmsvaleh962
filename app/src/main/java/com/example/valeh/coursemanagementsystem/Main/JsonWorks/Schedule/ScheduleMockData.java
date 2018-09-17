package com.example.valeh.coursemanagementsystem.Main.JsonWorks.Schedule;

import io.reactivex.Scheduler;

public class ScheduleMockData {

    String subj;
    String clock;


    public ScheduleMockData(String subj, String clock) {
        this.subj = subj;
        this.clock = clock;
    }

    public String getSubj() {
        return subj;
    }

    public void setSubj(String subj) {
        this.subj = subj;
    }

    public String getClock() {
        return clock;
    }

    public void setClock(String clock) {
        this.clock = clock;
    }
}
