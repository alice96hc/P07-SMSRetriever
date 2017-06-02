package sg.edu.rp.c347.p07_smsretriever;


import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static android.widget.Toast.LENGTH_SHORT;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentFirst extends Fragment {
    TextView textViewSMS;
    EditText etNumber;
    TextView tvSMS;
    Button btnRetrieve;


    public FragmentFirst() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_first, container, false);

        textViewSMS = (TextView) view.findViewById(R.id.textViewSMS);
        etNumber = (EditText) view.findViewById(R.id.etNumber);
        tvSMS = (TextView) view.findViewById(R.id.tvSMS);
        btnRetrieve = (Button) view.findViewById(R.id.btnRetrieve);

        btnRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS);
                if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_SMS}, 0);
                    //stop the action from proceeding further as permission not granted yet
                    return;
                }

                //create all message URI
                Uri uri = Uri.parse("content://sms");
                //the columns we want
                //date is when the message took place
                //address is the number of the other party
                //body is the message content
                //type 1 is received, type 2 sent
                String[] reqCols = new String[]{"date", "address", "body", "type", "time"};

                //get content Resolver object from which to
                //query the content provider
                ContentResolver cr = getActivity().getContentResolver();

                //the filter string
                String filter = "address LIKE ?";
                //the matches for the ?
                String[] filterArgs = {"" + "%" + textViewSMS + "%"};

                //fetch SMS message from built-in content provider
                Cursor cursor = cr.query(uri, reqCols, null, null, null);

                String smsBody = "";
                if (cursor.moveToFirst()) {
                    do {
                        long dateInMillis = cursor.getLong(0);
                        String date = (String) DateFormat.format("dd MMM yyyy h:mm:ss aa", dateInMillis);
                        String address = cursor.getString(1);
                        String body = cursor.getString(2);
                        String type = cursor.getString(3);
                        if (type.equalsIgnoreCase("1")) {
                            type = "Inbox:";
                        } else {
                            type = "at ";
                        }
                        smsBody += type + " " + address + "\n at " + date + "\n\"" + body + "\"\n\n";

                    } while (cursor.moveToNext());
                }
                tvSMS.setText(smsBody);
            }

        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

        switch (requestCode) {
            case 0: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the read SMS
                    //  as if the btnRetrieve is clicked
                    btnRetrieve.performClick();

                } else {
                    // permission denied... notify user
                    Toast.makeText(getActivity(), "Permission not granted",
                            LENGTH_SHORT).show();
                }
            }
        }
    }
}
