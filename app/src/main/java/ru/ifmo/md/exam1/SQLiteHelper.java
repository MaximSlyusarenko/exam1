package ru.ifmo.md.exam1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME =  "dataBase";
    public static final String TASKS_TABLE = "tasks";
    public static final String COLUMN_ID = "_id";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String DATE = "date";
    public static final int DATABASE_VERSION = 1;

    public static final String CREATE_TASKS_BASE = "create table " + TASKS_TABLE + " ("
            + COLUMN_ID + " integer primary key autoincrement, "
            + TITLE + " TEXT, "
            + DESCRIPTION + " TEXT, "
            + DATE + " TEXT" + ");";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase dataBase) {
        dataBase.execSQL(CREATE_TASKS_BASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase dataBase, int oldVersion, int newVersion) {
        dataBase.execSQL("drop table if exists " + TASKS_TABLE);
        onCreate(dataBase);
    }
}
