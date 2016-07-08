package in.apra.domoticsmartsystem.dss.messaging;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.appspot.domoticsmartsystem.dss.Dss;
import com.appspot.domoticsmartsystem.dss.model.GenericMessagesResponse;
import com.appspot.domoticsmartsystem.dss.model.NotificationMessageRegister;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.IOException;


public class MessagingInstanceListenerService extends FirebaseInstanceIdService {

    private static final String TAG = MessagingInstanceListenerService.class.getName();

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is also called
     * when the InstanceID token is initially generated, so this is where
     * you retrieve the token.
     */
    // [START refresh_token]
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedTokenRegId = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedTokenRegId);
        // TODO: Implement this method to send any registration to your app's servers.
        sendRegistrationToServer(refreshedTokenRegId);
    }

    private void sendRegistrationToServer(String tokenRegId) {
        new RegisterForNotificationTask(tokenRegId).execute(null, null, null);
    }

    private class RegisterForNotificationTask extends AsyncTask<Void, Void, Void> {

        String mRegId;

        public RegisterForNotificationTask(String token) {
            mRegId = token;
        }

        @Override
        protected Void doInBackground(Void... params) {

            SharedPreferences pref = getSharedPreferences("filePreference", MODE_PRIVATE);

            Dss dss = new Dss.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null).build();
            Dss.NotificationApi notificationApi = dss.notificationApi();
            NotificationMessageRegister registerReq = new NotificationMessageRegister();
            registerReq.setToken(pref.getString("token", null));
            registerReq.setRegId(mRegId);
            registerReq.setPlatform("android");
            SharedPreferences.Editor edit = pref.edit();
            try {
                GenericMessagesResponse response = notificationApi.register(registerReq).execute();
                if (response.getStatusCode() == 200) {
                    edit.putBoolean("messaging", true);
                } else {
                    edit.putBoolean("messaging", false);
                }
                edit.apply();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

}
