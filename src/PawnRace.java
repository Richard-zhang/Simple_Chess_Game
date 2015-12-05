/**
 * Created by R on 12/1/15.
 */

import java.util.Scanner;


public class PawnRace {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String order = "";
        char whiteGap = args[2].toCharArray()[0];
        char blackGap = args[3].toCharArray()[0];

        Board board = new Board(whiteGap,blackGap);
        Game game = new Game(board);
        Player one;
        Player two;

        boolean isComputerPlayer = (args[0].equals("P")) ? false : true;

        one = new Player(game, board, Color.WHITE, isComputerPlayer);

        isComputerPlayer = (args[1].equals("P")) ? false : true;
        two = new Player(game, board, Color.BLACK, isComputerPlayer);

        one.setOpponent(two);
        two.setOpponent(one);

        board.display();

        while(true) {
            if(game.getCurrentPlayer() == one.getColor()){
                if(one.isComputerPlayer()){
                    if (game.isFinished())
                        break;
                    one.makeMove();
                    board.display();

                } else {
                    do {
                        System.out.println("Input Move White");
                        order = in.nextLine();
                    } while(game.parseMove(order) == null);
                    Move mmm = game.parseMove(order);
                    game.applyMove(mmm);

                    if (game.isFinished())
                        break;
                    board.display();
                }
            } else {
                if(two.isComputerPlayer()) {
                    if(game.isFinished())
                        break;
                    two.makeMove();
                    board.display();
                } else {
                    do {
                        System.out.println("Input Move Black");
                        order = in.nextLine();
                    } while(game.parseMove(order) == null);

                    game.applyMove(game.parseMove(order));
                    if(game.isFinished())
                        break;
                    board.display();
                }

            }
        }

        board.display();

        if(game.getGameResult() == one.getColor()){
            System.out.println("WHITE");
        } else if(game.getGameResult() == two.getColor()){
            System.out.println("BLACK");
        } else {
            System.out.println("Tie");
        }

        System.out.println(game.counter);






    }
}
