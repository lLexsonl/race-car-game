/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import control.Carro;
import control.Carretera;
import control.CarroContra;
import control.Globales;
import control.TraficoCarros;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author hugo
 */
public class JuegoCarro extends JFrame implements Globales, KeyListener {

    private BufferedImage imgBuffered;
    private Image backGroundImage = null;
    private Graphics graficos;
    private int delay=200, score = 0, speed=10;
    private Clip clip;

    public Carro carro;
    public TraficoCarros traficoCarros;
    Carretera carretera = new Carretera(this);
    int contadorContra;
    CarroContra carroContra;

    public JuegoCarro() {
        this.startMusic();
        this.iniciar();
        this.addKeyListener(this);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        contadorContra = 0;
    }

    public void iniciar() {
        imgBuffered = new BufferedImage(ANCHO_FRAME, ALTO_FRAME,
                BufferedImage.TYPE_INT_RGB);
        graficos = imgBuffered.createGraphics();
        carro = new Carro();
        carroContra = new CarroContra();
        traficoCarros = new TraficoCarros(carro);
        carretera = new Carretera(this);
        this.setBackground(Color.black);
        this.setSize(ANCHO_FRAME, ALTO_FRAME);
        this.setLocationRelativeTo(this);
        this.setVisible(true);
        paint(graficos);
        arrancarCarro();
    }
    
    public void startMusic() {
        try {
            // create AudioInputStream object
            AudioInputStream song
                    = AudioSystem.getAudioInputStream(new File(CAR_MUSIC).getAbsoluteFile());

            // create clip reference
            clip = AudioSystem.getClip();

            // open audioInputStream to the clip
            clip.open(song);

            clip.loop(Clip.LOOP_CONTINUOUSLY);
            
            // volume control
            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(-25.0f);
            
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void arrancarCarro() {
        if (!hilo.isAlive()) {
            hilo.start();
        }
    }
    
    public void restart() {
        delay=200; score=0; speed=10;
        iniciar();
    }

    public void moverCarrosContrarios(int speed) {
        traficoCarros.moverTrafico(speed);
    }
    
    
    public void paint(Graphics g) {
        graficos.setColor(Color.black);
        graficos.fillRect(0, 0, ANCHO_FRAME, ALTO_FRAME);
        
        if(score <= 50) {
            backGroundImage = new ImageIcon(FONDOS_IMG[0]).getImage();
        }
        else if(score > 50 && score < 100) {
            backGroundImage = new ImageIcon(FONDOS_IMG[1]).getImage();
        } else {
            backGroundImage = new ImageIcon(FONDOS_IMG[2]).getImage();
        }
        graficos.drawImage(backGroundImage, 0, 0, this);
        carretera.dibujarCarretera(graficos);
        carretera.dibujarLineaSMedia(graficos);
        carro.dibujarCarro(graficos);
        traficoCarros.dibujarTafico(graficos);
        g.drawImage(imgBuffered, 0, 0, this);
        
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
        g.drawString(speed + " Km/h", 400, 67);
        score++;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            carro.setPosx(carro.getPosx() + DESPLAZO_CARRO);
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            carro.setPosx(carro.getPosx() - DESPLAZO_CARRO);
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            carro.setPosy(carro.getPosy() - DESPLAZO_CARRO);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            carro.setPosy(carro.getPosy() + DESPLAZO_CARRO);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private Thread hilo = new Thread() {
        @Override
        public void run() {
            try {
                while (true) {

                    if (carro.getPosx() >= ANCHO_FRAME) {
                        carro.setPosx(ANCHO_FRAME-80);
                    }
                    if (carro.getPosx() < 0) {
                         carro.setPosx(10);
                    }
                    repaint();

                    hilo.sleep(100);
                    if (delay >= 1) {
                        if (score % 10 == 0) {
                            delay -= 5;
                            speed += 5;
                        }
                    }
                    carretera.setInicioLinea(carretera.getFinlinea() + 15);
                    carretera.setFinlinea(carretera.getFinlinea() + 30);
                
                    if (carretera.getFinlinea() >= ANCHO_FRAME) {
                        carretera.setInicioLinea(0);
                        carretera.setFinlinea(30);
                    }
                    moverCarrosContrarios(speed);
                    if (carro.isImpacto()) {
                        restart();
                    }
                }
            } catch (java.lang.InterruptedException ie) {
                System.out.println(ie.getMessage());
            }
        }
    };

    public static void main(String[] args) {
        JuegoCarro ja = new JuegoCarro();
    }

}
