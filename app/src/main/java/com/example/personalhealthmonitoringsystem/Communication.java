package com.example.personalhealthmonitoringsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Communication extends AppCompatActivity
{
	private UserAccount currentUser;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_communication);


		Button viewContact, addContact, toDashboard;
		viewContact = findViewById(R.id.ViewContacts);
		addContact = findViewById(R.id.AddContact);
		toDashboard = findViewById(R.id.ReturnToDashboard);

		viewContact.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if(currentUser.getNumContacts() > 0)
				{
					Intent intent = new Intent(getApplicationContext(), Communication_ViewContact.class);
					intent.putExtra("CurrentUser", currentUser);
					intent.putExtra("contactsIndex", 0);
					startActivity(intent);
				}
				else
				{
					Toast toast = Toast.makeText(getApplicationContext(), "No Contacts Found.\nUse \"Add Contact\" to get started.", Toast.LENGTH_LONG);
					toast.show();
				}

			}
		});
		addContact.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(getApplicationContext(), Communication_CreateContact.class);
				intent.putExtra("CurrentUser", currentUser);
				intent.putExtra("mode", "ADD");
				startActivity(intent);
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