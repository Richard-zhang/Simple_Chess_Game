/**
 * Created by R on 11/30/15.
 */
public class Move {
    private boolean isCapture;
    private Square from;
    private Square to;

    public Move(Square from, Square to, boolean isCapture) {
        this.from = from;
        this.to   = to;
        this.isCapture = isCapture;
    }

    public Square getFrom() {
        return from;
    }

    public Square getTo() {
        return to;
    }

    public boolean isCapture() {
        return isCapture;
    }

    public String getSAN() {
        String path = "";
        int ys = to.getY() + 1;
        if (isCapture) {
            path = convertX(from) + "x" + convertX(to) + ys;
            return path;
        } else {
            path = convertX(to) + ys;
            return path;
        }
    }

    private String convertX(Square beConvert) {
        return String.valueOf((char)(beConvert.getX()+'a'));
    }
}
