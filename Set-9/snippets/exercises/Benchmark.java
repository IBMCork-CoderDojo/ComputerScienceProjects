public class Benchmark{
    public static double nanoTimeToRun(Runnable runnable){
        long startTime = System.nanoTime();
        runnable.run();
        return startTime - System.nanoTime();
    }

    public static double milliTimeToRun(Runnable runnable){
        long startTime = System.currentTimeMillis();
        runnable.run();
        return System.currentTimeMillis() - startTime;
    }
}
