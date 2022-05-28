package FormHelpers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.security.SecureRandom;
import java.util.Random;

public class Captcha {

    private static String img;
    //The width of the verification code image.
    private int width = 160;
    //The height of the verification code image.
    private int height = 50;
    //Number of characters in the verification code
    private int codeCount = 6;
    private Font font, rotatedFont;
    AffineTransform affineTransform;
    private int codeX = 0;
    //font height
    private int fontHeight;
    private int codeY;
    char[] codeSequence = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public Captcha() {
        init();
        ToImages();
    }

    /**
     * Initialize and verify image properties
     */
    public void init() {
        codeX = (width - 30) / (codeCount + 1);
        fontHeight = height - 8;
        codeY = height - 10;
    }
    
    public static String getImageCodeCaptcha(){
        return img;
    }
    public BufferedImage ToImages() {
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        try {
            Graphics2D g = buffImg.createGraphics();
            //Create a random number generator class
            Random random = new Random();
            //Fill the image with white
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, width, height);
            //Create a font, the size of the font should be determined according to the height of the picture.
            
            //Draw the border.
            g.setColor(Color.BLACK);
            g.drawRect(0, 0, width - 1, height - 1);
            //Randomly generate an interference line, so that the authentication code in the image is not easily detected by other programs.
            for (int i = 0; i < 250; i++) {
                int x = random.nextInt(width);
                int y = random.nextInt(height);
                g.drawOval(x, y, 1, 1);
            }

            //randomCode is used to save the randomly generated verification code so that the user can log in for verification.
            StringBuffer randomCode = new StringBuffer();
            int red = 0, green = 0, blue = 0;
            
            //Randomly generate a verification code with codeCount numbers.
            for (int i = 0; i < codeCount; i++) {
                //Get the randomly generated verification code number.
                String strRand = String.valueOf(codeSequence[random.nextInt(62)]);
                int randRadInt = new SecureRandom().nextInt(35 + 35) - 35;
                //System.out.println(randRadInt);
                switch (i) {
                    case 0:
                        font = new Font("Arial", Font.PLAIN, fontHeight);
                        affineTransform = new AffineTransform();
                        affineTransform.rotate(Math.toRadians(randRadInt), 0, 0);
                        rotatedFont = font.deriveFont(affineTransform);                       
                        break;
                    case 1:
                        font = new Font("Segoe UI", Font.PLAIN, fontHeight);
                        affineTransform = new AffineTransform();                        
                        affineTransform.rotate(Math.toRadians(randRadInt), 0, 0);
                        rotatedFont = font.deriveFont(affineTransform); 
                        break;
                    case 2:
                        font = new Font("Times New Roman", Font.PLAIN, fontHeight);   
                        affineTransform = new AffineTransform();
                        affineTransform.rotate(Math.toRadians(randRadInt), 0, 0);
                        rotatedFont = font.deriveFont(affineTransform); 
                        break;
                    case 3:
                        font = new Font("Serif", Font.PLAIN, fontHeight);
                        affineTransform = new AffineTransform();
                        affineTransform.rotate(Math.toRadians(randRadInt), 0, 0);
                        rotatedFont = font.deriveFont(affineTransform); 
                        break;
                    case 4:
                        font = new Font("SansSerif", Font.PLAIN, fontHeight);
                        affineTransform = new AffineTransform();
                        affineTransform.rotate(Math.toRadians(randRadInt), 0, 0);
                        rotatedFont = font.deriveFont(affineTransform); 
                        break;   
                    case 5:
                        font = new Font("Verdana", Font.PLAIN, fontHeight);
                        affineTransform = new AffineTransform();
                        affineTransform.rotate(Math.toRadians(randRadInt), 0, 0);
                        rotatedFont = font.deriveFont(affineTransform); 
                        break;
                    default:
                        font = new Font("Roboto", Font.PLAIN, fontHeight);
                        affineTransform = new AffineTransform();
                        affineTransform.rotate(Math.toRadians(randRadInt), 0, 0);
                        rotatedFont = font.deriveFont(affineTransform); 
                        break;
                }
                //Set the font
                g.setFont(rotatedFont);
                //Generate random color components to construct the color value, so that the color value of each number output will be different.
                red = random.nextInt(255);
                green = random.nextInt(255);
                blue = random.nextInt(255);
                while (red == 255 && green == 255 && blue == 255) {
                    red = random.nextInt(255);
                    green = random.nextInt(255);
                    blue = random.nextInt(255);
                }
                //Draw the verification code into the image with a randomly generated color.              
                g.setColor(new Color(red, green, blue));               
                g.drawString(strRand, i * codeX + codeX, codeY + 1);               
                //Combine the four random numbers generated.
                randomCode.append(strRand);
            }
            img = randomCode.toString();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return buffImg;
    }
}
