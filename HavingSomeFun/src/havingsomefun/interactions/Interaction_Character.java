//(:))

package havingsomefun.interactions;

import havingsomefun.Character;
import havingsomefun.LevelManager;

/**
 *
 * @author Daniel
 */
public class Interaction_Character extends InterfaceElement{

    public Character character;
    
    public Interaction_Character(String image, String image2, int x, int y, int w, int h, Character character, LevelManager lm) {

        super("/media/character_dialog_1.png", "/media/character_dialog_2.png", x, y, w, h, lm);
        if ((image != null && image2 != null) && (!image.isBlank() && !image2.isBlank()) ) {
            this.image = super.toBufferedImage(image);
            this.image2 = super.toBufferedImage(image2);
        }
        
        this.character = character;
    }
    
    
    @Override
    public void interact(int resposta) {
        
        character.startConversation();
        
    }
}
