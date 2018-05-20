package training.edu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import training.edu.data.DBProvider;
import training.edu.droidbountyhunter.*;
import training.edu.interfaces.OnLogListener;

/**
 * @author Giovani Gonzalez
 * Created by darkgeat on 8/29/17.
 */

public class LogEliminacion extends Fragment {

    private ArrayAdapter<String> adaptador;
    private ArrayList<String[]> logs;
    private OnLogListener listener;
    private boolean isTablet = false;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isTablet = getActivity().getSupportFragmentManager().findFragmentById(R.id.fragmentDetalleLogEliminacion) != null;
        if (isTablet) {
            listener = (OnLogListener) getActivity().getSupportFragmentManager().findFragmentById(R.id.fragmentDetalleLogEliminacion);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_log_eliminacion, container, false);

        DBProvider database = new DBProvider(getContext());
        logs = database.ObtenerLogsEliminacion();
        String[] logsRow = new String[logs.size()];
        for (int index = 0 ; index < logs.size() ; index++){
            logsRow[index] = logs.get(index)[0] + " --> " + logs.get(index)[1];
        }
        ListView lista = (ListView) view.findViewById(R.id.list);
        adaptador = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,logsRow);
        lista.setAdapter(adaptador);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String date = logs.get(position)[1];
                String status = logs.get(position)[2];
                Toast.makeText(getContext(), String.valueOf(position) + " " + adaptador.getItem(position),Toast.LENGTH_LONG).show();
                if (isTablet){
                    listener.OnLogItemList(date,status);
                }else {
                    Intent intent = new Intent(getContext(), training.edu.droidbountyhunter.DetalleLogEliminacion.class);
                    intent.putExtra("isTablet",isTablet);
                    intent.putExtra("status",status);
                    intent.putExtra("date",date);
                    startActivity(intent);
                }
            }
        });

        return view;
    }
}
