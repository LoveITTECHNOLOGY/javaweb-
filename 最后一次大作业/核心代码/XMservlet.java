package action;

import dao.GoodsDao;
import dao.UserDao;
import pojo.Goods;
import pojo.User;
import sun.swing.BakedArrayList;
import util.sendMessageUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class XMservlet extends HttpServlet {


    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html,charset=utf-8");
        PrintWriter o =resp.getWriter();
        String uri =req.getRequestURI();//得到请求路径的uri
        UserDao userDao = new UserDao();//生成一个用户的dao层对象
        GoodsDao goodsDao=new GoodsDao();//生成一个操作商品的dao层
        if (uri.equals("/send.do")){
            //发送验证码的请求
            String tel=req.getParameter("tel");
            String code= sendMessageUtil.getRandomCode(6);
            //发送短信 mobilecode状态码
            //   int mobilecode=sendMessageUtil.send("zgycsmb","d41d8cd98f00b204e98011",tel,code);
            // String message = sendMessageUtil.getMessage(mobilecode);
            //  System.out.println(message);
            o.print(code);
        }else if (uri.equals("/test.do")){
            //验证用户名是否存在的ajax请求
            String name=req.getParameter("name");
            User u1=new User();
            try {
                u1=userDao.findByName(name);
                if (u1!=null){
                    //证明找到了用户，则用户名不可用
                    //前端$ajax.responseText()的值就是 o.print(输出的)
                    o.print("用户名已存在!");
                }else {
                    o.print("用户名可用！");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        else if(uri.equals("/regedit.do")){
            //注册的跳转
            //获取前端页面的账号密码
            //调用dao层的通过账号密码注册用户的功能
            //实现跳转 转发或者重定向到login.jsp
            String name=req.getParameter("username");
            String psw=req.getParameter("password");
            try {
                int a=userDao.addUser(name,psw);//a就是sql语句执行后的受影响行数
                if (a==1){
                    //有一条数据被添加就跳转到登录界面


                    //把此时的用户名存到req对象中，在下一个页面使用
                    req.setAttribute("name",name);
                    req.getRequestDispatcher("login.jsp").forward(req,resp);
                }else {
                    //如果添加失败就重定向为注册页面
                    resp.sendRedirect("register.jsp");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        else if(uri.equals("/login.do")){
            //登录的请求
            //获取登录页面的账号和密码
            String name=req.getParameter("username");
            String psw=req.getParameter("password");
            //调用dao层的登录方法（通过用户名和密码找用户）
            User u1=new User();
            try {
                u1=userDao.findByNameAndPsw(name,psw);
                //根据刺客的u1是否为空来判断是否找到用户
                //得到一个session
                HttpSession session=req.getSession();
                if (u1!=null){
                    //找到了用户，登录成功  把u1对象存进user快递中
                    session.setAttribute("user",u1);
                    req.getRequestDispatcher("index.do").forward(req,resp);
                }else {
                    o.print("登录失败！");
                    req.setAttribute("error","用户名或密码错误");
                    req.getRequestDispatcher("index.jsp").forward(req,resp);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else if(uri.equals("/index.do")){
            //登录成功后，先到这里，得到数据再去首页
            //得到商品列表
            List<Goods> goodsList=new ArrayList<Goods>();
            //调用dao层得到所有商品
            try {
                goodsList=goodsDao.findAllGoods();
                //把商品列表存进快递list中
                req.setAttribute("list",goodsList);
                //转发至首页
                req.getRequestDispatcher("index.jsp").forward(req,resp);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }

    }
}
