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
 * @author acv
 */
public class ClassQueries{
    private static Connection connection;
    private static ArrayList<String> faculty = new ArrayList<String>();
    private static PreparedStatement addClass;
    private static PreparedStatement getCourseCodes;
    private static PreparedStatement getClassSeats;
    private static PreparedStatement getDropClass;
    private static ResultSet resultSet;
    
    public static void addClass(String semester, String code, int seats)
    {
        connection = DBConnection.getConnection();
        try
        {
            addClass = connection.prepareStatement("insert into app.class (semester,coursecode,seats) values (?,?,?)");
            addClass.setString(1, semester);
            addClass.setString(2, code);
            addClass.setInt(3, seats);
            addClass.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<String> getAllCourseCodes(String semester)
    {
        connection = DBConnection.getConnection();
        ArrayList<String> courseCodes = new ArrayList<String>();
        try
        {
            getCourseCodes = connection.prepareStatement("select coursecode from app.class where semester=?");
            getCourseCodes.setString(1,semester);
            resultSet = getCourseCodes.executeQuery();
            
            while(resultSet.next())
            {
                courseCodes.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return courseCodes;
        
    }
    public static int getClassSeats(String semester, String code){
        connection=DBConnection.getConnection();
        int result=0;
        try{
            getClassSeats=connection.prepareStatement("select seats from app.class where semester=? and coursecode=?");
            getClassSeats.setString(1,semester);
            getClassSeats.setString(2,code);
            resultSet=getClassSeats.executeQuery();
            result=resultSet.getInt(3);
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return result;
    }
    public static void dropClass(String semester,String coursecode){
        connection=DBConnection.getConnection();
        try{
            getDropClass=connection.prepareStatement("delete from app.class where semester=? and coursecode=?");
            getDropClass.setString(1,semester);
            getDropClass.setString(2,coursecode);
            getDropClass.executeUpdate();
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
}