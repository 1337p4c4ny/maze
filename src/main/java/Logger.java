
public class Logger {

    private static java.util.logging.Logger log = null;

    private static void init() {
        if (log == null) {
            log = java.util.logging.Logger.getLogger(Logger.class.getName());
        }
    }

    public static void info(String msg) {
        init();
        synchronized (log) {
            log.info(msg);
        }
    }

    public static void warn(String msg) {
        init();
        synchronized (log) {
            log.warning(msg);
        }
    }

    public static void error(String msg) {
        init();
        synchronized (log) {
            log.severe(msg);
        }
    }

}
