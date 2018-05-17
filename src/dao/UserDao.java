package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class UserDao implements BaseDao {
    private String url = "jdbc:mysql://localhost:3306/ocr?useSSL=false";
    private String user = "root";
    private String password = "";


    public boolean insert(String username,String upassword) throws Exception
    {
        Class.forName("com.mysql.jdbc.Driver");
        String sql = "insert into users(username,password)Values(?,?)";
        Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, username);
        pst.setString(2, upassword);
        int i = pst.executeUpdate();
        if (i >= 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Collection select() throws Exception {
        ArrayList al = new ArrayList();
        Class.forName("com.mysql.jdbc.Driver");
        String sql = "select * from users";
        Connection conn = DriverManager.getConnection(url, user, password);
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        if (rs.next()) {
            User Get = new User();
            Get.setUsername(rs.getString("username"));
            Get.setPassword(rs.getString("password"));
            al.add(Get);
        }
        return al;
    }

    /*@Override
    public boolean delete(String username) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String sql = "delete from users where username=?";
        Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, username);
        int i = pst.executeUpdate();
        if (i >= 1) {
            return true;
        } else {
            return false;
        }

    }*/

    public ArrayList up_select(String username) throws Exception {
        ArrayList al = new ArrayList();
        Class.forName("com.mysql.jdbc.Driver");
        String sql = "select * from users where username=?";
        Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1,username);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            User Get = new User();
            Get.setUsername(rs.getString("username"));
            Get.setPassword(rs.getString("password"));
            al.add(Get);
        }
        return al;
    }

    /*@Override
    public boolean update(String username,String upassword) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String sql = "update users set password=? where username=?";
        Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1,upassword);
        pst.setString(2,username);
        int i = pst.executeUpdate();
        if (i >= 1) {
            return true;
        } else {
            return false;

        }
    }*/

    public boolean check(String username, String upassword) throws Exception
    {
        Class.forName("com.mysql.jdbc.Driver");
        String sql = "select * from users where username = ? AND password = ?";
        Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1,username);
        pst.setString(2,upassword);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            return true;
        }
        return false;
    }
}
