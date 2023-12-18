package com.example.personalhealthmonitoringsystem;

import java.io.Serializable;

public class ContactInfo implements Serializable
{
	private String contactName;
	private String Phone;
	private String Email;
	private String Address;
	private String Priority;

	private String Relationship;


	public ContactInfo(String name, String phone, String email, String address, String priority, String relationship)
	{
		contactName = name;
		Phone = phone;
		Email = email;
		Address = address;
		Priority = priority;
		Relationship = relationship;
	}

	public void setContactName(String n)
	{
		contactName = n;
	}
	public String getContactName()
	{
		return contactName;
	}


	public String getPhone()
	{
		return Phone;
	}
	public void setPhone(String phone)
	{
		Phone = phone;
	}

	public void setAddress(String address)
	{
		Address = address;
	}
	public String getAddress()
	{
		return Address;
	}

	public void setEmail(String email)
	{
		Email = email;
	}
	public String getEmail()
	{
		return Email;
	}

	public String getPriority()
	{
		return Priority;
	}
	public void setPriority(String priority)
	{
		Priority = priority;
	}

	public String getRelationship()
	{
		return Relationship;
	}
	public void setRelationship(String relationship)
	{
		Relationship = relationship;
	}
}
