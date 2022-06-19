package configs;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ChessListener implements MouseListener,Config {

    public Graphics g = null;
    int x;
    int y;

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
        g.setColor(Color.black);
        g.fillOval(x-CHESS_SIZE/2,y-CHESS_SIZE/2,CHESS_SIZE,CHESS_SIZE);

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

