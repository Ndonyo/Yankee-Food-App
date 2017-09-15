package com.example.ndony.yankeefood;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    Button bLogin;
    EditText etUsername, etPassword;
    TextView tvRegisterLink;
   // UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername=(EditText)findViewById(R.id.etUsername);
        etPassword=(EditText)findViewById(R.id.etPassword);
        bLogin=(Button) findViewById(R.id.bLogin);
        tvRegisterLink=(TextView) findViewById(R.id.tvRegisterLink);

        bLogin.setOnClickListener(this);
        tvRegisterLink.setOnClickListener(this);
       // userLocalStore= new UserLocalStore(this);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.bLogin:

               String username=etUsername.getText().toString();
                String password=etPassword.getText().toString();

                loginnow(username,password);



                break;

            case R.id.tvRegisterLink:
                startActivity(new Intent(this,RegisterActivity.class));
                break;

        }
    }


    public void loginnow (final String username,final String password)
    {
        class AddEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog progressDialog;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Authenticating...");
                progressDialog.show();

            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put("username",username);
                params.put("password",password);
                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest("http://192.168.43.154:8080/project/login.php", params);
                return res;

            }

            @Override
            protected void onPostExecute(final String s) {
                super.onPostExecute(s);
                progressDialog.dismiss();

                Toast.makeText(LoginActivity.this, s , Toast.LENGTH_SHORT).show();

                if(s.equals("1"))
                {
                    //go to another activity
                }
                else{
                    Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                }



            }
        }
        AddEmployee ae = new AddEmployee();
        ae.execute();  


    }


}
