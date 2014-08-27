package convert.oxbao.ru.cenverter;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class CommonFragment extends Fragment {
    protected Spinner InSpinner;
    protected Spinner OutSpinner;
    protected EditText edtIn;
    protected TextView tvOut;
    protected int index1;
    protected int index2;


    public CommonFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.com_lay, container, false);
        InSpinner = (Spinner) rootView.findViewById(R.id.spinIn_moment);
        OutSpinner = (Spinner) rootView.findViewById(R.id.spinOut_moment);
        edtIn = (EditText) rootView.findViewById(R.id.edMoment);
        tvOut = (TextView) rootView.findViewById(R.id.tvMoment);

        InSpinner.setAdapter(MainActivity.adapter);
        OutSpinner.setAdapter(MainActivity.adapter);


        Bitmap plSp = BitmapFactory.decodeResource(getActivity().getResources() ,R.drawable.spin4);
        Bitmap plEd = BitmapFactory.decodeResource(getActivity().getResources() ,R.drawable.edt2);
      //  Bitmap pl3 = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.tst);

        BitmapDrawable sp = new BitmapDrawable(plSp);
        BitmapDrawable ed = new BitmapDrawable(plEd);
      //  BitmapDrawable tst = new BitmapDrawable(pl3);

        InSpinner.setBackgroundDrawable(sp);
        OutSpinner.setBackgroundDrawable(sp);
        edtIn.setBackgroundDrawable(ed);
        tvOut.setBackgroundDrawable(ed);


       /* Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "NotoSans-Bold.ttf");
        tvOut.setTypeface(font);
        edtIn.setTypeface(font);*/


        edtIn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                tvOut.setText(calculate(index1, index2, edtIn.getText().toString(), MainActivity.coeff));
            }
        });

        InSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                index1 = i;
                tvOut.setText(calculate(index1, index2, edtIn.getText().toString(), MainActivity.coeff));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        OutSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                index2 = i;
                tvOut.setText(calculate(index1, index2, edtIn.getText().toString(), MainActivity.coeff));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        return rootView;
    }
    protected String calculate(int i1, int i2, String inData, String[] choose) {
        double comUnit;

        try {
            comUnit = Double.parseDouble(inData);
            comUnit = comUnit * Double.parseDouble(choose[i2]) / (Double.parseDouble(choose[i1]));
        } catch (Exception e) {
            e.printStackTrace();
            return getResources().getString(R.string.EnterVal);
        }
        double RoundComUnit = new BigDecimal(comUnit).setScale(9, RoundingMode.UP).doubleValue();
        RoundComUnit = new BigDecimal(RoundComUnit).setScale(8, RoundingMode.DOWN).doubleValue();

        return String.valueOf(RoundComUnit);
    }
}
