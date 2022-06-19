package configs;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class ChessBoard extends JFrame implements Config {
    @Serial
    private static final long serialVersionUID  =1L;
    public ChessBoard(){
        setTitle("五子棋AI");
        setSize(800,800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < LINES; i++) {
            g.setColor(Color.black);
            g.drawLine(X1,X1+SIZE*i,X1+SIZE*(LINES-1),Y1+SIZE*i);
            g.drawLine(X1 + SIZE * i, X1, Y1 + SIZE * i, Y1 + SIZE * (LINES - 1));
        }
    }

    public static void main(String[] args) {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.addMouseListener(new ChessListener(chessBoard.getGraphics()));

    }
}
