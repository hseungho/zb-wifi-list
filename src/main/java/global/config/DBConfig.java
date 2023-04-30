package global.config;

public class DBConfig {

    public static final String SQLITE_DRIVER = "org.sqlite.JDBC";
    public static final String SQLITE_FILE_DB_URL = "jdbc:sqlite:wifi.db";
    public static final String SQLITE_MEMORY_DB_URL = "jdbc:sqlite::memory";
    public static final boolean OPT_AUTO_COMMIT = false;
    public static final int OPT_BATCH_SIZE = 500;


}
