package com.example.valeh.coursemanagementsystem.Main.JsonWorks.Groups;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddGroup_Data {

    @SerializedName("subject")
    @Expose
    private Subject subject;
    @SerializedName("teacher")
    @Expose
    private Teacher teacher;

    public AddGroup_Data(Subject subject, Teacher teacher) {
        this.subject = subject;
        this.teacher = teacher;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public static class Subject {

        @SerializedName("id")
        @Expose
        private Integer id;

        public Subject(Integer id) {
            this.id = id;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }


    }


    public static class Teacher {

        @SerializedName("id")
        @Expose
        private Integer id;

        public Teacher(Integer id) {
            this.id = id;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }

}
