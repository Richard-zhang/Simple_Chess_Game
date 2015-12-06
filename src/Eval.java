import java.util.IntSummaryStatistics;

/**
 * Created by R on 12/5/15.
 */
public class Eval {

    public static int eval(Player player) {
        int eval = 0;
        if(player.game.isFinished()) {
            if (player.game.getGameResult() == Color.BLACK) {
                return Integer.MIN_VALUE;
            } else if (player.game.getGameResult() == Color.WHITE) {
                return Integer.MAX_VALUE;
            } else {
                return 0;
            }
        }

        Square[] allpawns = player.getTrullyAllPawns();
        for(Square pawn: allpawns){
            if(!player.game.isBlocked(pawn))
                eval += pawn.getValue();
        }



        return eval;



    }

    public static int miniMax(Player player, int depth, int min, int max) {


        int bestValue = 0;
        int value = 0;
        if (player.game.isFinished() || depth == 0) {
            return eval(player);
        }

        if (player.game.getCurrentPlayer() == Color.WHITE) {
            bestValue = min;
            Move[] moves = player.getAllValidMoves();
            for (Move move : moves) {
                player.game.applyMove(move);
                value = miniMax(player, depth - 1, bestValue, max);
                player.game.unapplyMove();
                bestValue = Math.max(bestValue, value);


                if (bestValue > max)
                    return max;
            }



        } else {
            bestValue = max;
            Move[] moves = player.getAllValidMoves();
            for (Move move : moves) {
                player.game.applyMove(move);
                value = miniMax(player, depth - 1, min, bestValue);
                player.game.unapplyMove();
                bestValue = Math.min(bestValue, value);

                if(bestValue < min)
                    return min;
            }

        }

        return bestValue;
    }
}
