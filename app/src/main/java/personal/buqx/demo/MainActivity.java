package personal.buqx.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import net.sqlcipher.database.SQLiteDatabase;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private SQLiteDatabase mDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void creatDatabase(String folderName, String fileName) {
        String dbPath = getApplication().getFilesDir().getAbsolutePath() + "/" + folderName;
        String dbName = dbPath + "/" + fileName + ".db";

        File dbDir = new File(dbPath);
        if (!dbDir.exists()) {
            dbDir.mkdirs();
        }

        File dbFile = new File(dbName);
        if (!dbFile.exists()) {
            try {
                dbFile.createNewFile();
            } catch (IOException e) {
                Log.e(TAG, "createDatabase: " + e.getMessage());
            }
        }

        // 指定数据库文件
        mDB = SQLiteDatabase.openOrCreateDatabase(dbFile, "123MiMa", null);

        // 默认数据库文件位置
        mDB = SQLiteDatabase.create(null, DBCipherHelper.DB_PWD);

    }


}
