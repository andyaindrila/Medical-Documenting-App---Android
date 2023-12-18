package com.example.personalhealthmonitoringsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Communication_ViewContact extends AppCompatActivity
{
	private UserAccount currentUser;
	private TextView contactType, contactName, contactPhone, contactEmail, contactAddress, contactPriority, contactRelationship;
	private TextView doctorHoursLabel, doctorHours;

	private int contactsIndex;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_communication_view_contact);

		Button next = findViewById(R.id.ContactsNext);
		Button prev = findViewById(R.id.ContactsPrevious);
		Button edit = findViewById(R.id.EditContact);
		Button delete = findViewById(R.id.DeleteContact);

		contactType = findViewById(R.id.DisplayType);
		contactName = findViewById(R.id.DisplayName);
		contactPhone = findViewById(R.id.DisplayPhone);
		contactEmail = findViewById(R.id.DisplayEmail);
		contactAddress = findViewById(R.id.DisplayAddress);
		contactPriority = findViewById(R.id.DisplayPriority);
		contactRelationship = findViewById(R.id.DisplayRelationship);

		doctorHoursLabel = findViewById(R.id.LabelHours);
		doctorHours = findViewById(R.id.DisplayHours);



		next.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				contactsIndex++;
				if(contactsIndex >= currentUser.getNumContacts())
					contactsIndex = 0;

				displayContact();
			}
		});

		prev.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				contactsIndex--;
				if(contactsIndex < 0)
					contactsIndex = currentUser.getNumContacts() - 1;

				displayContact();
			}
		});
		edit.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(getApplicationContext(), Communication_CreateContact.class);
				intent.putExtra("CurrentUser", currentUser);
				intent.putExtra("mode", "EDIT");
				intent.putExtra("editIndex", contactsIndex);
				startActivity(intent);
			}
		});

		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which){
					case DialogInterface.BUTTON_POSITIVE:
						//Yes button clicked
						currentUser.deleteContact(contactsIndex);

						Intent intent = new Intent(getApplicationContext(), Communication.class);
						intent.putExtra("CurrentUser", currentUser);
						startActivity(intent);

						break;

					case DialogInterface.BUTTON_NEGATIVE:
						//No button clicked
						break;
				}
			}
		};

		delete.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				AlertDialog.Builder builder = new AlertDialog.Builder(Communication_ViewContact.this);
				builder.setMessage("Are you sure you want to delete contact?").setPositiveButton("Yes", dialogClickListener)
						.setNegativeButton("No", dialogClickListener).show();
			}
		});



	}

	@Override
	protected void onResume()
	{
		super.onResume();




		currentUser = (UserAccount) getIntent().getSerializableExtra("CurrentUser");
		contactsIndex = getIntent().getIntExtra("contactsIndex", 0);

		displayContact();
	}

	private void displayContact()
	{
		ContactInfo toDisplay = currentUser.getContact(contactsIndex);

		contactType.setText("Default Contact");
		if(toDisplay instanceof DoctorContactInfo)
			contactType.setText("Doctor");
		else if(toDisplay instanceof NextOfKinContactInfo)
			contactType.setText("Next of Kin");

		contactName.setText(toDisplay.getContactName());
		contactPhone.setText(toDisplay.getPhone());
		contactEmail.setText(toDisplay.getEmail());
		contactAddress.setText(toDisplay.getAddress());
		contactPriority.setText(toDisplay.getPriority());

		contactRelationship.setText(toDisplay.getRelationship());

		if(toDisplay instanceof DoctorContactInfo)
		{
			doctorHoursLabel.setText("Hours:");
			doctorHours.setText(((DoctorContactInfo) toDisplay).getHoursString());
		}
		else
		{
			doctorHoursLabel.setText("");
			doctorHours.setText("");
		}


	}
}