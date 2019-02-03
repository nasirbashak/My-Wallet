package e.nasirbashak007.mywallet;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import e.nasirbashak007.mywallet.MainActivity.*;

import java.util.Calendar;

public class NewPackets extends AppCompatActivity {


    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;



    EditText eDate,eTotal,eDelivered,eAttempted,eRejected;




    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_packets);

        init();



        Calendar c = Calendar.getInstance();

        String[] monthName = {"January","February","March", "April", "May", "June", "July",
                "August", "September", "October", "November",
                "December"};

        int m = c.get(Calendar.MONTH);

        String month = monthName[m];


        int year = c.get(Calendar.YEAR);
        int day = c.get(Calendar.DATE);

        int mm = m+1;
        date = day+"/"+mm+"/"+year;


        Toast.makeText(getApplicationContext(),mm+"",Toast.LENGTH_SHORT).show();


        eDate.setText(day+"/"+mm+"/"+year);


        firebaseDatabase = FirebaseDatabase.getInstance();

        myRef = firebaseDatabase.getReference().child(year+"").child(month);

    }

    private void init() {

        eDate = (EditText)findViewById(R.id.eTextDate);
        eTotal = (EditText)findViewById(R.id.eTextTpackets);
        eDelivered = (EditText)findViewById(R.id.eTextDelivered);
        eAttempted = (EditText)findViewById(R.id.eTextAttempted);
        eRejected = (EditText)findViewById(R.id.eTextRejected);

    }

    public void addNewPackets(View view) {

                date = eDate.getText().toString();
        String total = eTotal.getText().toString().trim();
        String delivered = eDelivered.getText().toString().trim();
        String attempted = eAttempted.getText().toString().trim();
        String rejected = eRejected.getText().toString().trim();


        DailyPacket dailyPacket = new DailyPacket(date,total,delivered,attempted,rejected);


        myRef.push().setValue(dailyPacket).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                Toast.makeText(getApplicationContext(),"data uploaded successfully",Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),  e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

            }
        });



        eDate.setText("");
        eTotal.setText("");
        eDelivered.setText("");
        eAttempted.setText("");
        eRejected.setText("");

    }

    public void cancelled(View view) {

        startActivity(new Intent(this,MainActivity.class ));
    }
}
