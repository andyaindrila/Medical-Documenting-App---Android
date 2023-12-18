package com.example.personalhealthmonitoringsystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class UserAccount implements Serializable
{
	private String FirstName;
	private String MiddleName;
	private String LastName;

	private String email;
	private String address;

	private String userId;
	private String password;

	private ArrayList<ContactInfo> ContactsList;
	private ArrayList<DietInfo> DietsList;
	private ArrayList<NotesInfo> NotesList;
	private ArrayList<MedicationInfo> MedicationList;
	private ArrayList<PrescriptionInfo> PrescriptionList;

	public UserAccount(String _userId, String _password)
	{
		userId = _userId;
		password = _password;
	}

	// Used to set up a testing account with basic information already entered
	public void SetUpTestingAccountData()
	{
		FirstName = "Test";
		LastName ="Account";
		email = "testaccount@testing.com";
		address = "1234 UTA Blvd, Arlington TX";

		ContactsList = new ArrayList<ContactInfo>();

		ContactsList.add(new ContactInfo(
						"Test User 1",
						"5555551111",
						"test.user@example.com",
						"1234 Real Street, City, TX",
						"Low",
						"Father"));

		ContactsList.add(new DoctorContactInfo(
						"Dr. Pepper",
						"5555552222",
						"test.again@example.gov",
						"1235 Real Street, City, TX",
						"High",
						"Heart Doctor",
						"8:00 AM",
						"6:00 PM"));

		ContactsList.add(new NextOfKinContactInfo(
						"Another Test",
						"5555553333",
						"3333333333@example.com",
						"1234 Another Street, City, TX",
						"Medium",
						"Son"));

		DietsList = new ArrayList<DietInfo>();


		NotesList = new ArrayList<NotesInfo>();


		MedicationList = new ArrayList<MedicationInfo>();

		//TODO: These should live in the database eventually and be a global list of all medications
		MedicationList.add(new MedicationInfo("Medication1"));
		MedicationList.add(new MedicationInfo("Medication2"));
		MedicationList.add(new MedicationInfo("Medication3"));
		MedicationList.get(0).AddConflictingMedication(MedicationList.get(2));

		PrescriptionList = new ArrayList<PrescriptionInfo>();

	}

	public int getNumContacts()
	{
		return ContactsList.size();
	}

	public ContactInfo getContact(int i)
	{
		return ContactsList.get(i);
	}

	public void addContact(ContactInfo c)
	{
		ContactsList.add(c);
	}
	public void updateContact(int i, ContactInfo c)
	{
		ContactsList.set(i, c);
	}

	public void deleteContact(int i)
	{
		ContactsList.remove(i);
	}

}
