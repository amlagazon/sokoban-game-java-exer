public interface Sprite{
    int x = 0; 
    int y = 0;
    String type = "";
    boolean isSolved = false;
    void printStatus();
    String returnType();
    void left();
    void up();
    void right();
    void down();
    void tick_solve();
}