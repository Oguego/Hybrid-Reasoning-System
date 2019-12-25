package edu.casetools.icase.mreasoner;

import java.sql.Timestamp;
import java.util.Observable;

import edu.casetools.icase.mreasoner.core.MSpecification;
import edu.casetools.icase.mreasoner.core.elements.MRules;
import edu.casetools.icase.mreasoner.core.elements.MStatus;
import edu.casetools.icase.mreasoner.core.elements.time.Time;
import edu.casetools.icase.mreasoner.core.elements.time.conf.TimeConfigs;
import edu.casetools.icase.mreasoner.database.MDBInterface;
import edu.casetools.icase.mreasoner.utils.MSemaphore;
import edu.casetools.icase.mreasoner.utils.RuleStratificator;

public abstract class AbstractMReasoner extends Observable implements Runnable {

	public  static MStatus        	   systemStatus;
	public  static MSemaphore  		   semaphore;	
	private 	   MSpecification	   systemInput;
	private 	   MRules			   systemRules;		
	protected 	   MDBInterface		   database;
	
	private boolean running;
	private boolean hasMaxExecutionTime;
	private boolean stratify;
	
	public AbstractMReasoner( MSpecification systemInput, TimeConfigs timeConfigs, boolean stratify, boolean hasMaxExecTime ){
		running 	   		= true;
		this.systemInput    = systemInput;		
		this.systemRules    = systemInput.getSystemRules();
		this.stratify       = stratify;
		hasMaxExecutionTime = hasMaxExecTime;		
		semaphore   		= new MSemaphore( timeConfigs.isSimulation() );	
		initTime( timeConfigs );
	}

	private void initTime(TimeConfigs timeConfigs){
		Time time              = new Time  ( timeConfigs );
		systemStatus      	   = systemInput.getSystemStatus();
		systemStatus.setTime(time);
	}
	
	private void printHeader(){
		System.out.println("**********************************************************");
		System.out.println("*************FORWARD REASONING ALGORITHM******************");
		System.out.println("**********************************************************");
		systemRules.showSystemRules();
		systemStatus.showStates();
	}
	
	public void initReasoner(){
		if(this.stratify) stratify();
		printHeader();
		System.out.println("INITIALIZATION AT t = 0");
		systemStatus.getTime().start();
		System.out.println(systemStatus.getTime().getSystemRealTime());
		assertSameTimeRules();
		assertNextTimeRules();
		nextIteration();
		semaphore.inputPut();
		System.out.println("END OF INITIALIZATION AT t = 0\n");

	}
	

	
	public void run(){
		
		this.initReasoner();
		if(hasMaxExecutionTime){
			while(systemStatus.getTime().simulationTime() && running) 
				iteration();
			terminate();
		}
		else while(running) iteration();

	}
	
	private void iteration(){
	    System.out.println("\nITERATION: "+systemStatus.getTime().getIteration() +" - "+new Timestamp(systemStatus.getTime().getSystemRealTime())+"\n");

	   semaphore.reasonerTake();
			readEvents();
			assertSameTimeRules();	
			assertNextTimeRules();	
			nextIteration();
	   semaphore.inputPut();
	}
	

	private void readEvents(){
		systemStatus = database.findLatestEvents(systemStatus);
	}
	
	public void assertSameTimeRules(){	
		for( int i=0; i < systemRules.getSameTimeRules().size(); i++ ){
				systemStatus = systemRules.getSameTimeRules().get(i).assertRule(systemStatus,database);
		}
	}
	
	public void assertNextTimeRules(){	
		for( int i=0; i < systemRules.getNextTimeRules().size(); i++ ){
				systemStatus = systemRules.getNextTimeRules().get(i).assertRule(systemStatus,database);
		}
	}
	
	private void nextIteration(){
		database.writeLog(systemStatus);
		while(!systemStatus.getTime().endOfTimeUnit());
		systemStatus.getTime().nextTime();	
		System.out.println("");
		
	}
	
	public void terminate(){
		running = false;
		semaphore.reasonerTake();
		database.disconnect();
		semaphore.inputPut();
	}
	
	private void stratify(){
		RuleStratificator ruleStratificator = new RuleStratificator(systemRules);
		System.out.println("______________________________________");
		System.out.println("Statifying..");
		systemRules.setSameTimeRules( ruleStratificator.stratify( systemInput.getIndependentStates()) );
		System.out.println("______________________________________");
		System.out.println("Same Time rules succesfully stratified\n");
	}


}
