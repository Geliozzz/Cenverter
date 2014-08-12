package convert.oxbao.ru.cenverter;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvUnit = (TextView)findViewById(R.id.tvCalcUnit);
        edUnit = (EditText)findViewById(R.id.etEnterUnit);

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
        });

    }
    /*****************function calculate********************************/
    private String calculate(int i1, int i2)
    {
        String[] choose = getResources().getStringArray(R.array.unitCoeff);
        String[] chooTxt = getResources().getStringArray(R.array.unitList);
       // String firstVal = "";
        try{
            comUnit = Double.parseDouble(edUnit.getText().toString());
           // firstVal = edUnit.getText().toString();
            comUnit = comUnit * Double.parseDouble(choose[i2]) / (Double.parseDouble(choose[i1])); // in radians
        }catch (Exception e)
        {
            e.printStackTrace();
            Toast toast =  Toast.makeText(getApplicationContext(), "Недопустимое значение", Toast.LENGTH_SHORT);
            toast.show();
            comUnit = 0;
        }
        tvUnit.setText(String.valueOf(comUnit));
        return String.valueOf(comUnit);
    }

    /******************************************************************/
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
