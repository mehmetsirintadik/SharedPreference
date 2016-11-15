package mehmet.com.sharedpreference;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView name, email;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String name_string, email_string;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (TextView)findViewById(R.id.name);
        email = (TextView)findViewById(R.id.email);

        preferences =PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor =preferences.edit();
//        name_string=preferences.getString("name",name_string).toString();
//        email_string= preferences.getString("email",email_string).toString();
//
//        name.setText(name_string);
//        email.setText(email_string);

        userInformation();


    }

    private void userInformation() {
        Users user = new Users();
        name_string = user.getName();
        email_string = user.getEmail();
        name.setText(name_string);
        email.setText(email_string);
    }

    @Override
    public void onBackPressed() {
        Logout();
    }
    public void Logout(){
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = preferences.edit();

        editor.putBoolean("login",false);
        editor.commit();
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);
        finish();
    }
}
