package control;

import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author yerso
 */
public class Detail implements Globales {
    
    private Image imgDetail;
    
    public void dibujarDetail(Graphics g, int score) {
	for(int i=0; i < (int)1150/50; i++) {
	    if(score < 1000) {
		imgDetail = new javax.swing.ImageIcon(DETAILS_GREEN[i%DETAILS_GREEN.length]).getImage();
	    } else if(score < 2000) {
		imgDetail = new javax.swing.ImageIcon(DETAILS_BLACK[i%DETAILS_BLACK.length]).getImage();
	    } else {
		imgDetail = new javax.swing.ImageIcon(DETAILS_STAR[i%DETAILS_STAR.length]).getImage();
	    }
	    g.drawImage(imgDetail, i*50, 450, null);
	}
    }
}
