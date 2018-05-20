package training.edu.droidbountyhunter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import training.edu.services.ServicioNotificaciones;

/**
 * @author Giovani Gonzalez
 * Created by darkgeat on 8/31/17.
 */

public class Receiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!ServicioNotificaciones.isRunning()){
            context.startService(new Intent(context,ServicioNotificaciones.class));
        }
    }
}
