 //(:))
package havingsomefun.interactions;

import havingsomefun.LevelManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

/**
 *
 * @author Daniel
 */
public class Interaction_Action extends InterfaceElement {

    String text, textDeath;
    Font arial_20 = new Font("Arial", Font.BOLD, 14);
    FontMetrics fm;
    int next;

    public Interaction_Action(String text, int prox, int x, int y, LevelManager lm, String imageURL) {
        super("/media/placa_1.png", "/media/placa_2.png", x, y, 170, 80, lm);
        this.text = text;
        this.next = prox;
     
        if (imageURL != null && !imageURL.isBlank()) {
            image = toBufferedImage(imageURL);
            image2 = toBufferedImage(imageURL);

        }
        
    }

    public Interaction_Action(String text, int prox, int x, int y, int w, int h, LevelManager lm, String imageURL) {
        super("/media/placa_1.png", "/media/placa_2.png", x, y, w, h, lm);
        this.text = text;
        this.next = prox;
     
        if (imageURL != null && !imageURL.isBlank()) {
            image = toBufferedImage(imageURL);
            image2 = toBufferedImage(imageURL);

        }
    }

    
    
    public void interact(int resposta) {
        
        if(next != 999) {
            lm.cursor.changeScene(next);
        }else{
            lm.gameOver = true;
        }
    }

    public void drawImage(Graphics2D g) {

        if (text != null) {
            g.setColor(Color.black);
            g.setFont(arial_20);
            fm = g.getFontMetrics();

            if (fullArea.contains(lm.cursor.location)) {

                g.setColor(Color.getHSBColor(43, 33, 90));

                g.fillRect(x, y, w, h);

                g.setColor(Color.DARK_GRAY);
                drawStringMultiLine(g, text, w - (w / 6), realX - (w / 6), realY);

            } else {

                g.setColor(Color.getHSBColor(44, 36, 92));

                g.fillRect(x, y, w, h);

                g.setColor(Color.black);
                drawStringMultiLine(g, text, w - (w / 6), realX - (w / 6), realY);

            }

        } else {

            if (fullArea.contains(lm.cursor.location)) {

                g.drawImage(image2, x - 15, y - 15, w + 30, h + 30, null);

            } else {

                g.drawImage(image, x, y, w, h, null);

            }
        }
    }

}
