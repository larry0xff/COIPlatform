package zhongd.coiplatform.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author xiezd 2018-01-05 9:32
 */
public class ImageUtil {
    private static final char[] CHARS = { '2', '3', '4', '5', '6', '7', '8',
            '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M',
            'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
    private static Random random = new Random();
    public static BufferedImage generateVerifyCode(String code,int width, int height){
        BufferedImage bi = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        Color color = getRandomColor();
        Color reverse = getReverseColor(color);
        //g.setColor(color);
        g.fillRect(0, 0, width, height);
        g.setColor(reverse);
        g.drawString(code, 0, 15);
        for (int i = 0, n = random.nextInt(1); i < n; i++)
        {
            //g.drawRect(random.nextInt(width), random.nextInt(height), 1, 1);
        }
        return bi;
    }
    public static String getRandomString()
    {
        StringBuffer buffer = new StringBuffer();
        for(int i = 0; i < 5; i++)
        {
            buffer.append(CHARS[random.nextInt(CHARS.length)]);
        }
        return buffer.toString();
    }
    private static Color getRandomColor()
    {
        return new Color(random.nextInt(155)+100,random.nextInt(155)+100,
                random.nextInt(155)+100);
    }
    /*
     * 返回某颜色的反色
     */
    private static Color getReverseColor(Color c)
    {
        return new Color(255 - c.getRed(), 255 - c.getGreen(),
                255 - c.getBlue());
    }
}

