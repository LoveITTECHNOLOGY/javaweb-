package dao;

import pojo.Student;
import util.DButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author TYY
 * @version 1.0
 * @date 2020/6/6 14:19
 */
public class StuDao {
    //添加数据的方法  insert
    public void addStu(Student s) throws SQLException, ClassNotFoundException {
        //1.得到连接  类似登录数据库
        Connection con= DButil.getCon();
        //2.写SQL语句   ?表示占位符，暂时没有值的  类似准备好SQL语句
        String sql="insert into student values(STUDENT_ID_SEQ.Nextval,?,?,?)";
        //3.预处理   类似把SQL语句写在SQL window中
        PreparedStatement pst=con.prepareStatement(sql);
        //对占位符赋值 ？赋值  setString(第几个问号，赋的值)
        //主键字段应该自动生成
        pst.setInt(1,s.getAge());
        pst.setString(2,s.getName());
        pst.setString(3,s.getSex());
        //4.执行SQL语句 点击齿轮 有更新操作的使用executeUpdate()
        pst.executeUpdate();
    }
    //根据学生名删除数据的方法
    public void delStuByName(String stuName) throws SQLException, ClassNotFoundException {
        Connection con=DButil.getCon();
        String sql="delete from student where name=?";
        PreparedStatement pst=con.prepareStatement(sql);
        pst.setString(1,stuName);
        pst.executeUpdate();
    }

    //查询所有学生信息  select *
    public ArrayList<Student> findAll() throws SQLException, ClassNotFoundException {
        Connection con=DButil.getCon();
        String sql="select * from student";
        PreparedStatement pst=con.prepareStatement(sql);
        //执行sql语句 查询语句executeQuery()   查询操作的返回值为结果集
        ResultSet rs=pst.executeQuery();
        ArrayList<Student> arr=new ArrayList<Student>();
        while (rs.next()){
            //结果集的next()方法，是返回下一行是否有数据 真或假
            Student s1=new Student();//用来存放数据库的某条数据
            //此时s1的属性都是空
            //rs.getInt(字段名)
            s1.setId(rs.getInt("id"));
            s1.setAge(rs.getInt("age"));
            s1.setName(rs.getString("name"));
            s1.setSex(rs.getString("sex"));
            //s1的属性就和数据库中的数据对应上了
            //得到了s1，就要把s1放入arr中
            arr.add(s1);
        }
        return arr;
    }

    //根据学生姓名查找学生信息
    public Student findStuByNameAndId(String stuName,int id) throws SQLException, ClassNotFoundException {
        Connection con=DButil.getCon();
        String sql="select * from student where name=? and id=?";
        PreparedStatement pst=con.prepareStatement(sql);
        pst.setString(1,stuName);
        pst.setInt(2,id);
        ResultSet rs=pst.executeQuery();
        while (rs.next()){
            Student s1=new Student();//用来存放数据库的某条数据
            s1.setId(rs.getInt("id"));
            s1.setAge(rs.getInt("age"));
            s1.setName(rs.getString("name"));
            s1.setSex(rs.getString("sex"));
            return s1;//直接结束方法
        }
        return null;
    }






    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Student s1=new Student(22,"张三","男");
        //生成一个学生类的操作数据库的工具人
        StuDao stuDao=new StuDao();
        //stuDao.addStu(s1);
        //stuDao.delStuByName("张三");
      /*
            ArrayList<Student> arr=stuDao.findAll();
            for (Student stu:arr) {
                System.out.println("学生姓名"+stu.getName()+",学生id:"+stu.getId());
        }*/
        Student stu=stuDao.findStuByNameAndId("小明",1);
        if (stu!=null){
            System.out.println("学生姓名"+stu.getName()+",学生id:"+stu.getId());
        }else{
            System.out.println("找不到此学生！");
        }
    }
}
