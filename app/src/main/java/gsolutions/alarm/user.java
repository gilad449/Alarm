package gsolutions.alarm;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by גלעד on 1/5/2018.
 */

public class user {
    public String name;
    public String group;
    public String uid;
    user() {
        // empty constructor required by jackson
    }
    user(String name, String group, String uid) {
        this.name = name;
        this.group = group;
        this.uid = uid;
    }
    public void setUserGroup(String group)
    {
        this.group = group;
    }
    public static DatabaseReference getUserRef(String uid)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("/users").child(uid);
        return usersRef;
    }
    public static void createUser(user user , String uid)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("/users").child(uid);
        usersRef.setValue(user);
    }
}
