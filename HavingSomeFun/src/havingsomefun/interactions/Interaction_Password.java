//(:))

package havingsomefun.interactions;

import havingsomefun.LevelManager;
import java.util.Arrays;

/**
 *
 * @author Daniel
 */
public class Interaction_Password extends Interaction_Action{

    String passwordDigit;

    public Interaction_Password(String image, String image2, int prox, String passwordDigit, String imgDeath, String textDeath, 
            int x, int y, int w, int h, LevelManager lm) {
        super(image, image2, prox, imgDeath, textDeath, x, y, w, h, lm);
        this.passwordDigit = passwordDigit;
    }

    public Interaction_Password(String image, String image2, int prox, String passwordDigit, int x, int y, int w, int h, LevelManager lm) {
        super(image, image2, prox, x, y, w, h, lm);
        this.passwordDigit = passwordDigit;
    }

    @Override
    public void interact(int resposta) {

        stackPassword(0, passwordDigit);

        if (Arrays.toString(lm.currentScene.playerPassword).equals(Arrays.toString(lm.currentScene.levelPassword))) {
            //NOVO NIVEL
            
            resetPassword();
                
            lm.previousScene = lm.currentScene;
            lm.currentScene = lm.cenas[next];

        } else {
            if (this.imgDeath != null) {
                
                resetPassword();
                
                lm.cenas[next].layer3 = imgDeath;
                lm.cenas[next].setNarration(textDeath);
                
                
                lm.previousScene = lm.currentScene;
                lm.currentScene = lm.cenas[next];
            }
        }

    }
    
    public void stackPassword(int step, String value) {

        //If empty
        if (lm.currentScene.playerPassword[step].isBlank()) {
            lm.currentScene.playerPassword[step] = value;
        //If it's full
        } else {
            //If there is another step
            if (lm.currentScene.playerPassword.length > step + 1) {
                stackPassword(step + 1, lm.currentScene.playerPassword[step]);
                lm.currentScene.playerPassword[step] = value;
            } else {
            //If there isn't another step
                lm.currentScene.playerPassword[step] = value;
            }
        }
    }
    
    public void resetPassword() {
        for (String character : lm.currentScene.playerPassword) {
            character = "";
        }
    }

}
