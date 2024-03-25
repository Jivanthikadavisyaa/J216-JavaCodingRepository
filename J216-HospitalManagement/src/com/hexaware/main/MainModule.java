
package com.hexaware.main;

import java.util.Scanner;

import com.hexaware.dao.*;
import com.hexaware.exception.PatientNumberNotFoundException;
import com.hexaware.model.Appointments;

public class MainModule{

	public static void main(String[] args)  {
		IHospitalService ihs = new HospitalServiceImpl();
		 Appointments appointment = new Appointments();
		 Scanner sc = new Scanner(System.in);
		 String ch = null;
		do {
			System.out.println("Enter your choice:");
			System.out.println("1. Get Info about Appointment");
			System.out.println("2. Appointment for patient");
			System.out.println("3. Appointmnet for doctor");
			System.out.println("4. Schedule New Appointment");
			System.out.println("5. Update Appointment");
			System.out.println("6. Delete Appointment");
		

			int choice = sc.nextInt();
			switch (choice) {
			case 1: {
				ihs.getAppointmentById();
				
					break;
			}
			case 2: {
				try {
					ihs.getAppointmentsForPatient();
				} catch (PatientNumberNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				break;
			}
			case 3: {
				ihs.getAppointmentsForDoctor();

				break;
		}
			case 4: { 
			System.out.println("Enter Appointment ID:");
            int Appointmentid=sc.nextInt();
            System.out.println("Enter Doctor ID:");
            int Doctorid=sc.nextInt();
            System.out.println("Enter Patient ID:");
            int Patientid=sc.nextInt();
            System.out.println("Enter Appointment Date:");
            String AppDate=sc.next();
            System.out.println("Enter Patient Description:");
            String Desc=sc.next();
            Appointments app=new Appointments(Appointmentid,Doctorid,Patientid,AppDate,Desc);
            
			
		    ihs.scheduleAppointment(app);
		    break;
			}
			case 5: {
					ihs.updateAppointment(appointment);
				break;
			}
			case 6: {
				ihs.cancelAppointment(appointment);				
			break;
		}
			default: {
				System.out.println("Enter the right choice. ");
			}
			}
			System.out.println("Do you want to continue? Y or y");
			ch = sc.next();
		} while (ch.equals("Y") || ch.equals("y"));
		System.out.println("Thanks for using our system !!!");
		System.exit(0);
	}
}