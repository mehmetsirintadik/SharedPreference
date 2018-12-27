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

import java.util.prefs.Preferences;

/**
 * Created by Mehmet on 2.11.2016.
 */

public class LoginActivity extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    Button sign, login;
    EditText mail, password;

    String mail_string, password_string;
    Button loginWithDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = preferences.edit();

        if (preferences.getBoolean("login",false)){
            Intent i =new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
            finish();
        }

        mail = (EditText)findViewById(R.id.txtMail);
        password = (EditText)findViewById(R.id.txtPassword);

        login = (Button)findViewById(R.id.btnLogin);
        sign = (Button)findViewById(R.id.btnKayit);
        loginWithDb = (Button)findViewById(R.id.btnDbLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mail_string = mail.getText().toString();
                password_string = password.getText().toString();

                if (mail_string.isEmpty() || password_string.isEmpty()){
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(LoginActivity.this);
                    alertDialog.setTitle("Uyarı");
                    alertDialog.setMessage("Lütfen alanları eksiksiz doldurunuz!");
                    alertDialog.setCancelable(false);
                    alertDialog.setPositiveButton("Kapat", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    alertDialog.show();
                }else {
                    String email = preferences.getString("email","");
                    String password = preferences.getString("password","");

                    if (email.matches(mail_string) && password.matches(password_string)){
                        editor.putBoolean("login",true);
                        Intent i = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(i);
                        finish();
                    }else {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(LoginActivity.this);
                        alertDialog.setTitle("Hata");
                        alertDialog.setMessage("Şifre veya mail yanlış girildi.");
                        alertDialog.setCancelable(false);
                        alertDialog.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        alertDialog.show();
                    }
                }
            }
        });

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SignActivity.class);
                startActivity(i);
            }
        });

        loginWithDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHandler db = new DbHandler(LoginActivity.this);
                mail_string = mail.getText().toString();
                password_string = password.getText().toString();

                Users user = new Users();
                if (db.getUser(mail_string)!=null){
                    user = db.getUser(mail_string);
                    System.out.println(mail_string + user.getEmail() + password_string + user.getPassword());
                    if (!user.getEmail().equals("null") || !user.getPassword().equals("null")){
                        if(user.getEmail().equals(mail_string) && user.getPassword().equals(password_string)){
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(i);
                            finish();
                        }else{
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(LoginActivity.this);
                            alertDialog.setTitle("Hata");
                            alertDialog.setMessage("Mailiniz veya Şifreniz Uyuşmuyor!");
                            alertDialog.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            alertDialog.show();
                        }
                    }

                }
            }
        });
    }

}
