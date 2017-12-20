package a0817moact03c_2.a0817moact03c_02.View.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import a0817moact03c_2.a0817moact03c_02.Model.Usuario;
import a0817moact03c_2.a0817moact03c_02.R;
import a0817moact03c_2.a0817moact03c_02.View.MainActivity;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private GoogleApiClient googleApiClient;
    CallbackManager callbackManager;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        ImageView imageView = (ImageView) findViewById(R.id.imageviewlogin);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();



        Glide.with(getApplicationContext()).load("https://i.kinja-img.com/gawker-media/image/upload/t_original/ljtry40abluorsmb7hnh.gif").into(imageView);


        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
        loginButton.setReadPermissions("public_profile");

        //FacebookSdk.sdkInitialize(getApplicationContext());
        // AppEventsLogger.activateApp(this);
        mAuth = FirebaseAuth.getInstance();


        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in


                    Log.d("algo", "onAuthStateChanged:signed_in:" + user.getUid());
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));



                } else {
                    // User is signed out
                    Log.d("algo", "onAuthStateChanged:signed_out");

                }
                // ...
            }
        };

        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("Algo", "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());





                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }

            @Override
            public void onCancel() {
                Log.d("Algo", "facebook:onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("Algo", "facebook: onError", error);
                //...

            }
        });
        SignInButton signInButton = (SignInButton)findViewById(R.id.googleSingin);
        signInButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent,777);
            }
        });
    }








    private void handleFacebookAccessToken(AccessToken token) {
        Log.d("Algo", "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Algo", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Algo", "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }




    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 777){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);

        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if(result.isSuccess()){
            firebaseAuthWitchGoogle(result.getSignInAccount());
            goMainScreen();
        }else{
            Toast.makeText(getApplicationContext(),"error al iniciar sesion",Toast.LENGTH_SHORT).show();
        }

    }

    private void firebaseAuthWitchGoogle(GoogleSignInAccount signInAccount) {
        AuthCredential credential = GoogleAuthProvider.getCredential(signInAccount.getIdToken(),null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

            }
        });

    }

    private void goMainScreen() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}

