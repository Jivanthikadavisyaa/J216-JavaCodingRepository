package com.hexaware.dao;

import java.util.List;

import com.hexaware.exception.PatientNumberNotFoundException;
import com.hexaware.model.Appointments;

public interface IHospitalService {
	
	public Appointments getAppointmentById();

	public List<Appointments> getAppointmentsForPatient() throws PatientNumberNotFoundException;

	public List<Appointments> getAppointmentsForDoctor();

	public boolean scheduleAppointment(Appointments app);

	public boolean updateAppointment(Appointments appointment);

	public boolean cancelAppointment(Appointments appointment);


}

