package temperatureanalyzer;

public class TemperatureAnalyzer {
    public static void main(String args[]) {
        float weeks[][] = {
            {0.0f, 1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f, 8.0f, 9.0f, 10.0f, 11.0f,
             12.0f, 13.0f, 14.0f, 15.0f, 16.0f, 17.0f, 18.0f, 19.0f, 20.0f, 21.0f, 22.0f, 23.0f}, // Day 0
            {100.0f, 101.0f, 102.0f, 103.0f, 104.0f, 105.0f, 106.0f, 107.0f, 108.0f, 109.0f, 110.0f, 111.0f,
             112.0f, 113.0f, 114.0f, 115.0f, 116.0f, 117.0f, 118.0f, 119.0f, 120.0f, 121.0f, 122.0f, 123.0f}, // Day 1
            {200.0f, 201.0f, 202.0f, 203.0f, 204.0f, 205.0f, 206.0f, 207.0f, 208.0f, 209.0f, 210.0f, 211.0f,
             212.0f, 213.0f, 214.0f, 215.0f, 216.0f, 217.0f, 218.0f, 219.0f, 220.0f, 221.0f, 222.0f, 223.0f}, // Day 2
            {300.0f, 301.0f, 302.0f, 303.0f, 304.0f, 305.0f, 306.0f, 307.0f, 308.0f, 309.0f, 310.0f, 311.0f,
             312.0f, 313.0f, 314.0f, 315.0f, 316.0f, 317.0f, 318.0f, 319.0f, 320.0f, 321.0f, 322.0f, 323.0f}, // Day 3
            {400.0f, 401.0f, 402.0f, 403.0f, 404.0f, 405.0f, 406.0f, 407.0f, 408.0f, 409.0f, 410.0f, 411.0f,
             412.0f, 413.0f, 414.0f, 415.0f, 416.0f, 417.0f, 418.0f, 419.0f, 420.0f, 421.0f, 422.0f, 423.0f}, // Day 4
            {500.0f, 501.0f, 502.0f, 503.0f, 504.0f, 505.0f, 506.0f, 507.0f, 508.0f, 509.0f, 510.0f, 511.0f,
             512.0f, 513.0f, 514.0f, 515.0f, 516.0f, 517.0f, 518.0f, 519.0f, 520.0f, 521.0f, 522.0f, 523.0f}, // Day 5
            {600.0f, 601.0f, 602.0f, 603.0f, 604.0f, 605.0f, 606.0f, 607.0f, 608.0f, 609.0f, 610.0f, 611.0f,
             612.0f, 613.0f, 614.0f, 615.0f, 616.0f, 617.0f, 618.0f, 619.0f, 620.0f, 621.0f, 622.0f, 623.0f}  // Day 6
        };

        float hottest = Float.MIN_VALUE, coldest = Float.MAX_VALUE;
        float avg_temperature_per_day[] = new float[7];
        int index = 0;

        for (float[] dailyTemperatures : weeks) {
            float sum = 0.0f;
            for (float hourlyTemperature : dailyTemperatures) {
                if (hourlyTemperature > hottest) hottest = hourlyTemperature;
                if (hourlyTemperature < coldest) coldest = hourlyTemperature;
                sum += hourlyTemperature;
            }
            avg_temperature_per_day[index++] = sum / 24;
        }

        System.out.printf("Hottest temperature is %.2f and Coldest temperature is %.2f%n", hottest, coldest);
        for (int i = 0; i < avg_temperature_per_day.length; i++) {
            System.out.printf("Day %d average temperature: %.2f%n", i, avg_temperature_per_day[i]);
        }
    }
}