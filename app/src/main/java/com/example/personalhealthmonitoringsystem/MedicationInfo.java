package com.example.personalhealthmonitoringsystem;

import java.util.ArrayList;
import java.util.Date;

public class MedicationInfo
{
	private String name;
	private ArrayList<MedicationInfo> conflictsWith;


	public MedicationInfo(String _name)
	{
		name = _name;
	}

	public void AddConflictingMedication(MedicationInfo other)
	{
		conflictsWith.add(other);
		other.conflictsWith.add(this);
	}



}
