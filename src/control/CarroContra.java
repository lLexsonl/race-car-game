/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author hugo
 */
public class CarroContra implements Globales {

    private int posx;
    private int posy;
    private Image imgCarroContra;
    int tipoCarro;

    public int getTipoCarro() {
        return tipoCarro;
    }

    public void setTipoCarro(int tipoCarro) {
        this.tipoCarro = tipoCarro;
    }

    public CarroContra() {
        this.posx = ANCHO_FRAME - 80;
        this.posy = (int) ALTO_FRAME / 2;

    }

    public CarroContra(int posx, int posy, int tipo) {
        this.posx = posx;
        this.posy = posy;
        this.tipoCarro=tipo;
    }

    public void dibujarCarroContra(Graphics g) {
        imgCarroContra = new javax.swing.ImageIcon(CAARROS_CONTRA[getTipoCarro()]).getImage();
        g.drawImage(imgCarroContra, posx, posy, null);
    }

    public int getPosx() {
        return posx;
    }

    public void setPosx(int posx) {
        this.posx = posx;
    }

    public int getPosy() {
        return posy;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }
}
