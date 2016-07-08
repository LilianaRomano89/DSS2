package in.apra.domoticsmartsystem.dss;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.appspot.domoticsmartsystem.dss.Dss;
import com.appspot.domoticsmartsystem.dss.model.GenericMessagesResponse;
import com.appspot.domoticsmartsystem.dss.model.UserMessageLoginRequest;
import com.appspot.domoticsmartsystem.dss.model.UserMessageLoginResponse;
import com.appspot.domoticsmartsystem.dss.model.UserMessageRegistrationRequest;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static android.Manifest.permission.READ_CONTACTS;

//import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;

/**
 * Schermata di login che permette accesso via e-mail / password, in essa vengono
 * inseriti i dati dell'utente per creare un nuovo account:
 * - Name
 * -LastName
 * -Host
 * -Password
 * -Email
 */
public class RegisterActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserRegisterTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private EditText mNameView;
    private EditText mLastnameView;
    private EditText mHostView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        populateAutoComplete();
        mPasswordView = (EditText) findViewById(R.id.password);
        mNameView = (EditText) findViewById(R.id.name);
        mLastnameView = (EditText) findViewById(R.id.lname);
        mHostView = (EditText) findViewById(R.id.host);

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    attemptRegister();
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }

        getLoaderManager().initLoader(0, null, this);
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    /**
     * Callback ricevuta quando una richiesta di autorizzazioni e' stata completata
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }


    /**
     * Tentativo di Sign in o register dell'account.
     * Se ci sono errori di validita' (e-mail, password,campi mancanti, ecc), non
     * verra' effettuata la registrazione e il tentativo di accesso verra' rifiutato.
     * Se invece la registrazione va' a buon fine, con un Intent, verra' lanciata una
     *nuova activity.
     *
     */
    private void attemptRegister() throws ExecutionException, InterruptedException {
        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);
        mNameView.setError(null);
        mLastnameView.setError(null);
        mHostView.setError(null);
        SharedPreferences mPreferences = getSharedPreferences("filePreference", MODE_PRIVATE);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        String name = mNameView.getText().toString();
        String lname = mNameView.getText().toString();
        String host = mHostView.getText().toString();
        /***********************************/

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(host)) {
            mHostView.setError("This field is required");
            focusView = mHostView;
            cancel = true;
        }

        if (TextUtils.isEmpty(name)) {
            mNameView.setError("This field is required");
            focusView = mNameView;
            cancel = true;
        }

        if (TextUtils.isEmpty(lname)) {
            mLastnameView.setError("This field is required");
            focusView = mLastnameView;
            cancel = true;
        }

        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        if (!isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserRegisterTask(email, password, name, lname, host);
            if (mAuthTask.execute((Void) null).get()) {
                Intent openPage3 = new Intent(RegisterActivity.this, SettingActivity.class);
                startActivity(openPage3);
                SharedPreferences.Editor edit = mPreferences.edit();
                edit.putString("email", email);
                edit.commit();
            }
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.matches("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9\\s]).{7,}");
    }

    /**
     * Mostra l'interfaccia utente e nasconde il modulo di login.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)

    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(RegisterActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
    }

    /**
     * Tale classe permette la registrazione asincrona, per l'autentificazione da parte dell'utente,
     * se il server restituice un valore numeri pari a 200 la registrazione e' accettata restituisce
     * un valore true.
     *
     */
    public class UserRegisterTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;
        private final String mName;
        private final String mLname;
        private final String mHost;
        Dss mDss = new Dss.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null).build();

        UserRegisterTask(String email, String password, String name, String lname, String host) {
            mName = name;
            mLname = lname;
            mEmail = email;
            mPassword = password;
            mHost = host;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            Dss register = new Dss.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null).build();
            Dss.UserApi regapi = register.userApi();
            boolean var = false;
            try {
                GenericMessagesResponse objreg = regapi.register(new UserMessageRegistrationRequest()
                        .setEmail(mEmail)
                        .setPassword(mPassword)
                        .setFirstname(mName)
                        .setLastname(mLname)
                        .setHost(mHost))
                        .execute();
                if (objreg.getStatusCode() == 200) {

                    UserMessageLoginRequest request = new UserMessageLoginRequest();
                    request.setEmail(mEmail);
                    request.setPassword(mPassword);
                    UserMessageLoginResponse response = mDss.userApi().login(request).execute();
                    if (response.getResponse().getStatusCode() == 200) {
                        var = true;
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return var;
        }

        @Override
        protected void onPostExecute(final Boolean var) {
            showProgress(false);
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}

