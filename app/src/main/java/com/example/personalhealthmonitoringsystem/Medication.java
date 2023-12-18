package com.example.personalhealthmonitoringsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Medication extends AppCompatActivity
{
	private UserAccount currentUser;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_medication);

		Button viewMedication, addMedication, toDashboard;
		viewMedication = findViewById(R.id.ViewMedications);
		addMedication = findViewById(R.id.AddMedication);
		toDashboard = findViewById(R.id.ReturnToDashboard);

		addMedication.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{

			}
		});
		viewMedication.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{

			}
		});

		toDashboard.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(getApplicationContext(), Dashboard.class);
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