/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.Graphics;
import java.util.ArrayList;
import java.lang.Math;

/**
 *
 * @author hugo
 */
public class TraficoCarros implements Globales {

    private int posx, posy;
    public ArrayList<CarroContra> listacarrosContra;
    CarroContra carroContra;
    public Carro carro;
    
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

    public TraficoCarros(Carro carro) {
        this.listacarrosContra = new ArrayList<>();
        this.cargarListaCarrosContra();
        this.carro = carro;
    }

    public void cargarListaCarrosContra() {
        listacarrosContra.clear();
        int px = ANCHO_FRAME, py = ((int) ALTO_FRAME / 11) + 10;
        int tc;
        for (int i = 0; i < 8; i++) {
            if(((int)(Math.random()*100))%2 == 0) {
                tc = retornarNcarro();
                setPosx(px);
                setPosy(py);
                listacarrosContra.add(new CarroContra(getPosx(), getPosy(), tc));
            }
            py += (int) ALTO_FRAME / 11;
        }
    }

    public void dibujarTafico(Graphics g) {

        for(CarroContra carroCont : listacarrosContra ) {
            carroCont.dibujarCarroContra(g);
        }
    }

    public void moverTrafico(int speed) {
        int i = 0;
        int xpo = 0;
        int ypo=0;
        for(CarroContra carroCont : listacarrosContra ) {
            carroCont.setPosx(carroCont.getPosx() - speed);
            xpo = carroCont.getPosx();
            ypo = carroCont.getPosy();
            System.out.println("Carro x" +carro.getPosx() + " y " + carro.getPosy() + "     trafico x"+xpo+"   trafio y "+ypo);
            if (this.carro.verificarChoke(xpo, ypo)) {
                System.out.println("choque");
            }
            i++;
        }
        if(xpo < -100)
            this.cargarListaCarrosContra();
    }

    public int retornarNcarro() {
        int nCarro = (int) (Math.random() * 4);
        return nCarro;

    }
}
