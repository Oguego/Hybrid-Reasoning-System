package edu.casetools.icase.mreasoner.core.elements.time.top;

import java.util.Calendar;
import java.util.GregorianCalendar;

import edu.casetools.icase.mreasoner.core.elements.time.Time;
import edu.casetools.icase.mreasoner.core.elements.time.absolute.Date;
import edu.casetools.icase.mreasoner.core.elements.time.absolute.TimeOfDay;

public class Bound {

	private TimeOfDay time_of_day;
	private Date      date;
	private long 	  simulation_value;
	private int 	  weekDay;
	private boolean   emptyWeekDay;

	public Bound(){
		time_of_day 		  = new TimeOfDay();
		date        		  = new Date();
		emptyWeekDay 		  = true;
		this.simulation_value = -1;
	}

	public TimeOfDay getTime_of_day() {
		return time_of_day;
	}

	public void setTime_of_day(TimeOfDay time_of_day) {
		this.time_of_day = time_of_day;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


	private Calendar getCalendar(Time time){
		Calendar c = new GregorianCalendar();
		c.setTimeInMillis(time.getSystemRealTime());
		c = this.date.setValuesIntoCalendar(c);
		c = this.time_of_day.setValuesIntoCalendar_LocalAM_PM(c);//.setValuesIntoCalendar(c);
		return c;
	}
	
	public long getTimeMillis(Time time){
		Calendar c = getCalendar(time);
	System.out.println("//////////////////////");
			System.out.print(""+c.get(Calendar.DAY_OF_MONTH)+"/");
		
			System.out.print(""+(c.get(Calendar.MONTH)+1)+"/");
		
			System.out.print(""+c.get(Calendar.YEAR)+".");
			

			if(c.get(Calendar.HOUR)<10)	System.out.print("0"+c.get(Calendar.HOUR)+":");
			else 	System.out.print(""+c.get(Calendar.HOUR)+":");
			
		
			if(c.get(Calendar.MINUTE)<10)	System.out.print("0"+c.get(Calendar.MINUTE)+":");
			else 	System.out.print(""+c.get(Calendar.MINUTE)+":");


			if(c.get(Calendar.SECOND)<10)	System.out.print("0"+c.get(Calendar.SECOND));
			else 	System.out.print(""+c.get(Calendar.SECOND));
			System.out.println("\n//////////////////////");				
			
		return c.getTimeInMillis();

	}
	
	public long getBoundInMillis(){
		Calendar c = new GregorianCalendar();
		if(!this.date.isEmpty()) {
			date.setValuesIntoCalendar(c);
			if(!this.time_of_day.isEmpty()){
				time_of_day.setValuesIntoCalendar(c);
			}
		}
		else {
			if(!this.time_of_day.isEmpty()){
				return (time_of_day.getTimeOfDayInSeconds()*1000);
			}
			else return 0;
		}
		return c.getTimeInMillis();
	}


	public void printBoundInTimes(Time time){
		System.out.println(this.getCalendar(time).getTime());
	}

	public boolean isNotFuture(Time presentTime){
		if(presentTime.timeIsGivenInIterations())
			return this.isNotFuture_Iteration(presentTime);
		else
			return this.isNotFuture_RealTime(presentTime);
		
		
	}
	
	private boolean isNotFuture_RealTime(Time presentTime){
		//dividido entre 1000 cada uno
		if((this.getTimeMillis(presentTime)) < (presentTime.getSystemRealTime())){
			System.out.print(" IS NOT FUTURE \n");
			return true;
		}else return false;
	}
	
	private boolean isNotFuture_Iteration(Time presentTime){
		long simulation = this.simulation_value;
		if((simulation) < (presentTime.getIteration())){
		//	System.out.print(" IS NOT FUTURE \n");
			return true;
		}else return false;
	}
	
	public long getSimulation_value() {
		return simulation_value;
	}

	public void setSimulation_value(long simulation_value) {
		this.simulation_value = simulation_value;
	}
	
	public void printBound(){
		this.time_of_day.print();
		this.date.print();
		if(!emptyWeekDay) System.out.print("WEEKDAY: "+weekDay);
	}

	public int getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(int weekDay) {
		this.weekDay = weekDay;
		this.emptyWeekDay = false;
	}
	
	public boolean isEmpty_Simulation(){
		if(this.simulation_value > 0) return false;
		else return true;
	}
	
	public boolean isEmpty_RealTime(){
		if ( !this.time_of_day.isEmpty() || !this.date.isEmpty() || !this.emptyWeekDay){
			return false;
		}else return true;
	}
	
	public boolean isEmptyWeekDay() {
		return emptyWeekDay;
	}

	public void setEmptyWeekDay(boolean emptyWeekDay) {
		this.emptyWeekDay = emptyWeekDay;
	}

	public void printRealTime(){
		this.time_of_day.print();
		System.out.print(".");
		this.date.print();
		System.out.println("");
	}

}
