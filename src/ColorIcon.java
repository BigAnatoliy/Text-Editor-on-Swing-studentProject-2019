import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Icon;

public class ColorIcon implements Icon {

    private int width = 10, height = 10;

    Color col;

    public ColorIcon(Color col) {
        this.col = col;
    }

    @Override
    public int getIconHeight() {
        // TODO Auto-generated method stub

        return height;
    }

    @Override
    public int getIconWidth() {
        // TODO Auto-generated method stub
        return width;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        // TODO Auto-generated method stub
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setColor(col);
        g2d.fillOval(x + 1, y + 1, width - 2, height - 2);

        g2d.dispose();
    }
}
