package jr.com.teampatureconverter;

/*
  Author: Joel Rainey
  Date: 3/19
  Class: CS211D Spring 2015
  Android Project: User Login/Password + Temperature Converter
  Filename: ConvertTempActivity.java
  Assignment Objective: Create an application with Two Activities. First Activity User login
  entering user id "admin" and user password "password" and press login button to open
  Temperature Converter Activity
*/

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


public class ConvertTempActivity extends ActionBarActivity
{
     EditText et_cel, et_far;

    /***********onCreate***********************************/
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert_temp);
        et_cel = (EditText)findViewById(R.id.celField);
        et_far = (EditText)findViewById(R.id.farField);

        //add On Key Listener for Celsius text field
        et_cel.setOnKeyListener(new View.OnKeyListener()
        {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent ke)
            {
                //check which key was pressed
                if ((ke.getAction() == KeyEvent.ACTION_DOWN)
                        && (keyCode == KeyEvent.KEYCODE_ENTER))
                {
                    updateFar();
                    return true;
                }
                return false;
            }
        });

        //add On Key Listener for Fahrenheit text field
        et_far.setOnKeyListener(new View.OnKeyListener()
        {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent ke)
            {
                //check which key was pressed
                if((ke.getAction() == KeyEvent.ACTION_DOWN)
                        &&(keyCode == KeyEvent.KEYCODE_ENTER))
                {
                    updateCel();
                    return true;
                }
                return false;
            }
        });
    }

    /***********goToUserList()*****************************/
    public void goTo_UserList(View view)
    {
        Intent i = new Intent(getApplicationContext(),UserListScreen.class);
        startActivity(i);
    }

    /***********goToLogin()********************************/
    public void goToLogin(View view)
    {
        Intent i = new Intent(getApplicationContext(),LoginScreen.class);
        startActivity(i);
    }

    /***********onRadioButtonClicked***********************/
    public void onRadioButtonClicked(View v)
    {
        boolean checked = ((RadioButton)v).isChecked();

        switch (v.getId())
        {
            case  R.id.rbCeltoFar:
            {
                if(checked)
                {
                    updateFar();
                }
                break;
            }
            case R.id.rbFartoCel:
                if(checked)
                {
                    updateCel();
                }
                break;
        }
    }

    /***********updateFar***********************************/
    public void updateFar()
    {
        if(et_cel.length() > 0)
        {
            String etString = et_cel.getText().toString();
            int fahTemp =(int) Double.parseDouble(etString);
            etString = convertCeltoFar(fahTemp);
            et_far.setText("");
            et_far.setText(etString);
        }
        else
        {
            mkToast("Input Celsius Temperature");
        }
    }

    /***********updateCel***********************************/
    public void updateCel()
    {
        if(et_far.length() > 0)
        {
            String etString = et_far.getText().toString();
            int  celTemp = (int)Double.parseDouble(etString);
            etString = convertFartoCel(celTemp);

            //Clear text field before adding new text
            et_cel.setText(" ");
            et_cel.setText(etString);
        }
        else
        {
            mkToast("Input Fahrenheit Temperature");
        }
    }

    /***********convertCeltoFar*****************************/
    public String convertCeltoFar(double celsius)
    {
        String result;
        int fah = (int)((1.8 * celsius) + 32);
        result = " "+fah;
        return result;
    }

    /***********convertFartoCel******************************/
    public String convertFartoCel(double fahrenheit)
    {
        String result;
        int celsius = (int)((fahrenheit - 32)*.5556);
        result = ""+celsius;
        return result;
    }


    /***********mkToast***********************************/
    public void mkToast(String text)
    {
        Context con = getApplicationContext();
        Toast t = Toast.makeText(con, text, Toast.LENGTH_SHORT);
        t.show();
    }
}
