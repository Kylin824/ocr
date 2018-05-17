package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class ImgDao implements BaseDao
{

    private String url = "jdbc:mysql://localhost:3306/ocr?useSSL=false";
    private String user = "root";
    private String password = "";
    private String insert_id;

    public String getInsert_id() {
        return insert_id;
    }

    @Override
    public Collection select() throws Exception {
        ArrayList al = new ArrayList();
        Class.forName("com.mysql.jdbc.Driver");
        String sql = "select * from imgs";
        Connection conn = DriverManager.getConnection(url, user, password);
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        if (rs.next()) {
            Images Get = new Images();
            Get.setUsername(rs.getString("username"));
            Get.setPath(rs.getString("path"));
            al.add(Get);
        }
        return al;
    }

    public Boolean insert(String username, String date, String path) throws Exception
    {
        Class.forName("com.mysql.jdbc.Driver");
        String sql = "insert into imgs(username,date,path)Values(?,?,?)";
        Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, username);
        pst.setString(2, date);
        pst.setString(3, path);
        int i = pst.executeUpdate();
        if (i >= 1) {
            sql = "select last_insert_id()";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                insert_id = String.valueOf(rs.getInt(1));
                //System.out.println ("生成记录的key为 ：" + insert_id);
            }
            return true;
        } else {
            return false;
        }
    }

}
