package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DButil {
    //用于获得数据库连接的工具类
    public static Connection getCon() throws ClassNotFoundException, SQLException {
        //一个静态的获得数据库的方法
        //加载类，并执行类中静态代码块
        Class.forName("oracle.jdbc.OracleDriver");

        //getConnection（参数一，数据库，参数，登录名，密码)
        Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE",
                "xmsc","111111");
        return con;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection con=DButil.getCon();
        System.out.println(con);
    }
}
