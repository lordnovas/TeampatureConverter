package jr.com.teampatureconverter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;


public class UserListScreen extends ActionBarActivity
{
    TextView tv_userNames;
    private ArrayList<String> ls_names = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list_screen);
        tv_userNames = (TextView)findViewById(R.id.userList);
        loadUsers(tv_userNames);
    }


    /***********goToConverter()*****************************/
    public void goToConverter(View view)
    {
        Intent i = new Intent(getApplicationContext(),ConvertTempActivity.class);
        startActivity(i);
    }

    /***********goToLogin()********************************/
    public void goToLogin(View view)
    {
        Intent i = new Intent(getApplicationContext(),LoginScreen.class);
        startActivity(i);
    }

    /***********loadUsers()*****************************/
    public void loadUsers(View view)
    {
        /*
          Get all entries in SharedPreferences
          Clear List of names from array and
            remap/reload current list of names from SharedPreferences file
          Sort list of names to display alphabetically
          Clear tv_userNames to append fresh list
          Add each name to tv_userNames up to the tenth user
         */
        SharedPreferences sp = getSharedPreferences("user_list", Context.MODE_PRIVATE);
        Map<String, ?> allEntries = sp.getAll();
        ls_names.clear();

        for (Map.Entry<String, ?> entry : allEntries.entrySet())
        {
            System.out.println("Inside Shared Preferecense Here is a Value " + entry.getValue());
            ls_names.add(entry.getValue().toString().trim());
        }

        Collections.sort(ls_names, String.CASE_INSENSITIVE_ORDER);
        tv_userNames.setText(" ");

        for(int i=0,j=10;i<ls_names.size()-1 && j>0;j--,i++)
        {
            tv_userNames.append("\n#" + (i+1) + " "+ ls_names.get(i));
        }
    }

}
