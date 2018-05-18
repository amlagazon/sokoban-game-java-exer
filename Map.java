public  class Map{
    static Sprite map[][];
    public Map(Sprite[][] map){
        this.map = map;
    }

    void printMap(){
        int i,j;
         for(i=0;i<10;i++){
            for(j=0; j<10; j++){
                    System.out.print(map[i][j].returnType()+"  ");
                    // frame.setContentPane(elements[i][j]);  //set the ball as the JPanel of the frame
                    // player.repaint();
            }
            System.out.print('\n');
            }
    }
    
}