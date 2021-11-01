package com.example.levi.DB;

import android.provider.BaseColumns;

/**
 * This class will store in constants variables the database properties
 */

public class TravelsContract {
    private TravelsContract() {}
    public static class TravelsEntry implements BaseColumns {
        public static final String TABLE_NAME ="travels";
        public static final String ID = "id";
        public static final String COLUMN_NAME_COUNTRY = "country";
        public static final String COLUMN_NAME_CITY = "city";
        public static final String COLUMN_NAME_AIRPORT = "airport";

    }
}
