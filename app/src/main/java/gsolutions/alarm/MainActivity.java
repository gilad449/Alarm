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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    FirebaseAuth mAuth;
    Button button1;
    public static  MediaPlayer mp;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Let's take a look at Toast and Log in action
        // TODO: admati
        mAuth = FirebaseAuth.getInstance();
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);
        mp = null;
        checkConnection();
        checkStatus();
        Log.i("info", "Done creating the app");
    }
    private void checkStatus()
    {
        Intent intent = new Intent(this, login_activity.class);
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null)
        {
            Log.i("addddmatttttttai", "gggggggggggggreaaaaaaaaaat");
            startActivity(intent);
        }
    }
    @Override
    public void onStart()
    {
        super.onStart();
        checkStatus();
    }

    public void toastIt( String text)
    {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
//////////////
    protected void onStop()
    {
        super.onStop();
        setDone();
        Toast.makeText(this, "stoooooooooooooooooooooop", Toast.LENGTH_SHORT).show();
    }
    public void startSound()
    {
        mp.start();
        Toast.makeText(this, "Started", Toast.LENGTH_SHORT).show();
    }
    public void pauseSound()
    {
        mp.pause();
        Toast.makeText(this, "paused", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.button1:
                if (mp.isPlaying())
                {
                    setFalse();
                }
                else
                {
                    setTrue();
                }
                break;
            case R.id.exit:
                signOut(mAuth);
                break;
        }
    }
    public void signOut(FirebaseAuth mAuth) {
        toastIt("YOU HAVE SIGNED OUT");
        Log.i("", "SSSSSSSSIGNED THE FUCK OUT");
        mAuth.signOut();
        LoginManager.getInstance().logOut();
        Intent intent = new Intent(this, login_activity.class);
        startActivity(intent);
    }
    protected void setFalse()
    {
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        DatabaseReference state1 = database1.getReference().child("State");
        state1.setValue("false");
    }
    protected void setTrue()
    {
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        DatabaseReference state1 = database1.getReference().child("State");
        state1.setValue("true");
    }
    protected void setDone()
    {
        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        DatabaseReference state1 = database1.getReference().child("State");
        state1.setValue("done");
    }
    protected boolean isOnline()
    {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public void checkConnection()
    {
        if(!isOnline())
        {
            Toast.makeText(MainActivity.this, "You are not connected to the Internet", Toast.LENGTH_SHORT).show();
            finish();
            Log.i("info", "No connection to the internet");
        }

    }
    ValueEventListener valueEventListener = myRef.child("State").addValueEventListener(new ValueEventListener()
    {@Override
    public void onDataChange(DataSnapshot dataSnapshot)
    {
        if (mp == null)
        {
            mp = MediaPlayer.create(getApplicationContext(), R.raw.siren);
        }
        mp.setOnErrorListener(new MediaPlayer.OnErrorListener()
        {
            public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                mp = MediaPlayer.create(getApplicationContext(), R.raw.siren);
                return false;
            }
        });
        String value = dataSnapshot.getValue(String.class);
        Log.d("blaaa", "Value is: " + value);
        if(value.equals("true"))
        {
            Log.i("info", "Played it");
            startSound();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
            {
                public void onCompletion(MediaPlayer mp)
                {
                    Log.i("info", "done done");
                    setDone();
                }
            });
        }
        else if(value.equals("false"))
        {
            Log.i("info", "Stopped  it");
            pauseSound();
        }
        else
        {
            mp.pause();
        }
    }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });

}

