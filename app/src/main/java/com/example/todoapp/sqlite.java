package com.example.todoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class sqlite extends SQLiteOpenHelper {
    public static final String dbname="database1";
    public static final String tablename="table1";
    private String TAG;


    public sqlite(@Nullable Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE "+tablename+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, TASK TEXT,  INTEGER STATUS)";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+tablename);
    }
    public void insertdata(String task, int status){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("TASK",task);
        cv.put("INTEGER",status);
        long result=db.insert(tablename,null,cv);
        if (result==-1){
            Log.e(TAG, "insertdata: "+"failed to insert" );
        }else {
            Log.e(TAG, "insertdata: "+"Successfully inserted" );

        }

    }
    public Cursor getalldata(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM  "+tablename,null,null);
        return c;
    }
    public void deleteddata (int id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(tablename,"ID =?",new String[]{String.valueOf(id)});
    }
    public void updatedata(int id,int status){
        Log.e(TAG, "updatedata: "+id+status );

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("INTEGER",status);
        db.update(tablename,cv,"id=?",new String[]{String.valueOf(id)});


        }



}
