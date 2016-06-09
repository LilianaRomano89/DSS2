package in.apra.domoticsmartsystem.dss;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main extends AppCompatActivity {
    private Button bottone1;
    private Button bottone2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bottone1 = (Button) findViewById(R.id.bottone1);
        bottone2 = (Button) findViewById(R.id.bottone2);
        //Accedi o Registrati
        View.OnClickListener gestore = new View.OnClickListener() {
            public void onClick(View view) {
                switch(view.getId()) {
                    case R.id.bottone1:
                        Intent openPage1 = new Intent(Main.this,LoginActivity.class);
                        startActivity(openPage1);

                        break;
                    case R.id.bottone2:
                        Intent openPage2 = new Intent(Main.this,RegisterActivity.class);
                        startActivity(openPage2);


                        break;
                }
            }
        };

        bottone1.setOnClickListener(gestore);
        bottone2.setOnClickListener(gestore);

    }
}
