import java.time.LocalTime;

public class clockThread implements Runnable {
    private int h = 0;
    private int m = 0;

    public clockThread(int h, int m) {
        this.h = h;
        this.m = m;
    }


    public void run() {
        while (true) {
            LocalTime current = LocalTime.now();
            String cString = current.toString();
            String[] currentSplit = cString.split(":");

            if (!(Integer.parseInt(currentSplit[0]) == h && Integer.parseInt(currentSplit[1]) == m)) {
                    try {
                        System.out.println("\033c");
                        System.out.println("ALARM SET FOR: " + h + ":" + m);
                        System.out.println(currentSplit[0] + ":" + currentSplit[1] + ":" + currentSplit[2].substring(0, 2));
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        System.out.print(e);
                    }
            } else {
                System.out.println("\033c");
                System.out.println("Time's Up!");
                System.exit(0);
            }
        }
    }
}
