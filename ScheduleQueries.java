/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
/**
 *
 * @author firet
 */
public class ScheduleQueries {
    private static Connection connection;
    private static ArrayList<String> faculty = new ArrayList<String>();
    private static PreparedStatement addSchedule;
    private static PreparedStatement getSchedule;
    private static PreparedStatement getSeats;
    private static ResultSet resultSet;
    public static void addScheduleEntry(ScheduleEntry entry){
        connection=DBConnection.getConnection();
        try{
            addSchedule=connection.prepareStatement("insert into app.schedule (semester,coursecode,studentid,status,timestamp) values (?,?,?,?,?)");
            addSchedule.setString(1,entry.getSemester());
            addSchedule.setString(2,entry.getCourseCode());
            addSchedule.setString(3,entry.getStudentID());
            addSchedule.setString(4,entry.getStatus());
            addSchedule.setTimestamp(5,entry.getTime());
            addSchedule.executeUpdate();
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    public static ArrayList<ScheduleEntry> getScheduleByStudent(String s,String ID){
        connection=DBConnection.getConnection();
        ArrayList<ScheduleEntry> schedules=new ArrayList<>();
        try{
            getSchedule=connection.prepareStatement("select coursecode,status,timestamp from app.schedule where semester=? and studentid=? order by timestamp,coursecode");
            getSchedule.setString(1,s);
            getSchedule.setString(2,ID);
            resultSet=getSchedule.executeQuery();
            while(resultSet.next()){
                schedules.add(new ScheduleEntry(s,resultSet.getString(1),ID,resultSet.getString(2),resultSet.getTimestamp(3)));
            }
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return schedules;
    }
    public static int getScheduledStudentCount(String semester,String courseCode){
        connection=DBConnection.getConnection();
        int result=0;
        try{
            getSeats=connection.prepareStatement("select status from app.schedule where semester=? and coursecode=?");
            getSeats.setString(1,semester);
            getSeats.setString(2,courseCode);
            resultSet=getSeats.executeQuery();
            while(resultSet.next()){
                result++;
            }
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return result;
    }
    public static ArrayList<ScheduleEntry> getWaitlistedStudentsByClass(String semester, String coursecode){
        connection=DBConnection.getConnection();
        ArrayList<ScheduleEntry> result=new ArrayList<>();
        try{
            getSeats=connection.prepareStatement("select studentid,timestamp from app.schedule where semester=? and coursecode=? and status=? order by timestamp");
            getSeats.setString(1,semester);
            getSeats.setString(2,coursecode);
            getSeats.setString(3,"W");
            resultSet=getSeats.executeQuery();
            while(resultSet.next()) result.add(new ScheduleEntry(semester,coursecode,resultSet.getString(1),"W",resultSet.getTimestamp(2)));
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return result;
    }
    public static void dropStudentScheduleByCourse(String semester,String studentID, String coursecode){
        connection=DBConnection.getConnection();
        try{
            getSeats=connection.prepareStatement("delete from app.schedule where semester=? and studentid=? and coursecode=?");
            getSeats.setString(1,semester);
            getSeats.setString(2,studentID);
            getSeats.setString(3,coursecode);
            getSeats.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void dropScheduleByCourse(String semester,String coursecode){
        connection=DBConnection.getConnection();
        try{
            getSeats=connection.prepareStatement("delete from app.schedule where semester=? and coursecode=?");
            getSeats.setString(1,semester);
            getSeats.setString(2,coursecode);
            getSeats.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void updateScheduleEntry(ScheduleEntry entry){
        connection=connection=DBConnection.getConnection();
        try{
            getSeats=connection.prepareStatement("update app.schedule set status=?,timestamp=? where semester=? and coursecode=? and studentid=?");
            getSeats.setString(1,"S");
            getSeats.setTimestamp(2,new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
            getSeats.setString(3,entry.getSemester());
            getSeats.setString(4,entry.getCourseCode());
            getSeats.setString(5,entry.getStudentID());
            getSeats.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}