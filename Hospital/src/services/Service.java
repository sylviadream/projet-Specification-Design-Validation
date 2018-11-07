package services;

import java.util.ArrayList;

import resources.*;

public class Service {

	private ArrayList<Nurse> nurses = new ArrayList<Nurse>();
	private ArrayList<Room> rooms = new ArrayList<Room>();
	private ArrayList<Physician> physicians = new ArrayList<Physician>();
	private ArrayList<Patient> patients = new ArrayList<Patient>();
	
	private int nb_nurses_available = 10;
	private int nb_rooms_available = 8;
	private int nb_physicians_available = 5;
	
	public Service() {
		for(int i = 0 ; i < 10 ; i++)
			new Nurse(this);
		for(int i = 0 ; i < 8 ; i++)
			new Room(this);
		for(int i = 0 ; i < 5 ; i++)
			new Physician(this);
	}
	
	public ArrayList<Nurse> getNurses() {
		return nurses;
	}
	
	public ArrayList<Room> getRooms() {
		return rooms;
	}
	
	public ArrayList<Physician> getPhysicians() {
		return physicians;
	}
	
	public ArrayList<Patient> getPatients() {
		return patients;
	}
	
	public void addResource(Resource resource) {
		if(resource instanceof Nurse)
			nurses.add((Nurse) resource);
		if(resource instanceof Room)
			rooms.add((Room) resource);
		if(resource instanceof Physician)
			physicians.add((Physician) resource);
	}
	
	public void removeResource(Resource resource) {
		if(resource instanceof Nurse)
			nurses.remove((Nurse) resource);
		if(resource instanceof Room)
			rooms.remove((Room) resource);
		if(resource instanceof Physician)
			physicians.remove((Physician) resource);
	}
	
	public int getNb_nurses_available() {
		return nb_nurses_available;
	}
	
	public void setNb_nurses_available(int nb) {
		this.nb_nurses_available = nb;
	}
	
	public int getNb_room_available() {
		return nb_rooms_available;
	}
	
	public void setNb_rooms_available(int nb) {
		this.nb_rooms_available = nb;
	}
	
	public int getNb_physicians_available() {
		return nb_physicians_available;
	}
	
	public void setNb_physicians_available(int nb) {
		this.nb_physicians_available = nb;
	}
	
	public void checkIn(Patient patient) {
		float waiting_time = 0;
		
		for(Patient p : patients) {
			if(p.getAction() == "filling paperwork" || p.getAction() == "waiting nurse" || p.getAction() == "nurse processing")
				waiting_time += 10/nurses.size();
			waiting_time += 50/physicians.size();
		}
		
		if(waiting_time >= 600)
			patient.setAction("finish");
		else {
			patients.add(patient);
			patient.setAction("filling paperwork");
		}
	}
	
	public void checkOut(Patient patient) {
		patients.remove(patient);
	}
	
	public void TakeResource(Resource resource) {
		resource.setAvailable(false);
		
		if(resource instanceof Nurse)
			nb_nurses_available--;
		if(resource instanceof Room)
			nb_rooms_available--;
		if(resource instanceof Physician)
			nb_physicians_available--;
	}
	
	public void FreeResource(Resource resource) {
		resource.setAvailable(true);
		
		if(resource instanceof Nurse)
			nb_nurses_available++;
		if(resource instanceof Room)
			nb_rooms_available++;
		if(resource instanceof Physician)
			nb_physicians_available++;
	}
	
	public void treatment(Patient patient) {
		if(patient.getAction() == "new") {
			checkIn(patient);
			return;
		}
		
		if(patient.getAction() == "filling paperwork") {
			patient.setAction("waiting nurse");
			return;
		}
		
		if(patient.getAction() == "waiting nurse") {
			for(Nurse n : nurses) {
				if(n.isAvailable()) {
					TakeResource(n);
					patient.setAction("nurse processing");
					return;
				}
			}
		}
		
		if(patient.getAction() == "nurse processing") {
			for(Nurse n : nurses) {
				if(!n.isAvailable()) {
					FreeResource(n);
					patient.setAction("waiting room");
					return;
				}
			}
		}
		
		if(patient.getAction() == "waiting room") {
			for(Room r : rooms) {
				if(r.isAvailable()) {
					TakeResource(r);
					patient.setAction("waiting physician");
					return;
				}
			}
		}
		
		if(patient.getAction() == "waiting physician") {
			for(Physician p : physicians) {
				if(p.isAvailable()) {
					TakeResource(p);
					patient.setAction("physician examining");
					return;
				}
			}
		}
		
		if(patient.getAction() == "physician examining") {
			for(Room r : rooms) {
				if(!r.isAvailable()) {
					for(Physician p : physicians) {
						if(!p.isAvailable()) {
							FreeResource(r);
							FreeResource(p);
							patient.setAction("finish");
							return;
						}
					}
				}
			}
		}
		
		if(patient.getAction() == "finish") {
			checkOut(patient);
			return;
		}
	}
}
