package services;

import resources.Patient;

public class Hospital {

	public static void main(String[] args) {
		Service service = new Service();
		for(int i = 0 ; i < 10 ; i++)
			service.treatment(new Patient(service));
		while(service.getPatients().size() > 0) {
			int j = service.getPatients().size();
			for(int i = 0 ; i < j ; i++) {
				service.treatment(service.getPatients().get(i));
				j = service.getPatients().size();
			}
		}
		System.out.println("it's ok");
	}
}
