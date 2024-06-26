package havingsomefun;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Daniel
 */
public class LevelManager extends JPanel implements Runnable{

    public JFrame frame;
    final int FPS = 60;
    Thread t1;
    public int initialLevelId;
    public Scene currentScene, previousScene;
    public Cursor cursor = new Cursor(this);
    public String ending = "Dead", endingImage = "/media/ending/ending.png";
    public boolean gameOver;

    public LevelManager(JFrame frame) {
        this.frame = frame;
        addMouseListener(cursor);
        t1 = new Thread(this);
        t1.start();
    }
 
    public void changeScene(int id, String title, String password, String narrationText, String layer1, String layer2, String layer3){
        previousScene = currentScene;
        currentScene = new Scene(id, title, password, narrationText, layer1, layer2, layer3, this);
    }
    
    private void labelScene(String labelTitle) {
        currentScene = new AgeLabelWarning(labelTitle, this);
    }

    public void endGame(){
        
        if (gameOver) {

            
            try {
                Scene endScene = new Scene(999, "ENDING", "", "", endingImage, "/media/black.png", "", this);
                
                if (currentScene.layer2 != null){
                    endScene.layer2 = currentScene.layer2;
                }else if (currentScene.layer2 == null && currentScene.layer3 != null){
                    endScene.layer2 = currentScene.layer3;
                }else if (currentScene.layer2 == null && currentScene.layer3 == null){
                    endScene.layer2 = currentScene.layer1;
                }

                if (ending == null || ending.isBlank()) {
                    ending = "as an Adventurer!";
                }

                endScene.addText("You finished", (getWidth() / 2), (getHeight() / 2) - 70, 500, 500, 60, "center");
                endScene.addText(ending + "!", (getWidth() / 2), (getHeight() / 2), 500, 500, 60, "center");

                currentScene = endScene;

            } catch (Exception ex) {
                System.err.println(ex);
            }
            gameOver = false;
        }
    }     
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        Graphics2D g2 = (Graphics2D) g;
        
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        currentScene.drawImage(g2);
        
    }

    private void update(){
        cursor.updateLocation();
    }
    
    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        while(t1 != null){
         
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            
            lastTime = currentTime;
            
            if (delta >= 1) {
                
                update();
                
                endGame();
                
                repaint();
                
                delta--;
                drawCount ++;
            }

            if(timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    void confirm(String confirm) {
        if(confirm.equals("THIS IS ADULT CONTENT")){
            System.out.println("The developer of this game, not the engine, agree that this game is Family Friendly and friendly for all ages.");
            labelScene("ADULT LABEL");
            
        }else if(confirm.equals("THIS IS NOT ADULT CONTENT")){
            System.out.println("The developer of this game, not the engine, agree that this game is Adult Content and NOT friendly for all ages.");
            labelScene("FAMILY LABEL");
            
        }else{
            System.out.println("This game has no information from its author about age labels, so it will not work for public safety.");
            labelScene("NO LABEL");
            
        }
    }


}
