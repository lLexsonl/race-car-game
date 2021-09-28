/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JOptionPane;

/**
 *
 * @author hugo
 */
public class Carro implements Globales {

    private int posx;
    private int posy;
    private Image imgCarro;
    private boolean impacto=false;

    public Carro() {
        posx = 0;
        posy = (int) ALTO_FRAME / 2;
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

    public void dibujarCarro(Graphics g) {
        this.imgCarro = new javax.swing.ImageIcon(CAR_IMG).getImage();
        g.drawImage(this.imgCarro, this.posx, this.posy, null);
    }
    
    public boolean verificarChoke(int x, int y){
              
          if( x==getPosx() && (getPosy()>24 && getPosy() < 86) && ( y<=54) ) {
              JOptionPane.showMessageDialog(null, "Impactado");
              this.impacto = true;
          }
       return  impacto;
    }
}
