
public class Logger {

    private static java.util.logging.Logger log = null;

    private static void init() {
        if (log == null) {
            log = java.util.logging.Logger.getLogger(Logger.class.getName());
        }
    }

    public static void info(String msg) {
        init();
        log.info(msg);
    }

    public static void warn(String msg) {
        init();
        log.warning(msg);
    }

    public static void error(String msg) {
        init();
        log.severe(msg);
    }

}
