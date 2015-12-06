/**
 * Created by R on 11/30/15.
 */
import java.util.Random;

public class Player {
    public Game game;
    private Board board;
    private Color color;
    private Boolean isComputerPlayer;
    private Player opponent;
    private int counter;

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
                if (board.getSquare(i, r).occupiedBy() == color) {
                    pawns[count] = board.getSquare(i, r);
                    count++;
                }

        return pawns;
    }

    public Square[] getAllOpponentPawns() {
        Square[] pawns = new Square[game.getPawn(opponent.color)];
        int count = 0;
        for (int i = 0; i < 8; i++)
            for (int r = 0; r < 8; r++)
                if (board.getSquare(i, r).occupiedBy() == opponent.color) {
                    pawns[count] = board.getSquare(i, r);
                    count++;
                }

        return pawns;

    }

    public Square[] getTrullyAllPawns() {
        Square[] pawns = new Square[game.getPawn(opponent.color)+game.getPawn(color)];
        int count = 0;
        for (int i = 0; i < 8; i++)
            for (int r = 0; r < 8; r++)
                if (board.getSquare(i, r).occupiedBy() != Color.NONE) {
                    pawns[count] = board.getSquare(i, r);
                    count++;
                }

        return pawns;

    }

    public Move[] getAllValidMoves() {
        Move[] moves = new Move[21];
        int count = 0;
        if (color == Color.BLACK) {
            for (int i = 0; i < 8; i++)
                for (int q = 1; q < 7; q++) {
                    if (board.getSquare(i, q).occupiedBy() == Color.BLACK) {
                        //move
                        if (board.getSquare(i, q - 1).occupiedBy() == Color.NONE) {
                            moves[count] = new Move(board.getSquare(i, q), board.getSquare(i, q - 1), false);
                            count++;
                        }

                        if (q == 6 && board.getSquare(i, q - 1).occupiedBy() == Color.NONE
                                && board.getSquare(i, q - 2).occupiedBy() == Color.NONE) {
                            moves[count] = new Move(board.getSquare(i, q), board.getSquare(i, q - 2), false);
                            count++;
                        }

                        //attack
                        if (i == 0) {
                            if (board.getSquare(1, q - 1).occupiedBy() == Color.WHITE) {
                                moves[count] = new Move(board.getSquare(i, q), board.getSquare(1, q - 1), true);
                                count++;
                            } else {
                                if (game.passingPawn() && game.getLastMove().getTo().getX() == 1
                                        && game.getLastMove().getTo().getY() == 4) {
                                    moves[count] = new Move(board.getSquare(i, q), board.getSquare(1, q - 1), true);
                                    count++;
                                }
                            }
                        } else if (i == 7) {
                            if (board.getSquare(6, q - 1).occupiedBy() == Color.WHITE) {
                                moves[count] = new Move(board.getSquare(i, q), board.getSquare(6, q - 1), true);
                                count++;
                            } else {
                                if (game.passingPawn() && game.getLastMove().getTo().getX() == 6
                                        && game.getLastMove().getTo().getY() == 4) {
                                    moves[count] = new Move(board.getSquare(i, q), board.getSquare(6, q - 1), true);
                                    count++;
                                }
                            }
                        } else {
                            if (board.getSquare(i - 1, q - 1).occupiedBy() == Color.WHITE) {
                                moves[count] = new Move(board.getSquare(i, q), board.getSquare(i - 1, q - 1), true);
                                count++;
                            }

                            if (board.getSquare(i + 1, q - 1).occupiedBy() == Color.WHITE) {
                                moves[count] = new Move(board.getSquare(i, q), board.getSquare(i + 1, q - 1), true);
                                count++;
                            }

                            if (game.passingPawn() && game.getLastMove().getTo().getX() == (i - 1)
                                    && game.getLastMove().getTo().getY() == 4) {
                                moves[count] = new Move(board.getSquare(i, q), board.getSquare(i - 1, q - 1), true);
                                count++;
                            }

                            if (game.passingPawn() && game.getLastMove().getTo().getX() == (i + 1)
                                    && game.getLastMove().getTo().getY() == 4) {
                                moves[count] = new Move(board.getSquare(i, q), board.getSquare(i + 1, q - 1), true);
                                count++;
                            }

                        }


                    }
                }
        } else {

            for (int i = 0; i < 8; i++)
                for (int q = 1; q < 7; q++) {
                    if (board.getSquare(i, q).occupiedBy() == Color.WHITE) {
                        //move
                        if (board.getSquare(i, q + 1).occupiedBy() == Color.NONE) {
                            moves[count] = new Move(board.getSquare(i, q), board.getSquare(i, q + 1), false);
                            count++;
                        }

                        if (q == 1 && board.getSquare(i, q + 1).occupiedBy() == Color.NONE
                                && board.getSquare(i, q + 2).occupiedBy() == Color.NONE) {
                            moves[count] = new Move(board.getSquare(i, q), board.getSquare(i, q + 2), false);
                            count++;
                        }

                        //attack
                        if (i == 0) {
                            if (board.getSquare(1, q + 1).occupiedBy() == Color.BLACK) {
                                moves[count] = new Move(board.getSquare(i, q), board.getSquare(1, q + 1), true);
                                count++;
                            } else {
                                if (game.passingPawn() && game.getLastMove().getTo().getX() == 1
                                        && game.getLastMove().getTo().getY() == 3) {
                                    moves[count] = new Move(board.getSquare(i, q), board.getSquare(1, q + 1), true);
                                    count++;
                                }
                            }
                        } else if (i == 7) {
                            if (board.getSquare(6, q + 1).occupiedBy() == Color.BLACK) {
                                moves[count] = new Move(board.getSquare(i, q), board.getSquare(6, q + 1), true);
                                count++;
                            } else {
                                if (game.passingPawn() && game.getLastMove().getTo().getX() == 6
                                        && game.getLastMove().getTo().getY() == 3) {
                                    moves[count] = new Move(board.getSquare(i, q), board.getSquare(6, q + 1), true);
                                    count++;
                                }
                            }
                        } else {
                            if (board.getSquare(i + 1, q + 1).occupiedBy() == Color.BLACK) {
                                moves[count] = new Move(board.getSquare(i, q), board.getSquare(i + 1, q + 1), true);
                                count++;
                            }

                            if (board.getSquare(i - 1, q + 1).occupiedBy() == Color.BLACK) {
                                moves[count] = new Move(board.getSquare(i, q), board.getSquare(i - 1, q + 1), true);
                                count++;
                            }

                            if (game.passingPawn() && game.getLastMove().getTo().getX() == (i - 1)
                                    && game.getLastMove().getTo().getY() == 3) {
                                moves[count] = new Move(board.getSquare(i, q), board.getSquare(i - 1, q + 1), true);
                                count++;
                            }

                            if (game.passingPawn() && game.getLastMove().getTo().getX() == (i + 1)
                                    && game.getLastMove().getTo().getY() == 4) {
                                moves[count] = new Move(board.getSquare(i, q), board.getSquare(i + 1, q + 1), true);
                                count++;
                            }

                        }


                    }
                }

        }

        return moves;
    }





    public boolean isPassedPawn(Square square) {
        int xs = square.getX();
        int ys = square.getY();
        if (color == Color.BLACK) {
            if (ys == 0)
                return false;
            if (xs == 0) {
                for (int i = 0; i < 2; i++) {
                    for (int q = (ys - 1); q > 0; q--) {
                        if (board.getSquare(i, q).occupiedBy() != Color.NONE) {
                            return false;
                        }
                    }
                }
            } else if (xs == 7) {
                for (int i = 7; i >= 6; i--) {
                    for (int q = (ys - 1); q > 0; q--) {
                        if (board.getSquare(i, q).occupiedBy() != Color.NONE) {
                            return false;
                        }
                    }
                }
            } else {
                for (int i = xs - 1; i <= xs + 1; i++) {
                    for (int q = (ys - 1); q > 0; q--) {
                        if (board.getSquare(i, q).occupiedBy() != Color.NONE) {
                            return false;
                        }
                    }
                }
            }
        } else if (color == Color.WHITE) {
            if (ys == 7)
                return false;
            if (xs == 0) {
                for (int i = 0; i < 2; i++) {
                    for (int q = (ys - 1); q <= 7; q++) {
                        if (board.getSquare(i, q).occupiedBy() != Color.NONE) {
                            return false;
                        }
                    }
                }
            } else if (xs == 7) {
                for (int i = 7; i >= 6; i--) {
                    for (int q = (ys - 1); q <= 7; q++) {
                        if (board.getSquare(i, q).occupiedBy() != Color.NONE) {
                            return false;
                        }
                    }
                }
            } else {
                for (int i = xs - 1; i <= xs + 1; i++) {
                    for (int q = (ys - 1); q <= 7; q++) {
                        if (board.getSquare(i, q).occupiedBy() != Color.NONE) {
                            return false;
                        }
                    }
                }
            }
        } else {
            return false;
        }

        return true;
    }


    public void makeMove() {


        Random rg = new Random();
        int randomRange = 0;
        Move[] moves = getAllValidMoves();

        while (moves[randomRange] != null) {
            randomRange++;
        }

        for (Move m : moves) {
            if (m == null)
                break;
            if (m.isCapture() == true) {
                System.out.println(color + " Played " + m.getSAN());
                game.applyMove(m);
                return;
            }
        }

        Move rm = moves[rg.nextInt(randomRange)];
        System.out.println(color + " Played " + rm.getSAN());
        game.applyMove(rm);


    }

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




