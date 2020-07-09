package computeSum;

import utils.Format;

public class serialSum {
    static double sum(long times){
        double sum = 0.0;
        double round = 10_000_000;
        for (long i = 0; i < times; i++) {
            for (double j = 0; j < round; j++) {
                sum += j;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        long complexity = Long.parseLong(args[args.length-1]);
        long start = System.nanoTime();

        /* == begin task == */
        double result = sum(complexity);
        /* == end task == */
        long end = System.nanoTime();

        System.out.println("complexity: " + complexity);
        System.out.println("sum: "+result);
        long timeNano = end - start;
        long duration = timeNano / 1000_000;
        System.out.println("=======================================");
        System.out.println("Duration (in milli-sec): "+ Format.longToString(duration));
    }
}
