package jr.com.teampatureconverter;

/*
  Author: Joel Rainey
  Date: 3/19
  Class: CS211D Spring 2015
  Android Project: User Login/Password + Temperature Converter
  Filename: LoginScreen.java
  Assignment Objective: Create an application with Two Activities. First Activity User login
  entering user id "admin" and user password "password" and press login button to open
  Temperature Converter Activity
*/

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;


public class LoginScreen extends ActionBarActivity
{
    EditText et_userID, et_password;
    Button mBtn_login;
    private static final String TAG = "LoginScreen";
    int counter = 0;
    private ArrayList<String> ls_names = new ArrayList<>();



    /**************onCreate()******************************/
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        et_userID = (EditText) findViewById(R.id.et_UserID);
        et_password = (EditText) findViewById(R.id.et_Password);
        mBtn_login = (Button) findViewById(R.id.btn_login);
        populate();

    }


    /***********Login()************************************/
    public void login(View v)
    {

        //Get user ID and Pass from editText (et_userID & et_password)
        //Validate userID, if true start ConvertTempActivity
        String tempUserID = et_userID.getText().toString();

        if (validate(tempUserID))
        {
            mkToast("Success lets start temp converter Activity");
            Intent i = new Intent(getApplicationContext(), ConvertTempActivity.class);
            startActivity(i);
        }
        else
        {
            mkToast("Login Failed: user id and password are incorrect");
        }
    }

    /***********goTo_UserList()*****************************/
    public void goTo_UserList(View view)
    {
        mkToast("Go To User List Screen");
        Intent i = new Intent(getApplicationContext(),UserListScreen.class);
        startActivity(i);
    }

    /***********validate()*********************************/
    //validate userId
    private Boolean validate(String name)
    {
        SharedPreferences sp = getSharedPreferences("userList",Context.MODE_PRIVATE);

        //Clear previous entries to prevent dupes
        ls_names.clear();

        //get all key/val and set to allEntries
        Map<String, ?> allEntries = sp.getAll();

        //Add all entries to ls_names
        for (Map.Entry<String, ?> entry : allEntries.entrySet())
        {
            ls_names.add(entry.getValue().toString().trim());
        }
        ls_names.add("joel");
        return ls_names.contains(name);
    }


    /**********onRadioButtonClicked()**********************/
    public void onRadioButtonClicked(View v)
    {
        //If checkbox checked is true, change visible password to on

        boolean checked = ((RadioButton) v).isChecked();

        switch (v.getId())
        {
            case R.id.showPassword:
            {
                if (checked)
                {
                    et_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                break;
            }

        }
    }


    /***********save()*************************************/
    //save userId
    public void save(View v)
    {
        SharedPreferences sp = getSharedPreferences("user_list", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        counter++;
        String fakePass = ""+counter;
        editor.putString(fakePass,et_userID.getText().toString());
        editor.commit();
        mkToast("The Data was successfully saved!!");
    }


    /***********populate()****************************/
    //pre Populate SharedPreference user_list file
    public void populate()
    {
        ls_names.add("joel");
        ls_names.add("Marry");
        ls_names.add("Barry");
        ls_names.add("Max");
        ls_names.add("Kitty");
        ls_names.add("Samsung");
        ls_names.add("Adam");
        ls_names.add("Larry");
        ls_names.add("Ken");
        ls_names.add("Steve");
        ls_names.add("Zander");

        SharedPreferences sp = getSharedPreferences("user_list", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        int counter =0;

        for(int i=0;i<ls_names.size()-1;i++)
        {
            System.out.println(ls_names.get(i));
            editor.putString(""+counter++, ls_names.get(i));

            editor.commit();
        }
    }


    /**********mkToast()**********************************/
    public void mkToast(String text)
    {
        Context con = getApplicationContext();
        Toast t = Toast.makeText(con, text, Toast.LENGTH_SHORT);
        t.show();
    }



}
