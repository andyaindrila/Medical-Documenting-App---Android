package com.example.personalhealthmonitoringsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
	private UserAccount currentUser;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		EditText usernameEntry, passwordEntry;
		Button loginButton, forgotPasswordButton;

		usernameEntry = findViewById(R.id.UsernameEntry);
		passwordEntry = findViewById(R.id.PasswordEntry);
		loginButton = findViewById(R.id.LoginButton);
		forgotPasswordButton = findViewById(R.id.ForgotPasswordButton);

		//TODO: REMOVE HARDCODING
		currentUser = new UserAccount("testuser", "password");



		loginButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{

				String user = usernameEntry.getText().toString();
				String password = passwordEntry.getText().toString();

				if (user.equals("testuser") && password.equals("password"))
				{
					//Hardcoded test user
					//TODO: Add actual login code
					currentUser.SetUpTestingAccountData();

					Intent intent = new Intent(getApplicationContext(), Dashboard.class);
					intent.putExtra("CurrentUser", currentUser);
					startActivity(intent);
				} else
				{
					//Invalid login

					Context context = getApplicationContext();
					CharSequence text = "Invalid Username or Password";
					int duration = Toast.LENGTH_LONG;

					Toast toast = Toast.makeText(context, text, duration);
					toast.show();

				}

			}
		});
		forgotPasswordButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				//TODO: Add reset password functionality
			}
		});
	}

}