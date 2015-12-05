/**
 * Created by R on 11/30/15.
 */
public class Game {
    public int counter=0;
    private int index;
    private Move[] moves = new Move[60];
    private Color currentPlayer = Color.WHITE;
    private Board board;
    private int whiteChess = 7;
    private int blackChess = 7;
    public Game(Board board) {
        this.board = board;
    }

    public int getPawn(Color player) {
        if (player == Color.BLACK)
            return blackChess;
        else
            return whiteChess;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public Move getLastMove(){
        if(index == 0)
            return null;
        else
            return moves[index-1];
    }

    public void applyMove(Move move){
        counter++;
        moves[index] = move;
        index++;

        if(move.isCapture() && move.getTo().occupiedBy() == Color.NONE) {
            if (currentPlayer == Color.BLACK) {

                board.getSquare(move.getTo().getX(),move.getTo().getY()+1).setOccupied(Color.NONE);
            } else {

                board.getSquare(move.getTo().getX(),move.getTo().getY()-1).setOccupied(Color.NONE);
            }

        }

        board.applyMove(move);


        if (move.isCapture()) {

            if (currentPlayer == Color.BLACK) {
                whiteChess--;
            } else {
                blackChess--;
            }
        }
        currentPlayer = (currentPlayer == Color.BLACK) ? Color.WHITE : Color.BLACK;
    }

    public void unapplyMove() {
        if (getLastMove() == null) {

        }

        if(getLastMove().isCapture() && getLastMove().getTo().occupiedBy() == Color.NONE) {
            if (currentPlayer == Color.BLACK) {

                board.getSquare(getLastMove().getTo().getX(),getLastMove().getTo().getY()+1).setOccupied(Color.WHITE);
            } else {

                board.getSquare(getLastMove().getTo().getX(),getLastMove().getTo().getY()-1).setOccupied(Color.BLACK);
            }

        }

        else {
            board.unapplyMove(getLastMove());
            if(getLastMove().isCapture()) {
                if (currentPlayer == Color.BLACK) {
                    blackChess++;
                } else {
                    whiteChess++;
                }
            }

            moves[index] = null;
            index--;
        }

    }

    public boolean isFinished() {
        Move winMove = getLastMove();
        if (winMove == null)
            return false;

        if(winMove.getTo().getY() == 0 || winMove.getTo().getY()== 7) {
            return true;
        } else if (whiteChess == 0 || blackChess == 0) {
            return true;
        } else {
            //return isBlocked;
            return block(currentPlayer);
        }


    }

    private boolean block(Color color) {

        if(color == Color.BLACK){
            for(int i = 0; i < 8; i++)
                for(int q = 1; q < 7; q++){
                    if(board.getSquare(i,q).occupiedBy()==Color.BLACK){
                        //move
                        if(board.getSquare(i,q-1).occupiedBy() == Color.NONE){
                            return false;
                        }

                        if(q==6 && board.getSquare(i,q-1).occupiedBy() == Color.NONE
                                && board.getSquare(i,q-2).occupiedBy() == Color.NONE) {
                            return false;
                        }

                        //attack
                        if(i==0){
                            if(board.getSquare(1,q-1).occupiedBy() == Color.WHITE){
                                return false;
                            } else {
                                if(passingPawn() && getLastMove().getTo().getX() == 1
                                        && getLastMove().getTo().getY() == 4) {
                                    return false;
                                }
                            }
                        } else if (i == 7) {
                            if(board.getSquare(6,q-1).occupiedBy() == Color.WHITE){
                                return false;
                            } else {
                                if(passingPawn() && getLastMove().getTo().getX() == 6
                                        && getLastMove().getTo().getY() == 4) {
                                    return false;
                                }
                            }
                        } else {
                            if(board.getSquare(i-1,q-1).occupiedBy() == Color.WHITE) {
                                return false;
                            }

                            if(board.getSquare(i+1,q-1).occupiedBy() == Color.WHITE) {
                                return false;
                            }

                            if(passingPawn() && getLastMove().getTo().getX() == (i-1)
                                    && getLastMove().getTo().getY() == 4) {
                                return false;
                            }

                            if(passingPawn() && getLastMove().getTo().getX() == (i+1)
                                    && getLastMove().getTo().getY() == 4) {
                                return false;
                            }

                        }





                    }
                }
        } else {

            for(int i = 0; i < 8; i++)
                for(int q = 1; q < 7; q++){
                    if(board.getSquare(i,q).occupiedBy()==Color.WHITE){
                        //move
                        if(board.getSquare(i,q+1).occupiedBy() == Color.NONE){
                            return false;
                        }

                        if(q==1 && board.getSquare(i,q+1).occupiedBy() == Color.NONE
                                && board.getSquare(i,q+2).occupiedBy() == Color.NONE) {
                            return false;

                        }

                        //attack
                        if(i==0){
                            if(board.getSquare(1,q+1).occupiedBy() == Color.BLACK){
                                return false;

                            } else {
                                if(passingPawn() && getLastMove().getTo().getX() == 1
                                        && getLastMove().getTo().getY() == 3) {
                                    return false;
                                }
                            }
                        } else if (i == 7) {
                            if(board.getSquare(6,q+1).occupiedBy() == Color.BLACK){
                                return false;
                            } else {
                                if(passingPawn() && getLastMove().getTo().getX() == 6
                                        && getLastMove().getTo().getY() == 3) {
                                    return false;
                                }
                            }
                        } else {
                            if(board.getSquare(i+1,q+1).occupiedBy() == Color.BLACK) {
                                return false;
                            }

                            if(board.getSquare(i-1,q+1).occupiedBy() == Color.BLACK) {
                                return false;
                            }

                            if(passingPawn() && getLastMove().getTo().getX() == (i-1)
                                    && getLastMove().getTo().getY() == 3) {
                                return false;

                            }

                            if(passingPawn() && getLastMove().getTo().getX() == (i+1)
                                    && getLastMove().getTo().getY() == 4) {
                                return false;
                            }

                        }





                    }
                }

        }

        return true;
    }






    /*
    private boolean isBlocked(Square x) {
        int xcor = x.getX();
        int ycor = x.getY();

        if (x.occupiedBy() == Color.NONE)
            return false;
        else if (x.occupiedBy() == Color.BLACK) {
            if (xcor == 0) {
                if (board.getSquare(xcor,ycor-1).occupiedBy()!= Color.NONE
                        && board.getSquare(xcor+1,ycor-1).occupiedBy() == Color.BLACK)
                    return true;
            } else if (xcor == 7) {
                if (board.getSquare(xcor,ycor-1).occupiedBy()!= Color.NONE
                        && board.getSquare(xcor-1,ycor-1).occupiedBy() == Color.BLACK)
                    return true;
            } else {
                if (board.getSquare(xcor,ycor-1).occupiedBy()!= Color.NONE
                        && board.getSquare(xcor-1, ycor-1).occupiedBy() == Color.BLACK
                            && board.getSquare(xcor+1,ycor-1).occupiedBy() == Color.BLACK)
                    return true;
            }

        } else {
            if (xcor == 0) {
                if (board.getSquare(xcor,ycor+1).occupiedBy()!= Color.NONE
                        && board.getSquare(xcor+1,ycor+1).occupiedBy() == Color.WHITE)
                    return true;
            } else if (xcor == 7) {
                if (board.getSquare(xcor,ycor+1).occupiedBy()!= Color.NONE
                        && board.getSquare(xcor-1,ycor+1).occupiedBy() == Color.WHITE)
                    return true;
            } else {
                if (board.getSquare(xcor,ycor+1).occupiedBy()!= Color.NONE
                        && board.getSquare(xcor-1, ycor+1).occupiedBy() == Color.WHITE
                        && board.getSquare(xcor+1,ycor+1).occupiedBy() == Color.WHITE)
                    return true;
            }
        }

        return false;
    }

    */

    Color getGameResult() {
        if (isFinished()) {
            if (whiteChess == 0 || getLastMove().getTo().getY() == 0)
                return Color.BLACK;
            else if (blackChess == 0 || getLastMove().getTo().getY() == 7)
                return Color.WHITE;
            else
                return Color.NONE;
        } else {
            return null;
        }
    }

    public Move parseMove(String san) {
        boolean isCapture = false;
        if (san.substring(1,2).equals("x")) {
            int start = san.substring(0,1).toCharArray()[0] -'a';
            Square dest = getLocation(san.substring(2,4));
            int xs = dest.getX();
            int ys = dest.getY();
            isCapture = true;
            if(dest.occupiedBy() == currentPlayer){
                return null;
            }

            if(dest.occupiedBy() == Color.NONE && !passingPawn()){
                return null;
            }

            if(currentPlayer == Color.BLACK) {
                if (ys >=6)
                    return null;

                if (!(start + 1 == xs || start - 1 == xs))
                    return null;
                /*
                if (xs == 0)

                    if(board.getSquare(xs+1,ys+1).occupiedBy() != Color.BLACK)
                        return null;
                if (xs == 7)
                    if(board.getSquare(xs-1,ys+1).occupiedBy() != Color.BLACK)
                        return null;
                else {
                        if(board.getSquare(xs-1,ys+1).occupiedBy() != Color.BLACK
                                && board.getSquare(xs+1,ys+1).occupiedBy() != Color.BLACK)
                            return null;
                    }
                */
                if (board.getSquare(start,ys+1).occupiedBy() == Color.BLACK)
                    return new Move(board.getSquare(start,ys+1),dest,isCapture);


            }

            if(currentPlayer == Color.WHITE) {

                if (ys <= 1)
                    return null;

                if (!(start + 1 == xs || start - 1 == xs))
                    return null;

                if (board.getSquare(start,ys-1).occupiedBy() == Color.WHITE)
                    return new Move(board.getSquare(start,ys-1),dest,isCapture);

            }


            return null;


        } else {
            Square dest = getLocation(san);
            int xs = dest.getX();
            int ys = dest.getY();

            if(currentPlayer == Color.WHITE) {
                if (ys <= 1)
                    return null;
                if (dest.occupiedBy() != Color.NONE)
                    return null;
                if(board.getSquare(xs,ys-1).occupiedBy() == Color.WHITE) {
                    return new Move(board.getSquare(xs,ys-1),dest, isCapture);
                } else if (board.getSquare(xs,ys-1).occupiedBy() == Color.NONE
                        && board.getSquare(xs,ys-2).occupiedBy() == Color.WHITE && ys == 3)
                    return new Move(board.getSquare(xs,ys-2),dest,isCapture);
            }

            if(currentPlayer == Color.BLACK) {
                if (ys >= 6)
                    return null;
                if (dest.occupiedBy() != Color.NONE)
                    return null;
                if(board.getSquare(xs,ys+1).occupiedBy() == Color.BLACK) {
                    return new Move(board.getSquare(xs,ys+1),dest,isCapture);
                } else if (board.getSquare(xs,ys+1).occupiedBy() == Color.NONE
                        && board.getSquare(xs,ys+2).occupiedBy() == Color.BLACK && ys == 4)
                    return new Move(board.getSquare(xs,ys+2),dest,isCapture);
            }
        }

        return null;
    }

    public boolean passingPawn() {
        Move lastMove = getLastMove();

        if(getLastMove() == null)
            return false;

        return (lastMove.getFrom().getY() == 6 && lastMove.getTo().getY() == 4)
                || ((lastMove.getFrom().getY() == 1) && lastMove.getTo().getY() == 3);

    }

    public Square getLocation(String loca) {
        char[] charArray = loca.toCharArray();
        int x = charArray[0] - 'a';
        int y = charArray[1] - '0'-1;

        return board.getSquare(x,y);
    }
}
