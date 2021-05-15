package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements setdat {
    private static final String TAG ="Tag" ;
    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    ArrayList<model> datalist;
    adapter ad1;
    sqlite sq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        recyclerView=(RecyclerView)findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
      floatingActionButton=(FloatingActionButton)findViewById(R.id.floating);
      floatingActionButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
                bottomflag bottomlayout=new bottomflag(ad1,datalist);
                bottomlayout.show(getSupportFragmentManager(),"Tag");
          }
      });
      datalist=new ArrayList<>();
        ad1=new adapter(getApplicationContext(),datalist,this);

      recyclerView.setAdapter(ad1);
      sq =new sqlite(getApplicationContext());
        Cursor c1=sq.getalldata();
        if(c1.getCount()==0){
            Log.e(TAG, "onCreate: "+"nodata" );
            Toast.makeText(getApplicationContext(),"Woohoo! No Task Left",Toast.LENGTH_LONG).show();
        }else {
            while (c1.moveToNext()){
                model m=new model(c1.getString(1),c1.getInt(2),c1.getInt(0));
                datalist.add(m);
            }
            ad1.notifyDataSetChanged();

        }





    }
    public void delete(int id) {

        sq.deleteddata(id);
        datalist.clear();
        Log.e(TAG, "delete: "+datalist.size() );
        Cursor c1 = sq.getalldata();
        if (c1.getCount() == 0) {
            Log.e(TAG, "onCreate: " + "nodata");
            datalist.clear();
            Toast.makeText(getApplicationContext(),"Woohoo! No Task Left",Toast.LENGTH_LONG).show();
        } else {
            while (c1.moveToNext()) {
                model m = new model(c1.getString(1), c1.getInt(2),c1.getInt(0));
                datalist.add(m);
            }

        }
        ad1.notifyDataSetChanged();
    }
    public void update(int id,int status){
        Log.e(TAG, "update: "+id+ status );
        this.sq.updatedata(id,status);
        datalist.clear();
        Cursor c1 = sq.getalldata();
        if (c1.getCount() == 0) {
            Log.e(TAG, "onCreate: " + "nodata");
            datalist.clear();
            Toast.makeText(getApplicationContext(),"Woohoo! No Task Left",Toast.LENGTH_LONG).show();
        } else {
            while (c1.moveToNext()) {
                model m = new model(c1.getString(1), c1.getInt(2),c1.getInt(0));
                datalist.add(m);
            }

        }
        ad1.notifyDataSetChanged();


    }
}