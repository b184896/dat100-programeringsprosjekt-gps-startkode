package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import java.util.Locale;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.TODO;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] da) {

		double min;

		min = da[0];
		
		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}
		
		return min;
	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {
		
		double tab[] = new double[gpspoints.length];
		
		for (int i = 0; i < gpspoints.length; i++) {
			tab[i] = gpspoints[i].getLatitude();
		}
		
		return tab;
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		double tab[] = new double[gpspoints.length];
		
		for (int i = 0; i < gpspoints.length; i++) {
			tab[i] = gpspoints[i].getLongitude();
		}
		
		return tab;
	}

	private static final int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d;
		double latitude1, longitude1, latitude2, longitude2;
		
		latitude1 = gpspoint1.getLatitude();
		latitude2 = gpspoint2.getLatitude();
		longitude1 = gpspoint1.getLongitude();
		longitude2 = gpspoint2.getLongitude();
		
		double phi1 = Math.toRadians(latitude1);
		double phi2 = Math.toRadians(latitude2);
		double deltaphi = Math.toRadians(latitude2 - latitude1);
		double deltadelta = Math.toRadians(longitude2 - longitude1);
		
		double a = compute_a(phi1, phi2, deltaphi, deltadelta);
		double c = compute_c(a);
		
		d = R * c;
		
		return d;
	}
	
	private static double compute_a(double phi1, double phi2, double deltaphi, double deltadelta) {
	
		double a =(Math.pow((sin(deltaphi/2)), 2) + cos(phi1) * cos(phi2) * Math.pow((sin(deltadelta/2)), 2));
		
		return a;

	}

	private static double compute_c(double a) {

		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		
		return c;

	}

	
	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs;
		double speed;
		
		secs = gpspoint2.getTime() - gpspoint1.getTime();
		
		double d = distance(gpspoint1, gpspoint2);
		
		speed = d / secs;
		
		return speed;

	}

	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";

		String s1 = "  %02d%s%02d%s%02d";
		
		int t = secs / 3600;
		int m = (secs % 3600) / 60;
		int s = (secs % 3600) % 60;
		
		timestr = String.format(s1, t, TIMESEP, m, TIMESEP, s);
		
		return timestr;
	}
	
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		return String.format(Locale.US, "%10.2f", d);
		
	}
}
