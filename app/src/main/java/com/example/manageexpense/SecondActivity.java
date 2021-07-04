package com.example.manageexpense;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.api.services.sheets.v4.SheetsScopes;

public class SecondActivity extends AppCompatActivity {

    TextView name;
    Button getAllSheetButton;
    Button logout;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        name = findViewById(R.id.textView);
        getAllSheetButton = findViewById(R.id.button);
        logout = findViewById(R.id.logout);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestScopes(new Scope(SheetsScopes.SPREADSHEETS))
                .requestScopes(new Scope("https://www.googleapis.com/auth/drive.file"))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        //Logout
        logout.setOnClickListener((View v) -> {
            switch (v.getId()){
                case R.id.logout:
                    logout();
                    break;
            }
        });

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);

        if (acct != null) {
            String personName = acct.getDisplayName();

            name.setText(personName);
            getAllSheetButton.setOnClickListener((View v) -> {
                Toast.makeText(this, "I am touched", Toast.LENGTH_SHORT).show();
//                getAllSheetIds();
            });
        }
    }

    private void getAllSheetIds(){

    }

    private void logout(){
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        finish();
                        Toast.makeText(getApplicationContext(), "Logout successfully", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
