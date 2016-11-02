package mehmet.com.sharedpreference;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Mehmet on 2.11.2016.
 */

public class SignActivity extends AppCompatActivity {

    SharedPreferences preference;
    SharedPreferences.Editor editor;

    Button signIn;
    EditText name, email, password;
    String name_string, email_string ,password_string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        preference = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = preference.edit();

        name = (EditText) findViewById(R.id.editText3);
        email = (EditText) findViewById(R.id.editText4);
        password = (EditText) findViewById(R.id.editText5);

        signIn = (Button)findViewById(R.id.button3);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name_string = name.getText().toString();
                email_string = email.getText().toString();
                password_string = password.getText().toString();

                if (name_string.isEmpty() || email_string.isEmpty() || password_string.isEmpty()){
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(SignActivity.this);
                    alertDialog.setTitle("Uyarı");
                    alertDialog.setMessage("Alanları eksiksiz doldurunuz.");
                    alertDialog.setCancelable(false);
                    alertDialog.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    alertDialog.show();
                }else {

                    editor.putBoolean("login",true);
                    editor.putString("name",name_string);
                    editor.putString("email",email_string);
                    editor.putString("password", password_string);
                    editor.commit();

                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });

    }
}
