package global.constants;

public class SQLConstants {

    public static class WIFI_TABLE {
        public static final String DDL =
                "CREATE TABLE IF NOT EXISTS WIFI " +
                        "(id text PRIMARY KEY, district text, name text, address1 text, address2 text, instl_floor text, " +
                        "instl_type text, instl_org text, service_class text, net_type text, instl_year integer, in_out_type text, " +
                        "connect_env text, lat real, lnt real, worked_at text);";

        public static final String INSERT_BASIC_FORMAT =
                "INSERT INTO WIFI " +
                        "(id, district, name, address1, address2, instl_floor, instl_type, instl_org, service_class, net_type, instl_year, in_out_type, connect_env, lat, lnt, worked_at) " +
                        "VALUES " +
                        "('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%d', '%s', '%s', '%f', '%f', '%s')";

        public static final String INSERT_BASIC_STATEMENT =
                "INSERT INTO WIFI " +
                        "(id, district, name, address1, address2, instl_floor, instl_type, instl_org, service_class, net_type, instl_year, in_out_type, connect_env, lat, lnt, worked_at) " +
                        "VALUES " +
                        "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        public static final String SELECT_ALL = "SELECT * FROM WIFI";

        public static final String SELECT_WHERE_ID = "SELECT * FROM WIFI WHERE id = ?;";

    }

    public static class HISTORY_TABLE {
        public static final String DDL = "CREATE TABLE IF NOT EXISTS history " +
                "(id integer PRIMARY KEY AUTOINCREMENT, lat real, lnt real, created_at text);";

        public static final String INSERT_BASIC_STATEMENT =
                "INSERT INTO HISTORY (lat, lnt, created_at) VALUES (?, ?, ?);";

        public static final String SELECT_ALL = "SELECT * FROM HISTORY";
        public static final String SELECT_WHERE_ID_STATEMENT = "SELECT * FROM HISTORY WHERE id = ?;";
        public static final String DELETE_WHERE_ID = "DELETE FROM HISTORY WHERE id = ?;";
    }

}
