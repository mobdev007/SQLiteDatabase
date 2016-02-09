package com.pinku.sqlitedatabase;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by mine on 2/7/2016.
 */
public class DBHelper {
    public static String TAG = "DBHelper";
    public static String Key_RowId = "_Id";
    public static int Column_Id = 0;

    public static final String KEY_NAME = "Username";
    public static final String KEY_SALARY = "Salary";
    public static final String KEY_PHONE = "Phone_no";
    public static final String KEY_GENDER = "Gender";
    public static final String KEY_LANG = "Language";

    public static int COLUMN_NAME = 1;
    public static int COLUMN_SALARY = 2;
    public static int COLUMN_PHONE = 3;
    public static int CLOUMN_GENDER = 4;
    public static int COLUMN_LANG = 5;

    public static final String[] ALL_KEYS = new String[] {Key_RowId,KEY_NAME,KEY_GENDER,KEY_PHONE,KEY_SALARY,KEY_LANG};

    public static final String DatabaseName = "db_value";
    public static final String DatabaseTable = "table_value";

    public static final int Version = 7;

    public static final String Database_Create_Sql = "create table " + DatabaseTable
            + "(" + Key_RowId + " integer primary key autoincrement," + KEY_NAME
            + " text unique not null," + KEY_GENDER + " integer not null," + KEY_PHONE
            + " integer not null," + KEY_SALARY + " integer not null," + KEY_LANG
            + " string not null" + ");";

    private final Context context;
    private DatabaseHelper myDbHelper;
    private SQLiteDatabase db;

    public DBHelper (Context context){
        this.context= context;
        myDbHelper = new DatabaseHelper(context);
    }

    public DBHelper open(){
        db = myDbHelper.getWritableDatabase();
        return this;
    }

    public long insertRow(String name,int gender,int phone,Double salary,boolean language){
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME,name);
        contentValues.put(KEY_GENDER,gender);
        contentValues.put(KEY_PHONE,phone);
        contentValues.put(KEY_SALARY,salary);
        contentValues.put(KEY_LANG,language);
        return db.insert(DatabaseTable, null, contentValues);
    }

    public boolean deleteRow(long rowId){
        String where= Key_RowId + "=" + rowId;
        return db.delete(where,DatabaseTable,null)!=0;
    }

    public void deleteAll(){
        Cursor c = getAllRows();
        long rowId = c.getColumnIndexOrThrow(Key_RowId);
        if (c.moveToFirst()){
            do {
                    deleteRow(c.getLong((int) rowId));
            }
            while (c.moveToNext());
        }
        c.close();
    }

    public Cursor getAllRows(){
        Cursor c = db.query(true, DatabaseTable, ALL_KEYS, null, null, null, null, null, null);
        if (c != null){
            c.moveToFirst();
        }
        return c;
    }

    public boolean updateRow (String name,String salary,String phone ){
        String where = KEY_NAME + "=" + name + " ";
        ContentValues newValues = new ContentValues();
        newValues.put(KEY_SALARY,salary);
        newValues.put(KEY_PHONE,phone);
        return db.update(DatabaseTable,newValues,where,null)!=0;
    }
    public void close(){
        myDbHelper.close();
    }

private class DatabaseHelper extends SQLiteOpenHelper{
    DatabaseHelper(Context context){
        super(context,DatabaseName,null,Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Database_Create_Sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v(TAG, "Upgrading application's database from version " + oldVersion
        + "to" + newVersion + "which will destroy all old data ! ");

        //destroy old table
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseTable);

        //Recreate new database
        onCreate(db);
    }
}
}
