import java.time.LocalTime;

// Give the current time in string format
public class clockTime {
    public clockTime() {}

    public String currentTime() {
        LocalTime t = LocalTime.now();
        String[] tS = t.toString().split(":");

        // Returns
        return String.format("%02d", Integer.parseInt(tS[0])) + ":" + String.format("%02d", Integer.parseInt(tS[1]));
    }
}