package control;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author yerso
 */
public class InfoVidas {
    private int lives = 2;
    
    public InfoVidas() {
    }
    
    public void dibujarVidas(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(650,35,150,50);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(655,40, 140, 40);
	
        g.setColor(Color.white);
        g.setFont(new Font("Arial",Font.BOLD,30));
        g.drawString("Vidas: " + lives, 660, 67);
    }
    
    public void die() {
	this.lives--;
    }
    
    public int getVidas() {
	return this.lives;
    }
}
