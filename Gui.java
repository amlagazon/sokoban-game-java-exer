import java.io.File;
import java.io.FileReader;
import java.io.IOException;


import javax.imageio.*;
import java.util.Scanner; 

import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;
public class Gui extends JPanel{  
    static Sprite elements[][];
    static Player player;
    static int no_of_boxes;
    static int ok_boxes;
    FileReader inputStream = null;
    
    char array[][] = new char[10][10];
    int i,j;
    int c;
   static JFrame f;  
Gui(){  
    f=new JFrame();  
    try{
        f.setContentPane(new JLabel(new ImageIcon(("bg.jpg"))));
    
    }catch(Exception e)
    {}
    //initializationssssss-----------------
    no_of_boxes = 0;
    ok_boxes = 0;
    elements = new Sprite[10][10];
    player =  new Player(0,0);

    this.fill_array();
    this.draw_board(this.elements);
    f.setSize(700  ,700);  
    f.setVisible(true);  
}  

public static void print_no_boxes(){
    System.out.println("Boxes: "+ok_boxes+" /"+no_of_boxes);
}
public void fill_array(){
       //reads what's in the puzzle.in file
   try {
            inputStream = new FileReader("puzzle.in");

            
            int a=0,b=0;
            for(i=0 ; i <10; i++, a++){
                b=0;
                for(j=0; j < 20; j++){
                    c = inputStream.read();
                    if((char)c == ' ' || (char)c == '\n') continue;
                   
                    array[i][b] = (char)c;
                    switch((char)c){
                        case 'k':   
                                    player =  new Player(a,b);
                                    System.out.println("a: "+a+"b: "+b);
                                    //  frame.add(player);
                                    elements[a][b] = player;
                                    break;
                        case 'w':   Wall wall = new Wall(a,b);
                                    // frame.add(wall);
                                    elements[a][b] = wall;
                                    break;
                        case 'b':   Box box = new Box(a,b,false);
                                    no_of_boxes++;
                                    elements[a][b] = box;                                    
                                    break;
                        case 'e':    Empty empty = new Empty(a,b);
                                    elements[a][b] = empty;
                                    break;
                        case 'x':  None none = new None(a,b);
                                    elements[a][b] = none;
                                    break;
                        case 's':   Storage storage = new Storage(a,b);
                                    elements[a][b] = storage;
                                    break;
                        case 'B':   Box box_ = new Box(a,b,true);
                                    no_of_boxes++;
                                    elements[a][b] = box_;
                                    break;
                        default: break;
                    }
                    b++;



                }
                // 
            }

        }catch(Exception e){

        }
                     
}
public static void draw_board(Sprite[][] elements){
    
    f.getContentPane().removeAll();
    ok_boxes = 0;
    for(int w = 0; w<10; w++){
        for(int x = 0; x<10; x++){
            // BufferedImage obj_img = ImageIO.read(this.getClass().getResource("wall.jpg"));
            String type = elements[w][x].returnType();
           JPanel obj = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
           if(type.equals("k")){
            
            obj.add(new JLabel(new ImageIcon(("player.gif"))));
            obj.setOpaque(false);
        //    obj.setBackground(new Color(255, 51, 51));
           }else if (type.equals("w")){
               obj.add(new JLabel(new ImageIcon(("wall2.jpg"))));
        //   obj.setBackground(new Color(153, 76, 0));
           }else if (type.equals("x")){
               obj.add(new JLabel(new ImageIcon(("wall2.jpg"))));
           obj.setBackground(Color.black);
           }else if (type.equals("b")){
               
            obj.add(new JLabel(new ImageIcon(("rock1.png"))));
            
            obj.setOpaque(false);
            // obj.setBackground(new Color(255, 204,153));
           }else if (type.equals("B")){
               ok_boxes++;
               
            obj.add(new JLabel(new ImageIcon(("on_storage.png"))));
            
            obj.setOpaque(false);
            // obj.setBackground(new Color(255, 251,51));
           }else if (type.equals("e")){
               
               obj.add(new JLabel(new ImageIcon(("grass.jpg"))));
               obj.setVisible(false);
            // obj.setBackground(new Color(224, 224,224));
           }else if (type.equals("s")){
               
               obj.add(new JLabel(new ImageIcon(("storage1.png"))));
               
            obj.setOpaque(false);
            // obj.setBackground(new Color(160, 160,160));
           }
         f.add(obj);
        }
        

    }
    f.setLayout(new GridLayout(10,10));
    
    f.getContentPane().validate();
    f.getContentPane().repaint();
    
    
}

}