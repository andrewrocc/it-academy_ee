package my.first.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LoginServlet", urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String userName = req.getParameter("username");
        final String userPhone = req.getParameter("userphone");
        final String userEmail = req.getParameter("useremail");
        final String userAgent = "Приветствую пользователя " + whatTheBrowser(req.getHeader("User-Agent"));
        if (userName.isEmpty()) {
            req.setAttribute("message", "User name can not be empty");
            getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
        }
        if (userPhone.isEmpty() || userEmail.isEmpty()) {
            req.setAttribute("message", "User phone and email can not be empty");
            getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
        }

        req.setAttribute("userName", userName);
        req.setAttribute("userPhone", userPhone);
        req.setAttribute("userEmail", userEmail);
        req.setAttribute("useragent", userAgent);
        resp.setContentType("text/html");
        getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    private String whatTheBrowser(String userAgent) {
        String browser;
        if (userAgent.contains("Chrome")) {
            browser = "Chrome";
        } else if (userAgent.contains("Firefox")) {
            browser = "Firefox";
        } else if (userAgent.contains("Opera") || userAgent.contains("OPR")) {
            browser = "Opera";
        } else if (userAgent.contains("Safari")) {
            browser = "Safari";
        } else if (userAgent.contains("Mozilla")) {
            browser = "Mozilla";
        } else if (userAgent.contains("Edge")) {
            browser = "Edge";
        } else {
            browser = "unknown";
        }
        return browser;
    }
}
