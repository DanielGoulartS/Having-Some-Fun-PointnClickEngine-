//(:))

package havingsomefun.interactions;

import havingsomefun.LevelManager;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Daniel
 */
public class Interaction_Galery  extends InterfaceElement {

    public Interaction_Galery(String text, int x, int y, int w, int h, LevelManager lm, String imageURL) {
        super(imageURL, imageURL, x, y, w, h, lm);
        this.text = imageURL;
        
        if (imageURL != null && !imageURL.isBlank()) {
            
            boolean secret = true;
            
            for (String img : lm.cursor.images) {
                if (secret) {
                    if (img!= null && img.equals(imageURL)) {
                        secret = false;
                    }
                }
            }
            
            if (secret) {
                this.image = toBufferedImage("/media/character_dialog_1.png");
                this.image2 = toBufferedImage("/media/character_dialog_1.png");
            } else {
                this.image = toBufferedImage(imageURL);
                this.image2 = toBufferedImage(imageURL);
            }
        }
    }

    public void interact(int resposta) {
        
        int i = 0;
        
        for (InterfaceElement interaction : lm.currentScene.interactions) {
            if (interaction != null) {
                if (interaction.text == text) {
                    i++;
                }
            }
        }
        
        if (i > 1) {
            lm.currentScene.interactions[lm.currentScene.interactions.length - 1] = null;
        } else {
            
            int w = (70 * lm.getWidth()) / 100;
            int h = (70 * lm.getHeight()) / 100;
            int x = (lm.getWidth() / 2) - (w / 2);
            int y = (lm.getHeight() / 2) - (h / 2);

            lm.currentScene.interactions[lm.currentScene.interactions.length - 1] = new Interaction_Galery("", x, y, w, h, lm, text);
        }

        i = 0;
        
    }
    
    public void drawImage(Graphics2D g) {

        if (fullArea.contains(lm.cursor.location)) {

            g.setColor(Color.getHSBColor(44, 36, 92));
            g.fillRect(x - 25, y - 25, w + 50, h + 50);
            g.drawImage(image, x - 15, y - 15, w + 30, h + 30, null);
            
            
        } else {

            g.drawImage(image, x, y, w, h, null);

        }

    }

    
    
}
