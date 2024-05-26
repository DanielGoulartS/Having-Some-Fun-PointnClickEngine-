//(:))

package havingsomefun;

import havingsomefun.interactions.Interaction_Action;
import havingsomefun.interactions.InterfaceElement;
import java.awt.Color;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Daniel
 */
public class AgeLabelWarning extends Scene{

    public AgeLabelWarning(String labelTitle, LevelManager lm) {
        super(0, labelTitle, "", "", "", "", "", lm);
    
        if (labelTitle.equals("ADULT LABEL")) {
            this.lm.setBackground(Color.RED);
            interactions = addInteractions();

            try {
                this.layer1 = ImageIO.read(getClass().getResourceAsStream("/labels/18.png"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            addText("AGE LABEL:", (lm.frame.getWidth() / 2), 0, 500, 500, 30, "end");
            addText("ADULT CONTENT.", (lm.frame.getWidth() / 2), 100, 500, 500, 60, "end");
            addText("DO NOT PLAY THIS IN PRESENCE OF ", (lm.frame.getWidth() / 2), 170, 500, 500, 30, "end");
            addText("CHILDS OR MINORS. IT MIGHT CONTAIN ", (lm.frame.getWidth() / 2), 210, 500, 500, 30, "end");
            addText("SEX, DRUGS, VIOLENCE OR GAMBLING. ", (lm.frame.getWidth() / 2), 250, 500, 500, 30, "end");

        } else if (labelTitle.equals("FAMILY LABEL")) {
            this.lm.setBackground(Color.GREEN);
            interactions = addInteractions();

            addText("AGE LABEL:", (lm.frame.getWidth() / 2), 0, 500, 500, 30, "end");
            addText("FAMILY CONTENT.", (lm.frame.getWidth() / 2), 100, 500, 500, 60, "end");
            addText("IT WAS IDEALIZATED ", (lm.frame.getWidth() / 2), 200, 500, 500, 30, "end");
            addText(" TO BE PLAYED WITHOUT WARNINGS.", (lm.frame.getWidth() / 2), 240, 500, 500, 30, "end");

        } else {
            this.lm.setBackground(Color.GRAY);
            interactions = new InterfaceElement[10];

            addText("AGE LABEL:", (lm.frame.getWidth() / 2), 0, 500, 500, 30, "center");
            addText("THE DEVELOPER OF THIS GAME ", (lm.frame.getWidth() / 2), 100, 500, 500, 30, "center");
            addText("DOES NOT INFORM PROPERLY ", (lm.frame.getWidth() / 2), 140, 500, 500, 30, "center");
            addText("ABOUT AGE LABELS, SO IT WILL ", (lm.frame.getWidth() / 2), 180, 500, 500, 30, "center");
            addText(" NOT WORKS FOR PUBLIC SAFETY. ", (lm.frame.getWidth() / 2), 220, 500, 500, 30, "center");

        }

    }

    private InterfaceElement[] addInteractions(){
        interactions = new InterfaceElement[10];
        interactions[0] = new Interaction_Action("I UNDERSTAND!", lm.initialLevelId, 
                (lm.frame.getWidth() / 2) - 100, 570, lm, "");
        return interactions;
        
    }


}
