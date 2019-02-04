package e.nasirbashak007.mywallet;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Random;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , AdapterView.OnItemSelectedListener {


    Spinner spinnerYear,spinnerStartMonth,spinnerEndMonth;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;


    private Random rand;

    private String year;
    private String startMonth;
    private String endMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        spinnerYear = (Spinner)findViewById(R.id.spinnerYear);
        spinnerStartMonth = (Spinner)findViewById(R.id.spinnerStartMonth);
        spinnerEndMonth = (Spinner)findViewById(R.id.spinnerEndMonth);



        initializeTheSpinner(spinnerYear,R.array.year);
        initializeTheSpinner(spinnerStartMonth, R.array.month);
        initializeTheSpinner(spinnerEndMonth, R.array.month);





        rand = new Random();


        Calendar c = Calendar.getInstance();

        String[] monthName = {"January","February","March", "April", "May", "June", "July",
                "August", "September", "October", "November",
                "December"};

        String month = monthName[c.get(Calendar.MONTH)];

        int n = Math.abs(rand.nextInt(12));

        int year = c.get(Calendar.YEAR);

        //String month = monthName[n];


        firebaseDatabase = FirebaseDatabase.getInstance();







        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                  //      .setAction("Action", null).show();

                startActivity(new Intent(MainActivity.this,NewPackets.class));


            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initializeTheSpinner(Spinner spinner, int array) {

        ArrayAdapter<CharSequence> adapterYear =  ArrayAdapter.createFromResource(this,array,android.R.layout.simple_spinner_item);

        adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterYear);

        spinner.setOnItemSelectedListener(this);




    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void showInRecyclerView(View view) {

        myRef = firebaseDatabase.getReference().child(year+"").child(startMonth);


    }

    public void calculateTheAmount(View view) {

        myRef = firebaseDatabase.getReference().child(year+"").child(startMonth);

        Intent i = new Intent(MainActivity.this,CalculateAmount.class);

        i.putExtra("YEAR",year);
        i.putExtra("START_MONTH", startMonth);
        i.putExtra("END_MONTH",endMonth);

        startActivity(i);



    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {






        switch (parent.getId()){

            case R.id.spinnerYear:
            {
                 year = parent.getItemAtPosition(position).toString();


            }break;

            case R.id.spinnerStartMonth:
            {
                startMonth = parent.getItemAtPosition(position).toString();

            }break;

            case R.id.spinnerEndMonth:
            {
                endMonth = parent.getItemAtPosition(position).toString();

            }break;




        }


       // Toast.makeText(getApplicationContext(),year +" year",Toast.LENGTH_SHORT).show();
        //Toast.makeText(getApplicationContext(),startMonth+" start",Toast.LENGTH_SHORT).show();
        //Toast.makeText(getApplicationContext(),endMonth+" end",Toast.LENGTH_SHORT).show();





    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
