package configs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ChessListener implements MouseListener, Config {

    public Graphics g = null;//接收组件的graphics
    public ChessBoard chessBoard;
    int x;
    int y;
    int flag = 1; //判断棋子颜色

    public ChessListener(Graphics g, ChessBoard chessBoard) {
        this.g = g;
        this.chessBoard = chessBoard;
    }

    int[][] chessOnBoard = new int[LINES][LINES];//建立一个棋盘来维护棋子的情况，初始值为0，黑子为1，白子为2
    int row = 0;                //记录行数
    int col = 0;                //记录列数


    public ChessListener() {
    }

    public ChessListener(Graphics g) {
        this.g = g;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        x = e.getX();
        y = e.getY();
        //对鼠标的位置进行校正
        if (x % SIZE < SIZE / 2 && y % SIZE < SIZE / 2) {
            x -= x % SIZE;
            y -= y % SIZE;
        } else if (x % SIZE >= SIZE / 2 && y % SIZE >= SIZE / 2) {
            x = x - x % SIZE + SIZE;
            y = y - y % SIZE + SIZE;
        } else if (x % SIZE >= SIZE / 2 && y % SIZE < SIZE / 2) {
            x = x - x % SIZE + SIZE;
            y -= y % SIZE;
        } else if (x % SIZE < SIZE / 2 && y % SIZE >= SIZE / 2) {
            x -= x % SIZE;
            y = y - y % SIZE + SIZE;
        }
        if ((x > X1 + SIZE * (LINES - 1) || y > Y1 + SIZE * (LINES - 1))) {
            return;//提前结束监听事件
        }
        //校正完毕，判断校正后的棋盘是否有子存在，若有直接返回监听事件
        row = x / SIZE - 1;
        col = y / SIZE - 1;
        if (chessOnBoard[row][col] != 0) {
            return;
        }
        if (flag == 1) {
            g.setColor(Color.black);                        //设置图形颜色为黑色
            g.fillOval(x - CHESS_SIZE / 2, y - CHESS_SIZE / 2, CHESS_SIZE, CHESS_SIZE); //以(x,y)为圆心,CHESS(30px)为直径画圆
            flag = 2;
        } else if (flag == 2) {
            g.setColor(Color.white);                        //设置图形颜色为白色
            g.fillOval(x - CHESS_SIZE / 2, y - CHESS_SIZE / 2, CHESS_SIZE, CHESS_SIZE); //以(x,y)为圆心,CHESS(30px)为直径画圆
            flag = 1;
        }
        chessOnBoard[row][col] = flag;


    }

    @Override//鼠标松开时执行的方法
    public void mouseReleased(MouseEvent e) {
        if(ifWin()) {
            if (chessOnBoard[row][col] == 1) {
                JOptionPane.showConfirmDialog(null, "白方胜利", "游戏结束", JOptionPane.OK_CANCEL_OPTION);
            }
            else {
                JOptionPane.showConfirmDialog(null, "黑方胜利", "游戏结束", JOptionPane.OK_CANCEL_OPTION);
            }
            clear();
            chessBoard.paint(g);

        }
    }
    private boolean ifWin() {
        if (win1(row, col) >= 5 || win2(row, col) >= 5 || win3(row, col) >= 5 || win4(row, col) >= 5) {
            return true;
        }
        else
            return false;
    }

    private int win1(int x, int y) {//横向判断输赢
        int count = 0;
        //向右
        for (int i = x + 1; i < LINES; i++) {
            if (chessOnBoard[x][y] == chessOnBoard[i][y]&&chessOnBoard[x][y]!=0) {
                count++;
            } else
                break;
        }
        //向左
        for (int i = x; i >= 0; i--) {
            if (chessOnBoard[x][y] == chessOnBoard[i][y]&&chessOnBoard[x][y]!=0) {
                count++;
            } else
                break;
        }
        return count;
    }

    private int win2(int x, int y) {//纵向判断输赢
        int count = 0;
        int i = 0;
        for (i = y + 1; i < LINES; i++) {//向下
            if (chessOnBoard[x][y] == chessOnBoard[x][i] && chessOnBoard[x][y] != 0) {
                count++;
            } else
                break;
        }
        for (i = y; i >= 0; i--) {
            if (chessOnBoard[x][y] == chessOnBoard[x][i] && chessOnBoard[x][y] != 0) {//向上
                count++;
            } else
                break;
        }
        return count;
    }

    private int win3(int x, int y) {//主对角线判断
        int count = 0;
        int i = 0, j = 0;
        for (i = x + 1, j = y + 1; i < LINES && j < LINES; i++, j++) {
            if (chessOnBoard[x][y] == chessOnBoard[i][j]&&chessOnBoard[x][y]!=0) {
                count++;
            } else
                break;
        }
        for (i = x, j = y; i >= 0 && j >= 0; i--, j--) {
            if (chessOnBoard[x][y] == chessOnBoard[i][j]&&chessOnBoard[x][y]!=0) {
                count++;
            } else
                break;
        }
        return count;
    }

    private int win4(int x, int y) {//副对角线判别
        int count = 0;
        int i = 0, j = 0;
        for (i = x + 1, j = y - 1; i < LINES && j >= 0; i++, j--) {
            if (chessOnBoard[x][y] == chessOnBoard[i][j]&&chessOnBoard[x][y]!=0) {
                count++;
            } else
                break;
        }
        for (i = x, j = y; i >= 0 && j < LINES; i--, j++) {
            if (chessOnBoard[x][y] == chessOnBoard[i][j]&&chessOnBoard[x][y]!=0) {
                count++;
            } else
                break;
        }
        return count;
    }
    void clear() { //矩阵元素清零
        for (int i = 0; i < LINES; i++) {
            for (int j = 0; j < LINES; j++) {
                chessOnBoard[i][j] = 0;
            }
        }
    }


    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

