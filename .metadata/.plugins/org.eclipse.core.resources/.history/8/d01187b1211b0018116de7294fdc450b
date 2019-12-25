package edu.casetools.icase.mreasoner.core.elements.states;

import edu.casetools.icase.mreasoner.core.elements.time.Time;

public  class State extends DefaultState{

	public State(){
		
	}
	
	public State(String name){
		super(name);
	}
	@Override
	public boolean assertState(Time actualTime) {
		return this.status;
	}

	@Override
	public void print() {
		String sign = "";
		if(!status) sign = "!";
		System.out.print(sign+name);
		
	}
	
	

}
