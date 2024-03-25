package com.hexaware.model;

public class Patient {

	private int patientId;
	private String firstName;
	private String lastName;
	private String dateOfBirth;
	private String gender;
	private String contactNumber;
	private String address;

	public Patient() {
		System.out.println("From Patient Constr...");
	}
	
	public Patient(int Patientid, String FirstName,String LastName,
			String DateofBirth, String Gender,String ContactNumber,String Address) {
				super();
				this.patientId=Patientid ;
				this.firstName = FirstName;
				this.lastName = LastName;
				this.dateOfBirth=DateofBirth;
				this.gender=Gender;
				this.contactNumber=ContactNumber;
				this.address=Address;
			}

	
	
	
	public int getPatientid() {
		return patientId;
	}
	public void setPatientid(int patientid) {
		patientId = patientid;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		lastName = lastName;
	}
	public String getDateofBirth() {
		return dateOfBirth;
	}
	public void setDateofBirth(String dateofBirth) {
		dateOfBirth = dateofBirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		gender = gender;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		contactNumber = contactNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public String toString() {
		return("Patient:"
				+ "\nPatient Id:"+patientId
				+"\nFirst name:"+firstName
				+"\nLast name:"+lastName
				+"\nDate of Birth: "+dateOfBirth
				+"\nGender: "+gender
				+"\nContact number: "+contactNumber
				+"\nAddress: "+ address);
	}
}
