/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import vista.JuegoCarro;

/**
 *
 * @author yerso
 */
public class Info implements Globales, Runnable {
    private int speed = 0;
    private int score = 1;
    public Thread thread;
    private JuegoCarro juegoCarro;
    
    public Info(JuegoCarro ja) {
	this.juegoCarro = ja;
	thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
	try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            Logger.getLogger(Carretera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void dibujarInfo(Graphics g) {
	g.setColor(Color.gray);
        g.fillRect(120,35,220,50);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(125,40, 210, 40);
        g.setColor(Color.gray);
        g.fillRect(385,35,180,50);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(390,40, 170, 40);
        g.setColor(Color.white);
        g.setFont(new Font("Arial",Font.BOLD,30));
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
