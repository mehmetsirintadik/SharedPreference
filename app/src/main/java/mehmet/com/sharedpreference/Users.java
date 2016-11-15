package mehmet.com.sharedpreference;

/**
 * Created by Mehmet on 7.11.2016.
 */

public class Users {
    private String name;
    private String email;
    private String password;

    public Users() {
        super();
    }

    public Users(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
