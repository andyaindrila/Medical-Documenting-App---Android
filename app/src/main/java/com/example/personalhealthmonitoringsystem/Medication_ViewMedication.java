package com.example.personalhealthmonitoringsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Medication_ViewMedication extends AppCompatActivity
{
	private UserAccount currentUser;

	@Override
	protected void onResume()
	{
		super.onResume();

		currentUser = (UserAccount) getIntent().getSerializableExtra("CurrentUser");

	}
}