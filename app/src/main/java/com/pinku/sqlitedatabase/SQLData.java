package com.pinku.sqlitedatabase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by mine on 2/8/2016.
 */
public class SQLData extends Activity implements View.OnClickListener {
    EditText user,sal,phn;
    RadioGroup rg;
    RadioButton m,f;
    CheckBox e,h,t;
    Button b;
    DBHelper dbHelper;

protected void onCreate (Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.sql_data);

    user = (EditText) findViewById(R.id.editName);
    sal = (EditText) findViewById(R.id.editsalary);
    phn = (EditText)findViewById(R.id.phone_no);
    m = (RadioButton)findViewById(R.id.male);
    f = (RadioButton)findViewById(R.id.female);
    e = (CheckBox)findViewById(R.id.english);
    h = (CheckBox)findViewById(R.id.hindi);
    t = (CheckBox)findViewById(R.id.telegu);
    b = (Button)findViewById(R.id.submit_button);
    rg = (RadioGroup) findViewById(R.id.radio);

    b.setOnClickListener(this);
}

    @Override
    public void onClick(View v) {
        dbHelper = new DBHelper(this);
        dbHelper.open();


        String name=user.getText().toString();
        int selectedId=rg.getCheckedRadioButtonId();
        Integer phon= Integer.valueOf(phn.getText().toString());
        Double salr= Double.valueOf(sal.getText().toString());
        Integer selectcheckbox=t.getImeActionId();
        dbHelper.insertRow(name,selectedId,phon,salr,selectcheckbox);
        Toast.makeText(this, "data is successfully entered", Toast.LENGTH_LONG).show();
        dbHelper.close();
        Intent i=new Intent(this,SQLDataActivity.class);
        startActivity(i);

    }
}
