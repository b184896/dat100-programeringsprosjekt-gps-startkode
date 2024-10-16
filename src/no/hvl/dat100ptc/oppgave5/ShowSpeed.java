package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;
import no.hvl.dat100ptc.TODO;

public class ShowSpeed extends EasyGraphics {
			
	private static int MARGIN = 50;
	private static int BARHEIGHT = 100; 

	private GPSComputer gpscomputer;
	
	public ShowSpeed() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Speed profile", 
				2 * MARGIN + 
				2 * gpscomputer.speeds().length, 2 * MARGIN + BARHEIGHT);
		
		showSpeedProfile(MARGIN + BARHEIGHT);
	}
	
	public void showSpeedProfile(int ybase) {
		
		int x = MARGIN,y;
		
		double[] sp = gpscomputer.speeds();
		
		int y1 = (int) gpscomputer.averageSpeed();			
		
		double s = 0;
		
		int x21 = x + 1;
		
		double a = sp.length;

		double scalingFactor = 5;
		for (int i = 0; i < a - 1; i++) {
			setColor(0,0,255);
			double scaledElevation = (double) (sp[i] * scalingFactor);
			y = (int) Math.max(0, BARHEIGHT - scaledElevation + 100);
			int y2 = (int) Math.max(ybase, sp[i +1]);
			int x2 = 2;
			int ss  = fillRectangle(x + 2*i, y, x2, (int) y2);
			setColor(255,255,255);
			int ds  = drawRectangle(x + 2*i, y, x2, (int) y2);
			s += sp[i];
			
			
		}
		double g = Math.max(0,BARHEIGHT - ((s / a) * scalingFactor) + 100); 
		System.out.println(sp.length);
		System.out.println(g);
		
		int x2 = 2;
		for (int o = 0; 0 < a-1; o++) {
			setColor(0,255,50);
			
			drawLine(x+2*o,(int) g,x2 * (o+1),(int)g);
		}

	}	
	
}

