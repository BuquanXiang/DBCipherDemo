package personal.buqx.demo;

import android.content.Context;

import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;

import java.util.List;

/**
 * Function:
 */

public class DBCipherHelper extends SQLiteOpenHelper {

    private static final String TAG = "DBCipherHelper";

    private static final String DB_NAME = "test";
    private static final int DB_VERSION = 1;
    public static final String DB_PWD = "123test";


    public DBCipherHelper(Context context) {
        this(context, DB_NAME, null, DB_VERSION);
        SQLiteDatabase.loadLibs(context);
    }

    public DBCipherHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        SQLiteDatabase.loadLibs(context);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void createTable(SQLiteDatabase db, String tableName, DBField[] fields) {
        String sql = "CREATE TABLE" + tableName + '(';
        for (DBField field : fields) {
            sql += tableName + " " + field.fieldName + " " + field.primaryKey + " "
                    + field.fieldValue + ',';
        }
        sql += ')';
        db.execSQL(sql);
    }

    class DBField {
        String fieldName;
        String primaryKey;
        String fieldValue;

        public DBField(String fieldName, String fieldValue, boolean isKey) {
            this.fieldName = fieldName;
            this.fieldValue = fieldValue;
            if (isKey) {
                primaryKey = "primary key";
            }
        }
    }

}
