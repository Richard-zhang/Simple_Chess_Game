/**
 * Created by R on 11/30/15.
 */
public class Player {
    private Game game;
    private Board board;
    private Color color;
    private Boolean isComputerPlayer;
    private Player opponent;
    public Player(Game game, Board board, Color color, boolean isComputerPlayer) {
        this.game = game;
        this.board = board;
        this.color = color;
        this.isComputerPlayer = isComputerPlayer;

    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public Color getColor() {
        return color;
    }

    public boolean isComputerPlayer() {
        return isComputerPlayer;
    }

    public Square[] getAllPawns() {
        Square[] pawns = new Square[game.getPawn(color)];
        int count = 0;
        for (int i = 0; i < 8; i++)
            for (int r = 0; r < 8; r++)
                if(board.getSquare(i,r).occupiedBy() == color) {
                    pawns[count] = board.getSquare(i,r);
                    count++;
                }

        return pawns;
    }

    public Move[] getAllValidMoves() {
        Move[] validMoves = new Move[14];
        return validMoves;
    }

    public boolean isPassedPawn(Square square) {
        return true;
    }

    /*
    public boolean isPassedPawn(Square square) {
        if(square.getY() == 4 || square.getY() == 5) {
            if((game.getLastMove().getFrom().getY() == 7 && game.getLastMove().getTo().getY() == 5)
                    || (game.getLastMove().getFrom().getY() == 1) && game.getLastMove().getTo().getY() == 4) {
                if (adjacnetSquare(square, board.getSquare(game.getLastMove().getTo().getX(),game.getLastMove().getTo().getY())))
                    return true;


            } else {
                return false;
            }
        } else {
            return false;
        }



    }

    private boolean adjacnetSquare(Square sq1, Square sq2) {
        if (sq1.getY()  == sq2.getY()) {
            if(sq1.getX()+1 == sq2.getX() || sq1.getX()-1 == sq2.getX())
                return true;
        }

        return false;
    }

    */



}
