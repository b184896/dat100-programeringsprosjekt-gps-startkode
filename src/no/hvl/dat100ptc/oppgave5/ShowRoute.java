package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

import no.hvl.dat100ptc.TODO;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 700;
	private static int MAPYSIZE = 600;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;

	private double minlon, minlat, maxlon, maxlat;

	private double xstep, ystep;

	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
		minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		
		System.out.println(minlon);
		System.out.println(maxlon);
		System.out.println(gpspoints[0].getLongitude());
		
		xstep = scale(MAPXSIZE, minlon, maxlon);
		ystep = scale(MAPYSIZE, minlat, maxlat);

		showRouteMap(MARGIN + MAPYSIZE);

		replayRoute(MARGIN + MAPYSIZE);

		showStatistics();
		
	}

	public double scale(int maxsize, double minval, double maxval) {

		double step = maxsize / (Math.abs(maxval - minval));

		return step;
	}

	public void showRouteMap(int ybase) {

		setColor(0, 255, 0);

		for (int i = 0; i < gpspoints.length - 1; i++) {
			GPSPoint P1 = gpspoints[i];
			GPSPoint P2 = gpspoints[i + 1];

			int x1 = MARGIN + (int) ((P1.getLongitude() - minlon) * xstep);
			int y1 = ybase + (int) ((P1.getLatitude() - minlat) * ystep);
			
			System.out.println(x1);
			
			int x2 = MARGIN + (int) ((P2.getLongitude() - minlon) * xstep);
			int y2 = ybase + (int) ((P2.getLatitude() - minlat) * ystep);

			fillCircle(x1, y1, 3);
			fillCircle(x2, y2, 3);

			drawLine(x1, y1, x2, y2);
		}

	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;

		setColor(0, 0, 0);
		setFont("Courier", 12);

		int sec = gpscomputer.totalTime();

		String a = "Total Time     : " + GPSUtils.formatTime(sec);
		String b = "Total distance : " + GPSUtils.formatDouble(gpscomputer.totalDistance() / 1000) + " km";
		String c = "Total elevation: " + GPSUtils.formatDouble(gpscomputer.totalElevation()) + " m";
		String d = "Max speed      : " + GPSUtils.formatDouble(gpscomputer.maxSpeed() * 3.6) + " km/t";
		String e = "Average speed  : " + GPSUtils.formatDouble(gpscomputer.averageSpeed() * 3.6) + " km/t";
		String f = "Energy         : " + GPSUtils.formatDouble(gpscomputer.totalKcal(80)) + " kcal";

		drawString(a, TEXTDISTANCE, TEXTDISTANCE);
		drawString(b, TEXTDISTANCE, TEXTDISTANCE * 2);
		drawString(c, TEXTDISTANCE, TEXTDISTANCE * 3);
		drawString(d, TEXTDISTANCE, TEXTDISTANCE * 4);
		drawString(e, TEXTDISTANCE, TEXTDISTANCE * 5);
		drawString(f, TEXTDISTANCE, TEXTDISTANCE * 6);
	}

	public void replayRoute(int ybase) {

		setColor(0, 0, 255);
		
		int a = MARGIN + (int) ((gpspoints[0].getLongitude() - minlon) * xstep);
		int b = ybase - (int) ((gpspoints[0].getLatitude() - minlat) * ystep);
		
		int c = fillCircle(a, b, 4);
		
		for (int i = 0; i < gpspoints.length - 1; i++) {
			GPSPoint P1 = gpspoints[i];
			GPSPoint P2 = gpspoints[i + 1];

			int x1 = MARGIN + (int) ((P1.getLongitude() - minlon) * xstep);
			int y1 = ybase - (int) ((P1.getLatitude() - minlat) * ystep);

			int x2 = MARGIN + (int) ((P2.getLongitude() - minlon) * xstep);
			int y2 = ybase - (int) ((P2.getLatitude() - minlat) * ystep);

			moveCircle(c, x2, y2);
			
		}

	}
}
