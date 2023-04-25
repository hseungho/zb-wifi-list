package global.config;

public class DBConfig {

    public static final String SQLITE_DRIVER = "org.sqlite.JDBC";
    public static final String SQLITE_FILE_DB_URL = "jdbc:sqlite:wifi.db";
    public static final String SQLITE_MEMORY_DB_URL = "jdbc:sqlite::memory";
    public static final boolean OPT_AUTO_COMMIT = false;
    public static final int OPT_BATCH_SIZE = 500;


    private static final String DDL =
            "CREATE TABLE wifi (id text PRIMARY KEY, district text, name text, address1 text, address2 text, instl_floor text, instl_type text, instl_org text, service_class text, net_type text, instl_year integer, in_out_type text, connect_env text, lat real, lnt real, worked_at text);";
}
