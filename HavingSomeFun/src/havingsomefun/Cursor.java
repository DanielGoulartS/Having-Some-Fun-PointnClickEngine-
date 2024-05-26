//(:))

package havingsomefun;

import havingsomefun.interactions.InterfaceElement;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import havingsomefun.Connection.Connection;
import havingsomefun.interactions.Interaction_Dialog;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Daniel
 */
public class Cursor implements MouseListener{

    Connection connection;
    LevelManager lm;
    public Point location;
    private int buildingScene;
    public String[] images;
    

    public Cursor(LevelManager lm) {
        this.lm = lm;
        this.connection = new Connection();
        
        ResultSet rs = selectImages();
        
        try {
            if (rs != null) {
                rs.last();
                images = new String[rs.getRow()];
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(Cursor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Point updateLocation(){
        
        location = MouseInfo.getPointerInfo().getLocation();
        
        location.x = (int) location.getX() - lm.frame.getX() - 8;
        location.y = (int) location.getY() - lm.frame.getY() - (lm.frame.getHeight() - lm.getHeight()) + 8;

        return location;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        for(InterfaceElement interaction : lm.currentScene.interactions){
            
            if (interaction != null) {
                
                if (interaction.fullArea.contains(location)) {
                    interaction.interact(0);
                    return;
                }
            }
        }
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("You could save");
    }
    
    
    public boolean changeScene(int sceneId){
        
        connection.conectar();
        try {
            int i;
            String[] url = {"","",""};
            ResultSet rs = connection.executeQuery("SELECT * FROM scene WHERE id = " + sceneId + ";");
            ResultSet rsImg = connection.executeQuery("SELECT i.image FROM images AS i "
                    + "INNER JOIN scene_images AS si ON i.id = si.images_id "
                    + "WHERE si.scene_id = " + sceneId + ";");
            rs.next();

            i = 0;
            while(rsImg.next()){
                url[i] = rsImg.getString("image");
                i++;
            }
            
            lm.changeScene(sceneId, rs.getString("title"), rs.getString("password"), rs.getString("narrationText"), url[0], url[1], url[2]);
            
            
        } catch (SQLException ex) {
            System.err.println(ex);
            Logger.getLogger(Cursor.class.getName()).log(Level.SEVERE, null, ex);
            connection.desconectar();
            return false;
        }
            connection.desconectar();
            return true;
    }
    
    public InterfaceElement[] getLevelInteractions(int sceneId) {
        
        buildingScene = sceneId;

        InterfaceElement[] interactions = new InterfaceElement[10];
        
        connection.conectar();
        try {
            int i = 0;
            
            //Action
            ResultSet rsInt = connection.executeQuery("SELECT i.id, i.x, i.y, i.w, i.h, i.text, i.type, ia.next, im.image"
                    + " FROM interaction AS i"
                    + " INNER JOIN interaction_action AS ia ON i.id = ia.interaction_id"
                    + " LEFT JOIN interaction_images AS ii ON ii.interaction_id = ia.interaction_id"
                    + " LEFT JOIN images AS im ON ii.images_id = im.id "
                    + " WHERE ia.scene_id = " + sceneId + ";");
            
            while(rsInt.next()){
                //System.out.println(rsInt.getString("image"));
                interactions[i] = new InterfaceElement(rsInt.getInt("id"), rsInt.getString("text"), rsInt.getInt("x"), rsInt.getInt("y"), 
                        rsInt.getInt("w"), rsInt.getInt("h"), lm, rsInt.getString("type")).buildInteraction(rsInt.getString("image"));
                
                i++;
            }
            

            
            //Character
            rsInt = connection.executeQuery("SELECT i.id, i.x, i.y, i.w, i.h, i.text, i.type"
                    + " FROM interaction AS i"
                    + " INNER JOIN interaction_character AS ic ON i.id = ic.interaction_id"
                    + " WHERE ic.scene_id = " + sceneId + ";");
            
            while(rsInt.next()){
                interactions[i] = new InterfaceElement(rsInt.getInt("id"), rsInt.getString("text"), rsInt.getInt("x"), rsInt.getInt("y"), 
                        rsInt.getInt("w"), rsInt.getInt("h"), lm, rsInt.getString("type")).buildInteraction("");
                i++;
            }
            
            
            
            //Password
            rsInt = connection.executeQuery("SELECT i.id, i.x, i.y, i.w, i.h, i.text, i.type, ip.next"
                    + " FROM interaction AS i"
                    + " INNER JOIN interaction_password AS ip ON i.id = ip.interaction_id"
                    + " WHERE ip.scene_id = " + sceneId + ";");
            
            while(rsInt.next()){
                interactions[i] = new InterfaceElement(rsInt.getInt("id"), rsInt.getString("text"), rsInt.getInt("x"), rsInt.getInt("y"), 
                        rsInt.getInt("w"), rsInt.getInt("h"), lm, rsInt.getString("type")).buildInteraction("");
                i++;
            }
            
            
            //Galery
            rsInt = connection.executeQuery("SELECT i.id, i.x, i.y, i.w, i.h, i.text, i.type, im.image"
                    + " FROM interaction AS i"
                    + " INNER JOIN interaction_galery AS ig ON i.id = ig.interaction_id"
                    + " LEFT JOIN interaction_images AS ii ON ii.interaction_id = ig.interaction_id"
                    + " LEFT JOIN images AS im ON ii.images_id = im.id"
                    + " WHERE ig.scene_id = " + sceneId + ";");

            int j = 0;
            
            while(rsInt.next()){
                if(interactions[interactions.length - 1] != null){
                    System.out.println("Too much Interactions_Galery in this level. At least one must be empty.");
                    return interactions;
                }
                if (j == 0) {
                    interactions[i] = new InterfaceElement(rsInt.getInt("id"), rsInt.getString("text"), 60, 500,
                            256, 144, lm, rsInt.getString("type")).buildInteraction(rsInt.getString("image"));
                }else{
                    interactions[i] = new InterfaceElement(rsInt.getInt("id"), rsInt.getString("text"), interactions[i-1].x + interactions[i-1].w + 40,
                             interactions[i-1].y, interactions[i-1].w, interactions[i-1].h, lm, 
                            rsInt.getString("type")).buildInteraction(rsInt.getString("image"));
                }
                i++;
                j++;
            }
            
        } catch (SQLException ex) {
            System.err.println(ex);
            Logger.getLogger("333" + Cursor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return interactions;
    }
    
    public int getInteractionAction(int interactionId){
        int next = 0;
        connection.conectar();

        try {

            ResultSet rsAct = connection.executeQuery("SELECT next FROM interaction_action WHERE scene_id = " + buildingScene + " AND interaction_id = "
                    + interactionId + ";");
            rsAct.next();
            next = rsAct.getInt("next");
            
        } catch (SQLException ex) {
            System.err.println(ex);
            Logger.getLogger("222" + Cursor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return next;
    }
    
    public Character getInteractionCharacter(int interactionId){
        Character character = null;
        String[] images = new String[8];
        Conversation[] conversations = new Conversation[11];
        int i , j;
        connection.conectar();
        
        try {

            ResultSet rsCharact = connection.executeQuery("SELECT c.id, c.name, c.ending, c.isBoss FROM interaction_character AS ic "
                    + "INNER JOIN hsf.character AS c ON (c.id = ic.character_id) "
                    + "WHERE scene_id = " + buildingScene + " AND interaction_id = " + interactionId + ";");
            rsCharact.next();
            
            
            
            ResultSet rsImages = connection.executeQuery("SELECT i.image FROM images AS i "
                    + "INNER JOIN character_images AS ci ON i.id = ci.images_id "
                    + "WHERE ci.character_id = " + rsCharact.getInt("id") + " ORDER BY ci.id;");
            
            i = 0;
            while(rsImages.next()){
                //System.out.println(rsImages.getString("image"));
                images[i] = rsImages.getString("image");
                i++;
            }
            
            
            character = new Character(rsCharact.getInt("id"), rsCharact.getString("name"), rsCharact.getString("ending"), lm, images, conversations, rsCharact.getBoolean("isBoss"));
            
            
            ResultSet rsConv = connection.executeQuery("SELECT con.id, con.character_id, con.speech, con.reaction_id FROM conversation AS con "
                    + "WHERE character_id = " + rsCharact.getInt("id") + ";");
            
            j = 0;
            while (rsConv.next()) {
                conversations[j] = new Conversation(character, rsConv.getInt("id"), rsConv.getString("speech"), rsConv.getInt("reaction_id"));
                j++;
            }
            
            
            character.conversations = conversations;
            
        } catch (SQLException ex) {
            System.err.println(ex);
            Logger.getLogger("111" + Cursor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return character;
    }
    
    public Interaction_Dialog[] getInteractionDialogs(Conversation conversation){
        int i;
        Interaction_Dialog[] interactions = new Interaction_Dialog[4];
        connection.conectar();
        
        try {

            ResultSet rsInteract = connection.executeQuery("SELECT id.character_id, id.conversation_id, id.next_conversation, id.next_character, id.next_scene, i.text FROM interaction_dialog AS id "
                    + "INNER JOIN interaction AS i ON id.interaction_id = i.id "
                    + "WHERE id.conversation_id = " + conversation.id + " AND id.character_id = " + conversation.character.id + ";");
            i = 0;
            while (rsInteract.next()) {
                if (i == 0) {
                    Interaction_Dialog interaction = new Interaction_Dialog(rsInteract.getString("text"), 100, 100,
                            conversation.character, lm, conversation, rsInteract.getInt("next_conversation"), rsInteract.getInt("next_character"), 
                            rsInteract.getInt("next_scene"));
                    interactions[i] = interaction;
                } else {
                    Interaction_Dialog interaction = new Interaction_Dialog(rsInteract.getString("text"), 100, 
                            interactions[i - 1].y + interactions[i - 1].fullArea.height, conversation.character, lm, conversation,
                            rsInteract.getInt("next_conversation"), rsInteract.getInt("next_character"), 
                            rsInteract.getInt("next_scene"));
                    interactions[i] = interaction;
                }
                i++;
            }
            
            return interactions;
        } catch (SQLException ex) {
            System.err.println(ex);
            Logger.getLogger("Interaction Dialog" + Cursor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    public ResultSet selectImages(){
        
        connection.conectar();
        return connection.executeQuery("SELECT * FROM character_images;");
        
    }
    
    public void registerImage(String url) {

        for (int i = 0; i < images.length; i++) {
            if (images[i] == null) {
                images[i] = url;

                return;
            } else if (images[i] == url) {
                return;
            }
        }
    }


}
