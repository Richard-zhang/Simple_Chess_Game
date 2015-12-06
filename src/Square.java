/**
 * Created by R on 11/30/15.
 */
public class Square {
    private int x;
    private int y;
    private Color occupied = Color.NONE;

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getValue() {
        if(y > 3){
            return ((y-4)*2+1)*((y-4)*2+1);
        } else {
            return -(Math.abs(y-3)*2+1)*(Math.abs(y-3)*2+1);
        }
    }

    public void setOccupied(Color occupied) {
        this.occupied = occupied;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return  y;
    }

    public Color occupiedBy() {
        return occupied;
    }

}
