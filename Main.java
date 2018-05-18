import java.io.File;
import java.io.FileReader;
import java.io.IOException;


import javax.imageio.ImageIO;
import java.util.Scanner; 

import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;

//plans: 
//use array to save everything from the file to a class named Map
//per element contains a character.
//if '\n', then skip. it is not needed tho.


//dapat per element may attribute na x,y para ma indicate yung position
public class Main{
	public static void main(String[] args) {
        
    Gui gui  = new Gui();
    FileReader inputStream = null;
    int i,j;
    int c;



    Map sokoban_map = new Map(Gui.elements);

    sokoban_map.printMap();
    Gui.print_no_boxes();

    //key listeners W A S D movementss
    Gui.f.addKeyListener(new KeyListener(){
        public void keyPressed(KeyEvent ke){
        if(ke.getKeyCode()==KeyEvent.VK_W){
            Gui.player.up();
            Gui.draw_board(Map.map);
        }else if(ke.getKeyCode()==KeyEvent.VK_A){
            Gui.player.left();
            Gui.draw_board(Map.map);
        }
        else if(ke.getKeyCode()==KeyEvent.VK_S){
            Gui.player.down();
            Gui.draw_board(Map.map);}
        else if(ke.getKeyCode()==KeyEvent.VK_D){
            Gui.player.right();
            Gui.draw_board(Map.map);
        }else{
            System.out.println("INVALID KEY!");
        }

        sokoban_map.printMap();
        Gui.print_no_boxes();
        if(Gui.ok_boxes >= Gui.no_of_boxes){

            Gui.draw_board(Map.map);
                System.out.println("CONGRATULATIONS!");
            
                JOptionPane.showMessageDialog(Gui.f,"CONGRATULATIONS! Play Again!");
                Gui.f.dispose();
            }
    }

    public void keyTyped(KeyEvent ke){}
    public void keyReleased(KeyEvent ke){}

    });

    Gui.f.setFocusable(true); // set focus to the frame to listen to the key

       

	}
}