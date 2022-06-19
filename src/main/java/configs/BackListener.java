package configs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackListener implements ActionListener {
    public ChessBoard chessBoard;//获取棋盘
    public ChessListener chessListener;//获取鼠标监听器
    @Override
    public void actionPerformed(ActionEvent e) {//按下了悔棋按钮
        if (chessListener.canBack&&chessListener.isStart){
            //执行悔棋，监听器中记录的上一次值归0，同时变为另一方下棋
            chessListener.chessOnBoard[chessListener.row][chessListener.col] = 0;
            chessListener.flag = chessListener.flag==1?2:1;
            //同时重新绘制
            chessBoard.paint(chessBoard.getGraphics());
            chessListener.canBack = false;
        }
    }
}
