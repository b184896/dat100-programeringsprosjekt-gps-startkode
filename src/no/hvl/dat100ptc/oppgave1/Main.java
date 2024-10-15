package no.hvl.dat100ptc.oppgave1;

public class Main {

	public static void main(String[] args) {
		
		GPSPoint P1 = new GPSPoint(1, 2.0, 3.0, 5.0);
		
		P1.getTime();
		
		P1.setTime(2);
		
		System.out.print(P1.toString());
		
	}

}
