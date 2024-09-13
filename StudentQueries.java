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
public class StudentQueries {
    private static Connection connection;
    private static ArrayList<String> faculty = new ArrayList<String>();
    private static PreparedStatement addStudent;
    private static PreparedStatement getStudentList;
    private static ResultSet resultSet;
    
    public static void addStudent(String id,String first,String last)
    {
        connection = DBConnection.getConnection();
        try
        {
            addStudent = connection.prepareStatement("insert into app.student (studentid, firstname,lastname) values (?,?,?)");
            addStudent.setString(1, id);
            addStudent.setString(2, first);
            addStudent.setString(3, last);
            addStudent.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<StudentEntry> getAllStudents()
    {
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> students = new ArrayList<>();
        try
        {
            getStudentList = connection.prepareStatement("select studentid,firstname,lastname from app.student order by studentid");
            resultSet = getStudentList.executeQuery();
            
            while(resultSet.next())
            {
                students.add(new StudentEntry(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3)));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return students;
    }
    public static StudentEntry getStudent(String studentID){
        connection=DBConnection.getConnection();
        try{
            getStudentList=connection.prepareStatement("select studentid,firstname,lastname from app.student where studentid=?");
            getStudentList.setString(1,studentID);
            resultSet=getStudentList.executeQuery();
            resultSet.next();
            return new StudentEntry(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3));
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return null;
    }
    public static void dropStudent(String studentID){
        connection=DBConnection.getConnection();
        try{
            getStudentList=connection.prepareStatement("delete from app.student where studentid=?");
            getStudentList.setString(1,studentID);
            getStudentList.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
}