package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

import no.hvl.dat100ptc.TODO;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

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

		xstep = scale(MAPXSIZE, minlon, maxlon);
		ystep = scale(MAPYSIZE, minlat, maxlat);

		showRouteMap(MARGIN + MAPYSIZE);

		replayRoute(MARGIN + MAPYSIZE);

		showStatistics();

		System.out.println(gpspoints[0].getLongitude() + " " + gpspoints[0].getLatitude());
		System.out.println(gpspoints[1].getLongitude() + " " + gpspoints[1].getLatitude());
		System.out.println(gpspoints[2].getLongitude() + " " + gpspoints[2].getLatitude());

	}

	public double scale(int maxsize, double minval, double maxval) {

		double step = maxsize / (Math.abs(maxval - minval));

		return step;
	}

	public void showRouteMap(int ybase) {

		for (int i = 0; i < gpspoints.length; i++) {
			
			int x = (int)(gpspoints[i].getLongitude() * 1000000);
			int y = (int)(gpspoints[i].getLatitude() * 1000000);

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

	}

}
