package finaltest;

import javax.swing.*;
import java.awt.*;

public class Yellow extends JPanel {
	Image img;//이미지를 불러오기 위한
	/**
	 * Create the panel.
	 */
	public Yellow() {

		ImageIcon icon = new ImageIcon("D:\\20-2\\자바용\\기말과제\\yellow.png");//이미지를 불러온다.
		img = icon.getImage();//img에 넣는다.
	}
	public void paintComponent(Graphics g) {//불러온 이미지를 패널에 그린다.
		super.paintComponent(g);
		g.drawImage(img, 0, 0,  200,50, this);
	}

}
