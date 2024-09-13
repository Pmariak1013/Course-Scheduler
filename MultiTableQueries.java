/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author firet
 */
public class MultiTableQueries {
    private static Connection connection1;
    private static Connection connection2;
    private static ArrayList<String> faculty = new ArrayList<String>();
    private static PreparedStatement getCourseInfo;
    private static PreparedStatement getClassInfo;
    private static PreparedStatement getSchedules;
    private static PreparedStatement getNames;
    private static ResultSet courseSet;
    private static ResultSet classSet;
    private static ResultSet scheduleSet;
    private static ResultSet nameSet;
    public static ArrayList<ClassDescription> getAllCourseCodes(String semester){
        connection1=DBConnection.getConnection();
        connection2=DBConnection.getConnection();
        ArrayList<ClassDescription> courseCodes=new ArrayList<>();
        try{
            getCourseInfo=connection1.prepareStatement("select coursecode,description from app.course order by coursecode");
            courseSet=getCourseInfo.executeQuery();
            getClassInfo=connection2.prepareStatement("select semester,coursecode,seats from app.class where semester=?");
            getClassInfo.setString(1,semester);
            classSet=getClassInfo.executeQuery();
            classSet.next();
            while(courseSet.next()){
                if(courseSet.getString(1).equals(classSet.getString(2))){
                    courseCodes.add(new ClassDescription(courseSet.getString(1),courseSet.getString(2),classSet.getInt(3)));
                    classSet.next();
                }
            }
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return courseCodes;
    }
    public static ArrayList<StudentEntry> getScheduledStudentsByClass(String semester,String coursecode){
        connection1=DBConnection.getConnection();
        ArrayList<StudentEntry> entries=new ArrayList<>();
        try{
            getSchedules=connection1.prepareStatement("select studentid from app.schedule where semester=? and coursecode=? and status=?");
            getSchedules.setString(1,semester);
            getSchedules.setString(2,coursecode);
            getSchedules.setString(3,"S");
            scheduleSet=getSchedules.executeQuery();
            while(scheduleSet.next()) entries.add(StudentQueries.getStudent(scheduleSet.getString(1)));
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return entries;
    }
    public static ArrayList<StudentEntry> getWaitlistedStudentsByClass(String semester,String coursecode){
        connection1=DBConnection.getConnection();
        ArrayList<StudentEntry> entries=new ArrayList<>();
        try{
            getSchedules=connection1.prepareStatement("select studentid from app.schedule where semester=? and coursecode=? and status=? order by timestamp");
            getSchedules.setString(1,semester);
            getSchedules.setString(2,coursecode);
            getSchedules.setString(3,"W");
            scheduleSet=getSchedules.executeQuery();
            while(scheduleSet.next()) entries.add(StudentQueries.getStudent(scheduleSet.getString(1)));
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return entries;
    }
}