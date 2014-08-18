package convert.oxbao.ru.cenverter;


import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
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
                        groups.get(i).add(length[j]);
                    }
                    break;
                case 1:
                    String[] mechanic = getResources().getStringArray(R.array.mechanic);
                    for (int j = 0; j < mechanic.length; j++)
                    {
                        groups.get(i).add(mechanic[j]);
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

    /**
     * **************function calculate*******************************
     */
    public static String calculate(int i1, int i2, String inData, String[] choose) {
        double comUnit;

        try {
            comUnit = Double.parseDouble(inData);
            comUnit = comUnit * Double.parseDouble(choose[i2]) / (Double.parseDouble(choose[i1]));
        } catch (Exception e) {
            e.printStackTrace();
            comUnit = 0;
        }

        return String.valueOf(comUnit);
    }

    /**
     * **************************************************************
     */

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

        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, title_name);

        return new CommonFragment();
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
    }

    private void displayView(int position, int i2) {
        // update the maon content by replacing fragments
        Fragment fragment = null;
       int  pos_calc = position * 1000 + i2;
        switch (pos_calc) {
            case 0:
                fragment = newForm(R.array.length);
                break;
            case 1:
                fragment = newForm(R.array.AngleSpeed);
                break;
            case 2:
                fragment = newForm(R.array.Title_moment_power);
                break;
            case 3:
                fragment = newForm(R.array.Title_moment_power);
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
}
