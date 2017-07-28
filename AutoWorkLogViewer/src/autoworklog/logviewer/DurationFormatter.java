package autoworklog.logviewer;

import java.time.Duration;

/**
 * Basic extension of the duration, for simple formatting.
 * Created by kagy on 28.07.2017.
 */
public final class DurationFormatter {

    /**
     * Private constructor in order to prevent object creation.
     */
    private DurationFormatter() {

    }

    /**
     * Formats a duration into H:MM:SS.
     * @param dur is the input duation.
     * @return is the String representation of the duration.
     */
    public static String formatHMS(Duration dur) {

        long seconds=dur.getSeconds();

        return String.format("%2d:%2d:%2d", seconds/3600, (seconds%3600)/60, seconds%60);
    }
}
