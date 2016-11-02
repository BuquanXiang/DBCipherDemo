package personal.buqx.demo;

import android.content.Context;
import android.util.Log;

import net.sqlcipher.Cursor;
import net.sqlcipher.SQLException;
import net.sqlcipher.database.SQLiteDatabase;

/**
 * Function:
 */

public class DBCipherManager {

    private static final String TAG = "DBCipherManager";
    public static DBCipherManager mInstance = null;

    private DBCipherHelper mDBCipherHelper;

    public DBCipherManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DBCipherManager(context);
        }
        return mInstance;
    }

    private DBCipherManager(Context context) {
        mDBCipherHelper = new DBCipherHelper(context);
    }

    public void execlSQL(SQLiteDatabase db, String sql) {
        if (!db.isOpen()) {
            return;
        }
        try {
            if (sql.contains("select")) {
                Log.i(TAG, "unable select in android");
            } else if (sql.contains("insert") || sql.contains("update")) {
                db.beginTransaction();
                db.execSQL(sql);
                db.setTransactionSuccessful();
            }
        } catch (SQLException e) {
            Log.e(TAG, "exculSQL" + e.getMessage());
        } finally {
            db.endTransaction();
            db.close();

        }
    }

    public int getDataNum(SQLiteDatabase db, String tabe, String[] colums) {
        Cursor cursor = null;
        try {
            cursor = db.query(tabe, colums, null, null, null, null, null);
            return cursor.getCount();
        } catch (SQLException e) {
            Log.e(TAG, "getDataNum:" + e.getMessage());
        }
        return 0;
    }
}
