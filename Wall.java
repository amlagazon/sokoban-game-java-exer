
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.Random;
class Wall extends JPanel implements Runnable, Sprite{
    int x;
    int y;
	
    boolean isSolved;
    private int life;
	private Image img;
	String type;
    public Wall(int x, int y){
        this.x = x;
        this.y = y;
		this.type = "w";
    }
	public void tick_solve(){}
	public void printStatus(){
		System.out.println("X: "+ this.x);
		System.out.println("Y: "+ this.y);
	}
	public String returnType(){
        return this.type;
    }
	public void left(){
		this.y--;
	}
	public void right(){
		this.y++;
	}
	public void up(){
		this.x--;
	}
	public void down(){
		this.x++;
	}
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2d = (Graphics2D) g;
      g2d.setColor(new Color(0, 200, 1));
      g2d.fillRect(this.x*60, this.y*60, 60, 60);
   } 
    public void run() {
			
					try {
						this.repaint(); // invokes paintComponent() again
						Thread.sleep(100);
					} catch(Exception e) {  }
				
			}
}