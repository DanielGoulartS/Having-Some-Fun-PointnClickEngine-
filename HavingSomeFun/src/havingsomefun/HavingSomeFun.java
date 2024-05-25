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
        
        JFrame frame = new JFrame("GAME");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        
        LevelManager lm = new LevelManager(frame);
        
        lm.cursor.changeScene(100);

        
        frame.add(lm);
        frame.setVisible(true);

    }

}
