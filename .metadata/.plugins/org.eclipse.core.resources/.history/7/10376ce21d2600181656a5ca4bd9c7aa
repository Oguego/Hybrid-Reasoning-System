package edu.casetools.icase.mreasoner.core.elements.time.top;

import java.util.Calendar;

import edu.casetools.icase.mreasoner.core.elements.states.State;

public class TemporalOperator extends State{

	public enum TOP_TYPE {STRONG_RELATIVE,WEAK_RELATIVE,STRONG_ABSOLUTE,WEAK_ABSOLUTE};
	
	private TOP_TYPE type;
	private Bound    since;
	private Bound    until;

	
	public Bound getUntilValue() {
		return until;
	}
	
	public void setUntilValue(Bound until) {
		this.until = until;
	}

	public TemporalOperator(){
		super();
	}
	public Bound getSinceValue() {
		return since;
	}
	public void setSinceValue(Bound start) {
		this.since = start;
	}
	

//	public void printTOp(Time time){
//		System.out.println("----------------");
//		this.printSymbol();
//		since.printBoundInTimes(time);
//		System.out.println(" - ");
//		if(this.type == TOP_TYPE.STRONG_RELATIVE || this.type == TOP_TYPE.WEAK_RELATIVE){
//			System.out.println("PRESENT");
//		}
//		else until.printBoundInTimes(time);
//		System.out.println("----------------");
//	}
	
//	private void printSymbol(){
//		switch(this.type){
//		case STRONG_ABSOLUTE:
//			System.out.println("[-]");
//			break;
//		case STRONG_RELATIVE:
//			System.out.println("[-]");
//			break;
//		case WEAK_ABSOLUTE:
//			System.out.println("<->");
//			break;
//		case WEAK_RELATIVE:
//			System.out.println("<->");
//			break;
//		default:
//			break;
//		
//		}
//	}
	
	public void setState( State s ){
		this.setName( s.getName()   );
		this.setStatus( s.getStatus() );
	}
	public TOP_TYPE getType() {
		return type;
	}
	public void setType(TOP_TYPE type) {
		this.type = type;
	}
	
	private void printAbsolute(){
		System.out.print("[ ");
		printBound(since);
		System.out.print(" - ");
		printBound(until);
		System.out.print(" ] ");
	}
	
	private void printBound(Bound bound){
		
		if(bound.getSimulation_value()!=-1)
			System.out.print(bound.getSimulation_value());
		else{
			if(!bound.getDate().isEmpty()){
				bound.getDate().print();
			}
			
			if(!bound.getTime_of_day().isEmpty()){
				if(!bound.getDate().isEmpty()){
					System.out.print(".");
				}
				bound.getTime_of_day().print();
			}
			
			if(!bound.isEmptyWeekDay()){
				if(bound.getWeekDay() == Calendar.MONDAY) System.out.print("monday");
				if(bound.getWeekDay() == Calendar.TUESDAY) System.out.print("tuesday");
				if(bound.getWeekDay() == Calendar.WEDNESDAY) System.out.print("wednesday");
				if(bound.getWeekDay() == Calendar.THURSDAY) System.out.print("thursday");
				if(bound.getWeekDay() == Calendar.FRIDAY) System.out.print("friday");
				if(bound.getWeekDay() == Calendar.SATURDAY) System.out.print("saturday");
				if(bound.getWeekDay() == Calendar.SUNDAY) System.out.print("sunday");
			}
			
		}
		
	}
	
	private void printInmediate(){
		System.out.print("[ ");
		printBound(since);
		System.out.print(" ] ");
	}

	public void print(){
		//System.out.println("TYPE: "+this.type.toString());
		switch(this.type){
		case STRONG_ABSOLUTE:
			System.out.print("[-]");
			printAbsolute();
			break;
		case STRONG_RELATIVE:
			System.out.print("[-]");
			printInmediate();
			break;
		case WEAK_ABSOLUTE:
			System.out.print("<->");
			printAbsolute();
			break;
		case WEAK_RELATIVE:
			System.out.print("<->");
			printInmediate();
			break;
		default:
			break;
		
		}
		super.print();
	}
	
}
