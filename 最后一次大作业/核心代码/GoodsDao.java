package dao;



import pojo.Goods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.DButil;


public class GoodsDao {
    public ArrayList<Goods> findAllGoods() throws SQLException, ClassNotFoundException {
        Connection con= DButil.getCon();
        ArrayList<Goods> list=new ArrayList<Goods>();
        String sql="select * from XM_GOODS";
        PreparedStatement pst=con.prepareStatement(sql);
        ResultSet rs=pst.executeQuery();
        while (rs.next()){
            Goods g1=new Goods();
            g1.setId(rs.getInt("id"));
            g1.setEvaluate(rs.getInt("evaluate"));
            g1.setManufacturer(rs.getString("Manufacturer"));
            g1.setImgurl(rs.getString("imgurl"));
            g1.setPrice(rs.getDouble("price"));
            g1.setTitle(rs.getString("title"));
            g1.setType(rs.getString("type"));
            list.add(g1);
        }
        //关闭结果集
        DButil.close(con,rs,pst);
        return list;
    }

}
