package havingsomefun.interactions;

import havingsomefun.LevelManager;
import havingsomefun.Character;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Daniel
 */
public class InterfaceElement {

    public LevelManager lm;
    public BufferedImage image, image2;
    Font arial_60 = new Font("Arial", Font.BOLD, 40), arial_30 = new Font("Arial", Font.ROMAN_BASELINE, 30);
    public String text, type, align;
    public int id, x, y, w, h, realX, realY, realW, realH, fontSize = 0;
    public Rectangle fullArea, solidArea;

    public InterfaceElement(String image, String image2, int x, int y, int w, int h, LevelManager lm) {

        this.lm = lm;
        this.image = toBufferedImage(image);
        this.image2 = toBufferedImage(image2);
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        realX = x + (w / 4);
        realY = y + (h / 4);
        realW = w / 2;
        realH = h / 2;

        fullArea = new Rectangle(x, y, w, h);
        solidArea = new Rectangle(realX, realY, realW, realH);
    }
    
    public InterfaceElement(int id, String text, int x, int y, int w, int h, LevelManager lm, String type) {

        this.lm = lm;
        this.id = id;
        this.text = text;
        this.type = type;

        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        realX = x + (w / 4);
        realY = y + (h / 4);
        realW = w / 2;
        realH = h / 2;

        fullArea = new Rectangle(x, y, w, h);
        solidArea = new Rectangle(realX, realY, realW, realH);
        
    }
    
    public InterfaceElement(int id, String text, int x, int y, int w, int h, LevelManager lm, String type, int fontSize) {

        this.lm = lm;
        this.id = id;
        this.text = text;
        this.type = type;

        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        realX = x + (w / 4);
        realY = y + (h / 4);
        realW = w / 2;
        realH = h / 2;

        fullArea = new Rectangle(x, y, w, h);
        solidArea = new Rectangle(realX, realY, realW, realH);
    }
    

    public void drawImage(Graphics2D g) {
        
        if(fontSize == 30){
            g.setFont(arial_30);
        }else{
            g.setFont(arial_60);
        }
        
        if (image != null && image2 != null) {

            if (solidArea.contains(lm.cursor.location)) {
                
                g.drawImage(image2, x, y, w, h, null);

            } else {
                
                g.drawImage(image, x, y, w, h, null);

            }

        } else{
            int textLength = (int) g.getFontMetrics().getStringBounds(text, g).getWidth();
            
            g.setColor(Color.DARK_GRAY);
            
            if(align == "center"){
            g.drawString(text, x - (textLength / 2), y + 50);
            }else if(align == "end"){
            g.drawString(text, lm.frame.getWidth() - (textLength + 50), y + 50);
            }else if(align == "start"){
            g.drawString(text, 70, y + 50);
            }else{
            g.drawString(text, Integer.parseInt(align), y + 50);
            }
                
        }
    }

    public int drawStringMultiLine(Graphics2D g, String text, int lineWidth, int x, int y) {
        FontMetrics m = g.getFontMetrics();
        int num = 0;
        y += m.getHeight();
        if (m.stringWidth(text) < lineWidth) {
            g.drawString(text, x, y);
            num ++;
        } else {
            String[] words = text.split(" ");
            String currentLine = words[0];
            for (int i = 1; i < words.length; i++) {
                if (m.stringWidth(currentLine + words[i]) < lineWidth) {
                    currentLine += " " + words[i];
                } else {
                    g.drawString(currentLine, x, y);
                    num ++;
                    y += m.getHeight();
                    currentLine = words[i];
                }
            }
            if (currentLine.trim().length() > 0) {
                g.drawString(currentLine, x, y);
                num++;
            }
        }
        return num;
    }
    
    public void interact(int resposta) {
    }

    public InterfaceElement buildInteraction(String param) {

        switch (type) {
            case "A":
                int next = lm.cursor.getInteractionAction(id);
                if (w == 0 && h == 0) {
                    return new Interaction_Action(text, next, x, y, lm, param);
                } else {
                    return new Interaction_Action(text, next, x, y, w, h, lm, param);
                }
                
            case "C":
                Character character = lm.cursor.getInteractionCharacter(id);
                return new Interaction_Character(null, null, x, y, w, h, character, lm);
                
            case "P":
                break;
                
            case "G":
                return new Interaction_Galery(text, x, y, w, h, lm, param);
        }
        
        return null;
    }
    
    public BufferedImage toBufferedImage(String URL){
        BufferedImage img = null;
        try {
            img = ImageIO.read(getClass().getResourceAsStream(URL));
        } catch (IOException ex) {
            System.err.println("888" + ex);
        }
        return img;
        
    }
}
