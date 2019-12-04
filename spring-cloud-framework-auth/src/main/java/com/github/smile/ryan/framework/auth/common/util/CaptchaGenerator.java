package com.github.smile.ryan.framework.auth.common.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 名称：CaptchaGenerator
 * 描述：CaptchaGenerator.java
 * </pre>
 *
 * @author <a href="mailto:smile.ryan@outlook.com">Ryan Chen</a>
 * @since v1.0.0
 */
@Slf4j
public class CaptchaGenerator {

  private static final String SOURCE_CODES = "0123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghjkmnpqrstuvwxyz";
  private static Random random = new Random();

  private static String createCodeStr(int codeLength) {
    Random random = new Random();
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < codeLength; i++) {
      int key = random.nextInt(SOURCE_CODES.length() - 1);
      builder.append(SOURCE_CODES.charAt(key));
    }
    return builder.toString();
  }

  public static void generate(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    String captcha = createCodeStr(4);
    int verifySize = captcha.length();
    int height = 36;
    int width = 95;
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    Random rand = new Random();
    Graphics2D g2 = image.createGraphics();
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    Color[] colors = new Color[5];
    Color[] colorSpaces = new Color[]{Color.WHITE, Color.CYAN,
        Color.GRAY, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE,
        Color.PINK, Color.YELLOW};
    float[] fractions = new float[colors.length];
    for (int i = 0; i < colors.length; i++) {
      colors[i] = colorSpaces[rand.nextInt(colorSpaces.length)];
      fractions[i] = rand.nextFloat();
    }
    Arrays.sort(fractions);

    // 设置边框色
    Color c = getRandColor(200, 255);
    g2.setColor(c);
    g2.fillRect(0, 0, width, height);

    // 设置背景色
    g2.setColor(c);
    g2.fillRect(0, 2, width, height - 4);

    //绘制干扰线
    Random random = new Random();
    // 设置线条的颜色
    g2.setColor(getRandColor(128, 160));
    BasicStroke stokeLine = new BasicStroke(1.5f);
    for (int i = 0; i < 10; i++) {
      int x = random.nextInt(width);
      int y = random.nextInt(height);
      int xl = random.nextInt(width);
      int yl = random.nextInt(height);
      g2.setStroke(stokeLine);
      g2.drawLine(x, y, xl, yl);
    }

    // 添加噪点
    // 噪声率
    float yawpRate = 0.05f;
    int area = (int) (yawpRate * width * height);
    for (int i = 0; i < area; i++) {
      int x = random.nextInt(width);
      int y = random.nextInt(height);
      int rgb = getRandomIntColor();
      image.setRGB(x, y, rgb);
    }

    g2.setColor(getRandColor(0, 160));
    int fontSize = height - 4;
    Font font = new Font("Algerian", Font.ITALIC, fontSize);
    g2.setFont(font);
    char[] chars = captcha.toCharArray();
    for (int i = 0; i < verifySize; i++) {
      AffineTransform affine = new AffineTransform();
      affine.setToRotation(Math.PI / 4 * rand.nextDouble() * (rand.nextBoolean() ? 1 : -1),
          (width / verifySize) * i + fontSize / 2, height / 2);
      g2.setTransform(affine);
      g2.drawChars(chars, i, 1, ((width - 10) / verifySize) * i + 5,
          height / 2 + fontSize / 2 - 10);
    }
    g2.dispose();
    log.info("SessionId:" + request.getSession().getId() + ", Captcha:" + captcha);
    HttpSession session = request.getSession();
    session.setAttribute("captcha", captcha);
    OutputStream os = response.getOutputStream();
    ImageIO.write(image, "jpg", os);
    os.flush();
    os.close();
  }

  private static int[] getRandomRgb() {
    int[] rgb = new int[3];
    for (int i = 0; i < 3; i++) {
      rgb[i] = random.nextInt(255);
    }
    return rgb;
  }

  private static int getRandomIntColor() {
    int[] rgb = getRandomRgb();
    int color = 0;
    for (int c : rgb) {
      color = color << 8;
      color = color | c;
    }
    return color;
  }


  private static Color getRandColor(int fc, int bc) {
    Random random = new Random();
    if (fc > 255) {
      fc = 255;
    }
    if (bc > 255) {
      bc = 255;
    }
    int r = fc + random.nextInt(bc - fc);
    int g = fc + random.nextInt(bc - fc);
    int b = fc + random.nextInt(bc - fc);
    return new Color(r, g, b);
  }


}
