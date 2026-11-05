package ca.hccis.multithreading;

/**
 * Thread-safe I/O utility class for multi-threaded output.
 * Provides synchronized println methods to prevent interleaved console output
 * from multiple threads.
 *
 * @author Peter Logan
 * @since 2026-10-18
 */
public class IO {

    /**
     * Thread-safe print to console.
     *
     * @param message the message to print
     *
     * @author Peter Logan
     * @since 2026-10-18
     */
    public static synchronized void print(String message) {
        System.out.print(message);
    }

    /**
     * Thread-safe println to console.
     *
     * @param message the message to print
     *
     * @author Peter Logan
     * @since 2026-10-18
     */
    public static synchronized void println(String message) {
        System.out.println(message);
    }

    /**
     * Thread-safe println to console with no message (blank line).
     *
     * @author Peter Logan
     * @since 2026-10-18
     */
    public static synchronized void println() {
        System.out.println();
    }
}
