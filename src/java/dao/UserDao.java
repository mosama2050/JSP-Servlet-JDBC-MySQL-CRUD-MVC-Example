/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author moham
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.User;

public class UserDao {

    private String jdbcURL = "jdbc:mysql://localhost:3306/demo";
    private String jdbcusername = "root";
    private String jdbpassword = "root";

    private static final String INSERT_USER_SQL = "inser INTO users" + "(name,email,country) VALUES" + "(?,?,?)";

    private static final String SELECT_USER_BY_ID = "SELECT  id ,  name ,  email ,  country  FROM  users  WHERE id =?";

    private static final String SELECT_ALL_USERS = "SELECT * FROM  users ";

    private static final String DELETE_USER_SQL = "DELETE FROM  users  WHERE id = ?";

    private static final String UPDATE_USER_SQL = "UPDATE  users  SET   name =?, email =?, country =? WHERE id = ? ";

    protected Connection getConnnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    jdbcURL,"root" ,"" );
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "connection");
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }

    // create user or insert user 
    public void insertuser(User user) throws SQLException {
        try {
          Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    jdbcURL,"root" ,"" );
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getCountry());
            System.out.println("user.getName()"+user.getName());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //update user
    public boolean updateuser(User user) throws SQLException {
        boolean rowUpdated = false;
        try {System.out.println("here");
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    jdbcURL,"root" ,"" );
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_SQL);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getCountry());
            preparedStatement.setInt(4, user.getId());
            System.out.println( "test updateuser "+user.getName() );
            preparedStatement.executeUpdate();
            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    //select user by id 
    public User selectuser(int id) {
        User user = null;
        try {
          Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    jdbcURL,"root" ,"" );
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                user = new User(id, name, email, country);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    //select users
    
        public  List<User> selectallusers( ) throws SQLException {
            System.out.println("selectallusers");
            int i = 0 ;
            List<User> users = new ArrayList<>();
          try{ 
//              Connection connection = getConnnection();
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    jdbcURL,"root" ,"" );
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);
             
            
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                 int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                  users.add(new User(id, name, email, country));i++;
                System.out.println("number of users = " +i );
            }
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
    //delete user
         public boolean deleteuser(int id) throws SQLException {
        boolean rowUpdated = false;
        try {
           Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    jdbcURL,"root" ,"" );
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_SQL);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    
}
