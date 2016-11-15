package mehmet.com.sharedpreference;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by Mehmet on 8.11.2016.
 */

public class DbHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "Users";
    private static final String TABLE_USERS = "UsersTable";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAİL = "email";
    private static final String KEY_PASSWORD = "password";

    public DbHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String createUsersTable = "CREATE TABLE " + TABLE_USERS + "(" + KEY_NAME + " text," + KEY_EMAİL + " text,"+ KEY_PASSWORD + " text"+ ")";

        db.execSQL(createUsersTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db  , int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXIST UsersTable");
        onCreate(db);
    }

    public void addUser(Users user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getName());
        values.put(KEY_EMAİL, user.getEmail());
        values.put(KEY_PASSWORD, user.getPassword());

        db.insert(TABLE_USERS, null, values);
        db.close();

    }
    public Users getUser(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, new String[]{KEY_NAME, KEY_EMAİL, KEY_PASSWORD}, KEY_EMAİL +"=?" , new String[]{String.valueOf(email)},null,null,null);

        if (cursor.getCount() !=0){
            cursor.moveToFirst();
            Users users = new Users(cursor.getString(0), cursor.getString(1), cursor.getString(2));
            return users;
        }
        return null;
    }

    public int updateUser(Users user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("KEY_NAME", user.getName());
        values.put("KEY_EMAİL", user.getEmail());
        values.put("KEY_PASSWORD", user.getPassword());

        return db.update(TABLE_USERS, values, KEY_EMAİL + "=?", new String[]{String.valueOf(user.getEmail())});

    }
    public void deleteUser(Users user){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, KEY_EMAİL + "=?", new String[]{String.valueOf(user.getEmail())});
    }
}
