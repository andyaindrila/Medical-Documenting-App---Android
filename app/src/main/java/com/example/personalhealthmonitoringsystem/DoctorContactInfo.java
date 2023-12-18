package com.example.personalhealthmonitoringsystem;

public class DoctorContactInfo extends ContactInfo
{
	private String openHours;
	private String closeHours;

	public DoctorContactInfo(String name, String phone, String email, String address, String priority, String relationship, String open, String close)
	{
		super(name, phone, email, address, priority, relationship);
		openHours = open;
		closeHours = close;
	}

	public String getCloseHours()
	{
		return closeHours;
	}

	public void setCloseHours(String closeHours)
	{
		this.closeHours = closeHours;
	}

	public String getOpenHours()
	{
		return openHours;
	}

	public void setOpenHours(String openHours)
	{
		this.openHours = openHours;
	}

	public String getHoursString()
	{
		return openHours + " - " + closeHours;
	}
}
