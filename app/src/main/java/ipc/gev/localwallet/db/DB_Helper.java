package ipc.gev.localwallet.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ipc.gev.localwallet.db.table.TradeTable;

/**
 * Created by Gevorg on 10.06.2017.
 */

public class DB_Helper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "program.db";
    private static final int DATABASE_VERSION = 1;

    public DB_Helper(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TradeTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TradeTable.TABLE_NAME);
        onCreate(db);
    }
}
