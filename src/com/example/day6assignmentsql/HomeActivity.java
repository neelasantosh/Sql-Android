package com.example.day6assignmentsql;

import java.util.ArrayList;


import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class HomeActivity extends ListActivity
{

	ListView listViewEmployee;
	ArrayList<Employee> listEmployee = new ArrayList<Employee>();
	ArrayAdapter<Employee> adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		DataBaseHelper helper = new DataBaseHelper(HomeActivity.this);
		SQLiteDatabase db = helper.getWritableDatabase();
		listViewEmployee = getListView();
		
		listViewEmployee.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, final int arg2,
					long arg3) {
			
				
				AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
				final Employee s = listEmployee.get(arg2);
				builder.setTitle("Employee" + s.getName());
				builder.setMessage("What Action you want to perform");
				
				builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						DataBaseHelper helper = new DataBaseHelper(HomeActivity.this);
						SQLiteDatabase db= helper.getWritableDatabase();
						String query="delete from employee where eid="+s.getEmpid();
						db.execSQL(query);
						db.close();
						listEmployee = helper.getEmployee();
						adapter = new ArrayAdapter<Employee>(HomeActivity.this,
								android.R.layout.simple_list_item_1, listEmployee);
						listViewEmployee.setAdapter(adapter);
						
					}
				});
				builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Intent in= new Intent(HomeActivity.this, UpdateEmployee.class);
						in.putExtra("employee", listEmployee.get(arg2));
						startActivityForResult(in, 100);
					}
				}); 
					
					
				builder.setNeutralButton("Cancel", null);
				builder.setIcon(R.drawable.ic_launcher);
				
				AlertDialog dialog = builder.create();
				dialog.show();
				
			}
		});
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		DataBaseHelper helper = new DataBaseHelper(HomeActivity.this);

		listEmployee = helper.getEmployee();
		adapter = new ArrayAdapter<Employee>(HomeActivity.this,
				android.R.layout.simple_list_item_1, listEmployee);
		listViewEmployee.setAdapter(adapter);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.emp_view, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		SharedPreferences sp = getSharedPreferences("userdata", MODE_PRIVATE);
		switch (item.getItemId()) {
		case R.id.empNew: {
			Intent in = new Intent(HomeActivity.this, NewEmployeeActivity.class);
			startActivity(in);
		}
			break;
		case R.id.deptNew:
			Intent in = new Intent(HomeActivity.this, NewDepartmentActivity.class);
			startActivity(in);
			break;
		case R.id.totSalary:
			DataBaseHelper helper = new DataBaseHelper(HomeActivity.this);
			int sal= helper.getTotal();
			Toast.makeText(HomeActivity.this, sal+"", Toast.LENGTH_LONG).show();
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
