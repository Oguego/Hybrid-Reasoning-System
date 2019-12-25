package edu.casetools.icase.mreasoner.core.elements.time.absolute;

import java.util.Calendar;

public class Date {

	 public final int EMPTY_YEAR		  = 1970;
	 public final int EMPTY_DAY_OF_MONTH  = 1;
	 public final int EMPTY_DEFAULT       = 0;
	
	 private int  day_of_month;
	 private int  month;
	 private int  year;
	 
	 private boolean emptyDay_of_month;
	 private boolean emptyMonth;
	 private boolean emptyYear;
	 
	 
	 private int  limit;

	public Date(){
			this.day_of_month	   = EMPTY_DAY_OF_MONTH;
			this.month       	   = EMPTY_DEFAULT;
			this.year         	   = EMPTY_YEAR;
			
			this.emptyDay_of_month = true;
			this.emptyMonth        = true;
			this.emptyYear         = true;
	}
	
	
	public int getLimit() {
		return limit;
	}


	public void setLimit(int limit) {
		this.limit = limit;
	}
	 
	public int getDay_of_month() {
			return day_of_month;
	}
	
	public void setDay_of_month(int day_of_month) {
		this.day_of_month = day_of_month;
		this.emptyDay_of_month = false;
	}
	
	public void setEmptyDay_of_month() {
		this.day_of_month = EMPTY_DAY_OF_MONTH;
		this.emptyDay_of_month = true;
	}
	
	public int getMonth() {
		return month;
	}
	
	public void setMonth(int month) {
		this.month = month;
		this.emptyMonth = false;
	}
	
	public void setEmptyMonth() {
		this.month = EMPTY_DEFAULT;
		this.emptyMonth = true;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
		this.emptyYear = false;
	}
	
	public void setEmptyYear() {
		this.year = EMPTY_YEAR;
		this.emptyYear = true;
	}

	public boolean isEmptyDay_of_month() {
			return emptyDay_of_month;
	}

	public boolean isEmptyMonth() {
		return emptyMonth;
	}

	public boolean isEmptyYear() {
		return emptyYear;
	}
	
	public Calendar setValuesIntoCalendar(Calendar calendar){
		
		if(!this.emptyYear) 		calendar.set(Calendar.YEAR, year);
		if(!this.emptyMonth) 		calendar.set(Calendar.MONTH, month);
		if(!this.emptyDay_of_month) calendar.set(Calendar.DAY_OF_MONTH, day_of_month);
		
		return calendar;
	}
	
	
	
	public boolean isEmpty(){
		return (emptyYear && emptyMonth && emptyDay_of_month);
	}


	public void print() {
		if(!isEmptyDay_of_month()){
			System.out.print(""+getDay_of_month()+"/");
		}else{
			System.out.print("_");
		}
		
		if(!isEmptyMonth()){
			System.out.print(""+getMonth()+"/");
		}else{
			System.out.print("_");
		}
		
		if(!isEmptyYear()){
			System.out.print(""+getYear());
		}else{
			System.out.print("_");
		}			
		
	}
}
