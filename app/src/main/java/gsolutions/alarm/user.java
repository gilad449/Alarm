package gsolutions.alarm;


import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static gsolutions.alarm.MainActivity.group1;


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
    public static void getUserGroup(String uid)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference users = database.getReference("/users/"+uid);
        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                group1 = snapshot.child("group").getValue().toString();
                System.out.println(group1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public static void setUserGroup(String uid,  String group)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.getReference("/users/"+uid+"/group").setValue(group);
        System.out.println("blaaaaaaaaaaaaaaaaaaaaaaalllllllllll"+ group);
    }
    public static void checkExistence(final String uid, final String name)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference users = database.getReference("/users");
        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (!snapshot.child(uid).exists()) {
                    user user = new user(name, "None", uid);
                    user.createUser(user, uid);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
