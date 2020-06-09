package action;

import dao.UserDao;
import util.sendMessageUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class XMservlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter o=resp.getWriter();
        String uri=req.getRequestURI();//得到请求路径的uri
        UserDao userDao=new UserDao();//生成一个用户的dao对象
        if(uri.equals("/send.do")){
            //发送验证码的请求
            String tel=req.getParameter("tel");
            //得到一个6位随机验证码
            String code= sendMessageUtil.getRandomCode(6);
            //发送短信 mobilecode状态码
            //send(账号，密码，手机号，随机验证码)
            int mobilecode=sendMessageUtil.send("zgycsmb","d41d8cd98f00b204e98011",tel,code);
            String message=sendMessageUtil.getMessage(mobilecode);
            System.out.println(message);
            o.print(code);
        }
    }
}
