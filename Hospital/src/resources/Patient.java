package resources;

import services.Service;

public class Patient {

	private Service service;
	private String action = "new";
	
	public Patient(Service service) {
		this.service = service;
	}
		
	public Service getService() {
		return service;
	}
	
	public String getAction() {
		return action;
	}
	
	public void setAction(String action) {
		this.action = action;
		System.out.println(action);
	}
}
