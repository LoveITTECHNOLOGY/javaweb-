package action;

import dao.StuDao;
import pojo.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author TYY
 * @version 1.0
 * @date 2020/6/6 16:19
 */
public class StuServ extends HttpServlet {

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");//设置字符编码
        resp.setContentType("text/html;charset=utf-8");//设置响应页面
        //接收前端页面传过来的值
        String stuName=req.getParameter("stuName");
        int stuId=Integer.parseInt(req.getParameter("stuId"));
        //判断名字+id 的学生是否存在
        StuDao stuDao=new StuDao();
        Student s1=new Student();
        try {
            s1=stuDao.findStuByNameAndId(stuName,stuId);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (s1!=null){
            //如果找到了学生 转发至首页
            req.getRequestDispatcher("index.jsp").forward(req,resp);
        }else{
            resp.sendRedirect("login.jsp");
        }
    }
}
