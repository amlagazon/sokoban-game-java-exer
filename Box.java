
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.Random;
class Box extends JPanel implements Runnable, Sprite{
    int x;
    int y;
    boolean isSolved;
    String type;
	private Image img;

    public Box(int x, int y, boolean isSolved){
        this.x = x;
        this.y = y;
        this.isSolved = isSolved;
        // this.type = "b";
    }
   
  public  void tick_solve(){
	   if(this.isSolved) this.isSolved = false;
	   else this.isSolved = true;
   }
    public void printStatus(){
		System.out.println("X: "+ this.x);
		System.out.println("Y: "+ this.y);
	}
    public String returnType(){
        if(isSolved) return "B";
        return "b";
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

		try {
			img = ImageIO.read(new File("wall.jpg"));	//load the image of bird
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		Graphics2D g2d = (Graphics2D)g;
		g.drawImage(img, x,y,50,50,null);
	}
    public void run() {
				
					try {
						this.repaint(); // invokes paintComponent() again
						Thread.sleep(100);
					} catch(Exception e) {  }
				
			}
}