package aru.navigationdrawar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;

public class HomeActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 007;
    private SignInButton mSignInButton;
    private ProgressDialog mProgressDialog;
    public static String personName,personPhotoUrl,email;
    public static boolean isSet = false;
    public static GoogleApiClient mGoogleApiClient;
    SecureSharedPreference pref;
    GoogleSignInResult result;
    Intent mIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mSignInButton = (SignInButton)findViewById(R.id.Gsigning);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, (GoogleApiClient.OnConnectionFailedListener) this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();
        mSignInButton.setSize(SignInButton.SIZE_STANDARD);
        mSignInButton.setScopes(gso.getScopeArray());
        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mGoogleApiClient.isConnected()){
                    signOut();
                    Log.e("aaaaaaaaaaaaa","aaaaaaaaaaaaa");
                    Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                    startActivityForResult(signInIntent, RC_SIGN_IN);
                }
            }
        });
        Bundle mBundle = getIntent().getExtras();
        if(mBundle != null){
            if(mBundle.getString("logout") != null){
                Log.e("Logout","Logout");
                signOut();
            }
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
           // Log.e("onActivityResult","onActivityResult");
            handleSignInResult(result);
        }
    }
    public void handleSignInResult(GoogleSignInResult result){
      //  Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if(result.isSuccess()){
            GoogleSignInAccount acct = result.getSignInAccount();
           // Log.e(TAG, "display name: " + acct.getDisplayName());
            personName = acct.getDisplayName();
            personPhotoUrl = acct.getPhotoUrl().toString();
            email = acct.getEmail();
            isSet = true;
           // Log.e(TAG, "Name: " + personName + ", email: " + email
                  //  + ", Image: " + personPhotoUrl);
            SecureSharedPreference.setPreference("UserName",personName,HomeActivity.this);
            SecureSharedPreference.setPreference("email",email,HomeActivity.this);
            SecureSharedPreference.setPreference("personalphotourl",personPhotoUrl,HomeActivity.this);
            Intent mIntent = new Intent(HomeActivity.this,MainActivity.class);
            startActivity(mIntent);

        }
    }
    @Override
    public void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
           // Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
           // handleSignInResult(result);
        } else {
             showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    hideProgressDialog();
                  //  handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }
        mProgressDialog.show();
    }
    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }
    public  void signOut() {
       try{
               Auth.GoogleSignInApi.signOut(mGoogleApiClient);
               Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient);
               mGoogleApiClient.disconnect();
               Log.e("LogoutException111","LogoutException111");
               SecureSharedPreference.deletePref(HomeActivity.this);
       }catch(IllegalStateException e){
           Log.e("LogoutException","LogoutException");
           e.printStackTrace();
       }

    }

}
