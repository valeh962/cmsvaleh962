package com.example.valeh.coursemanagementsystem.Main.Helpers;

public class SubjectsRoles {

    public String subjectt(int id){
        String subjectt="";

        switch (id){

            case 1:
                subjectt = "Java programming";
                break;
            case 2:
                subjectt = "C++ programming";
                break;
            case 3:
                subjectt = "Android programming";
                break;
            case 4:
                subjectt = "IOS programming";
                break;
            case 5:
                subjectt = "C# ptogramming";
                break;
        }
        return subjectt;
    }

    public String teacherr(int id){
        String teacherr = "";

        switch (id){

            case 1:
                teacherr = "Student";
                break;
            case 2:
                teacherr = "Teacher";
                break;

        }
        return teacherr;



    }


}
