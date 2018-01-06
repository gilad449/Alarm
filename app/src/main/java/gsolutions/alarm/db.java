package gsolutions.alarm;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by גלעד on 12/30/2017.
 */

public class db
{
    FirebaseDatabase database1 = FirebaseDatabase.getInstance();
    DatabaseReference state1 = database1.getReference().child("State");
    public static void main(String[] args)
    {
        do_shit();
    }
    public static void setDone()
    {
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        DatabaseReference state1 = database1.getReference().child("State");
        state1.setValue("done");
    }
    public static void do_shit()
    {
       FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference().child("users");
        DatabaseReference newUsersRef = usersRef.push();
       /*  newUsersRef.setValue(new user( "Gilad", "lev")); */
    }

    public void getUserInfo()
    {
        // get name of the user nand the group he is in
        // user current = new user();
    }
    public void addUser()
    {
        // check if the user exist and if not add
    }
    public void createGroup()
    {
        // creates a new alarm group
    }
    public void createGilad() throws JSONException {
        user current = new user("Gilad" , "Lev");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("/users").push();
        usersRef.setValue(current);
        // newUsersRef.setValue(new user( "Gilad", "lev"));
    }

}

