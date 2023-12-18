package com.example.personalhealthmonitoringsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MonitoringSystem extends AppCompatActivity
{
	private UserAccount currentUser;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_monitoring_system);
	}

	@Override
	protected void onResume()
	{
		super.onResume();

		currentUser = (UserAccount) getIntent().getSerializableExtra("CurrentUser");

	}
}