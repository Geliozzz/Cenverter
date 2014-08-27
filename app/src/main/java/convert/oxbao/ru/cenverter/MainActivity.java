package convert.oxbao.ru.cenverter;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private DrawerLayout myDrawerLayout;
    private ExpandableListView myDrawerList;
    private ActionBarDrawerToggle myDrawerToogle;
    private String mSplit = "-my_split-";
    // navigation drawer title
    private CharSequence myDrawerTitle;
    // used to stor app title
    private CharSequence myTitle;
    private String[] viewsNames;
    public static ArrayAdapter<?> adapter;
    public static String[] coeff;
    public static String[] title_name;
    private static  ArrayList<ArrayList<String>> groups;

    private class DrawerItemClickListner implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            //display view for selected nav drawer item
            //   displayView(i, 0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myTitle = getTitle();
        myDrawerTitle = getResources().getString(R.string.menu);

        //load slide menu items
        // viewsNames = getResources().getStringArray(R.array.);
        myDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        /*Drop shadow*/
        myDrawerLayout.setDrawerShadow(R.drawable.abc_search_dropdown_dark, GravityCompat.START);


       /* myDrawerList = (ListView) findViewById(R.id.left_drawer);*/
        myDrawerList = (ExpandableListView) findViewById(R.id.exListView);
        myDrawerList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i2, long l) {
                Toast.makeText(getApplicationContext(), String.valueOf(i*1000 + i2)  , Toast.LENGTH_SHORT).show();
                displayView(i, i2);
                return false;
            }
        });

        //Создаем набор данных для адаптера
        groups = new ArrayList<ArrayList<String>>();


        String[] arrGroup = getResources().getStringArray(R.array.groups);
        for (int i = 0; i < arrGroup.length; i++)
        {
            groups.add(new ArrayList<String>());

            switch (i){
                case 0:
                    String[] length = getResources().getStringArray(R.array.popular);
                    for (int j = 0; j < length.length; j++)
                    {
                        groups.get(i).add(length[j].split(mSplit)[0]);
                    }
                    break;
                case 1:
                    String[] mechanic = getResources().getStringArray(R.array.mechanic);
                    for (int j = 0; j < mechanic.length; j++)
                    {
                        groups.get(i).add(mechanic[j].split(mSplit)[0]);
                    }
                    break;
                case 2:
                    String[] termod = getResources().getStringArray(R.array.termodinamic);
                    for (int j = 0; j < termod.length; j++)
                    {
                        groups.get(i).add(termod[j].split(mSplit)[0]);
                    }
                    break;
                case 3:
                    String[] gidr = getResources().getStringArray(R.array.gidravlic);
                    for (int j = 0; j < gidr.length; j++)
                    {
                        groups.get(i).add(gidr[j].split(mSplit)[0]);
                    }
                    break;
                case 4:
                    String[] acus = getResources().getStringArray(R.array.acustic);
                    for (int j = 0; j < acus.length; j++)
                    {
                        groups.get(i).add(acus[j].split(mSplit)[0]);
                    }
                    break;
                case 5:
                    String[] foto = getResources().getStringArray(R.array.fotometry);
                    for (int j = 0; j < foto.length; j++)
                    {
                        groups.get(i).add(foto[j].split(mSplit)[0]);
                    }
                    break;
                case 6:
                    String[] elt = getResources().getStringArray(R.array.electrotehnic);
                    for (int j = 0; j < elt.length; j++)
                    {
                        groups.get(i).add(elt[j].split(mSplit)[0]);
                    }
                    break;
                case 7:
                    String[] mg = getResources().getStringArray(R.array.magnetic);
                    for (int j = 0; j < mg.length; j++)
                    {
                        groups.get(i).add(mg[j].split(mSplit)[0]);
                    }
                    break;
                case 8:
                    String[] rad = getResources().getStringArray(R.array.rad);
                    for (int j = 0; j < rad.length; j++)
                    {
                        groups.get(i).add(rad[j].split(mSplit)[0]);
                    }
                    break;
            }
        }


        ExpListAdapter ExAdapter = new ExpListAdapter(groups, this, arrGroup);
        myDrawerList.setAdapter(ExAdapter);
       /* myDrawerList.setAdapter(new ArrayAdapter<String>(
                this,
                R.layout.drawer_list_item,
                viewsNames
        ));*/
        //enabling action bar app icon and behaving if toogle button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        myDrawerToogle = new ActionBarDrawerToggle(
                this,
                myDrawerLayout,
                R.drawable.ic_drawer,
                R.string.drawer_open,
                R.string.drawer_close
        ) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(myTitle);
                //  invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {

                getSupportActionBar().setTitle(myDrawerTitle);
            }
        };

        myDrawerLayout.setDrawerListener(myDrawerToogle);

        if (savedInstanceState == null) {
            // on first time display view for first nav item
            //
            displayView(0, 0);
            //
        }
        myDrawerList.setOnItemClickListener(new DrawerItemClickListner());

    }

    /*function formirate new fragment*/
    private Fragment newForm(int resource) {

        String[] mRes = getResources().getStringArray(resource);
        title_name = new String[mRes.length];
        coeff = new String[mRes.length];
        for (int i = 0; i < mRes.length; i++) {
            String[] tmp = mRes[i].split(mSplit);
            title_name[i] = tmp[0];
            if (tmp.length >= 2) {
                coeff[i] = tmp[1];
            }

        }

       adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.spinner, title_name);
       // adapter = new MyCustomAdapter(MainActivity.this, R.layout.row, title_name);

        return new CommonFragment();
    }
    /**function set adapter for spinner use for specific forms  */
    private void setAdapter(int resource) {
        String[] mRes = getResources().getStringArray(resource);
        title_name = new String[mRes.length];
        coeff = new String[mRes.length];
        for (int i = 0; i < mRes.length; i++) {
            String[] tmp = mRes[i].split(mSplit);
            title_name[i] = tmp[0];
            if (tmp.length >= 2) {
                coeff[i] = tmp[1];
            }
        }
        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, title_name);
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
        /*int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);*/
        if (myDrawerToogle.onOptionsItemSelected(item)) return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(CharSequence title) {
        myTitle = title;
        getSupportActionBar().setTitle(myTitle);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.child_gradient));
    }

    private void displayView(int position, int i2) {
        // update the maon content by replacing fragments
        Fragment fragment = null;
        switch (position*1000+i2) {
            case 0:
                fragment = newForm(R.array.length);
                break;
            case 1:
                fragment = newForm(R.array.mass);
                break;
            case 2:
                fragment = newForm(R.array.volume_stew);
                break;
            case 3:
                fragment = newForm(R.array.Title_area);
                break;
            case 4:
                fragment = newForm(R.array.volume_cook);
                break;
            case 5:
                setAdapter(R.array.temperature);
                fragment = new TemperFragment();
                break;
            case 6:
                fragment = newForm(R.array.pressure);
                break;
            case 7:
                fragment = newForm(R.array.energy);
                break;
            case 8:
                fragment = newForm(R.array.power);
                break;
            case 9:
                fragment = newForm(R.array.force);
                break;
            case 10:
                fragment = newForm(R.array.time);
                break;
            case 11:
                fragment = newForm(R.array.speed);
                break;
            case 12:
                fragment = newForm(R.array.angle);
                break;
            case 13:
                fragment = newForm(R.array.heat);
                break;
            case 14:
                setAdapter(R.array.number);
                fragment = new NumberFragment();
                break;
            case 15:
                fragment = newForm(R.array.information);
                break;

            /* Mechanic*/

            case 1000:
                fragment = newForm(R.array.AngleSpeed);
                break;
            case 1001:
                fragment = newForm(R.array.accelerate);
                break;
            case 1002:
                fragment = newForm(R.array.AngleAccelerate);
                break;
            case 1003:
                fragment = newForm(R.array.density);
                break;
            case 1004:
                fragment = newForm(R.array.specificVolume);
                break;
            case 1005:
                fragment = newForm(R.array.momentOfInertia);
                break;
            case 1006:
                fragment = newForm(R.array.moment_power);
                break;

            /*termodinamic*/

            case 2000:
                fragment = newForm(R.array.specificHeatMass);
                break;
            case 2001:
                fragment = newForm(R.array.specificHeatVolume);
                break;
            case 2002:
                fragment = newForm(R.array.diffTemp);
                break;
            case 2003:
                fragment = newForm(R.array.CTE);
                break;
            case 2004:
                fragment = newForm(R.array.termResist);
                break;
            case 2005:
                fragment = newForm(R.array.thermalConductivity);
                break;
            case 2006:
                fragment = newForm(R.array.specificHeat);
                break;
            case 2007:
                fragment = newForm(R.array.powerHeat);
                break;
            case 2008:
                fragment = newForm(R.array.densityHeat);
                break;
            case 2009:
                fragment = newForm(R.array.heatTransferCoefficient);
                break;
            /*gidra*/

            case 3000:
                fragment = newForm(R.array.volumeFlowRate);
                break;
            case 3001:
                fragment = newForm(R.array.massFlowwRate);
                break;
            case 3002:
                fragment = newForm(R.array.moleFlowRate);
                break;
            case 3003:
                fragment = newForm(R.array.densityFlowMass);
                break;
            case 3004:
                fragment = newForm(R.array.moleConcentration);
                break;
            case 3005:
                fragment = newForm(R.array.moleConcentrationSolution);
                break;
            case 3006:
                fragment = newForm(R.array.dinamicVisconsity);
                break;
            case 3007:
                fragment = newForm(R.array.kinemVisconsity);
                break;
            case 3008:
                fragment = newForm(R.array.surfaceTension);
                break;
            case 3009:
                fragment = newForm(R.array.vaporPermeability);
                break;
            /*Acustic*/
            case 4000:
                fragment = newForm(R.array.volumeLevel);
                break;
            /*fotometry*/
            case 5000:
                fragment = newForm(R.array.brightness);
                break;
            case 5001:
                fragment = newForm(R.array.lumionousIntensity);
                break;
            case 5002:
                fragment = newForm(R.array.illumination);
                break;
            case 5003:
                fragment = newForm(R.array.resolution);
                break;
            case 5004:
                fragment = newForm(R.array.freq);
                break;
            /*Eltctric*/
            case 6000:
                fragment = newForm(R.array.electricCharge);
                break;
            case 6001:
                fragment = newForm(R.array.linearChargeDensity);
                break;
            case 6002:
                fragment = newForm(R.array.surfaceChargeDensity);
                break;
            case 6003:
                fragment = newForm(R.array.bulkDensityCharge);
                break;
            case 6004:
                fragment = newForm(R.array.electricCurrent);
                break;
            case 6005:
                fragment = newForm(R.array.linearCurrentDensity);
                break;
            case 6006:
                fragment = newForm(R.array.surfaceChargeDensity);
                break;
            case 6007:
                fragment = newForm(R.array.electricFieldStrength);
                break;
            case 6008:
                fragment = newForm(R.array.electrostatic);
                break;
            case 6009:
                fragment = newForm(R.array.resistance);
                break;
            case 6010:
                fragment = newForm(R.array.resistivity);
                break;
            case 6011:
                fragment = newForm(R.array.conductivity);
                break;
            case 6012:
                fragment = newForm(R.array.specificConductivity);
                break;
            case 6013:
                fragment = newForm(R.array.capacitance);
                break;
            case 6014:
                fragment = newForm(R.array.inductance);
                break;
            case 6015:
                fragment = newForm(R.array.dbm);
                break;
            /*Magic*/
            case 7000:
                fragment = newForm(R.array.magicForce);
                break;
            case 7001:
                fragment = newForm(R.array.magicField);
                break;
            case 7002:
                fragment = newForm(R.array.magicStream);
                break;
            case 7003:
                fragment = newForm(R.array.magicInd);
                break;
            /*rad*/
            case 8000:
                fragment = newForm(R.array.powerDozes);
                break;
            case 8001:
                fragment = newForm(R.array.radiation);
                break;
            case 8002:
                fragment = newForm(R.array.expDoze);
                break;
            case 8003:
                fragment = newForm(R.array.takeDoze);
                break;
            default:

                break;
        }
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
            // update selected item and title? the close drawer
            //   myDrawerList.setItemChecked(position, true);
            //  myDrawerList.setSelection(position);
            setTitle(groups.get(position).get(i2));
            myDrawerLayout.closeDrawer(myDrawerList);
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    public class MyCustomAdapter extends ArrayAdapter<String>{
        private String[] tmp;

        public MyCustomAdapter(Context context, int textViewResourceId,
                               String[] objects) {

            super(context, textViewResourceId, objects);
            tmp = objects;
            // TODO Auto-generated constructor stub
        }

        @Override
        public View getDropDownView(int position, View convertView,
                                    ViewGroup parent) {
            // TODO Auto-generated method stub
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            //return super.getView(position, convertView, parent);

            LayoutInflater inflater=getLayoutInflater();
            View row=inflater.inflate(R.layout.row, parent, false);
            TextView label=(TextView)row.findViewById(R.id.weekofday);

            label.setText(tmp[position]);



            ImageView icon=(ImageView)row.findViewById(R.id.icon);



            return row;
        }
    }
}