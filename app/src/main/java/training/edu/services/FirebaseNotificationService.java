package training.edu.services;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import training.edu.droidbountyhunter.Home;
import training.edu.droidbountyhunter.R;
import training.edu.utils.NotifyManager;

public class FirebaseNotificationService extends FirebaseMessagingService {
    private static final String TAG = FirebaseNotificationService.class.getSimpleName();

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage){
        Log.v(TAG, "From: " + remoteMessage.getFrom());
        Log.v(TAG, "Notification message body: " + remoteMessage.getNotification().getBody());

        NotifyManager manager = new NotifyManager();
        manager.enviarNotificacion(this, Home.class, remoteMessage.getNotification().getBody(), "Notificacion Push", R.mipmap.ic_launcher, 0);
    }
}
