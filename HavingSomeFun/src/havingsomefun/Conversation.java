//(:))

package havingsomefun;

import havingsomefun.interactions.Interaction_Dialog;

/**
 *
 * @author Daniel
 */
public class Conversation {
    
    public int id, reaction;
    public Character character;
    public String speech;
    public Interaction_Dialog[] answers;

    
    public Conversation(Character character, int id, String speech, int reaction) {
        this.id = id;
        this.character = character;
        this.speech = speech;
        this.reaction = reaction;
        this.answers = this.character.lm.cursor.getInteractionDialogs(this);
        
    }
/*
    public void addAnswer(String text, Character character, LevelManager lm, int resposta, int nextLevel){
        
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == null) {
                if (i == 0) {
                    answers[i] = new Interaction_Dialog(text, 100, 100, character, lm, this, resposta, nextLevel);
                    return;
                } else {
                    answers[i] = new Interaction_Dialog(text, 100,
                            this.answers[i - 1].y + this.answers[i - 1].fullArea.height,
                            character, lm, this, resposta, nextLevel);
                    return;
                }
            }
        }

    }
    
    public void addNeutralEnding(String text, LevelManager lm, int resposta){
        
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == null) {
                if (i == 0) {
                    answers[i] = new Interaction_Dialog(text, 100, 100, character, lm, this, resposta, true);
                    return;
                } else {
                    answers[i] = new Interaction_Dialog(text, 100,
                            this.answers[i - 1].y + this.answers[i - 1].fullArea.height,
                            character, lm, this, resposta, true);
                    return;
                }
            }
        }

    }
*/
}
