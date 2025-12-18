package earthvolume;

public class EarthVolume {
public static void main(String args[])
{
	int radius=6378;
	double volumeinkm=3.14*radius*radius;
	double volumeinmiles=3.14*0.621371*radius*radius;
	System.out.printf("Volume of earth in km : %f\n and in miles is : %f ",volumeinkm,volumeinmiles);
}
}
