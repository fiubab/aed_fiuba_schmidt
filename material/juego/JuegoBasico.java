package material.juego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class JuegoBasico extends JPanel implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private int x = 50;
    private int y = 50;
    private int vel = 5;

    public JuegoBasico() {
        Timer timer = new Timer(16, this); // ~60 FPS
        timer.start();

        setFocusable(true);
        addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillRect(x, y, 40, 40); // "personaje"
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint(); // refresca la pantalla
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> x -= vel;
            case KeyEvent.VK_RIGHT -> x += vel;
            case KeyEvent.VK_UP -> y -= vel;
            case KeyEvent.VK_DOWN -> y += vel;
            case KeyEvent.VK_ENTER -> vel += 5;
            case KeyEvent.VK_BACK_SPACE -> vel -= 5;
        }
    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Mi primer juego");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new JuegoBasico());
        frame.setVisible(true);
    }
}
