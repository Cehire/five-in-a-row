package configs;

import java.util.LinkedList;

public class BoardValue {
    //用来估值每个情形的价值
    int flag;//AI执棋时的颜色，flag==1为黑棋，flag==2为白棋
    int value;//当前形势的价值
    int a,b;
    int[][] chessOnBoard;//保存这个情形时的棋子情况
    Change change;
    BoardValue parent;//父节点
    LinkedList<BoardValue> children;//子树
    public BoardValue() {
    }

    public BoardValue(Change change, BoardValue parent) {
        this.change = change;
        this.parent = parent;
    }

    public BoardValue(int flag, Change change, BoardValue parent) {
        this.flag = flag;
        this.change = change;
        this.parent = parent;
    }

    void getChildren(){//获得子树
        int[][] ints = getTmpChessBoard();
        children = new LinkedList<>();
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints[0].length; j++) {
                if (ints[i][j]!=0&&Utils.nearPiece(ints,i,j)){//为空并且周围有棋子，可以作为子树
                    children.push(new BoardValue(this.flag==1?2:1,new Change(i,j,flag),this));
                }
            }
        }
    }

    int[][] getTmpChessBoard(){//根据change和parent获得当前情况的形势
        if(parent==null){//如果没有父节点，说明chessOnBoard存在，直接返回
            return chessOnBoard;
        }
        BoardValue tmp = this;//使用一个指针
        while (tmp.parent!=null){
            tmp = tmp.parent;//找到祖宗节点
        }
        int[][] clone = tmp.chessOnBoard.clone();//获取初始的chessOnBoard
        tmp = this;
        while (tmp.parent!=null){
            clone[tmp.change.x][tmp.change.y] = tmp.change.value;
            tmp = tmp.parent;
        }//更新变化
        return clone;
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