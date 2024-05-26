package havingsomefun;

import havingsomefun.interactions.InterfaceElement;
import havingsomefun.interactions.Interaction_Dialog;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Daniel
 */
public class Scene{
    
    public String title, narrationText = "";
    public Interaction_Dialog narration;
    LevelManager lm;
    public BufferedImage layer1, layer2, layer3;
    public InterfaceElement[] interactions;
    public String[] levelPassword, playerPassword;

    public Scene(int id, String title, String password, String narrationText, String layer1, String layer2, String layer3, LevelManager lm) {

        this.lm = lm;
        this.title = title;
        this.narrationText = narrationText;
        this.interactions = addInteractions(id);
        
        try {
            this.layer1 = ImageIO.read(getClass().getResourceAsStream(layer1));
            this.layer2 = ImageIO.read(getClass().getResourceAsStream(layer2));
            this.layer3 = ImageIO.read(getClass().getResourceAsStream(layer3));

        } catch (IOException ex) {
            Logger.getLogger(Scene.class.getName()).log(Level.SEVERE, null, ex);
        }
        narration = new Interaction_Dialog(narrationText, 0, 0, lm.frame.getWidth(), 10, null, lm);
    }
    
    protected void drawImage(Graphics2D g) {
        g.drawImage(layer3, 0, 0, null);
        g.drawImage(layer2, 0, 0, null);
        g.drawImage(layer1, 0, 0, null);
        
        for (InterfaceElement interaction : interactions) {
            if (interaction != null) {
                interaction.drawImage(g);
            }
        }
        
        narration.drawImage(g);
        
    }


    
    public void setLevelPassword(String[] levelPassword) {

        this.playerPassword = new String[levelPassword.length];
        
        for (int num = 0; num < levelPassword.length; num++) {
            this.playerPassword[num] = "";
        }

        this.levelPassword = levelPassword;
    }
    
    
    
    private InterfaceElement[] addInteractions(int id){
        
        InterfaceElement[] newInteractions = lm.cursor.getLevelInteractions(id);
        
        return newInteractions;
    }
    
    
    public InterfaceElement addText(String text, int x, int y, int w, int h, int fontSize, String align) {
        
        InterfaceElement newInteraction = new InterfaceElement(0,text, x, y, w, h, lm, "");
        newInteraction.fontSize = fontSize;
        newInteraction.align = align;
        
        for (int i = 0; i < interactions.length; i++) {
            if (interactions[i] == null) {
                interactions[i] = newInteraction;
                return newInteraction;
            }
        }

        System.out.println("Level cheio de interações");
        return null;
    }


}