package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

import no.hvl.dat100ptc.TODO;

public class GPSComputer {
	
	private GPSPoint[] gpspoints;
	
	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}
	
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	public double totalDistance() {

		double distance = 0;

		for (int i = 0; i < gpspoints.length - 1; i++) {
			double d1 = GPSUtils.distance(gpspoints[i], gpspoints[i + 1]);
			distance = distance + d1;
		}
		
		return distance;

	}

	public double totalElevation() {

		double elevation = 0;

		for (int i = 0; i < gpspoints.length-1; i++) {
			double e1 = gpspoints[i].getElevation(); 
			double e2 = gpspoints[i+1].getElevation();
			if (e1 < e2) {
				elevation +=  (e2-e1);
			}
		}
		return elevation;
	}

	public int totalTime() {

		int secs = 0;
		
		for (int i = 0; i < gpspoints.length - 1; i++) {
			secs += gpspoints[i + 1].getTime() - gpspoints[i].getTime();
		}
		return secs;
	}
		

	public double[] speeds() {
		
		double[] speeds = new double[gpspoints.length-1];
		for (int i = 0; i < speeds.length; i++) {
			speeds[i] = GPSUtils.speed(gpspoints[i],gpspoints[i+1]);
			
		}
		return speeds;
	}
	
	public double maxSpeed() {
		
		double max = 0;
		
		double[] s = speeds();
		
		for (int i = 0; i < s.length; i++) {
			if (max < s[i]) {
				max = s[i];
			}
		}
		return max;
	}

	public double averageSpeed() {

		double average = 0;
		
		double t = totalTime();
		double d = totalDistance();
		
		average = d/t;
		
		return average;
		
		
	}


	// conversion factor m/s to miles per hour (mps)
	public static final double MS = 2.23;

	public double kcal(double weight, int secs, double speed) {

		double kcal;

		double met = 0;		
		double speedmph = speed * MS;
		
		if (speedmph < 10) {
			met = 4.0;
		}
		else if (speedmph >= 10 && speedmph < 12) {
			met = 6.0;
		}
		else if (speedmph >= 12 && speedmph < 14) {
			met = 8.0;
		}
		else if (speedmph >= 14 && speedmph < 16) {
			met = 10.0;
		}
		else if (speedmph >= 16 && speedmph < 20) {
			met = 12.0;
		}
		else if (speedmph > 20) {
			met = 16.0;
		}
		
		double t = secs / 3600.0;
		
		kcal = met * weight * t;
		
		return kcal;
	}

	public double totalKcal(double weight) {

		double totalkcal = 0;
		
		
		for (int i = 0; i < gpspoints.length-1; i++) {
			
			double s = GPSUtils.speed(gpspoints[i], gpspoints[i+1]);
			int t = gpspoints[i+1].getTime()-gpspoints[i].getTime(); 
			double k = kcal(weight, t, s);
			
			totalkcal += k;
			
			
		}
		return totalkcal;
	}
	
	private static double WEIGHT = 80.0;
	
	public void displayStatistics() {

		for (int i = 0; i < gpspoints.length - 1; i++) {
			int sec = totalTime();
			
			System.out.println("==============================================");
			System.out.println("Total Time     : " + GPSUtils.formatTime(sec));	
			System.out.println("Total distance : " + GPSUtils.formatDouble(totalDistance() / 1000) + " km");
			System.out.println("Total elevation: " + GPSUtils.formatDouble(totalElevation()) + " m");
			System.out.println("Max speed      : " + GPSUtils.formatDouble(maxSpeed() * 3.6) + " km/t");
			System.out.println("Average speed  : " + GPSUtils.formatDouble(averageSpeed() * 3.6) + " km/t");
			System.out.println("Energy         : " + GPSUtils.formatDouble(totalKcal(WEIGHT)) + " kcal");
			System.out.println("==============================================");
		}

	}

}
