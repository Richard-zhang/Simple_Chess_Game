/**
 * Created by R on 11/30/15.
 */
public class Board {
    private Square[][] initBoard = new Square[8][8];


    public Board(char whiteGap, char blackGap) {
        int white = whiteGap - 'A';
        int black = blackGap - 'A';

        for(int i = 0; i < 8;i++)
            for(int r = 0; r < 8; r++)
                initBoard[i][r] = new Square(r,i);

        for(int i = 0;i < 8;i++) {
            if (i == white) {
                continue;
            }

            initBoard[1][i].setOccupied(Color.WHITE);
        }

        for (int i = 0;i < 8; i++) {
            if (i == black) {
                continue;
            }

            initBoard[6][i].setOccupied(Color.BLACK);
        }

    }

    public Square getSquare(int x, int y) {
        return initBoard[y][x];
    }

    void applyMove(Move move) {
        Square ori = getSquare(move.getFrom().getX(),move.getFrom().getY());
        initBoard[move.getTo().getY()][move.getTo().getX()].setOccupied(ori.occupiedBy());
        initBoard[ori.getY()][ori.getX()].setOccupied(Color.NONE);
    }

    void unapplyMove(Move move) {
        Square moved = getSquare(move.getTo().getX(),move.getTo().getY());
        initBoard[move.getFrom().getY()][move.getFrom().getX()].setOccupied(moved.occupiedBy());
        initBoard[move.getTo().getY()][move.getTo().getX()].setOccupied(move.getTo().occupiedBy());
    }

    void display() {
        String dis = "";
        for(int i = 7; i >= 0; i--)
            for(int r = 0; r < 8; r++) {
                int q = i + 1;
                if (r==7)
                    dis = dis + " " + initBoard[i][r].occupiedBy().getProperty() + "  " + q + "\n";
                else if (r==0)
                    dis = dis + q + "  " + initBoard[i][r].occupiedBy().getProperty();
                else
                    dis = dis + " " + initBoard[i][r].occupiedBy().getProperty();
            }

        String letters = "   A B C D E F G H  ";
        System.out.println(letters + "\n" + dis  + letters);

    }


}
