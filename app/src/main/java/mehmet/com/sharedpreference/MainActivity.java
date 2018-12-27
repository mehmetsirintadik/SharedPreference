package mehmet.com.sharedpreference;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    TextView name, email;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (TextView)findViewById(R.id.name);
        email = (TextView)findViewById(R.id.email);
        img=(ImageView)findViewById(R.id.photo);

        preferences =PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor =preferences.edit();

        userInformation();
    }

    private void userInformation() {
        String mailExtra = preferences.getString("email","email");
        String nameExtra = preferences.getString("name","name");
        name.setText(nameExtra);
        email.setText(mailExtra);
        Picasso.with(this).load("http://nulm.gov.in/images/user.png").into(img);
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
