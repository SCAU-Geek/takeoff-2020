package Showname;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author czy
 * @description 根据学号找出名字
 * @date 2020/11/14
 */
public class Showname extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String info = null;
        String number = req.getParameter("number");
        String password = req.getParameter("password");
        GET_NAME get_name = new GET_NAME();
        try {
            info = get_name.login(number,password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write(info);
        writer.close();
    }
}