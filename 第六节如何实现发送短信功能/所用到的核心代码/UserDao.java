package dao;



import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {
    // 写一个根据用户名和密码的添加用户功能
    public void addUser(String name,String psw) throws SQLException, ClassNotFoundException {
        Connection  con = DBUtil.getCon();
        String sql="insert into XM_USERS values(USERS_ID_SEQ.NEXTVAL,?,?,null);";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1,name);
        pst.setString(2,psw);
        pst.executeUpdate();
        // 关闭资源
        DBUtil.close(con,pst);

    }
}