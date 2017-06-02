package sg.edu.rp.c347.p07_smsretriever;


import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.PermissionChecker;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentFirst extends Fragment {
    TextView textViewSMS;
    EditText etNumber;
    Button btnRetrieve1;
    TextView tv;


    public FragmentFirst() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        textViewSMS = (TextView) view.findViewById(R.id.textViewSMS);
        etNumber = (EditText) view.findViewById(R.id.etNumber);

        return view;
    }
}
