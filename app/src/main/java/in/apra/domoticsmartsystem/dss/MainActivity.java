package in.apra.domoticsmartsystem.dss;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.appspot.domoticsmartsystem.dss.Dss;
import com.appspot.domoticsmartsystem.dss.model.GenericMessagesResponse;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

/**
 * La classe permette all'utente di effettuare il Login o la Registrazione
 * se l'utente e' gia loggato non necessita di effettuare nuovamente il login
 * pertanto puo' effettuare il Logout.
 */
public class MainActivity extends AppCompatActivity {
    private Button bottone1;
    private Button bottone2;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bottone1 = (Button) findViewById(R.id.bottone1);
        bottone2 = (Button) findViewById(R.id.bottone2);
        //Accedi o Registrati
        View.OnClickListener gestore = new View.OnClickListener() {
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.bottone1:
                        Intent openPage1 = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(openPage1);

                        break;
                    case R.id.bottone2:
                        Intent openPage2 = new Intent(MainActivity.this, RegisterActivity.class);
                        startActivity(openPage2);


                        break;
                }
            }
        };

        bottone1.setOnClickListener(gestore);
        bottone2.setOnClickListener(gestore);

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preference = getSharedPreferences("filePreference", MODE_PRIVATE);

        token = preference.getString("token", null);
        if (token != null) {
            new MainTask().execute();
        }


    }

    public class MainTask extends AsyncTask<Void, Void, Boolean> {

        private boolean res = false;


        @Override
        protected Boolean doInBackground(Void... voids) {
            Dss request = new Dss.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null).build();

            try {
                GenericMessagesResponse req = request.userApi().validateUser(token).execute();

                if (req.getStatusCode() == 200) {
                    res = true;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }


            return res;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean) {
                Intent page = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(page);
            }
        }
    }
}
