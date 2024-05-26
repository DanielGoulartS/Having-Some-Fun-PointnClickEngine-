package havingsomefun;

import javax.swing.JFrame;

/**
 *
 * @author Daniel
 */
public class HavingSomeFun {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        
        int initialLevelId = 100;
        
        JFrame frame = new JFrame("GAME");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        
        LevelManager lm = new LevelManager(frame);
        lm.initialLevelId = initialLevelId;
        
        /*
        -----------------------------------------------------------
        -------------------------IMPORTANT-------------------------
        BE SURE YOUR GAME WILL *NOT* FALL IN WRONG PURPOSES.
        
        IF YOU ARE MAKING 
        -ADULT CONTENT, 
        -NUDITY, 
        -SEX, 
        -DRUGS,
        -VIOLENT CONTENT, 
        PLEASE TYPE EXACTLY THIS WORDS IN THE 'confirm' VARIABLE BELOW: 
        [THIS IS ADULT CONTENT].
        
        OR IF YOU ARE *NOT* MAKING 
        -ADULT CONTENT, 
        -NUDITY, 
        -SEX, 
        -DRUGS,
        -VIOLENT CONTENT, 
        PLEASE, TYPE EXACTLY THIS WORDS INSIDE THE 'confirm' VARIABLE BELOW:
        [THIS IS NOT ADULT CONTENT].
        
        AND DO NOT USE [].
        -----------------------------------------------------------
        -----------------------------------------------------------
        */
        
        String confirm = "XXXXX";
        
        lm.confirm(confirm);

        
        frame.add(lm);
        frame.setVisible(true);

    }

}
