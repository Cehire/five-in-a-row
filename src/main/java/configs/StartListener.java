package configs;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartListener implements ActionListener {

    public ChessBoard chessBoard;//获取棋盘
    public ChessListener chessListener;//获取鼠标监听器
    public Object[] texts = new Object[]{"先手","后手"};
    public Object[] text = new Object[]{"人机","PVP"};
    @Override
    public void actionPerformed(ActionEvent e) {//按下了开始按钮
        chessListener.clear();//清空棋子
        chessListener.flag = 1;
        Object o1 = JOptionPane.showInputDialog(null, "选择模式", "提示", JOptionPane.INFORMATION_MESSAGE, null, text, texts[0]);
        //确定先手后手
        Object o = JOptionPane.showInputDialog(null, "选择先后手", "提示", JOptionPane.INFORMATION_MESSAGE, null, texts, texts[0]);
        chessListener.isAI = o1.equals("人机");
        if(!o.equals("先手")){//先手无所谓，后手的话，电脑先下(7,7),这样会使得一开始永远是我方下棋
            chessListener.chessOnBoard[7][7] = 2;
            chessListener.flag = 2;
        }
        chessListener.isStart = true;
        chessListener.AIsTurn = false;
        chessBoard.paint(chessBoard.getGraphics());//重绘棋盘

    }
}
