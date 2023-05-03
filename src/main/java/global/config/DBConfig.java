package global.config;

public class DBConfig {

    public static final boolean OPT_USING_FILE = false;
    public static final String SQLITE_DRIVER = "org.sqlite.JDBC";
    private static final String PROJECT_ABSOLUTE_PATH = System.getProperty("user.dir");
    private static final String SQLITE_FILE_PATH = PROJECT_ABSOLUTE_PATH + "\\wifi.db";
    public static final String SQLITE_FILE_DB_URL = "jdbc:sqlite:" + SQLITE_FILE_PATH;
    public static final String SQLITE_MEMORY_DB_URL = "jdbc:sqlite:file::memory:?cache=shared";
    public static final boolean OPT_AUTO_COMMIT = false;
    public static final int OPT_BATCH_SIZE = 1000;
    public static final int OPT_CONNECTION_POOL_MAX = 10;
    public static final int MAX_WAIT_TIME = 30;
    public static final int WAIT_TIME = 1000;


}
