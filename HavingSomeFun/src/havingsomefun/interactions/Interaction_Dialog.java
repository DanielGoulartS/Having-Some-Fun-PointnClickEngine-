//(:))
package havingsomefun.interactions;

import havingsomefun.Character;
import havingsomefun.LevelManager;
import havingsomefun.Conversation;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Daniel
 */
public class Interaction_Dialog extends InterfaceElement {

    public String text;
    Font arial_20 = new Font("Arial", Font.BOLD, 14);
    FontMetrics fm;
    Character character;
    public boolean clickable = true, neutralEnding = false;
    int nextConversation = 1, nextCharacter, nextScene;
    Conversation conversa;

    public Interaction_Dialog(String text, int x, int y, int w, int h, Character character, LevelManager lm) {
        super("","", x, y, w, h, lm);
        this.text = text;
        this.character = character;
        
        this.lm = lm;

        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        realX = x + 10;
        realY = y + 10;
        realW = w - 20;
        realH = h;

        fullArea = new Rectangle(x, y, w, h);
        solidArea = new Rectangle(realX, realY, realW, realH);
    }
    
    public Interaction_Dialog(String text, int x, int y, Character character, LevelManager lm, Conversation conversa, int resposta) {
        super("","", x, y, 250, 80, lm);
        this.text = text;
        this.character = character;
        this.conversa = conversa;
        this.nextConversation = resposta;
        this.nextScene = 1;
        
        realX = x + 10;
        realY = y + 10;
        realW = w;
        realH = h;
    }
    
    public Interaction_Dialog(String text, int x, int y, Character character, LevelManager lm, Conversation conversa, int resposta,
            boolean neutralEnding) {
        super("", "", x, y, 250, 80, lm);
        this.text = text;
        this.character = character;
        this.conversa = conversa;
        this.nextConversation = resposta;
        this.neutralEnding = true;
        
        realX = x + 10;
        realY = y + 10;
        realW = w;
        realH = h;
    }
    
    public Interaction_Dialog(String text, int x, int y, Character character, LevelManager lm, Conversation conversa, int nextConversation, int nextCharacter, int nextScene) {
        super("","", x, y, 250, 80, lm);
        this.text = text;
        this.character = character;
        this.conversa = conversa;
        this.nextConversation = nextConversation;
        this.nextCharacter = nextCharacter;
        this.nextScene = nextScene;
        
        realX = x + 10;
        realY = y + 10;
        realW = w;
        realH = h;
    }
    
        @Override
    public void interact(int resposta) {

            if (nextConversation != 0) {
                character.changeConversation(nextConversation);
            
            } else {
                
                if (character.isBoss) {
                    if (nextScene == 15) {
                        lm.ending = character.ending;
                        lm.gameOver = true;
                    } else if (nextScene == 16) {
                        lm.ending = "as an Adventurer";
                        lm.cursor.changeScene(nextScene);
                    }
                } else {
                    lm.cursor.changeScene(nextScene);
                }
                
            }
            
    }

    @Override
    public void drawImage(Graphics2D g) {
        if(text == null || text.equals("")){
            return;
        }
        
        g.setColor(Color.black);
        g.setFont(arial_20);
        fm = g.getFontMetrics();

        int variableH = fm.getHeight();
        int i = drawStringMultiLine(g, text, realW, realX, realY) + 2;
        fullArea.height = variableH * i;

        if (fullArea.contains(lm.cursor.location)) {

            g.setColor(Color.getHSBColor(43, 33, 90));
            g.fillRect(x, y, w, variableH * i);

            g.setColor(Color.DARK_GRAY);
            drawStringMultiLine(g, text, realW, realX, realY);

        } else {

            g.setColor(Color.getHSBColor(44, 36, 92));
            g.fillRect(x, y, w, variableH * i);

            g.setColor(Color.black);
            drawStringMultiLine(g, text, realW, realX, realY);

        }

    }

}
