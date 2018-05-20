package training.edu.droidbountyhunter;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import training.edu.data.DBProvider;

/**
 * @author Giovani Gonzalez
 * Created by darkgeat on 27/08/2017.
 */

public class LogEliminacion extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_log_eliminacion);
        setTitle("Log de Eliminación");
    }
}
