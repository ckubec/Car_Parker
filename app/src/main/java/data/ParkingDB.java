package data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import ckubec.tacoma.uw.edu.carparker.R;
import ckubec.tacoma.uw.edu.carparker.model.Parking;

/**
 * Created by philm on 6/1/2016.
 */
public class ParkingDB {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "Parking.db";
    private ParkingDB mParkingDB;
    private List<Parking> mParkingList;

    private ParkingDBHelper mParkingDBHelper;
    private SQLiteDatabase mSQLiteDatabase;

    public ParkingDB(Context context)
    {
        mParkingDBHelper = new ParkingDBHelper(
                context, DB_NAME, null, DB_VERSION);
        mSQLiteDatabase = mParkingDBHelper.getWritableDatabase();

    }

    public boolean insertParking(String spots, String spotsTaken, String address) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("spots", spots);
        contentValues.put("spotsTaken", spotsTaken);
        contentValues.put("address", address);

        long rowId = mSQLiteDatabase.insert("Parking", null, contentValues);
        return rowId != -1;
    }

    public void deleteParking() { mSQLiteDatabase.delete(PARKING_TABLE, null, null); }

    public void closeDB() { mSQLiteDatabase.close(); }

    private static final String PARKING_TABLE = "Parking";

    public List<Parking> getParking() {

        String[] columns = {
                "spots", "spotsTaken", "address"
        };

        Cursor c = mSQLiteDatabase.query(
                PARKING_TABLE,                      //The table to query
                columns,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order                          // The columns for the WHERE clause
        );
        c.moveToFirst();
        List<Parking> list = new ArrayList<Parking>();
        for (int i=0; i<c.getCount(); i++) {
            String spots = c.getString(0);
            String address = c.getString(1);
            String spotsTaken = c.getString(2);
            Parking parking = new Parking(null, spots, address, spotsTaken);
            list.add(parking);
            c.moveToNext();
        }

        return list;
    }

    private class ParkingDBHelper extends SQLiteOpenHelper {

        private final String CREATE_PARKING_SQL;
        private final String DROP_PARKING_SQL;

        public ParkingDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
            CREATE_PARKING_SQL = context.getString(R.string.CREATE_PARKING_SQL);
            DROP_PARKING_SQL = context.getString(R.string.DROP_PARKING_SQL);

        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_PARKING_SQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL(DROP_PARKING_SQL);
            onCreate(sqLiteDatabase);
        }
    }
}
