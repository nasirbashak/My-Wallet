package e.nasirbashak007.mywallet;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CalculateAmount extends AppCompatActivity {

    TextView textList, textTotalPackets, textTotalAmount;
    EditText editTextRate;


    String year, startMonth, endMonth;

    int totalPackets ;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_amount);

        textList = (TextView) findViewById(R.id.textList);
        textTotalPackets = (TextView) findViewById(R.id.textTotal);
        textTotalAmount = (TextView) findViewById(R.id.textTotalAmount);
        editTextRate = (EditText) findViewById(R.id.eTextRate);


        Intent iIn = getIntent();

        Bundle bundle = iIn.getExtras();

        year = bundle.getString("YEAR");
        startMonth = bundle.getString("START_MONTH");
        endMonth = bundle.getString("END_MONTH");

        textList.setText(year + "\n" + startMonth + "\n" + endMonth);

        firebaseDatabase = FirebaseDatabase.getInstance();

        myRef = firebaseDatabase.getReference().child(year).child(startMonth);

        Toast.makeText(getApplicationContext(),myRef.getKey(),Toast.LENGTH_LONG).show();




        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                StringBuffer sbr = new StringBuffer();

                int i = 0;
                totalPackets = 0;

                for(DataSnapshot d : dataSnapshot.getChildren()){

                    DailyPacket dp = (DailyPacket) d.getValue(DailyPacket.class);


                    int packets = Integer.valueOf(dp.getDeliveredPackets())+Integer.valueOf(dp.getRejectedPackets());

                    totalPackets+=packets;


                    sbr.append(dp.getDate())
                            .append("\t\t")
                            .append(packets+"\n");





                }

                textList.setText(sbr);
                textTotalPackets.setText(totalPackets+"");




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        editTextRate.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    Log.i("TAG","Enter pressed");


                }
                return false;
            }
        });






    }

    public void calculateTheAmount(View view) {

        String data = editTextRate.getText().toString().trim();


        if(data.isEmpty()){


        }else{

            float rate = Float.valueOf(data);

            float totalAmount = totalPackets*rate;

            textTotalAmount.setText(totalAmount+"");




        }






    }
}
