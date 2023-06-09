package global.constants;

public class SQLConstants {

    public static class WIFI_TABLE {
        public static final String DDL =
                "CREATE TABLE IF NOT EXISTS wifi " +
                        "(id text PRIMARY KEY, district text, name text, address1 text, address2 text, instl_floor text, " +
                        "instl_type text, instl_org text, service_class text, net_type text, instl_year integer, in_out_type text, " +
                        "connect_env text, lat real, lnt real, worked_at text);";

        public static final String INSERT_BASIC_STATEMENT =
                "INSERT INTO WIFI " +
                        "(id, district, name, address1, address2, instl_floor, instl_type, instl_org, service_class, net_type, instl_year, in_out_type, connect_env, lat, lnt, worked_at) " +
                        "VALUES " +
                        "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        public static final String SELECT_ALL = "SELECT * FROM WIFI";

        public static final String SELECT_WHERE_ID = "SELECT * FROM WIFI WHERE id = ?;";

        /** 1: nowLat, 2: nowLng, 3: nowLat */
        public static final String SELECT_TOP20_WHERE_CLOSED_DISTANCE_ASC =
                "SELECT *, " +
                    "(6371 * acos( cos( radians( ? ) ) * cos( radians( lat ) ) * cos( radians( lnt ) - radians( ? ) ) + sin( radians( ? ) ) * sin( radians( lat ) ) ) ) as distance " +
                "FROM wifi " +
                "ORDER BY distance ASC " +
                "LIMIT 0, 20; ";

        public static final String DELETE_ALL = "DELETE FROM wifi;";

        public static final String EXISTS_AT_LEAST_ONE = "SELECT EXISTS(SELECT 1 FROM wifi LIMIT 1);";
    }

    public static class HISTORY_TABLE {
        public static final String DDL = "CREATE TABLE IF NOT EXISTS history " +
                "(id integer PRIMARY KEY AUTOINCREMENT, lat real, lnt real, created_at text);";

        public static final String INSERT_BASIC_STATEMENT =
                "INSERT INTO HISTORY (lat, lnt, created_at) VALUES (?, ?, ?);";

        public static final String SELECT_ALL = "SELECT * FROM HISTORY";
        public static final String SELECT_WHERE_ID = "SELECT * FROM HISTORY WHERE id = ?;";
        public static final String DELETE_WHERE_ID = "DELETE FROM HISTORY WHERE id = ?;";
        public static final String EXISTS_WHERE_ID = "SELECT EXISTS(SELECT 1 FROM history WHERE id = ?);";

    }

    public static class BOOKMARK_TABLE {
        public static final String DDL =
                "CREATE TABLE IF NOT EXISTS bookmark (" +
                        "id integer PRIMARY KEY AUTOINCREMENT, name text, order_num integer, created_at text, updated_at text);";

        public static final String INSERT_BASIC_STATEMENT = "INSERT INTO BOOKMARK (name, order_num, created_at) VALUES (?, ?, ?);";
        public static final String SELECT_ALL = "SELECT * FROM BOOKMARK;";
        public static final String SELECT_WHERE_ID = "SELECT * FROM BOOKMARK WHERE id = ?;";
        public static final String DELETE_WHERE_ID = "DELETE FROM BOOKMARK WHERE id = ?;";
        public static final String UPDATE_NAME_ORDER_WHERE_ID = "UPDATE bookmark SET name = ?, order_num = ?, updated_at = ? WHERE id = ?;";
        public static final String EXISTS_WHERE_ID = "SELECT EXISTS(SELECT 1 FROM bookmark WHERE id = ?);";

    }

    public static class WIFI_BOOKMARK_TABLE {
        public static final String DDL =
                "CREATE TABLE IF NOT EXISTS wifi_bookmark (" +
                        "id integer PRIMARY KEY AUTOINCREMENT, " +
                        "wifi_id text NOT NULL, " +
                        "bookmark_id integer NOT NULL, " +
                        "created_at text, " +
                        "CONSTRAINT fk_wifi FOREIGN KEY (wifi_id) REFERENCES wifi(id) ON DELETE CASCADE, " +
                        "CONSTRAINT fk_bookmark FOREIGN KEY (bookmark_id) REFERENCES bookmark(id) ON DELETE CASCADE" +
                        ");";
        public static final String INSERT_BASIC_STATEMENT = "INSERT INTO WIFI_BOOKMARK (wifi_id, bookmark_id, created_at) VALUES (?, ?, ?);";
        public static final String SELECT_WHERE_ID_JOIN_WIFI_JOIN_BOOKMARK =
                "SELECT wb.id as id, wb.created_at, " +
                        "w.id as w_id, w.name as w_name, " +
                        "b.id as b_id, b.name as b_name, b.order_num as b_order_num " +
                        "FROM wifi_bookmark wb " +
                        "JOIN wifi w ON wb.wifi_id = w.id " +
                        "JOIN bookmark b ON wb.bookmark_id = b.id " +
                        "WHERE wb.id = ?;";
        public static final String SELECT_ALL_JOIN_WIFI_JOIN_BOOKMARK =
                "SELECT wb.id as id, wb.created_at, " +
                        "w.id as w_id, w.name as w_name, " +
                        "b.id as b_id, b.name as b_name, b.order_num as b_order_num " +
                        "FROM wifi_bookmark wb " +
                        "JOIN wifi w ON wb.wifi_id = w.id " +
                        "JOIN bookmark b ON wb.bookmark_id = b.id;";

        public static final String EXISTS_WHERE_ID = "SELECT EXISTS(SELECT 1 FROM wifi_bookmark WHERE id = ?);";

        public static final String DELETE_WHERE_ID = "DELETE FROM wifi_bookmark WHERE id = ?;";

    }

}
