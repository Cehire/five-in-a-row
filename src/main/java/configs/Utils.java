package configs;

public class Utils {

    public static boolean nearPiece(int[][] chessOnBoard,int x,int y){//
        int left;
        int up;
        int right;
        int down;
        left = Math.max(x - 2, 0);
        up = Math.max(y-2,0);
        right = Math.min(x + 2, 14);
        down = Math.min(y+2,14);
        for (; left <= right ; left++) {
            for (; up <= down ; up++) {
                if (chessOnBoard[left][up]!=0){
                    return true;
                }
            }
        }
        return false;
    }
}
