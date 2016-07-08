package in.apra.domoticsmartsystem.dss;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.appspot.domoticsmartsystem.dss.Dss;
import com.appspot.domoticsmartsystem.dss.model.NotificationMessageUnregister;
import com.appspot.domoticsmartsystem.dss.model.SensorMessageAlarmActionRequest;
import com.appspot.domoticsmartsystem.dss.model.SensorMessageSampleItem;
import com.appspot.domoticsmartsystem.dss.model.SensorMessageSensorItem;
import com.appspot.domoticsmartsystem.dss.model.SensorMessageStateResponse;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.firebase.iid.FirebaseInstanceId;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;

import in.apra.domoticsmartsystem.dss.messaging.MessagingInstanceListenerService;

/**
 * In questa classe verra' mostrato il grafico con i valori
 * Temperatura ed Umidita' dell'ambiente in cui i sensori
 * sono stati posti.
 * E due bottoni che permettono di attivare/disattivare
 * l'allarme per il Gas o di Intrusione, cioe' rileva il movimento.
 */
public class SettingActivity extends AppCompatActivity {

    SharedPreferences mPreferences;
    private Dss mDss;
    private String mToken;
    private List<SensorMessageSampleItem> mSensorList;
    private ToggleButton mGasBtn;
    private ToggleButton mIntrusionBtn;
    private BarChart mChart;
    private AtomicBoolean status;

    private int mInitState;

    private CompoundButton.OnCheckedChangeListener mOnSensorAlarmState = new CompoundButton.OnCheckedChangeListener() {
        /**
         * Tale metodo  controlla lo stato degli allarmi,e li setta a True o false
         * in base alla scelta da parte dell'utente.
         * @param buttonView
         * @param isChecked
         */
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            // If first time, we don't required an action
            // it is only a state settings
            if (mInitState < 2) {
                return;
            }

            if (status.get()) {
                status.set(false);
                return;
            }

            SetAlarmTask setAlarm = new SetAlarmTask();

            switch (buttonView.getId()) {
                case R.id.gas_state_btn:
                    setAlarm.setSensor(getString(R.string.gas_state_name));
                    setAlarm.setState(mGasBtn.isChecked());
                    break;

                case R.id.intrusion:
                    setAlarm.setSensor(getString(R.string.intrusion_state_name));
                    setAlarm.setState(mIntrusionBtn.isChecked());
                    break;
            }
            status.set(true);
            setAlarm.execute(null, null);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        mPreferences = getSharedPreferences("filePreference", MODE_PRIVATE);

        mToken = mPreferences.getString("token", null);

        mGasBtn = (ToggleButton) findViewById(R.id.gas_state_btn);
        mIntrusionBtn = (ToggleButton) findViewById(R.id.intrusion);

        mSensorList = new ArrayList<>();
        mChart = (BarChart) findViewById(R.id.chart);

        mDss = new Dss.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null).build();
        status = new AtomicBoolean(false);

        mGasBtn.setOnCheckedChangeListener(mOnSensorAlarmState);
        mIntrusionBtn.setOnCheckedChangeListener(mOnSensorAlarmState);

        mChart.setDescription(getString(R.string.chart_name));
        mChart.animateXY(3000, 3000);

        mInitState = 0;
    }

    /**
     * Tale metodo permette di effettuare il Logout dell'utente,
     * e riportarlo nell'activity iniziale in cui puo' effettuare
     * nuovamente il Login o una nuova registrazione.
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private void logout() throws ExecutionException, InterruptedException {
        mPreferences.edit().clear().commit();
        new UnregisterNotificationTask().execute().get();
        Intent page1 = new Intent(SettingActivity.this, MainActivity.class);
        startActivity(page1);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        try {
            logout();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        FirebaseInstanceId.getInstance().getToken();
        Intent intent = new Intent(SettingActivity.this, MessagingInstanceListenerService.class);
        startService(intent);
        new GetSensorTask().execute(null, null);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * In questo metodo viene disegnato il grafico Temperatura ed umidita',
     * sull' asse X sono rappresentate le ore relative al rilevamento dell'umidita'
     * e Temperatura prensente nell'ambiente in cui sono posti i sensori.
     * La "bar red" rappresenta la Temperatura, la "bar blu" l'umidita', e i valori
     * ad essi assegnati si ottengono invocando opportuni metodi, convertiti poi in formato floatValue() .
     */
    private void buildChart() {
        int count = 0;
        List<String> xAxis = new ArrayList<>();
        List<BarDataSet> mDataSets = new ArrayList<>();
        List<BarEntry> mTemperatureValue = new ArrayList<>();
        List<BarEntry> mHumidityValue = new ArrayList<>();

        for (int i = 0; i < mSensorList.size(); i = i + 4) {
            SensorMessageSampleItem item = mSensorList.get(i);
            Date date = new Date(item.getCreationTime().getValue());
            SimpleDateFormat sDate = new SimpleDateFormat("HH", Locale.getDefault());
            xAxis.add(sDate.format(date));
            BarEntry temperature = new BarEntry(item.getTemperature().floatValue(), count);
            BarEntry humidity = new BarEntry(item.getHumidity().floatValue(), count++);
            mTemperatureValue.add(temperature);
            mHumidityValue.add(humidity);
        }

        BarDataSet barDataSet1 = new BarDataSet(mTemperatureValue, getString(R.string.temperature));
        barDataSet1.setColor(Color.RED);
        BarDataSet barDataSet2 = new BarDataSet(mHumidityValue, getString(R.string.humidity));
        barDataSet2.setColor(Color.BLUE);

        mDataSets.add(barDataSet1);
        mDataSets.add(barDataSet2);
        BarData data = new BarData(xAxis, mDataSets);
        mChart.setData(data);
        mChart.invalidate();
    }

    @Override
    public void onBackPressed() {
        return;
    }

    private class UnregisterNotificationTask extends AsyncTask<Void, Void, Boolean> {

        NotificationMessageUnregister unregister = new NotificationMessageUnregister();

        @Override
        protected Boolean doInBackground(Void... voids) {
            try {
                mDss.notificationApi().unregister(unregister).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /**
     * In questa classe vengono restituiti gli stati dei ToggleButton relativi ai due allarmi,
     * e precedentemente settati.
     * in caso di fallimento restituisce un messaggio di errore.
     */
    class GetSensorTask extends AsyncTask<Void, Void, SensorMessageSensorItem> {

        @Override
        protected SensorMessageSensorItem doInBackground(Void... params) {
            mInitState = 0;

            Dss.SensorApi request = mDss.sensorApi();

            SensorMessageSensorItem res = null;
            try {
                SensorMessageStateResponse message = request.sensorState(mToken).execute();
                if (message.getResponse().getStatusCode() == 200) {
                    mSensorList.clear();
                    mSensorList.addAll(message.getSamples());
                    res = message.getSensor();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return res;
        }

        @Override
        protected void onPostExecute(SensorMessageSensorItem res) {
            super.onPostExecute(res);
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(SettingActivity.this);
            if (res != null) {
                mGasBtn.setChecked(res.getGasState());
                mInitState++;
//                if (res.getGasState()) {
//                    mGasBtn.setTextOn("ON");
//                } else {
//                    mGasBtn.setTextOff("OFF");
//                }

                mIntrusionBtn.setChecked(res.getIntrusionState());
                mInitState++;
//                if (res.getIntrusionState()) {
//                    mIntrusionBtn.setTextOn("ON");
//                } else {
//                    mIntrusionBtn.setTextOff("OFF");
//                }

                buildChart();
            } else {
                alertDialog.setMessage("Error");

            }
        }
    }

    /**
     * In questa classe vengono preparati i due allarmi per essere settati.
     */
    class SetAlarmTask extends AsyncTask<Void, Void, Boolean> {

        private String mSensorType;
        private boolean mSensorState;

        @Override
        protected Boolean doInBackground(Void... params) {
            Dss.SensorApi alarm = mDss.sensorApi();
            SensorMessageAlarmActionRequest action = new SensorMessageAlarmActionRequest();
            action.setAction(mSensorState);
            action.setSensor(mSensorType);
            action.setToken(mToken);
            boolean res = false;

            try {
                if (alarm.alarmAction(action).execute().getStatusCode() == 200) {
                    res = true;
                    status.set(false);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return res;
        }

        public void setSensor(String s) {
            mSensorType = s;
        }

        public void setState(boolean b) {
            mSensorState = b;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            super.onPostExecute(success);

            if (success) {
//                if (mSensorType.equals(getString(R.string.gas_state_name))) {
//                    if (mSensorState) {
//                        mGasBtn.setChecked(true);
//                        mGasBtn.setTextOff("ON");
//                    } else {
//                        mGasBtn.setChecked(false);
//                        mGasBtn.setTextOn("OFF");
//                    }
//                } else {
//                    if (mSensorState) {
//                        mIntrusionBtn.setChecked(true);
//                        mIntrusionBtn.setTextOff("ON");
//                    } else {
//                        mIntrusionBtn.setChecked(false);
//                        mIntrusionBtn.setTextOn("OFF");
//                    }
//                }
            } else {
                AlertDialog.Builder alert = new AlertDialog.Builder(SettingActivity.this);
                alert.setMessage("Error with '" + mSensorType + "' alarm state");
                alert.show();
            }


        }

    }

}




