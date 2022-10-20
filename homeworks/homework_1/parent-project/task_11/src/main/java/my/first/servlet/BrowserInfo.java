package my.first.servlet;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "BrowserInfo", urlPatterns = "/detect.do")
public class BrowserInfo extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String userAgent = "Приветствую пользователя " + whatTheBrowser(req.getHeader("User-Agent"));
        req.setAttribute("userAgent", userAgent);
        resp.setContentType("text/html");
        getServletContext().getRequestDispatcher("/jsp/browserInfo.jsp").forward(req, resp);
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
