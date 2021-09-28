/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import vista.JuegoCarro;

/**
 *
 * @author hugo
 */
public class Carretera implements Globales, Runnable {

    private int alto, ancho;
    private int inicioLinea = 0, finlinea = ANCHO_FRAME - 10;
    public Thread thread;

     private int anchoF=5;
   
     JuegoCarro juegoCarro;

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public int getInicioLinea() {
        return inicioLinea;
    }

    public void setInicioLinea(int inicioLinea) {
        this.inicioLinea = inicioLinea;
    }

    public int getFinlinea() {
        return finlinea;
    }

    public void setFinlinea(int finlinea) {
        this.finlinea = finlinea;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public Carretera(JuegoCarro ja) {
        activarLineas();
        this.juegoCarro = ja;
    }

    public void dibujarCarretera(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3.0f));
        g2.setPaint(Color.yellow);
        g2.drawLine(0, 50, ANCHO_FRAME, 50);
        g2.drawLine(0, 450, ANCHO_FRAME, 450);
    }

    public void dibujarLineaSMedia(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3.0f));
        g2.setPaint(Color.yellow);
        
        float dash[] = {20};

        g2.setStroke(new BasicStroke(anchoF, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 3.0f, dash, 0.0f));
        g2.drawLine(ANCHO_FRAME, ALTO_FRAME / 2, 0, ALTO_FRAME / 2);
    }

    public void moverLinea() {
       
        finlinea = inicioLinea + 5;

        if (inicioLinea < 0) {
            inicioLinea = 0;
            finlinea = ANCHO_FRAME - 100;
        }

    }

    public void activarLineas() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(10);
            moverLinea();
        } catch (InterruptedException ex) {
            Logger.getLogger(Carretera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
