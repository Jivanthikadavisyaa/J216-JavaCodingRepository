package com.hexaware.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.hexaware.exception.InvalidDataException;
import com.hexaware.exception.PatientNumberNotFoundException;
import com.hexaware.model.Appointments;
import com.hexaware.model.Customers;
import com.hexaware.model.Patient;
import com.hexaware.model.Products;
import com.hexaware.util.DbConnUtil;
import com.hexaware.util.DbConnection;

import com.hexaware.dao.newdao;
public class HospitalServiceImpl implements  IHospitalService {

	Scanner sc = new Scanner(System.in);
	Connection con;
	Statement stmt;
	PreparedStatement ps;
	ResultSet rs;
	Patient pt = new Patient();
	private List<Appointments> appointmentsList;
	

	public Appointments getAppointmentById() {
        System.out.println("Enter Appointment ID: ");
        int id = sc.nextInt();
        return getAppointmentByIdFromDatabase(id);
    }
	
	

    public Appointments getAppointmentByIdFromDatabase(int Appointmentid) {
        try {
            con = DbConnUtil.getDBConn();
            ps = con.prepareStatement("select * from appointments where appointmentid = ?");
            ps.setInt(1, Appointmentid);
            rs = ps.executeQuery();
            if (rs.next()) {
                Appointments appointment = new Appointments();
                appointment.setAppointmentId(rs.getInt("appointmentid"));
                appointment.setDoctorId(rs.getInt("doctorid"));
                appointment.setPatientId(rs.getInt("patientid"));
                appointment.setAppointmentDate(rs.getString("appdate"));
                appointment.setDescription(rs.getString("patdes"));
                  
              
                System.out.println("Appointment ID: " + appointment.getAppointmentId());
				System.out.println("Doctor ID: " + appointment.getDoctorId());
				System.out.println("Patient ID: " + appointment.getPatientId());
				System.out.println("Appointment Date: " + appointment.getAppointmentDate());
				System.out.println("Patient Description: " + appointment.getDescription());
                return appointment;
            } else {
                System.out.println("Detail Not Found");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    

	@Override
	public List<Appointments> getAppointmentsForPatient() throws PatientNumberNotFoundException{
	System.out.println("Enter the patient ID:");
	int pid=sc.nextInt();
	return getAppointmentsForPatientFromData(pid);
	
	}

	public List<Appointments> getAppointmentsForPatientFromData(int pid)throws PatientNumberNotFoundException {
		
		 List<Appointments> appointmentsList = new ArrayList<>();
		 try {
	            con = DbConnUtil.getDBConn();
	            ps = con.prepareStatement("select * from appointments where patientid = ?");
	            ps.setInt(1, pid);
	            rs = ps.executeQuery();
	         
	           
	            while (rs.next()) {
	                Appointments appointment = new Appointments();
	                appointment.setAppointmentId(rs.getInt("appointmentid"));
	                appointment.setDoctorId(rs.getInt("doctorid"));
	                appointment.setPatientId(rs.getInt("patientid"));
	                appointment.setAppointmentDate(rs.getString("appdate"));
	                appointment.setDescription(rs.getString("patdes"));
	                appointmentsList.add(appointment);
	                
	                System.out.println("Appointment ID: " + appointment.getAppointmentId());
					System.out.println("Doctor ID: " + appointment.getDoctorId());
					System.out.println("Patient ID: " + appointment.getPatientId());
					System.out.println("App Date: " + appointment.getAppointmentDate());
					System.out.println("Patient Descrip: " + appointment.getDescription());
					
					
	            }
	            if (appointmentsList.isEmpty()) {
	            	throw new PatientNumberNotFoundException("Patient Not Found");
	                
	            }
	          
	          
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
		return appointmentsList;
		
	}
	     
	
	
	
	public List<Appointments> getAppointmentsForDoctor() {
		System.out.println("Enter the Doctor ID:");
		int did=sc.nextInt();
		return getAppointmentsForDoctorFromData(did);
		
		}
	public List<Appointments> getAppointmentsForDoctorFromData(int did) {
		List<Appointments> appointmentsList = new ArrayList<>();
		 try {
	            con = DbConnUtil.getDBConn();
	            ps = con.prepareStatement("select * from appointments where doctorid = ?");
	            ps.setInt(1, did);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	                Appointments appointment = new Appointments();
	                appointment.setAppointmentId(rs.getInt("appointmentid"));
	                appointment.setDoctorId(rs.getInt("doctorid"));
	                appointment.setPatientId(rs.getInt("patientid"));
	                appointment.setAppointmentDate(rs.getString("appdate"));
	                appointment.setDescription(rs.getString("patdes"));
	                appointmentsList.add(appointment);
	                
	                System.out.println("Appointment ID: " + appointment.getAppointmentId());
					System.out.println("Doctor ID: " + appointment.getDoctorId());
					System.out.println("Patient ID: " + appointment.getPatientId());
					System.out.println("Appointment Date: " + appointment.getAppointmentDate());
					System.out.println("Patient Description: " + appointment.getDescription());
					
	                
	            }
	            if (appointmentsList.isEmpty()) {
	                System.out.println("No appointments found for doctor ID: " + did);
	                return null;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
		return appointmentsList;
	}
		
		




	public boolean scheduleAppointment(Appointments app) {
	    try {
	        con = DbConnUtil.getDBConn();
	        
	        
	       
	        ps = con.prepareStatement("insert into appointments (appointmentid,doctorid, patientid, appdate, patdes) VALUES (?,?, ?, ?, ?)");
	        ps.setInt(1, app.getAppointmentId());
	        ps.setInt(2, app.getDoctorId());
	        ps.setInt(3, app.getPatientId());
	        ps.setString(4, app.getAppointmentDate());
	        ps.setString(5, app.getDescription());
	        
	       
	        
	        int rowsAffected = ps.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("Scheduled");
	            return true;
	        } 
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return false;
	  
	}







	@Override
	public boolean updateAppointment(Appointments appointment) {
	    try {
	        con = DbConnUtil.getDBConn();
	        System.out.println("Enter Appointment ID:");
	        int appointmentId = sc.nextInt(); 
	        System.out.println("Enter Doctor ID:");
	        int doctorId = sc.nextInt(); 
	        System.out.println("Enter Patient ID:");
	        int patientId = sc.nextInt(); 
	        System.out.println("Enter Appointment Date:");
	        String appDate = sc.next(); 
	        System.out.println("Enter Patient Description:");
	        String desc = sc.next(); 
	        
	        ps = con.prepareStatement("update appointments SET doctorid = ?, appDate = ?, patdes = ? WHERE appointmentid = ?");
	        ps.setInt(1, doctorId);
	        ps.setString(2, appDate);
	        ps.setString(3, desc);
	        ps.setInt(4, appointmentId); 
	        int rowsUpdated = ps.executeUpdate();
	        if (rowsUpdated > 0) {
	            System.out.println("Appointment updated successfully");
	            return true;
	        } else {
	            System.out.println("Failed to update appointment. ");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	
	
	
	public boolean cancelAppointment(Appointments appointment) {
	    try {
	        con = DbConnUtil.getDBConn();
	        System.out.println("Enter Appointment ID to cancel:");
	        int appointmentId = sc.nextInt();     
	        ps = con.prepareStatement("delete from  appointments where appointmentid = ?");
	        ps.setInt(1, appointmentId);
	        int rowsUpdated = ps.executeUpdate();
	        if (rowsUpdated > 0) {
	            System.out.println("Appointment canceled successfully");
	            return true;
	        } else {
	            System.out.println("Failed to cancel appointment");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	
	
}
	/*List<Patient> patientList = new LinkedList<Patient>();
	
	public void insertpatient(Patient pt) throws SQLException, InvalidDataException  {
		
		System.out.println("Enter CustomerID to insert:");
		int Patientid = sc.nextInt();
		validatepatient(patientList,Patientid);
		pt.setPatientid(Patientid);
		
		
		System.out.println("Enter New First name");
		String newFirstName = sc.next();
		pt.setFirstName(newFirstName);

		System.out.println("Enter New Last name");
		String newLastName = sc.next();
		pt.setLastName(newLastName);

		System.out.println("Enter New Date Of Birth");
		String newDateofBirth = sc.next();
		pt.setDateofBirth(newDateofBirth );

		System.out.println("Enter Gender");
		String newGender = sc.next();
		pt.setGender(newGender);

		System.out.println("Enter New Contact");
		String newContactNumber = sc.next();
		pt.setContactNumber(newContactNumber);
		
		patientList.add(pt);
		
		.insertpatient(pt);
		
		
		}
		public boolean validatepatient(List<Patient> patientList2, int Patientid) throws InvalidDataException
		{
			String s="InvalidPatientData";
			for(Patient p:patientList2) {
				if(p.getPatientid()==Patientid)
				{
					return true;
				}
			}
			throw new InvalidDataException(s);
		}*/
		
		
		
		
	







	