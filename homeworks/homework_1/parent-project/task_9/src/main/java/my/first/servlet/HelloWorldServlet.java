package my.first.servlet;

import java.io.*;
import java.time.LocalDateTime;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.nio.charset.StandardCharsets;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HelloWorldServlet", urlPatterns = "/sayhello")
public class HelloWorldServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static int counter = 0;

    private File file;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        String userHomeDirectory = System.getProperty("user.home");
        userHomeDirectory = userHomeDirectory.concat("/IDEA_temp");
        File dir = new File(userHomeDirectory);
        if (!dir.exists()) {
            dir.mkdir();
        }
        String fileName = userHomeDirectory.concat("/counter.txt");
        file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        counter++;
        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();
        out.println("<html><head><title>First servlet</title></head>");
        out.println("<body><h1>this is first servlet</h1>");
        out.println("<h2>counter = " + counter + " </h2>");
        out.println("</body></html>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();

        try (BufferedWriter BUFFERED_WRITER = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file, true), StandardCharsets.UTF_8))) {
            BUFFERED_WRITER.write(LocalDateTime.now() + " -> counter = " + counter + "\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
