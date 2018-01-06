package gsolutions.alarm;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by גלעד on 1/6/2018.
 */

public class group {
    public String state = "done";
    group()
    {
        // Fucking required by firebase
    }
    public static void createGroup(String name)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("/groups").child(name);
        usersRef.setValue(new group());
    }
    public void getGroups()
    {
      /*  FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("/users").child(uid); */

    }

}
