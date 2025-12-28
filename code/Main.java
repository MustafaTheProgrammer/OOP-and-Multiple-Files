/*
Here's my first Java ascii game that makes good use of Object Oriented Programming. I made this not only to learn how to structure OOP more into these
programs of mine but also to learn how to make programs like these with multiple files. Making this was pain, and I searched many things, including
using google ai search(how could I?!?), and procrastinated a lot, but I'm satisfied with the progress I got done here.

The main issue here is I don't know how to make Entity objects access variables outside the Entity class. This prevented me from more convinently
using the update() method, and I tried adding a render() method but didn't because of this same issue. I plan to try and combat this problem
for future programs.

I definently plan to use this structure for future programs, as it's more convinent in readable code and multiple file use. Since I plan to stop using
Java as a primary text based language eventually I'd like to make more use of its OOP features before moving on to lower level programming languages.
*/

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import java.util.ArrayList;

public class Main extends JFrame implements KeyListener{
    /*
    some initilization
    */
    public static String[] pixels = new String[100];
    public static String white = "\u001B[100m  ";
    public static String black = "\u001B[47m  ";
    public static String red = "\u001B[101m  ";
    public static String green = "\u001B[102m  ";
    public static String blue = "\u001B[106m  ";
    public static boolean rightKey;
    public static boolean leftKey;
    public static boolean upKey;
    public static boolean downKey;
    public static boolean running = true;
    public static int score = 0;

    //player class
    public class player{
        public static int x = 0;
        public static int y = 0;
    }

    public Main(){
        this.setTitle("Game Made With OOP");
        this.setSize(100, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(this);
        this.setVisible(true);
        gameLoop();
    }

    /*
    game loop
    */
    static void gameLoop(){
        Entity.list.add(new Entity(4, -1, "gremlin"));
        Entity.list.add(new Entity(1, -7, "I'm mad"));
        Entity.list.add(new Entity(6, -5, "IDK"));
        while (running){
            System.out.print("\033[H\033[2J");
            System.out.flush();

            for (int i = 0; i < pixels.length; i++){
                pixels[i] = white;
            }

            //updating
            if (rightKey && player.x < 9){
                player.x++;
            }
            if (leftKey && player.x > 0){
                player.x--;
            }
            if (upKey && player.y < 0){
                player.y++;
            }
            if (downKey && player.y > -9){
                player.y--;
            }

            score++;

            for (int i = 0; i < Entity.list.size(); i++){
                Entity.list.get(i).update();
                //Normally I'd like to put the code below in the update method as well, but as of now I don't know how to make the entity objects access variables outside the class, such as the player's fields or the running variable
                if (Entity.list.get(i).x == player.x && Entity.list.get(i).y == player.y){
                    running = false;
                }
            }

            //rendering
            setPixel(player.x, player.y, black);

            for (int i = 0; i < Entity.list.size(); i++){
                switch (Entity.list.get(i).type){
                        case "gremlin": setPixel(Entity.list.get(i).x, Entity.list.get(i).y, red);
                        break;
                        case "I'm mad": setPixel(Entity.list.get(i).x, Entity.list.get(i).y, green);
                        break;
                        case "IDK": setPixel(Entity.list.get(i).x, Entity.list.get(i).y, blue);
                        break;
                }
            }

            System.out.println("Score: " + score);
            for (int i = 0; i < 10; i++){
                for (int j = 0; j < 10; j++){
                    System.out.print(pixels[i * 10 + j]);
                }
                System.out.println("\u001B[0m");
            }

            try{
                Thread.sleep(100);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.print("\033[H\033[2J");
        System.out.print("Final score: " + score);
    }

    public static void main(String[] args){
        new Main();
    }

    static void setPixel(int x, int y, String color){
        pixels[Math.abs(y) * 10 + x] = color;
    }

    public void keyTyped(KeyEvent e){
        
    }
    
    public void keyPressed(KeyEvent e){
        switch (e.getKeyCode()){
                case 37: leftKey = true;
                break;
                case 38: upKey = true;
                break;
                case 39: rightKey = true;
                break;
                case 40: downKey = true;
                break;
        }
    }
    
    public void keyReleased(KeyEvent e){
        switch (e.getKeyCode()){
                case 37: leftKey = false;
                break;
                case 38: upKey = false;
                break;
                case 39: rightKey = false;
                break;
                case 40: downKey = false;
                break;
        }
    }
}