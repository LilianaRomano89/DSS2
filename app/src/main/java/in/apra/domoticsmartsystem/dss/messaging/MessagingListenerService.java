package in.apra.domoticsmartsystem.dss.messaging;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import in.apra.domoticsmartsystem.dss.R;
import in.apra.domoticsmartsystem.dss.SettingActivity;


public class MessagingListenerService extends FirebaseMessagingService {

    public static final int MESSAGE_NOTIFICATION_ID = 4321;

    @Override
    public void onMessageReceived(RemoteMessage message) {
        createNotification(message.getNotification());
    }

    // Creates notification based on title and body received
    private void createNotification(RemoteMessage.Notification notification) {
        Context context = getBaseContext();

        Intent settingsIntent = new Intent(MessagingListenerService.this, SettingActivity.class);
        PendingIntent pendingIntent = PendingIntent.getService(MessagingListenerService.this, MESSAGE_NOTIFICATION_ID, settingsIntent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher).setContentTitle(notification.getTitle())
                .setContentText(notification.getBody())
                .setContentIntent(pendingIntent);

        NotificationManager mNotificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(MESSAGE_NOTIFICATION_ID, mBuilder.build());
    }

}
