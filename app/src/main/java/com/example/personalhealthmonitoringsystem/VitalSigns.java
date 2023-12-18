package com.example.personalhealthmonitoringsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class VitalSigns extends AppCompatActivity
{
	private UserAccount currentUser;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vital_signs);
	}

	@Override
	protected void onResume()
	{
		super.onResume();

		currentUser = (UserAccount) getIntent().getSerializableExtra("CurrentUser");

	}
}