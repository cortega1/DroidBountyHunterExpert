package training.edu.services;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FirebaseIdService extends FirebaseInstanceIdService {
    private final static String TAG = FirebaseIdService.class.getSimpleName();

    @Override
    public void onTokenRefresh(){
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Token: " + refreshedToken);
    }
}
