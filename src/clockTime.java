import java.time.LocalTime;

// Give the current time in string format
public class clockTime {
    public clockTime() {}

    public String currentTime() {
        LocalTime lt = LocalTime.now();
        String[] ltS = lt.toString().split(":");
        String seconds = ltS[2].substring(0, 2);

        // Returns
        return String.format("%02d", Integer.parseInt(ltS[0])) + ":" + String.format("%02d", Integer.parseInt(ltS[1])) + ":" + String.format("%02d", Integer.parseInt(seconds));
    }
}