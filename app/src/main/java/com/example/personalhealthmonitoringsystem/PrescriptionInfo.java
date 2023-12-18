package com.example.personalhealthmonitoringsystem;

import java.util.Date;

public class PrescriptionInfo
{
	private MedicationInfo medication;
	private DoctorContactInfo prescribedBy;

	private Date startDate;
	private Date endDate;
	private String dosage;

	private String daysOfWeek;
	private String time;

	public PrescriptionInfo(MedicationInfo _medication, String _dosage)
	{
		medication = _medication;
	}

	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}

	public Date getStartDate()
	{
		return startDate;
	}

	public DoctorContactInfo getPrescribedBy()
	{
		return prescribedBy;
	}

	public void setPrescribedBy(DoctorContactInfo prescribedBy)
	{
		this.prescribedBy = prescribedBy;
	}

	public Date getEndDate()
	{
		return endDate;
	}

	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	public String getDaysOfWeek()
	{
		return daysOfWeek;
	}

	public void setDaysOfWeek(String daysOfWeek)
	{
		this.daysOfWeek = daysOfWeek;
	}

	public String getTime()
	{
		return time;
	}

	public void setTime(String time)
	{
		this.time = time;
	}
}
