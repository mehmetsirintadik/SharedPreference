package mehmet.com.sharedpreference;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Adapter.CustomListViewAdapter;

/**
 * Created by Mehmet on 21.11.2016.
 */

public class CustomListViewActivity extends Activity {

    ArrayList<Users> users = new ArrayList<Users>();
    private CustomListViewAdapter listViewAdapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customlistview);

        getData();
        try{
            listViewAdapter= new CustomListViewAdapter(CustomListViewActivity.this,0,users);
            listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(listViewAdapter);

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),""+ e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }

    private void getData() {
        DbHandler db = new DbHandler(CustomListViewActivity.this);
        List<Users> usersList;
        usersList = db.getAllUsers();
        for (int i = 0; i<usersList.size(); i++){
            Users user = new Users();
            user.setName(usersList.get(i).getName().toString());
            user.setEmail(usersList.get(i).getEmail().toString());
            users.add(user);
        }
    }
}
