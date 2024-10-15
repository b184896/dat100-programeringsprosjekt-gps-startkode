package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSData {

	private GPSPoint[] gpspoints;
	protected int antall = 0;

	public GPSData(int antall) {

		gpspoints = new GPSPoint[antall];
		antall = 0;
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}

	protected boolean insertGPS(GPSPoint gpspoint) {

		boolean inserted = false;

		if (antall < gpspoints.length) {
			gpspoints[antall] = gpspoint;
			inserted = true;
			antall++;
		}

		return inserted;

	}

	public boolean insert(String time, String latitude, String longitude, String elevation) {

		int ti = GPSDataConverter.toSeconds(time);
		double la = Double.parseDouble(latitude);
		double lo = Double.parseDouble(longitude);
		double el = Double.parseDouble(elevation);

		GPSPoint gpspoint = new GPSPoint(ti, la, lo, el);

		return insertGPS(gpspoint);
	}

	public void print() {

		System.out.println("===== GPS Data - START =====");

		for (int i = 0; i < gpspoints.length; i++) {
			int j = i + 1;
			System.out.println(j + gpspoints.toString());
		}

		System.out.println("===== GPS Data - SLUTT =====");
	}
}
