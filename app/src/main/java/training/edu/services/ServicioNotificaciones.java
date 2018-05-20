package training.edu.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import training.edu.data.DBProvider;
import training.edu.droidbountyhunter.Home;
import training.edu.droidbountyhunter.R;
import training.edu.models.Fugitivo;
import training.edu.utils.NotifyManager;

/**
 * @author Giovani Gonzalez
 * Created by darkgeat on 8/30/17.
 */

public class ServicioNotificaciones extends Service {

    private static ServicioNotificaciones instance = null;
    private Timer timer;

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this,"Servicio Creado",Toast.LENGTH_SHORT).show();
        instance = this;
    }

    public void EnviarNotificacion(){
        try {
            String mensaje = "";
            DBProvider database = new DBProvider(this);
            ArrayList<Fugitivo> fugitivosSinNotificar = database.ObtenerFugitivosNotificacion();
            ArrayList<String[]> logsSinNotificar = database.ObtenerLogsNotificacion();
            int added = fugitivosSinNotificar.size();
            int deleted = logsSinNotificar.size();
            if (added > 0){
                mensaje += "Añadiste " + added;
                if (deleted > 0){
                    mensaje += ", Eliminaste " + deleted;
                }
            }else if (deleted > 0){
                mensaje += "Eliminaste " + deleted;
            }else {
                mensaje = "";
            }
            if (mensaje.length() > 0){
                // Se crea una Notificacion
                NotifyManager manager = new NotifyManager();
                manager.enviarNotificacion(this, Home.class, mensaje, "Notificacion DroidBountyHunter", R.mipmap.ic_launcher,0);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static boolean isRunning(){
        return instance != null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this,"Servicio Arrancado " + startId,Toast.LENGTH_SHORT).show();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                EnviarNotificacion();
            }
        },0,1000 * 60);
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this,"Servicion detenido",Toast.LENGTH_SHORT).show();
        instance = null;
    }
}
