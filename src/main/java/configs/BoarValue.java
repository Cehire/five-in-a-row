package configs;

import java.util.LinkedList;

public class BoarValue {
    //用来估值每个情形的价值
    int flag;//flag==1为黑棋，flag==2为白棋
    int value;
    int[][] chessOnBoard;//保存这个情形时的棋子情况
    Change change;
    BoarValue parent;//父节点
    LinkedList<BoarValue> children;//子树
    public BoarValue() {
    }

    public BoarValue(Change change, BoarValue parent) {
        this.change = change;
        this.parent = parent;
    }

    void getChildren(){//获得子树
        children = new LinkedList<>();
        for (int i = 0; i < chessOnBoard.length; i++) {
            for (int j = 0; j < chessOnBoard[0].length; j++) {
                if (chessOnBoard[i][j]!=0&&Utils.nearPiece(chessOnBoard,i,j)){//为空并且周围有棋子，可以作为子树
                    children.push(new BoarValue(new Change(i,j,flag),this));
                }
            }
        }

    }

    void countValue(){//用于计算当前情形的值

    }

}
class Change{//三元数组保存相比于父亲的变化
    int x;
    int y;
    int value;
    public Change(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }
}