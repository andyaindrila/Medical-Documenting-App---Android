package com.example.personalhealthmonitoringsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Communication_CreateContact extends AppCompatActivity
{
	private UserAccount currentUser;

	private Spinner selectContactType, selectPriority, selectHoursOpen, selectHoursClose;

	private EditText enterName, enterPhone, enterEmail, enterAddress, enterRelationship;

	private TextView hoursOpenLabel, hoursCloseLabel;

	private String mode;
	private int contactsIndex;

	private ArrayAdapter<CharSequence> adapterType, adapterPriority, adapterTime;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_communication_create_contact);

		selectContactType = findViewById(R.id.EnterType);
		adapterType = ArrayAdapter.createFromResource(this, R.array.dropDownMenuContactType, android.R.layout.simple_spinner_item);
		adapterType.setDropDownViewResource(android.R.layout.simple_spinner_item);
		selectContactType.setAdapter(adapterType);

		selectPriority = findViewById(R.id.EnterPriority);
		adapterPriority = ArrayAdapter.createFromResource(this, R.array.dropDownMenuPriority, android.R.layout.simple_spinner_item);
		adapterPriority.setDropDownViewResource(android.R.layout.simple_spinner_item);
		selectPriority.setAdapter(adapterPriority);

		selectHoursOpen = findViewById(R.id.EnterHoursOpen);
		selectHoursClose = findViewById(R.id.EnterHoursClose);
		adapterTime = ArrayAdapter.createFromResource(this, R.array.dropDownMenuTime, android.R.layout.simple_spinner_item);
		adapterTime.setDropDownViewResource(android.R.layout.simple_spinner_item);
		selectHoursOpen.setAdapter(adapterTime);
		selectHoursClose.setAdapter(adapterTime);

		enterName = findViewById(R.id.EnterName);
		enterPhone = findViewById(R.id.EnterPhone);
		enterEmail = findViewById(R.id.EnterEmail);
		enterAddress = findViewById(R.id.EnterAddress);
		enterRelationship = findViewById(R.id.EnterRelationship);


		hoursOpenLabel = findViewById(R.id.LabelHoursOpen);
		hoursCloseLabel = findViewById(R.id.LabelHoursClose);

		Button saveButton = findViewById(R.id.SaveContact);

		selectContactType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				if(selectContactType.getSelectedItem().toString().equals("Doctor"))
				{
					hoursOpenLabel.setEnabled(true);
					hoursOpenLabel.setVisibility(View.VISIBLE);
					hoursCloseLabel.setEnabled(true);
					hoursCloseLabel.setVisibility(View.VISIBLE);
					selectHoursOpen.setEnabled(true);
					selectHoursOpen.setVisibility(View.VISIBLE);
					selectHoursClose.setEnabled(true);
					selectHoursClose.setVisibility(View.VISIBLE);
				}
				else
				{
					hoursOpenLabel.setEnabled(false);
					hoursOpenLabel.setVisibility(View.INVISIBLE);
					hoursCloseLabel.setEnabled(false);
					hoursCloseLabel.setVisibility(View.INVISIBLE);
					selectHoursOpen.setEnabled(false);
					selectHoursOpen.setVisibility(View.INVISIBLE);
					selectHoursClose.setEnabled(false);
					selectHoursClose.setVisibility(View.INVISIBLE);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{

			}

		});

		saveButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				ValidateAndSave();
			}
		});


	}

	@Override
	protected void onResume()
	{
		super.onResume();


		currentUser = (UserAccount) getIntent().getSerializableExtra("CurrentUser");
		mode = getIntent().getStringExtra("mode");

		TextView titleText = findViewById(R.id.Title);

		if(mode.equals("EDIT"))
		{
			contactsIndex = getIntent().getIntExtra("editIndex", -1);

			ContactInfo toEdit = currentUser.getContact(contactsIndex);

			if(toEdit instanceof DoctorContactInfo)
				selectContactType.setSelection(adapterType.getPosition("Doctor"));
			else if(toEdit instanceof NextOfKinContactInfo)
				selectContactType.setSelection(adapterType.getPosition("Next of Kin"));

			enterName.setText(toEdit.getContactName());
			enterPhone.setText(toEdit.getPhone());
			enterEmail.setText(toEdit.getEmail());
			enterAddress.setText(toEdit.getAddress());
			enterRelationship.setText(toEdit.getRelationship());
			selectPriority.setSelection(adapterPriority.getPosition(toEdit.getPriority()));

			if(toEdit instanceof DoctorContactInfo)
			{
				selectHoursOpen.setSelection(adapterTime.getPosition(((DoctorContactInfo) toEdit).getOpenHours()));
				selectHoursClose.setSelection(adapterTime.getPosition(((DoctorContactInfo) toEdit).getCloseHours()));
			}

			selectContactType.setEnabled(false); //Once a contact is created, can not change data type

			titleText.setText("Edit Contact");
		}
		else
		{
			titleText.setText("Create Contact");
		}

	}

	private void ValidateAndSave()
	{
		String errorMessage = "ERROR:\n";
		boolean errorFound = false;

		//Need to validate all fields and save:

		// Get all entered values:
		String contactType = selectContactType.getSelectedItem().toString();
		String contactName = enterName.getText().toString();
		String contactPhone = enterPhone.getText().toString();
		String contactEmail = enterEmail.getText().toString();
		String contactAddress = enterAddress.getText().toString();
		String contactRelationship = enterRelationship.getText().toString();
		String contactPriority = selectPriority.getSelectedItem().toString();
		String contactHoursOpen = selectHoursOpen.getSelectedItem().toString();
		String contactHoursClose = selectHoursClose.getSelectedItem().toString();
		int contactHoursOpenIndex = selectHoursOpen.getSelectedItemPosition();
		int contactHoursCloseIndex = selectHoursClose.getSelectedItemPosition();

		if(contactName.equals(""))
		{
			errorFound = true;
			errorMessage += "Name Required.\n";
		}

		//Validate phone
		if(!contactPhone.matches("[0-9]{10}"))
		{
			errorFound = true;
			errorMessage += "Please enter a valid phone number!\n";
		}
		//Validate email
		if(!contactEmail.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))
		{
			errorFound = true;
			errorMessage += "Please enter a valid email address!\n";
		}

		//Validate hours
		if(contactType.equals("Doctor") && contactHoursOpenIndex > contactHoursCloseIndex)
		{
			errorFound = true;
			errorMessage += "Open time selected later than Close time!\n";
		}

		if(errorFound)
		{
			Toast toast = Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG);
			toast.show();
		}
		else
		{
			//Contact validated, save new contact or update:
			if(mode.equals("ADD"))
			{
				switch (contactType)
				{
					case "Default":
						currentUser.addContact(new ContactInfo(contactName, contactPhone, contactEmail, contactAddress, contactPriority, contactRelationship));
						break;
					case "Doctor":
						currentUser.addContact(new DoctorContactInfo(contactName, contactPhone, contactEmail, contactAddress, contactPriority, contactRelationship, contactHoursOpen, contactHoursClose));
						break;
					case "Next of Kin":
						currentUser.addContact(new NextOfKinContactInfo(contactName, contactPhone, contactEmail, contactAddress, contactPriority, contactRelationship));
						break;
				}

				Intent intent = new Intent(getApplicationContext(), Communication.class);
				intent.putExtra("CurrentUser", currentUser);
				startActivity(intent);
			}
			else if(mode.equals("EDIT"))
			{
				switch (contactType)
				{
					case "Default":
						currentUser.updateContact(contactsIndex, new ContactInfo(contactName, contactPhone, contactEmail, contactAddress, contactPriority, contactRelationship));
						break;
					case "Doctor":
						currentUser.updateContact(contactsIndex, new DoctorContactInfo(contactName, contactPhone, contactEmail, contactAddress, contactPriority, contactRelationship, contactHoursOpen, contactHoursClose));
						break;
					case "Next of Kin":
						currentUser.updateContact(contactsIndex, new NextOfKinContactInfo(contactName, contactPhone, contactEmail, contactAddress, contactPriority, contactRelationship));
						break;
				}

				Intent intent = new Intent(getApplicationContext(), Communication.class);
				intent.putExtra("CurrentUser", currentUser);
				startActivity(intent);
			}
		}

	}

}