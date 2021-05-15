package com.example.todoapp;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class bottomflag extends BottomSheetDialogFragment {
        EditText enter;
        Button save;
        ArrayList<model> m1;
        adapter adapter1;
        sqlite s2;

    public bottomflag(  adapter adapter1,ArrayList<model> m1) {

        this.adapter1 = adapter1;
        this.m1=m1;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // return super.onCreateView(inflater, container, savedInstanceState);
        View view=inflater.inflate(R.layout.bottom,container,false);
            enter=view.findViewById(R.id.edittext);
            save=view.findViewById(R.id.save);
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String taskentered= enter.getText().toString();
                    m1.clear();
                    s2=new sqlite(getContext());
                    s2.insertdata(taskentered,0);
                    Cursor c1=s2.getalldata();
                    if (c1.getCount()==0){
                        Log.e(TAG, "onClick: "+"notask" );
                    }
                    else{
                        while (c1.moveToNext()){
                            model m=new model(c1.getString(1),c1.getInt(2),c1.getInt(0));
                            m1.add(m);

                        }
                       // Log.e(TAG, "onClick: "+m1.size() );

                    }
                    Log.e(TAG, "onClick: "+m1.size() );

                        adapter1.notifyDataSetChanged();
                        dismiss();

                }
            });



        return view;
    }
}
