package configs;

import java.util.LinkedList;

public class Utils {

    public static boolean nearPiece(int[][] chessOnBoard,int x,int y){//用来检测周围是不是有棋子
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

    public static int countValue(int[][] chessOnBoard,int x,int y){//用于每个棋子所占的价值
        int value = 0;
        String tmp = "";//用于保存棋子的情形
        //左->右
        for (int i = 0; i < chessOnBoard[0].length; i++) {

        }
        //上->下
        for (int i = 0; i < chessOnBoard.length; i++) {

        }
        //左上->右下
        //右下->坐上
        return value;
    }

    public static int getValue(int[][] chessOnBoard){//用于计算当前情形的价值
        int value = 0;
        for (int i = 0; i < chessOnBoard.length; i++) {
            for (int j = 0; j < chessOnBoard[0].length; j++) {
                if (chessOnBoard[i][j]!=0){
                    if (chessOnBoard[i][j]==1){//为黑棋时加分，为白棋时要扣分
                        value+=countValue(chessOnBoard,i,j);
                    }else {
                        value-=countValue(chessOnBoard,i,j);
                    }

                }
            }
        }
        return value;
    }

    public static void prune(BoardValue boardValue){
        if(boardValue.a> boardValue.b){
            return;
        }//如果a>b直接返回
        if (boardValue.children == null){
            return;
        }//如果是叶子节点也直接返回
        LinkedList<BoardValue> list = new LinkedList<>();//使用一个栈
        for (BoardValue child : boardValue.children) {//有孩子的对孩子遍历
            prune(child);
        }

    }

}
