package dao;

import pojo.User;
import util.DButil;

import java.sql.*;

public class UserDao {
    //写一个根据用户名和密码的添加用户功能
    //写一个通过账号密码注册用户的方法（insert into 增的方法）
    //执行完增删改查分别返回什么
    //增删改返回受影响行数，查是返回结果集
    public int addUser(String name,String psw) throws SQLException, ClassNotFoundException {
        Connection con= DButil.getCon();
        String sql="insert into XM_USERS values(USERS_ID_SEQ.NEXTVAL,?,?,null)";
        PreparedStatement pst=con.prepareStatement(sql);
        pst.setString(1,name);
        pst.setString(2,psw);
        int a = pst.executeUpdate();//受影响行数
        //关闭资源
        DButil.close(con,pst);
        return a;
    }

    //根据用户名查找用户是否存在的方法
    public User findByName(String name) throws SQLException, ClassNotFoundException {
        Connection con= DButil.getCon();
        String sql="SELECT  * FROM XM_USERS WHERE name=?";
        PreparedStatement pst=con.prepareStatement(sql);
        pst.setString(1,name);
        ResultSet rs= pst.executeQuery();
        while (rs.next()){
            User u1=new User();
            u1.setId(rs.getInt("id"));
            u1.setName(rs.getString("name"));
            u1.setPassWord(rs.getString("password"));
            DButil.close(con,pst,rs);
            return u1;
        }

        DButil.close(con,pst,rs);
        return null;


    }

    //写一个通过账号密码找用户的方法
    public User findByNameAndPsw(String name,String psw) throws SQLException, ClassNotFoundException {
        Connection con= DButil.getCon();
        String sql="SELECT  * FROM XM_USERS WHERE name=? and password=?";
        PreparedStatement pst=con.prepareStatement(sql);
        pst.setString(1,name);
        pst.setString(2,psw);
        ResultSet rs=pst.executeQuery();
        while (rs.next()){
            User u1=new User();
            u1.setId(rs.getInt("id"));
            u1.setName(rs.getString("name"));
            u1.setPassWord(rs.getString("password"));
            DButil.close(con,pst,rs);
            return u1;
        }

        DButil.close(con,pst,rs);
        return null;
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao userDao =new UserDao();
        User u1=userDao.findByName("admin");
        if (u1!=null){
            System.out.print(u1.getId());
        }else {
            System.out.print("用户不存在！");
        }
    }
}
