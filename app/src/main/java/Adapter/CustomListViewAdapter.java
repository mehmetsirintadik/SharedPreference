package Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import mehmet.com.sharedpreference.R;
import mehmet.com.sharedpreference.Users;

/**
 * Created by Mehmet on 21.11.2016.
 */

public class CustomListViewAdapter extends ArrayAdapter<Users> {
    private Activity activity;
    private List<Users> usersList;
    Users user;

    public CustomListViewAdapter(Activity activity, int resource, List<Users> users) {
        super(activity, resource, users);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView  == null){
            convertView = inflater.inflate(R.layout.item_listview, parent, false );
            viewHolder =new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        user = usersList.get(position);
        viewHolder.name.setText(user.getName());
        viewHolder.email.setText(user.getEmail());

        Picasso.with(activity).load("http://nulm.gov.in/images/user.png").into(viewHolder.image);
        return convertView;

    }

    private static class ViewHolder {
        private ImageView image;
        private TextView name;
        private TextView email;

        public ViewHolder(View v) {
            image = (ImageView) v.findViewById(R.id.image);
            name = (TextView) v.findViewById(R.id.name1);
            email =(TextView) v.findViewById(R.id.email1);


        }
    }
}
