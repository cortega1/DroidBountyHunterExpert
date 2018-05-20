package training.edu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import training.edu.droidbountyhunter.R;
import training.edu.interfaces.OnLogListener;

/**
 * @author Giovani Gonzalez
 * Created by darkgeat on 29/08/2017.
 */

public class DetalleLogEliminacion extends Fragment implements OnLogListener{

    private boolean isTablet = false;
    private TextView statusTxt;
    private TextView dateTxt;
    private String status;
    private String date;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isTablet = getActivity().getIntent().getBooleanExtra("isTablet",false);
        if (!isTablet){
            status = getActivity().getIntent().getStringExtra("status");
            date = getActivity().getIntent().getStringExtra("date");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle_log_de_eliminacion,container,false);

        statusTxt = (TextView)view.findViewById(R.id.txtEstatus);
        dateTxt = (TextView)view.findViewById(R.id.txtFecha);
        if (!isTablet){
            UpdateData();
        }

        return view;
    }

    private void UpdateData() {
        if (status != null || date != null) {
            statusTxt.setText(status.equals("0") ? "Fugitivo" : "Atrapado");
            dateTxt.setText(date);
        }
    }

    @Override
    public void OnLogItemList(String date, String status) {
        this.status = status;
        this.date = date;
        UpdateData();
    }
}
