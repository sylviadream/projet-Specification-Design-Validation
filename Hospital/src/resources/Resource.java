package resources;

import services.Service;

public abstract class Resource {
	
	private boolean available = true;
	private Service service;
	
	public Resource(Service service) {
		this.service = service;
		service.addResource(this);
	}
	
	public boolean isAvailable() {
		return available;
	}
	
	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	public Service getService() {
		return service;
	}
}
