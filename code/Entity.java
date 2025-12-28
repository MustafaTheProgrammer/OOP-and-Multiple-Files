import java.util.ArrayList;

public class Entity{
    public int x;
    public int y;
    public int xv = 1;
    public int yv = -1;
    public String type;
    public static ArrayList<Entity> list = new ArrayList<Entity>();
    public Entity(int x, int y, String type){
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void update(){
        this.x += this.xv;
        if (this.x == 0 || this.x == 9){
            this.xv *= -1;
        }
        this.y += this.yv;
        if (this.y == 0 || this.y == -9){
            this.yv *= -1;
        }
    }
}