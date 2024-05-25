//(:))

package havingsomefun;

import java.io.IOException;
import javax.imageio.ImageIO;


/**
 *
 * @author Daniel
 */
public final class Character {
    
    public int id;
    public String[] images = new String[8];
    //0 Black, 1 Background, 2-3 Character Reactions, 4-6 Fight, 7 End Fight
    public String name, text, ending;
    Scene characterScene;
    public LevelManager lm;
    public boolean isBoss;
    Conversation[] conversations = new Conversation[11];
    Conversation currentConversation;
    
    public Character(int id, String name, String ending, LevelManager lm, String[] images, Conversation[] conversations, boolean isBoss) {
        
        this.id = id;
        this.name = name;
        this.ending = ending;
        this.lm = lm;
        this.conversations = conversations;
        this.isBoss = isBoss;
        this.images = images;


    }
    
    public void changeScene(Scene toBeReplaced, Scene thatReplaces){
        
        characterScene = toBeReplaced;
        lm.currentScene = thatReplaces;
        
    }
    public void startConversation(){
        changeScene(lm.currentScene, new Scene(999, "Dialog with " + name, "", "", images[2], images[1], images[0], lm));
        changeConversation(0);

    }
    
    public void changeConversation(int conv) {
        Conversation conversation = new Conversation(this, 0, "", 2);

        if (conv != 0) {
            for (Conversation cnvs : conversations) {
                if (cnvs != null && cnvs.id == conv) {
                    conversation = cnvs;
                }
            }
        } else {
            conversation = conversations[conv];
        }

        try {
            lm.currentScene.narration.text = conversation.speech;
            lm.currentScene.interactions = null;
            lm.currentScene.interactions = conversation.answers;
            lm.currentScene.layer1 = ImageIO.read(getClass().getResourceAsStream(images[conversation.reaction]));
            
            lm.cursor.registerImage(images[conversation.reaction]);
            
        } catch (IOException ex) {
            System.err.println(ex);
            ex.printStackTrace();
        }
        
    }

}