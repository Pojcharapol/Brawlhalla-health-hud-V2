package me.buffsee.bhh;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SmashWindowObject {

	public SmashWindowObject(String framename, Rectangle ponerect, int x, int y) {

		JFrame p1 = new JFrame(framename);
		
		JLabel label = new JLabel("werg");
		label.setFont(new Font("Verdana", Font.BOLD, 100));
		label.setForeground(new Color(255, 255, 255));

		p1.add(label);

		p1.setUndecorated(true);
		p1.setOpacity(0.95f);
		p1.setBackground(new Color(0, 0, 0, 0));
		p1.setAlwaysOnTop(true);
		p1.setLocation(x, y);

		p1.setVisible(true);
		
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		HealthCalculator healthcalc = new HealthCalculator();
		ImageRounder rounder = new ImageRounder();
		healthcalc.initRobot();
		
		while (true) {
			if(framename.equals("p1")) {
				int[] rgb = healthcalc.getTopLeftColor();
				label.setForeground(new Color(rgb[0], rgb[1], rgb[2]));
				label.setText((int) healthcalc.getHealthFromColor(rgb) + "");
			}
			if(framename.equals("p2")) {
				int[] rgb = healthcalc.getTopRightColor();
				label.setForeground(new Color(rgb[0], rgb[1], rgb[2]));
				label.setText((int) healthcalc.getHealthFromColor(rgb) + "");
			}
			label.setIcon(new ImageIcon(rounder.getRoundedImage(this.getScaledImage(robot.createScreenCapture(ponerect)))));
			p1.pack();
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	private BufferedImage getScaledImage(Image srcImg) {
		int w = (int) (srcImg.getWidth(null)*1.5);
		int h = (int) (srcImg.getHeight(null)*1.5);
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
	    Graphics2D g2 = resizedImg.createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
	    g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	    g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();
	    return resizedImg;
	}
	
}
