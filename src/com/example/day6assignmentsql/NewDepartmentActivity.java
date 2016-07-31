package com.example.day6assignmentsql;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewDepartmentActivity extends Activity{
	
	EditText deptid,deptname,managername;
	Button buttonSubmit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.department);
		deptid=(EditText) findViewById(R.id.editText1);
		deptname =(EditText) findViewById(R.id.editText2);
		managername=(EditText) findViewById(R.id.editText3);
		buttonSubmit=(Button) findViewById(R.id.button1);
		
		
		buttonSubmit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int id = Integer.parseInt(deptid.getText().toString());
				String name = deptname.getText().toString();
				String mname = managername.getText().toString();
				
				
				Department d = new Department();
				d.setDept_id(id);
				d.setDept_name(name);
				d.setManager_name(mname);
				// save student info using DatabaseHelper
				DataBaseHelper helper = new DataBaseHelper(
						NewDepartmentActivity.this);
				try {
					helper.insertDepartment(d);
					Toast.makeText(NewDepartmentActivity.this, "sucess",
							Toast.LENGTH_LONG).show();
				} catch (Exception ex) {
					Log.e("error", ex.toString());
				}
			}
		});
	}
	

}
