package com.example.personalhealthmonitoringsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Dashboard extends AppCompatActivity
{
	private UserAccount currentUser;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dashboard);

		Button navigate_VitalSigns, navigate_Medication, navigate_Diet, navigate_Notes, navigate_Search, navigate_MonitoringSystem, navigate_Communication, navigate_DataMaintenance;

		navigate_VitalSigns = findViewById(R.id.Navigate_VitalSigns);
		navigate_Medication = findViewById(R.id.Navigate_Medication);
		navigate_Diet = findViewById(R.id.Navigate_Diet);
		navigate_Notes = findViewById(R.id.Navigate_Notes);
		navigate_Search = findViewById(R.id.Navigate_Search);
		navigate_MonitoringSystem = findViewById(R.id.Navigate_MonitoringSystem);
		navigate_Communication = findViewById(R.id.Navigate_Communication);
		navigate_DataMaintenance = findViewById(R.id.Navigate_DataMaintenance);

		navigate_VitalSigns.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(getApplicationContext(), VitalSigns.class);
				intent.putExtra("CurrentUser", currentUser);
				startActivity(intent);
			}
		});

		navigate_Medication.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(getApplicationContext(), Medication.class);
				intent.putExtra("CurrentUser", currentUser);
				startActivity(intent);
			}
		});

		navigate_Diet.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(getApplicationContext(), Diet.class);
				intent.putExtra("CurrentUser", currentUser);
				startActivity(intent);
			}
		});

		navigate_Notes.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(getApplicationContext(), Notes.class);
				intent.putExtra("CurrentUser", currentUser);
				startActivity(intent);
			}
		});

		navigate_Search.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(getApplicationContext(), Search.class);
				intent.putExtra("CurrentUser", currentUser);
				startActivity(intent);
			}
		});

		navigate_MonitoringSystem.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(getApplicationContext(), MonitoringSystem.class);
				intent.putExtra("CurrentUser", currentUser);
				startActivity(intent);
			}
		});

		navigate_Communication.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(getApplicationContext(), Communication.class);
				intent.putExtra("CurrentUser", currentUser);
				startActivity(intent);
			}
		});

		navigate_DataMaintenance.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(getApplicationContext(), DataMaintenance.class);
				intent.putExtra("CurrentUser", currentUser);
				startActivity(intent);
			}
		});
	}

	@Override
	protected void onResume()
	{
		super.onResume();

		currentUser = (UserAccount) getIntent().getSerializableExtra("CurrentUser");

	}
}