package com.example.day6assignmentsql;



import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class UpdateEmployee extends Activity{
	
	EditText empname,empsalary,joindate,deptid;
	Button submitButton,datePicker;
	Spinner spinnerDepartment;
	ArrayList<Department> listDepartments = new ArrayList<Department>(); 
	ArrayAdapter<Department> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.updateemployee);
		empname = (EditText) findViewById(R.id.editText1);
		empsalary = (EditText) findViewById(R.id.editText2);
		joindate = (EditText) findViewById(R.id.editText3);
		deptid = (EditText) findViewById(R.id.editText4);
		spinnerDepartment = (Spinner) findViewById(R.id.spinner1);
		submitButton=(Button) findViewById(R.id.button1);
		datePicker=(Button) findViewById(R.id.button2);
		DataBaseHelper helper = new DataBaseHelper(UpdateEmployee.this);
		listDepartments=helper.getDepartment();
		
		adapter = new ArrayAdapter<Department>(UpdateEmployee.this, android.R.layout.simple_spinner_item,listDepartments);
		spinnerDepartment.setAdapter(adapter);
		Intent in = getIntent();
		final Employee emp = (Employee)in.getSerializableExtra("employee");
		
		empname.setText(emp.getName());
		empsalary.setText(""+emp.getSalary());
		joindate.setText(emp.getJoiningdate());
		int pos=0;
		for(Department d  : listDepartments)
		{
			
			if(d.getDept_id() == emp.getDept_id())
			{
				spinnerDepartment.setSelection(pos);
			}
			else
				pos++;
		}
		
		submitButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String name = empname.getText().toString();
				/*int salary = Integer.parseInt(empsalary.getText().toString());
				String date= joindate.getText().toString();
				int depid=Integer.parseInt(deptid.getText().toString());*/
				
				Employee s = new Employee();
				s.setEmpid(emp.getEmpid());
				s.setName(name);
				
				
				DataBaseHelper helper = new DataBaseHelper(
						UpdateEmployee.this);
				try {
					helper.updateEmployee(s);
					Toast.makeText(UpdateEmployee.this, "sucess",
							Toast.LENGTH_LONG).show();
				} catch (Exception ex) {
					Log.e("error", ex.toString());
				}
			}
		});
		
		datePicker.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			DatePicker date = new DatePicker(UpdateEmployee.this);
			Calendar mcurrentDate = Calendar.getInstance();
            int mYear = mcurrentDate.get(Calendar.YEAR);
            int mMonth = mcurrentDate.get(Calendar.MONTH);
            int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
            
			DatePickerDialog date1 = new DatePickerDialog(UpdateEmployee.this,new OnDateSetListener() {
				
				public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
					String jdate=arg3+"-"+arg2+"-"+arg1;
					joindate.setText(jdate);
					
				}
			},mYear,mMonth,mDay);
		
			date1.setTitle("Select Date");
			date1.show();
		}
				
			
		});
		
		
		
	}

}
