package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DButil {
    public static  Connection getCon() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.OracleDriver");
        //getConnection(参数一：数据库，参数二：登录名，参数三：密码）
        Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE",
                "xmsc",
                "111111");
        return con;
    }
    //数据库连接，预处理。结果集占用资源，不用时需要关闭
    //关闭资源的方法
    //AutoCloseable ... autoCloseables不确定参数数据
    //调用DBUtil.close(con,pst);

    public static  void close(AutoCloseable ... autoCloseables){
        for (AutoCloseable a:autoCloseables){
            //类似for(User u:userList)
            if(a!=null){
                try {
                    a.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

    }

  //psvm
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println(DButil.getCon());
    }
}
