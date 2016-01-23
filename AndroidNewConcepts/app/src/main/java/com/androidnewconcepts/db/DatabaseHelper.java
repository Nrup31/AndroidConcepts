package com.androidnewconcepts.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.androidnewconcepts.model.MCQModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    // All Static variables
// Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "mcq_database.sqlite";
    private static final String DB_PATH_SUFFIX = "/databases/";
    static Context ctx;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        ctx = context;
    }

    public ArrayList<MCQModel> getAllMCQ() {
        SQLiteDatabase db = this.getReadableDatabase();
        final String selectQuery = "SELECT * FROM tbl_mcq" ;
        final Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<MCQModel> mcqModelsList = new ArrayList<>();


        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                MCQModel mcqModel = new MCQModel();

                mcqModel.setqNo(cursor.getInt(cursor.getColumnIndex("qno")));
                mcqModel.setQuestion(cursor.getString(cursor.getColumnIndex("question")));
                mcqModel.setOptionA(cursor.getString(cursor.getColumnIndex("option_a")));
                mcqModel.setOptionB(cursor.getString(cursor.getColumnIndex("option_b")));
                mcqModel.setOptionC(cursor.getString(cursor.getColumnIndex("option_c")));
                mcqModel.setOptionD(cursor.getString(cursor.getColumnIndex("option_d")));
                mcqModel.setAnswer(cursor.getString(cursor.getColumnIndex("answer")));
                mcqModelsList.add(mcqModel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        mcqModelsList.trimToSize();
        return mcqModelsList;

    }

    public void CopyDataBaseFromAsset() throws IOException {

        InputStream myInput = ctx.getAssets().open(DATABASE_NAME);

// Path to the just created empty db
        String outFileName = getDatabasePath();

// if the path doesn't exist first, create it
        File f = new File(ctx.getApplicationInfo().dataDir + DB_PATH_SUFFIX);
        if (!f.exists())
            f.mkdir();

// Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

// transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

// Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    private static String getDatabasePath() {
        return ctx.getApplicationInfo().dataDir + DB_PATH_SUFFIX
                + DATABASE_NAME;
    }

    public SQLiteDatabase openDataBase() throws SQLException {
        File dbFile = ctx.getDatabasePath(DATABASE_NAME);

        if (!dbFile.exists()) {
            try {
                CopyDataBaseFromAsset();
                System.out.println("Copying sucess from Assets folder");
            } catch (IOException e) {
                throw new RuntimeException("Error creating source database", e);
            }
        }

        return SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.CREATE_IF_NECESSARY);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// TODO Auto-generated method stub

    }
}
