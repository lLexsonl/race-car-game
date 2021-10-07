/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author yerso
 */
public class Info {
    private int speed = 1;
    private int score = 0;
    
    public void dibujarInfo(Graphics g) {
	g.setColor(Color.gray);
        g.fillRect(120, 35, 190, 50);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(125, 40, 180, 40);
	
        g.setColor(Color.gray);
        g.fillRect(385, 35, 160,50);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(390,40, 150, 40);
	
        g.setColor(Color.white);
        g.setFont(new Font("Arial",Font.BOLD, 30));
        g.drawString("Score : " + score, 130, 67);
        g.drawString(speed * 10 + " Km/h", 400, 67);
    }
    
    public void setSpeed(int value) {
	this.speed = value;
    }
    
    public int getSpeed() {
	return this.speed;
    }
    
    public void addSpeed(int value) {
	this.speed += value;
    }

    public int getScore() {
	return score;
    }

    public void setScore(int score) {
	this.score = score;
    }
    
    public void addScore(int value) {
	this.score += value;
    }
}
