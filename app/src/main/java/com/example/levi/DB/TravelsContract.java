package com.example.levi.DB;

import android.provider.BaseColumns;

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
