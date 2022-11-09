package my.first.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

@WebServlet(name = "HelloWorldServlet", urlPatterns = "/sayhello")
public class HelloWorldServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static int counter = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("image/jpg");
        counter++;

        BufferedImage image = drawRainbow(req);
        ServletOutputStream out = resp.getOutputStream();
        ImageIO.write(image, "jpg", out);
    }

    private BufferedImage drawRainbow(HttpServletRequest req) {
        int imageWidth = 900;
        int imageHeight = 900;
        BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, imageWidth, imageHeight);

        int radius = 50;
        final Color VIOLET = new Color(128, 0, 128);
        final Color INDIGO = new Color(0, 191, 255);
        Color[] colors = { Color.WHITE, VIOLET, Color.BLUE, INDIGO,
                Color.GREEN, Color.YELLOW, Color.ORANGE, Color.RED };
        for (int counter = colors.length; counter > 0; counter--) {
            graphics.setColor(colors[counter - 1]);
            graphics.fillArc(imageWidth - counter * radius,
                    imageHeight - counter * radius,
                    counter * radius * 2, counter * radius * 2, 0, 180);
        }

        graphics.setFont(new Font("Helvetica", Font.BOLD, 48));
        graphics.setColor(Color.BLACK);
        graphics.drawString("Say hello!", 50, 80);
        graphics.drawString("Request number : " + counter, 50, 170);
        graphics.setFont(new Font("Helvetica", Font.PLAIN, 30));
        graphics.drawString("Session id: " + req.getSession().getId(), 55, 250);
        return image;
    }
}
