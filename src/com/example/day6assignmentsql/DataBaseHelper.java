package com.example.day6assignmentsql;

import java.util.ArrayList;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.widget.Toast;

public class DataBaseHelper extends SQLiteOpenHelper{

	public static final String DBNAME = "cdacempl.sqlite";
	public static final int VERSION = 1;
	
	public DataBaseHelper(Context context) {
		super(context, DBNAME, null, VERSION);
	}
	public DataBaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String table1Query = "create table employee "
				+ " (eid integer primary key " 
				+ "autoincrement,ename text, "
				+ "salary real, " 
				+ "date text," 
				+ "depid integer" +
				")";
		db.execSQL(table1Query);
		// insert queries
		db.execSQL("insert into employee(ename,salary,date,depid) values('abc',655,'2015-5-10',101)");
		db.execSQL("insert into employee(ename,salary,date,depid) values('def',850,'2010-6-12',102)");
		
		String table2Query = "create table department"
					+ "(dept_id integer,dept_name text,manager_name text)";
		db.execSQL(table2Query);
		db.execSQL("insert into department(dept_id,dept_name,manager_name) values(101,'HR','ABC')");
		db.execSQL("insert into department(dept_id,dept_name,manager_name) values(102,'CS','DEF')");
		
		
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	public ArrayList<Employee> getEmployee(){
		ArrayList<Employee> listEmployee =	new ArrayList<Employee>();
		
		SQLiteDatabase db = getWritableDatabase();
		String query = "select * from employee";
		Cursor cur = db.rawQuery(query, null);

		// get one row at a time from cursor
		while (cur.moveToNext() == true) {
			int empid = cur.getInt(0);
			String name = cur.getString(1);
			int salary = cur.getInt(2);
			String joiningdate = cur.getString(3);
			int dept_id = cur.getInt(4);
			

			// String data = id + "," +name + "," + courseID + "," + email;
			Employee s =new Employee(empid, name, salary, joiningdate, dept_id);

			listEmployee.add(s);
		}
		db.close();
		return listEmployee;
		
		
		
	}
 	 public ArrayList<Department> getDepartment(){
 		 ArrayList<Department> listDepartment = new ArrayList<Department>();
 		
		SQLiteDatabase db = getWritableDatabase();
		String query = "select * from Department";
		
		Cursor cur = db.rawQuery(query, null);
		while(cur.moveToNext() == true){
			int dept_id = cur.getInt(0);
			String dept_name = cur.getString(1);
			String manager_name=cur.getString(2);
			Department d = new Department(dept_id, dept_name, manager_name);
			listDepartment.add(d);
		}
		db.close();
		return listDepartment;
 		 
 	 }
 	 
 	 
	public void insertEmployee(Employee s) {
		String query = "insert into employee(ename,salary,date,depid) values('"+s.getName()+"','"+s.getSalary()+"','"+s.getJoiningdate()+"','"+s.getDept_id()+"')";
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL(query);
		db.close();
		
		
	}
	public void insertDepartment(Department d) {
		// TODO Auto-generated method stub
		String query = "insert into department(dept_id,dept_name,manager_name) values('"+d.getDept_id()+"','"+d.getDept_name()+"','"+d.getManager_name()+"')";
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL(query);
		db.close();
	}
	public int getTotal() {
		// TODO Auto-generated method stub
		int sal=0;
		SQLiteDatabase db= getWritableDatabase();
		Cursor x = db.rawQuery("select sum(salary) from employee", null);
		if(x.moveToNext())
		{
			 sal = x.getInt(0);
			
		}
		
		return sal;
	}
	public void updateEmployee(Employee s) {
		// TODO Auto-generated method stub
		String query = "update employee set  ename='"+s.getName()+"' WHERE eid='"+s.getEmpid()+"'";
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL(query);
		db.close();
	}
	
}
