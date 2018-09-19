package com.example.valeh.coursemanagementsystem.Main.JsonWorks.GroupSchedule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GroupScheduleData {

    @SerializedName("groupId")
    @Expose
    private Integer groupId;
    @SerializedName("day")
    @Expose
    private Integer day;
    @SerializedName("time")
    @Expose
    private Integer time;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

}