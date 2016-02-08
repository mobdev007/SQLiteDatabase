package com.pinku.sqlitedatabase;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by mine on 2/8/2016.
 */
public class SQLDataActivity extends Activity implements View.OnClickListener {
    TextView name,gen,salary,phone_no,language;
    Button exit;
    DBHelper dbHelper;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqldata_activity);

        name = (TextView)findViewById(R.id.name);
        gen = (TextView)findViewById(R.id.gender);
        salary = (TextView)findViewById(R.id.sal);
        phone_no = (TextView)findViewById(R.id.phone);
        language = (TextView)findViewById(R.id.lang);

        exit = (Button)findViewById(R.id.exit_button);
        exit.setOnClickListener(this);

        dbHelper = new DBHelper(this);
        dbHelper.open();

        Cursor cursor = dbHelper.getAllRows();

        cursor.moveToFirst();
        String sname = cursor.getString(1);
        String ssal = cursor.getString(3);
        String sphn = cursor.getString(4);
        name.setText(sname);
        gen.setText(cursor.getString(2));
        salary.setText(ssal);
        phone_no.setText(sphn);
        language.setText(cursor.getString(5));

        dbHelper.close();

    }

    @Override
    public void onClick(View v) {
        Intent in = new Intent(this,SQLData.class);
        startActivity(in);
    }
}
