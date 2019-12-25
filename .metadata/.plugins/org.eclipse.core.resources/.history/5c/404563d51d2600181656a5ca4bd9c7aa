package edu.casetools.icase.mreasoner.core.elements.states;

import edu.casetools.icase.mreasoner.core.elements.time.Time;

public abstract class DefaultState {

	protected String name;
	protected boolean status;
	
	public DefaultState(){
		name = "";
		status = false;
	}
	
	public DefaultState(String name, boolean status){
		this.name = name;
		this.status = status;
	}

	public DefaultState(String id){
		this.name = id;
		this.status = false;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String id) {
		this.name = id;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public abstract boolean assertState(Time actualTime);
	
	public abstract void print();
	
}
