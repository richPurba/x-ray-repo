package main.com.xray.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    try{
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet Controller</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1> Hello </h1>");
        out.println("</body>");
        out.println("</html>");
    } finally{
        out.close();
    }
}
}
