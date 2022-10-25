package my.first.servlet;

import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.time.LocalDateTime;
import javax.servlet.ServletConfig;
import java.awt.image.BufferedImage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet(name = "HelloWorldServlet", urlPatterns = "/sayhello")
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
        resp.setContentType("image/jpg");
        counter++;

        //region image/jpg content page
        int width = 900;
        int height = 900;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);

        /*draw the rainbow*/
        int radius = 50;
        final Color VIOLET = new Color(128, 0, 128);
        final Color INDIGO = new Color(0, 191, 255);
        Color[] colors = { Color.WHITE, Color.WHITE, VIOLET, Color.BLUE, INDIGO,
                        Color.GREEN, Color.YELLOW, Color.ORANGE, Color.RED };
        for (int counter = colors.length; counter > 0; counter--) {
            graphics.setColor(colors[counter - 1]);
            graphics.fillArc(width - counter * radius,
                    height - counter * radius,
                    counter * radius * 2, counter * radius * 2, 0, 180);
        }

        graphics.setFont(new Font("Helvetica", Font.BOLD, 48));
        graphics.setColor(Color.BLACK);
        graphics.drawString("Say hello!", 50, 80);
        graphics.drawString("Request number : " + counter, 50, 170);
        graphics.setFont(new Font("Helvetica", Font.PLAIN, 30));
        graphics.drawString("Session id: " + req.getSession().getId(), 55, 250);
        ServletOutputStream out = resp.getOutputStream();
        ImageIO.write(image, "jpg", out);
        //endregion
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
