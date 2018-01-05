package gsolutions.alarm;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login_activity extends AppCompatActivity {
    public FirebaseAuth mAuth;
    private TextView status;
    public CallbackManager callbackManager;
    public LoginButton loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        // Auth section
        mAuth = FirebaseAuth.getInstance();
        status =  (TextView) findViewById(R.id.status);
        callbackManager = CallbackManager.Factory.create();
        System.out.println("was here");
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
                Log.i("addddmatttttttai", "gggggggggggggreaaaaaaaaaat");
            }

            @Override
            public void onCancel() {
                Log.i("addddmatttttttai", "gggggggggggggreaaaaaaaaaat");
            }

            @Override
            public void onError(FacebookException exception) {
                Log.i("addddmatttttttai", "gggggggggggggreaaaaaaaaaat");
            }
        });


    }
    @Override
    public void onStart()
    {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        Log.i("addddmatttttttai", "la");
        status.setText("lo");
        checkStatus();

    }
    private void checkStatus()
    {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null)
        {
            Log.i("addddmatttttttai", "gggggggggggggreaaaaaaaaaat");
            finish();
        }
    }
    public void handleFacebookAccessToken(AccessToken token) {
        Log.d("bluw", "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Awesome", "signInWithCredential:success");
                            toastIt("Successfuly signed in");
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            toastIt("The sign in failed");
                            Log.w("regret", "signInWithCredential:failure", task.getException());
                        }
                    }
                });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Pass the activity result back to the Facebook SDK
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    public void toastIt( String text)
    {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
