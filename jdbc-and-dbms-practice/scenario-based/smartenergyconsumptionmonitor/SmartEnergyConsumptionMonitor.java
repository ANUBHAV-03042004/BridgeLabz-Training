package smartenergyconsumptionmonitor;

import java.time.LocalDate;
import java.time.YearMonth;

public class SmartEnergyConsumptionMonitor {

    public static void main(String[] args) {
        EnergyMonitor monitor = new EnergyMonitor("Green Home");

        Device hvac      = new Device("D001", "HVAC System",       DeviceType.HVAC,        15.0, false);
        Device lights    = new Device("D002", "Lighting",          DeviceType.LIGHTING,     3.0, false);
        Device fridge    = new Device("D003", "Refrigerator",      DeviceType.APPLIANCE,    2.5, false);
        Device evCharger = new Device("D004", "EV Charger",        DeviceType.EV_CHARGER,  12.0, false);
        Device solar     = new Device("D005", "Solar Panels",      DeviceType.SOLAR_PANEL,  0.0, true);

        monitor.registerDevice(hvac);
        monitor.registerDevice(lights);
        monitor.registerDevice(fridge);
        monitor.registerDevice(evCharger);
        monitor.registerDevice(solar);

        LocalDate day1  = LocalDate.of(2025, 6, 1);
        LocalDate day2  = LocalDate.of(2025, 6, 2);
        LocalDate day3  = LocalDate.of(2025, 6, 3);
        LocalDate day15 = LocalDate.of(2025, 6, 15);

        try {
        
            monitor.recordReadings("D001", day1,  5.2, 4.8, 6.1, 3.9);   
            monitor.recordReadings("D002", day1,  0.8, 0.9, 1.1);       
            monitor.recordReadings("D003", day1,  0.6, 0.7, 0.6, 0.7); 
            monitor.recordReadings("D004", day1,  7.5, 5.0);           
            monitor.recordReadings("D005", day1, -4.2, -3.8, -2.5);     

          
            monitor.recordReadings("D001", day2,  3.5, 3.0, 2.8, 2.5);  
            monitor.recordReadings("D002", day2,  0.5, 0.6, 0.7);       
            monitor.recordReadings("D003", day2,  0.6, 0.7, 0.65, 0.7); 
            monitor.recordReadings("D005", day2, -5.0, -4.5, -3.2);      

        
            monitor.recordReadings("D001", day3,  8.0, 7.5, 6.2, 5.8); 
            monitor.recordReadings("D002", day3,  1.2, 1.3, 1.4);       
            monitor.recordReadings("D003", day3,  0.7, 0.8, 0.7, 0.8); 
            monitor.recordReadings("D004", day3, 11.0, 3.0);            
            monitor.recordReadings("D005", day3, -6.0, -5.5, -4.0);      

      
            monitor.recordReadings("D001", day15, 4.5, 4.0, 5.0, 4.3);
            monitor.recordReadings("D002", day15, 0.9, 1.0, 0.8);
            monitor.recordReadings("D003", day15, 0.65, 0.7, 0.65, 0.7);
            monitor.recordReadings("D005", day15, -3.5, -4.0, -3.0);

        } catch (InvalidEnergyReadingException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }

       

        try {
            monitor.recordReading("D001", day1, -2.5);
        } catch (InvalidEnergyReadingException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
            System.out.printf( "         device='%s', reading=%.4f, reason='%s'%n",
                    e.getDeviceId(), e.getReading(), e.getReason());
        }

  
        try {
            monitor.recordReading("D001", day1, 55.0);
        } catch (InvalidEnergyReadingException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
        }

       
        try {
            monitor.recordReading("D005", day1, 3.5);
        } catch (InvalidEnergyReadingException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
        }

      
        try {
            monitor.recordReading("D005", day1, -25.0);
        } catch (InvalidEnergyReadingException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
        }

  
        try {
            monitor.recordReading("D002", day2, Double.NaN);
        } catch (InvalidEnergyReadingException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
        }

     
  
        System.out.printf("HVAC total on %s      : %.3f kWh%n", day1, monitor.getDailyTotal("D001", day1));
        System.out.printf("Lights avg  on %s      : %.3f kWh%n", day1, monitor.getDailyAverage("D002", day1));
        System.out.printf("Home net    on %s      : %.3f kWh%n", day1, monitor.getHomeDailyTotal(day1));

        
        monitor.printDailyReport(day1);
        monitor.printDailyReport(day3);

        
        monitor.runDailyAlerts(day1);
        monitor.runDailyAlerts(day3);

       
        YearMonth june = YearMonth.of(2025, 6);
    
        System.out.printf("HVAC monthly total      : %.3f kWh%n", monitor.getMonthlyTotal("D001", june));
        System.out.printf("HVAC daily average      : %.3f kWh%n", monitor.getMonthlyDailyAverage("D001", june));
        System.out.printf("Solar monthly total     : %.3f kWh%n", monitor.getMonthlyTotal("D005", june));
        System.out.printf("Home monthly total      : %.3f kWh%n", monitor.getHomeMonthlyTotal(june));

      
        monitor.printMonthlyReport(june);

      
        monitor.printAllData();
    }
}