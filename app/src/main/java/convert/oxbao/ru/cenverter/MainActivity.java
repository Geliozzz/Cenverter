package convert.oxbao.ru.cenverter;


import android.annotation.TargetApi;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.*;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.*;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    private Spinner unSpinner;
    private Spinner unSpinner2;
    private double comUnit; //Common unit is radian per second
    private TextView tvUnit;
    private EditText edUnit;
    private int index1;
    private int index2;
    private int temp;

    private DrawerLayout myDrawerLayout;
    private ListView myDrawerList;
    private ActionBarDrawerToggle myDrawerToogle;

    // navigation drawer title
    private CharSequence myDrawerTitle;
    // used to stor app title
    private CharSequence myTitle;

    private String[] viewsNames;

    private class DrawerItemClickListner implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            //display view for selected nav drawer item
            displayView(i);
        }
    }

    private void displayView(int position) {
        // update the maon content by replacing fragments
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new FirstFragment();
                break;
            case 1:
                fragment = new SecondFragment();
                break;
            case 2:
                fragment = new MomPower();
                break;
            default:

            break;
        }

        if (fragment != null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
           // update selected item and title? the close drawer
            myDrawerList.setItemChecked(position, true);
            myDrawerList.setSelection(position);
            setTitle(viewsNames[position]);
            myDrawerLayout.closeDrawer(myDrawerList);
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myTitle = getTitle();
        myDrawerTitle = getResources().getString(R.string.hello_world);

        //load slide menu items
        viewsNames = getResources().getStringArray(R.array.views_array);
        myDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        myDrawerList = (ListView) findViewById(R.id.left_drawer);

        myDrawerList.setAdapter(new ArrayAdapter<String>(
                this,
                R.layout.drawer_list_item,
                viewsNames
        ));

        //enabling action bar app icon and behaving if toogle button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        myDrawerToogle = new ActionBarDrawerToggle(
                this,
                myDrawerLayout,
                R.drawable.ic_drawer,
                R.string.app_name,
                R.string.app_name
        ) {
            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            public void onDrawerClosed(View view) {
               // getActionBar().setTitle(myTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
             //   invalidateOptionsMenu();
            }

            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            public void onDrawerOpened(View drawerView) {
               // getActionBar().setTitle(myDrawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
             //   invalidateOptionsMenu();
            }
        };

        myDrawerLayout.setDrawerListener(myDrawerToogle);

        if (savedInstanceState == null) {
            // on first time display view for first nav item
            //
             displayView(0);
            //
        }
        myDrawerList.setOnItemClickListener(new DrawerItemClickListner());


        //   tvUnit = (TextView)findViewById(R.id.tvCalcUnit);
        //   edUnit = (EditText)findViewById(R.id.etEnterUnit);
/*
        unSpinner = (Spinner) findViewById(R.id.spinUnit); // menu with first coefficients
        //Create ArrayAdapter
        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.layout.sim)
        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(this, R.array.unitList, R.layout.support_simple_spinner_dropdown_item);
        unSpinner.setAdapter(adapter);
        unSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String[] choose = getResources().getStringArray(R.array.unitList);
                Toast toast = Toast.makeText(getApplicationContext(), "Your choose: " + choose[i], Toast.LENGTH_SHORT);
                toast.show();
                index1 = i;
                calculate(index1, index2);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        unSpinner2 = (Spinner) findViewById(R.id.unSpinner2); // menu with second coefficients

        ArrayAdapter<?> adapter2 = ArrayAdapter.createFromResource(this, R.array.unitList, R.layout.support_simple_spinner_dropdown_item);
        unSpinner2.setAdapter(adapter2);
        unSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                index2 = i;
                calculate(index1, index2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        edUnit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

               calculate(index1, index2);
            }
        });*/

    }

    /**
     * **************function calculate*******************************
     */
    private String calculate(int i1, int i2) {
        String[] choose = getResources().getStringArray(R.array.unitCoeff);
        String[] chooTxt = getResources().getStringArray(R.array.unitList);
        // String firstVal = "";
        try {
            comUnit = Double.parseDouble(edUnit.getText().toString());
            // firstVal = edUnit.getText().toString();
            comUnit = comUnit * Double.parseDouble(choose[i2]) / (Double.parseDouble(choose[i1])); // in radians
        } catch (Exception e) {
            e.printStackTrace();
            Toast toast = Toast.makeText(getApplicationContext(), "Недопустимое значение", Toast.LENGTH_SHORT);
            toast.show();
            comUnit = 0;
        }
        tvUnit.setText(String.valueOf(comUnit));
        return String.valueOf(comUnit);
    }

    /**
     * **************************************************************
     */
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
