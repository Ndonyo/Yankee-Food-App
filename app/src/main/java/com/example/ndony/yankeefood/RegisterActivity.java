package com.example.ndony.yankeefood;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    Button bRegister;
    EditText etFullName,etLocation, etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etFullName = (EditText) findViewById(R.id.etFullName);
        etLocation = (EditText) findViewById(R.id.etLocation);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bRegister = (Button) findViewById(R.id.bRegister);

        bRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.bRegister:

                String FullName=etFullName.getText().toString();
                String Location=etLocation.getText().toString();
                String Username=etUsername.getText().toString();
                String Password=etPassword.getText().toString();

               // User registeredData= new User(FullName,Location, Username, Password);
                loginnow(FullName,Location, Username,Password);

                break;




        }
}


    public void loginnow (final String name,final String location, final String username, final String password)
    {
        class AddEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog progressDialog;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = new ProgressDialog(RegisterActivity.this);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Authenticating...");
                progressDialog.show();

            }
            /*
            $sname=			$_POST['name'];
$username=			$_POST['username'];
$password=		$_POST['password'];
$location=		$_POST['location'];

             */

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();

                params.put("name",name);
                params.put("username",username);
                params.put("password",password);
                params.put("location",location);


                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest("http://192.168.1.34:8080/project/register.php", params);
                return res;

            }

            @Override
            protected void onPostExecute(final String s) {
                super.onPostExecute(s);
                progressDialog.dismiss();

                Toast.makeText(RegisterActivity.this, s, Toast.LENGTH_SHORT).show();

                if(s.equals("1"))
                {
                    //go to another activity
                }
                else{
                    Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                }


            }
        }
        AddEmployee ae = new AddEmployee();
        ae.execute();


    }




}



