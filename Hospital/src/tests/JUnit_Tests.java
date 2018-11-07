package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import resources.Patient;
import resources.Physician;
import services.Service;

public class JUnit_Tests {

	Service service;
	Physician p;
	Patient patient;
	
	@Before
	public void setUp() throws Exception {
		service = new Service();
		p = service.getPhysicians().get(0);
		patient = new Patient(service);
	}

	@Test
	public void testResource() throws Exception {
		assertTrue(p.isAvailable());
		service.TakeResource(p);
		assertFalse(p.isAvailable());
		service.FreeResource(p);
		assertTrue(p.isAvailable());
	}
	
	@Test
	public void testExamination() throws Exception {
		assertEquals(patient.getAction(), "new");
		service.treatment(patient);
		assertEquals(patient.getAction(), "filling paperwork");
		service.treatment(patient);
		assertEquals(patient.getAction(), "waiting nurse");
		service.treatment(patient);
		assertEquals(patient.getAction(), "nurse processing");
		service.treatment(patient);
		assertEquals(patient.getAction(), "waiting room");
		service.treatment(patient);
		assertEquals(patient.getAction(), "waiting physician");
		service.treatment(patient);
		assertEquals(patient.getAction(), "physician examining");
		service.treatment(patient);
		assertEquals(patient.getAction(), "finish");
	}
}
