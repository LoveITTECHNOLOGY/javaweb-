package action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author TYY
 * @version 1.0
 * @date 2020/6/5 19:24
 */
public class FruitServlet extends HttpServlet {

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //页面跳转就是进此方法
        //System.out.println("成功跳转！");
        req.setCharacterEncoding("utf-8");//设置字符编码
        resp.setContentType("text/html;charset=utf-8");//设置响应页面
        //通过前端页面的name属性得到values值 前端页面是复选框所以这里是数组
        String[] arr=req.getParameterValues("fruit");
        PrintWriter o=resp.getWriter();//生成一个可写内容的对象
        o.print("你选择的水果是：");
        for (int i = 0; i <arr.length ; i++) {
            if (i!=arr.length-1){
                o.print(arr[i]+",");
            }else{
                o.print(arr[i]+"。");
            }
        }
    }
}
