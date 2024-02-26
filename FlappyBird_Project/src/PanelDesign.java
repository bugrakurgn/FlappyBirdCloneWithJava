import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;



public class PanelDesign extends JPanel{

	private MyBird bird;
    private ArrayList<Rectangle> rects;
    private GameDesign gd;
    private Font scoreFont, pauseFont;
    public static final Color bg = new Color(0, 158, 158);
    public static final int PIPE_W = 50, PIPE_H = 30;
    private Image pipeHead, pipeLength;

    public PanelDesign(GameDesign gd, MyBird bird, ArrayList<Rectangle> rects) {
        this.gd = gd;
        this.bird = bird;
        this.rects = rects;
        scoreFont = new Font("Comic Sans MS", Font.BOLD, 18);
        pauseFont = new Font("Arial", Font.BOLD, 48);
        
        try {
        	pipeHead = ImageIO.read(new File("78px-Pipe.png"));
        	pipeLength = ImageIO.read(new File("pipe_part.png"));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(bg);
        g.fillRect(0,0,GameDesign.WIDTH,GameDesign.HEIGHT);
        bird.update(g);
        g.setColor(Color.RED);
        for(Rectangle r : rects) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.GREEN);
            //g2d.fillRect(r.x, r.y, r.width, r.height);
            AffineTransform old = g2d.getTransform();
            g2d.translate(r.x+PIPE_W/2, r.y+PIPE_H/2);
            if(r.y < GameDesign.HEIGHT/2) {
                g2d.translate(0, r.height);
                g2d.rotate(Math.PI);
            }
            g2d.drawImage(pipeHead, -PIPE_W/2, -PIPE_H/2, PanelDesign.PIPE_W, PanelDesign.PIPE_H, null);
            g2d.drawImage(pipeLength, -PIPE_W/2, PIPE_H/2, PanelDesign.PIPE_W, r.height, null);
            g2d.setTransform(old);
        }
        g.setFont(scoreFont);
        g.setColor(Color.BLACK);
        g.drawString("Score: "+gd.getScore(), 10, 20);
        
        if(gd.paused()) {
            g.setFont(pauseFont);
            g.setColor(new Color(0,0,0,170));
            g.drawString("PAUSED", GameDesign.WIDTH/2-100, GameDesign.HEIGHT/2-100);
            g.drawString("PRESS SPACE TO BEGIN", GameDesign.WIDTH/2-300, GameDesign.HEIGHT/2+50);
        }
    }
}
