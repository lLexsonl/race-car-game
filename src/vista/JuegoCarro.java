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
import control.Info;
import control.InfoVidas;
import control.TraficoCarros;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

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
import javax.swing.JOptionPane;

/**
 *
 * @author hugo
 */
public class JuegoCarro extends JFrame implements Globales, KeyListener {

    private BufferedImage imgBuffered;
    private Image backGroundImage = new ImageIcon(FONDOS_IMG[0]).getImage();
    private Graphics graficos;
    //private int score = 0, speed=1, lifes=3;
    private Clip clip;

    public Carro carro;
    public TraficoCarros traficoCarros;
    Carretera carretera = new Carretera(this);
    Info info = new Info();
    InfoVidas vidas = new InfoVidas();
    CarroContra carroContra;

    public JuegoCarro() {
	this.startMusic();
	this.iniciar();
	this.addKeyListener(this);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void iniciar() {
	imgBuffered = new BufferedImage(ANCHO_FRAME, ALTO_FRAME,
		BufferedImage.TYPE_INT_RGB);
	graficos = imgBuffered.createGraphics();
	carro = new Carro();
	carroContra = new CarroContra();
	traficoCarros = new TraficoCarros(carro);
	carretera = new Carretera(this);
	info = new Info();
	vidas.dibujarVidas(graficos);
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
	}
    }
    
    public void startMusicChoque() {
	try {
	    // create AudioInputStream object
	    AudioInputStream song
		    = AudioSystem.getAudioInputStream(new File(CRASH_MUSIC).getAbsoluteFile());

	    // create clip reference
	    Clip crash = AudioSystem.getClip();

	    // open audioInputStream to the clip
	    crash.open(song);

	    crash.loop(0);

	    // volume control
	    FloatControl volume = (FloatControl) crash.getControl(FloatControl.Type.MASTER_GAIN);
	    volume.setValue(-20.0f);

	} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
	}
    }

    public void arrancarCarro() {
	if (!hilo.isAlive()) {
	    hilo.start();
	}
    }

    public void restart() {
	info.setScore(0);
	info.setSpeed(1);
	vidas.die();
	if (vidas.getVidas() <= 0) {
	    int input = JOptionPane.showConfirmDialog(null, 
                "Deseas continuar jugando?", "Confirmar Salida...", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
	    if (input == 1) {
		System.exit(0);
	    }
	    vidas = new InfoVidas();
	}
	iniciar();
    }

    public void moverCarrosContrarios(int speed) {
	traficoCarros.moverTrafico(speed);
    }

    @Override
    public void paint(Graphics g) {
	graficos.setColor(Color.black);
	graficos.fillRect(0, 0, ANCHO_FRAME, ALTO_FRAME);

	int score = info.getScore();
	int speed = info.getSpeed();

	if (score % 1000 == 0 && score <= 2000) {
	    backGroundImage = new ImageIcon(FONDOS_IMG[score / 1000]).getImage();
	}

	graficos.drawImage(backGroundImage, 0, 0, this);
	carretera.dibujarCarretera(graficos);
	carretera.dibujarLineaSMedia(graficos);
	info.dibujarInfo(graficos);
	vidas.dibujarVidas(graficos);
	carro.dibujarCarro(graficos);
	traficoCarros.dibujarTafico(graficos);
	g.drawImage(imgBuffered, 0, 0, this);
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
			carro.setPosx(ANCHO_FRAME - 80);
		    }
		    if (carro.getPosx() < 0) {
			carro.setPosx(10);
		    }

		    repaint();
		    info.addScore(1);

		    hilo.sleep(10);
		    if (info.getScore() % 100 == 0) {
			if (info.getSpeed() < 30) {
			    info.addSpeed(1);
			}
		    }
		    carretera.setInicioLinea(carretera.getFinlinea() + 15);
		    carretera.setFinlinea(carretera.getFinlinea() + 30);

		    if (carretera.getFinlinea() >= ANCHO_FRAME) {
			carretera.setInicioLinea(0);
			carretera.setFinlinea(30);
		    }
		    moverCarrosContrarios(info.getSpeed());

		    if (carro.isImpacto()) {
			repaint();
			startMusicChoque();
			JOptionPane.showMessageDialog(null, "Impactado");
			restart();
		    }
		}
	    } catch (java.lang.InterruptedException ie) {
		System.out.println(ie.getMessage());
	    }
	}
    };

    public static void main(String[] args) {
	new JuegoCarro();
    }

}
