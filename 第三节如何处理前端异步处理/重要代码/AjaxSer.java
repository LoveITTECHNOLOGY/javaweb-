package action;

import com.sun.xml.internal.ws.server.ServerRtException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

/**
 * @author TYY
 * @version 1.0
 * @date 2020/6/5 21:24
 */
public class AjaxSer extends HttpServlet {

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");//设置字符编码
        resp.setContentType("text/html;charset=utf-8");//设置响应页面
        PrintWriter o=resp.getWriter();
        String abc= URLDecoder.decode(req.getParameter("word"),"UTF-8");
        o.print(abc);//前端页面的xhr.responseText值

    }
}
