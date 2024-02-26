import java.io.*;
import javax.imageio.ImageIO;
import java.awt.*;


public class MyBird {

	public float x, y, vector_x, vector_y;
	public static final int RAD = 25;
	private Image img;
	
	public MyBird() {
		x = GameDesign.WIDTH/2;
		y = GameDesign.HEIGHT/2;
		try {
            img = ImageIO.read(new File("bird_image.png")); ///foto için file ismi
        }
        catch(IOException e) {
            e.printStackTrace();
        }
	}
	
	public void physics() {
		x+= vector_x;
		y+=vector_y;
		vector_y+=0.5f;
	}
	
	public void update(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawImage(img, Math.round(x-RAD),Math.round(y-RAD),2*RAD,2*RAD, null);
    }
    public void jump() {
        vector_y = -9;
    }
    
    public void reset() {
        x = 640/2;
        y = 640/2;
        vector_x = vector_y = 0;
    }
	

    
}
