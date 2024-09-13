
import java.util.Calendar;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author firet
 */
public class ScheduleEntry {
    private String semester;
    private String courseCode;
    private String studentID;
    private String status;
    private java.sql.Timestamp time;

    public ScheduleEntry(String semester, String courseCode, String studentID, String status, java.sql.Timestamp time) {
        this.semester=semester;
        this.courseCode=courseCode;
        this.studentID=studentID;
        this.status=status;
        this.time=new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
    }  
    public String getSemester(){return semester;}
    public String getCourseCode(){return courseCode;}
    public String getStudentID(){return studentID;}
    public String getStatus(){return status;}
    public java.sql.Timestamp getTime(){return time;}
}
