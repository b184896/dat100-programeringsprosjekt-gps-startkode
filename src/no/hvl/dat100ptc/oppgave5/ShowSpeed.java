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
		
		int s = (int) gpscomputer.averageSpeed();	
		
		int x21 = x + 1;
		

		double scalingFactor = 2;
		for (int i = 0; i < sp[i] - 1; i++) {
			double scaledElevation = (double) (sp[i] * scalingFactor);
			y = (int) Math.max(0, BARHEIGHT - scaledElevation);
			int y2 = (int) Math.max(ybase, sp[i +1].getSpeeds());
			int x2 = 2;
			int ss  = fillRectangle(x + 3 * i, y, x2, y2);
			

		}
		
			

	}	
	
}

