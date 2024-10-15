package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSDataConverter {

	
	private static int TIME_STARTINDEX = 11; 

	public static int toSeconds(String timestr) {
		
		int secs;
		int hr, min, sec;
		
		int startIndex, endIndex;
		
		startIndex = 11;
		endIndex = 20;
		
		String s1 = timestr.substring(startIndex, endIndex);
		String s2 = s1.substring(0, 2);
		String s3 = s1.substring(3, 5);
		String s4 = s1.substring(6, 8);
		
		hr = Integer.parseInt(s2);
		min = Integer.parseInt(s3);
		sec = Integer.parseInt(s4);
		
		secs = hr * 60 * 60 + min * 60 + sec;
		
		return secs;
		
	}

	public static GPSPoint convert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {

		GPSPoint gpspoint;
		
		int time = toSeconds(timeStr);
		double latitude = Double.parseDouble(latitudeStr);
		double longitude = Double.parseDouble(longitudeStr);
		double elevation = Double.parseDouble(elevationStr);
		
		gpspoint = new GPSPoint(time, latitude, longitude, elevation);
		
		return gpspoint;
		
	}
	
}
