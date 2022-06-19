package configs;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class ChessBoard extends JFrame implements Config {
    int[][] chessOnBoard = null;
    public ChessListener chessListener;
    @Serial
    private static final long serialVersionUID  =1L;

    public ChessBoard(){
        setTitle("五子棋AI");
        setSize(1000,800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
//        ChessListener chessListener = new ChessListener();//新建监听器
        chessListener = new ChessListener();
//        ChessBoard chessBoard = new ChessBoard();//新建棋盘
//        chessBoard.chessOnBoard = chessListener.chessOnBoard;
        chessOnBoard = chessListener.chessOnBoard;
//        chessListener.chessBoard = chessBoard;//在监听器导入棋盘（用于调用棋盘的绘画方法）
        chessListener.chessBoard = this;
//        chessListener.g = chessBoard.getGraphics();//监听器添加Graphics画笔（用于绘制棋子）
        chessListener.g = this.getGraphics();
//        chessBoard.addMouseListener(chessListener);//添加监听器
        this.addMouseListener(chessListener);
//
        JButton back = new JButton("悔棋");
        BackListener backListener = new BackListener();
        backListener.chessListener = chessListener;
        backListener.chessBoard = this;
        back.addActionListener(backListener);

        JButton start = new JButton("开始/重开");
        StartListener startListener = new StartListener();
        startListener.chessBoard = this;
        startListener.chessListener = chessListener;
        start.addActionListener(startListener);

        this.setLayout(null);
        this.add(back);
        this.add(start);
        back.setBounds(800, 200, 150, 40);
        start.setBounds(800, 100, 150, 40);


    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < LINES; i++) {
            g.setColor(Color.black);
            g.drawLine(X1,X1+SIZE*i,X1+SIZE*(LINES-1),Y1+SIZE*i);
            g.drawLine(X1 + SIZE * i, X1, Y1 + SIZE * i, Y1 + SIZE * (LINES - 1));
        }
        if (chessOnBoard!=null){
            for(int i = 0;i<LINES;i++) {              //重绘棋子,用于悔棋
                for(int j = 0;j<LINES;j++) {
                    if(chessOnBoard[i][j] == 2) {
                        g.setColor(Color.black);
                        g.fillOval((i+1)*SIZE-CHESS_SIZE/2,(j+1)*SIZE-CHESS_SIZE/2, CHESS_SIZE, CHESS_SIZE);
                    } else if(chessOnBoard[i][j] == 1) {
                        g.setColor(Color.white);
                        g.fillOval((i+1)*SIZE-CHESS_SIZE/2,(j+1)*SIZE-CHESS_SIZE/2, CHESS_SIZE, CHESS_SIZE);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        ChessBoard chessBoard = new ChessBoard();//新建棋盘
    }
}
