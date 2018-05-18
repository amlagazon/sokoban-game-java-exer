
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.Random;

class Player extends JPanel implements Runnable, Sprite{
    int x;
    int y;
    private int life;
	private Image img;
    String type;
	boolean onStorageLocation;
	
    boolean isSolved;
	public Player(int x, int y){
        this.x = x;
        this.y = y;
		this.onStorageLocation = false;
		this.type = "k";
    }


//=============================================movements========================================================
	public void up(){

		switch(Map.map[--this.x][this.y].returnType()){
			case "w": System.out.println("WALL"+Gui.elements[1][1].returnType());
					this.x++; //redo
				break;
			case "e": System.out.println("EMPTY");
				//must switch places. indicates movement
				Map.map[this.x][this.y].down(); //sets e's x
					
					//set the x and y values


					Sprite temp = Map.map[this.x][this.y]; // contains e
					Map.map[this.x][this.y] = Map.map[++this.x][this.y]; //move k
					if(onStorageLocation) {
						Storage s = new Storage(this.x,this.y);
						Map.map[this.x][this.y] = s;



					}else{
						Map.map[this.x][this.y] = temp;
					}
					onStorageLocation = false;
					this.x--;
				break;
			case "b": System.out.println("BOX");
				//must check if what's behind the box is an empty space, or storage location.
				/*
				s
				b
				k
				 */
				
				int up_up = this.x - 1; // checks what's on the next

				int k_previous = this.x + 1;
				switch(Map.map[up_up][this.y].returnType()){
					case "b":
					/**
					b B k
					 */
						this.x++; //reset
						break;
					case "B":
					/**
					B B k
					 */
						this.x++; //reset
						break;
					case "e":
							System.out.println("VALID!");
							Map.map[up_up][this.y] = Map.map[this.x][this.y];
							
							Map.map[this.x][this.y] = this;
							if(this.onStorageLocation) {
								Storage s = new Storage(k_previous,this.y);
								Map.map[k_previous][this.y] = s;

							} else {
								Empty e = new Empty(k_previous,this.y);
								Map.map[k_previous][this.y] = e;
							}
							
				this.onStorageLocation = false;
							break;
					case "w": this.x++;
								break; //reset
					case "s":
							System.out.println("VALID!");
							Map.map[up_up][this.y] = Map.map[this.x][this.y];
							Map.map[up_up][this.y].tick_solve();
							
							Map.map[this.x][this.y] = this;

							if(this.onStorageLocation){
								Storage s1 = new Storage(k_previous,this.y);
								Map.map[k_previous][this.y]= s1;
							}else{
								Empty e1 = new Empty(k_previous,this.y);
								Map.map[k_previous][this.y] = e1;
								
							}
							
				this.onStorageLocation = false;
							break;

				}
				break;
			case "s": System.out.println("STORAGE LOCATION");

			/**
			s
			k
			 */
					Sprite temp1 = Map.map[this.x][this.y]; // contains s
					int k_previous2 = this.x + 1;
					Map.map[this.x][this.y] = this; //move k

					if(this.onStorageLocation){
								Storage s2 = new Storage(k_previous2,this.y);
								Map.map[k_previous2][this.y]= s2;
							}else{
								Empty e2 = new Empty(k_previous2,this.y);
								Map.map[k_previous2][this.y] = e2;
								
							}
					
					this.onStorageLocation = true;
				break;
			case "B": System.out.println("STORAGE LOCATION");
					int up_up1 = this.x - 1;
					int k_previous1 = this.x + 1;
					switch(Map.map[up_up1][this.y].returnType()){
						case "b":
					
						this.x++; //reset
						break;
					case "B":
					
						this.x++; //reset
						break;
					case "e":
					/**
					e
					B
					k
					
					 */
							System.out.println("VALID!---");
							Map.map[up_up1][this.y] = Map.map[this.x][this.y];
							Map.map[up_up1][this.y].tick_solve();
							
							
							Map.map[this.x][this.y] = this;
							if(this.onStorageLocation) {
								Storage s2 = new Storage(k_previous1,this.y);
								Map.map[k_previous1][this.y] = s2;

							} else {
								Empty e3 = new Empty(k_previous1,this.y);
								Map.map[k_previous1][this.y] = e3;
							}
							this.onStorageLocation = true;
							break;
					case "w": this.x++;
								break; //reset
					case "s":
							System.out.println("VALID!===");
							Map.map[up_up1][this.y] = Map.map[this.x][this.y];
							// Empty e1 = new Empty(this.x,this.y);
							// this.onStorageLocation = true;
							Map.map[this.x][this.y] = this;
							// Storage s2 = new Storage(this.x,k_previous1);
							// Map.map[this.x][k_previous1] = s2;
							if(this.onStorageLocation){
								Storage s2 = new Storage(k_previous1,this.y);
								Map.map[k_previous1][this.y] = s2;	
								 

							}else{
								Empty e1 = new Empty(k_previous1,this.y);
								Map.map[k_previous1][this.y] =e1;

							}
							this.onStorageLocation = true;
							break;

				}
		
	}
	}
	public void down(){
		switch(Map.map[++this.x][this.y].returnType()){
			case "w": System.out.println("WALL");
					 this.x--; //redo
				break;
			case "e": System.out.println("EMPTY");
				//must switch places. indicates movement

				Map.map[this.x][this.y].up(); //sets e's x
					
					//set the x and y values


					Sprite temp = Map.map[this.x][this.y]; // contains e
					Map.map[this.x][this.y] = Map.map[--this.x][this.y]; //move k
					if(onStorageLocation) {
						Storage s = new Storage(this.x,this.y);
						Map.map[this.x][this.y] = s;



					}else{
						Map.map[this.x][this.y] = temp;
					}
					onStorageLocation = false;
					this.x++;
				break;
			case "b": System.out.println("BOX1212");
				//must check if what's behind the box is an empty space, or storage location.
				int down_down = this.x + 1; // checks what's on the next
				int k_previous = this.x - 1;

					/*
					k
					b
					s
					 */
				switch(Map.map[down_down][this.y].returnType()){
					case "b":
					/**
					b B k
					 */
						this.x--; //reset
						break;
					case "B":
					/**
					B B k
					 */
						this.x--; //reset
						break;
					case "e":
							System.out.println("VALID!");
							Map.map[down_down][this.y] = Map.map[this.x][this.y];
							
							Map.map[this.x][this.y] = this;
							if(this.onStorageLocation) {
								Storage s = new Storage(k_previous,this.y);
								Map.map[k_previous][this.y] = s;

							} else {
								Empty e = new Empty(k_previous,this.y);
								Map.map[k_previous][this.y] = e;
							}
							
				this.onStorageLocation = false;
							break;
					case "w": this.x--;
								break; //reset
					case "s":
							System.out.println("VALID!");
							Map.map[down_down][this.y] = Map.map[this.x][this.y];
							Map.map[down_down][this.y].tick_solve();
							
							Map.map[this.x][this.y] = this;

							if(this.onStorageLocation){
								Storage s1 = new Storage(k_previous,this.y);
								Map.map[k_previous][this.y]= s1;
							}else{
								Empty e1 = new Empty(k_previous,this.y);
								Map.map[k_previous][this.y] = e1;
								
							}
							
				this.onStorageLocation = false;
							break;

				}
				break;
			case "s": System.out.println("STORAGE LOCATION");
					/**
					k
					s
					s
					 */
					
					//set the x and y values
					Sprite temp1 = Map.map[this.x][this.y]; // contains s
					int k_previous2 = this.x - 1;
					Map.map[this.x][this.y] = this; //move k

					if(this.onStorageLocation){
								Storage s2 = new Storage(k_previous2,this.y);
								Map.map[k_previous2][this.y]= s2;
							}else{
								Empty e2 = new Empty(k_previous2,this.y);
								Map.map[k_previous2][this.y] = e2;
								
							}
					
					this.onStorageLocation = true;


				break;
				/*
				k
				B
				e

				k
				B
				s
				
				 */
			case "B": System.out.println("STORAGE LOCATION");
					int down_down1 = this.x + 1;
					int k_previous1 = this.x - 1;
					switch(Map.map[down_down1][this.y].returnType()){
						case "b":
					/**
					b B k
					 */
						this.x--; //reset
						break;
					case "B":
					/**
					B B k
					 */
						this.x--; //reset
						break;
					case "e":
							System.out.println("VALID!---");
							Map.map[down_down1][this.y] = Map.map[this.x][this.y];
							Map.map[down_down1][this.y].tick_solve();
							
							
							Map.map[this.x][this.y] = this;
							if(this.onStorageLocation) {
								Storage s2 = new Storage(k_previous1,this.y);
								Map.map[k_previous1][this.y] = s2;

							} else {
								Empty e3 = new Empty(k_previous1,this.y);
								Map.map[k_previous1][this.y] = e3;
							}
							this.onStorageLocation = true;
							break;
					case "w": this.x--;
								break; //reset
					case "s":
							System.out.println("VALID!===");
							Map.map[down_down1][this.y] = Map.map[this.x][this.y];
							// Empty e1 = new Empty(this.x,this.y);
							// this.onStorageLocation = true;
							Map.map[this.x][this.y] = this;
							// Storage s2 = new Storage(this.x,k_previous1);
							// Map.map[this.x][k_previous1] = s2;
							if(this.onStorageLocation){
								Storage s2 = new Storage(k_previous1,this.y);
								Map.map[k_previous1][this.y] = s2;	
								 

							}else{
								Empty e1 = new Empty(k_previous1,this.y);
								Map.map[k_previous1][this.y] =e1;

							}
							this.onStorageLocation = true;
							break;

				}

		}
		// this.x--;
	}
	public void left(){
		switch(Map.map[this.x][--this.y].returnType()){
			case "w": System.out.println("WALL");
					 this.y++; //redo
				break;
			case "e": System.out.println("EMPTY");
				//must switch places. indicates movement
				Map.map[this.x][this.y].right(); //sets e's y
					
					//set the x and y values

					Sprite temp = Map.map[this.x][this.y]; // contains e
					Map.map[this.x][this.y] = Map.map[this.x][++this.y]; //move k
					if(onStorageLocation) {
						Storage s = new Storage(this.x,this.y);
						Map.map[this.x][this.y] = s;



					}else{
						Map.map[this.x][this.y] = temp;
					}
					
					this.y--;
					onStorageLocation = false;
				break;
			case "b": System.out.println("BOX");
				//must check if what's behind the box is an empty space, or storage location.
				int left_left = this.y - 1; // checks what's on the next
				int k_previous = this.y + 1;

				switch(Map.map[this.x][left_left].returnType()){
					case "b":
					/**
					b B k
					 */
						this.y++; //reset
						break;
					case "B":
					/**
					B B k
					 */
						this.y++; //reset
						break;
					case "e":
							System.out.println("VALID!");
							Map.map[this.x][left_left] = Map.map[this.x][this.y];
							
							Map.map[this.x][this.y] = this;
							if(this.onStorageLocation) {
								Storage s = new Storage(this.x,k_previous);
								Map.map[this.x][k_previous] = s;

							} else {
								Empty e = new Empty(this.x,k_previous);
								Map.map[this.x][k_previous] = e;
							}
							
				this.onStorageLocation = false;
							break;
					case "w": this.y++;
								break; //reset
					case "s":
							System.out.println("VALID!");
							Map.map[this.x][left_left] = Map.map[this.x][this.y];
							Map.map[this.x][left_left].tick_solve();
							
							Map.map[this.x][this.y] = this;

							if(this.onStorageLocation){
								Storage s1 = new Storage(this.x,k_previous);
								Map.map[this.x][k_previous] = s1;
							}else{
								Empty e1 = new Empty(this.x,this.y);
								Map.map[this.x][k_previous] = e1;
							}
							
				this.onStorageLocation = false;

							
							break;
							
				}
				break;
			case "s": System.out.println("STORAGE LOCATION");
					int k_previous3 = y + 1;
					
					//set the x and y values
					Sprite temp1 = Map.map[this.x][this.y]; // contains s
					Map.map[this.x][this.y] = this; //move k

					if(this.onStorageLocation) {
								Storage s3 = new Storage(this.x,k_previous3);
								Map.map[this.x][k_previous3] = s3;

							} else {
								Empty ee = new Empty(this.x,k_previous3);
								Map.map[this.x][k_previous3] = ee;
							}
						
					this.onStorageLocation = true;
					
					


				break;
			case "B": System.out.println("STORAGE LOCATION");
					int left_left1 = this.y - 1;
					int k_previous1 = this.y + 1;
					switch(Map.map[this.x][left_left1].returnType()){
					case "b":
					/**
					b B k
					 */
						this.y++; //reset
						break;
					case "B":
					/**
					B B k
					 */
						this.y++; //reset
						break;
					case "e":
							System.out.println("VALID!");
							Map.map[this.x][left_left1] = Map.map[this.x][this.y];
							Map.map[this.x][left_left1].tick_solve();
							
							
							Map.map[this.x][this.y] = this;
							if(this.onStorageLocation) {
								Storage s1 = new Storage(this.x,k_previous1);
								Map.map[this.x][k_previous1] = s1;

							} else {
								Empty e2 = new Empty(this.x,k_previous1);
								Map.map[this.x][k_previous1] = e2;
							}
							this.onStorageLocation = true;
							break;
					case "w": this.y++;
								break; //reset
					case "s":
							System.out.println("VALID!");
							Map.map[this.x][left_left1] = Map.map[this.x][this.y];
							// Empty e1 = new Empty(this.x,this.y);
							// this.onStorageLocation = true;
							Map.map[this.x][this.y] = this;
							// Storage s2 = new Storage(this.x,k_previous1);
							// Map.map[this.x][k_previous1] = s2;
							if(this.onStorageLocation){
								Storage s2 = new Storage(this.x,k_previous1);
								Map.map[this.x][k_previous1] = s2;	
								 

							}else{
								Empty e1 = new Empty(this.x,k_previous1);
								Map.map[this.x][k_previous1] =e1;

							}
							this.onStorageLocation = true;
							break;

				}
				

				break;

		}
		// this.y++;
	}
	public void right(){
		switch(Map.map[this.x][++this.y].returnType()){
			case "w": System.out.println("WALL");
					this.y--; //redo
				break;
			case "e": System.out.println("EMPTY");
					Map.map[this.x][this.y].left(); //sets e's y
					
					//set the x and y values

					Sprite temp = Map.map[this.x][this.y]; // contains e
					Map.map[this.x][this.y] = Map.map[this.x][--this.y]; //move k
					if(onStorageLocation) {
						Storage s = new Storage(this.x,this.y);
						Map.map[this.x][this.y] = s;



					}else{
						Map.map[this.x][this.y] = temp;
					}
					onStorageLocation = false;
					this.y ++;
				//must switch places. indicates movement
				break;
			case "b": System.out.println("BOX");
				//must check if what's behind the box is an empty space, or storage location.
				int right_right = this.y + 1; // checks what's on the next
				int k_previous = this.y - 1;

				switch(Map.map[this.x][right_right].returnType()){
					case "b":
					/**
					b B k
					 */
						this.y--; //reset
						break;
					case "B":
					/**
					B B k
					 */
						this.y--; //reset
						break;
					case "e":
							System.out.println("VALID!");
							Map.map[this.x][right_right] = Map.map[this.x][this.y];
							Map.map[this.x][this.y] = this;
							if(this.onStorageLocation){
								Storage s_ = new Storage(this.x,k_previous);
								Map.map[this.x][k_previous] = s_;	
								 

							}else{
								Empty e_ = new Empty(this.x,k_previous);
								Map.map[this.x][k_previous] =e_;

							}
							
				this.onStorageLocation = false;
							break;
					case "w": this.y--;
								break; //reset
					case "s":
							System.out.println("VALID!");
							Map.map[this.x][right_right] = Map.map[this.x][this.y];
							Map.map[this.x][right_right].tick_solve();
							Map.map[this.x][this.y] = this;

							if(this.onStorageLocation){
								Storage s2 = new Storage(this.x,k_previous);
								Map.map[this.x][k_previous] = s2;	
								 

							}else{
								Empty e1 = new Empty(this.x,k_previous);
								Map.map[this.x][k_previous] =e1;

							}
							
				this.onStorageLocation = false;
							break;

				}
				
				break;
			case "s": System.out.println("STORAGE LOCATION");
					int k_previous3 = y - 1;
					
					//set the x and y values
					Sprite temp1 = Map.map[this.x][this.y]; // contains s
					Map.map[this.x][this.y] = this; //move k

					if(this.onStorageLocation) {
								Storage s3 = new Storage(this.x,k_previous3);
								Map.map[this.x][k_previous3] = s3;

							} else {
								Empty ee = new Empty(this.x,k_previous3);
								Map.map[this.x][k_previous3] = ee;
							}
						
					this.onStorageLocation = true;
					

				break;
			case "B": System.out.println("STORAGE LOCATION");
					int right_right1 = this.y + 1;
					int k_previous1 = this.y - 1;
					switch(Map.map[this.x][right_right1].returnType()){
						case "b":
					/**
					b B k
					 */
						this.y--; //reset
						break;
					case "B":
					/**
					B B k
					 */
						this.y--; //reset
						break;
					case "e":
							System.out.println("VALID!");
							Map.map[this.x][right_right1] = Map.map[this.x][this.y];
							Map.map[this.x][right_right1].tick_solve();
						
							Map.map[this.x][this.y] = this;
							
							if(this.onStorageLocation) {
								Storage s3 = new Storage(this.x,k_previous1);
								Map.map[this.x][k_previous1] = s3;

							} else {
								Empty e2 = new Empty(this.x,k_previous1);
								Map.map[this.x][k_previous1] = e2;
							}
							this.onStorageLocation = true;
							break;
					case "w": this.y--;
								break; //reset
					case "s":
							System.out.println("VALID!");
							Map.map[this.x][right_right1] = Map.map[this.x][this.y];
							Empty e1 = new Empty(this.x,this.y);
							
							Map.map[this.x][this.y] = this;
							if(this.onStorageLocation){
								Storage s2 = new Storage(this.x,k_previous1);
								Map.map[this.x][k_previous1] = s2;	
								 

							}else{
								Empty e2 = new Empty(this.x,k_previous1);
								Map.map[this.x][k_previous1] =e2;

							}
							this.onStorageLocation = true;
							break;

				}
				

				break;

		}
		// this.y--;
	}
//=============================================movements========================================================

	public void tick_solve(){}
	public void printStatus(){
		System.out.println("X: "+ this.x);
		System.out.println("Y: "+ this.y);
		
		System.out.println("Storage: "+ this.onStorageLocation);
	}
	public String returnType(){
        return this.type;
    }
	public void run(){}
	public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2d = (Graphics2D) g;
      g2d.setColor(new Color(31, 21, 1));
      g2d.fillRect(this.x*60, this.y*60, 60, 60);
   } 
}

