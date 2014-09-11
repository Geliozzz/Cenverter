package convert.oxbao.ru.cenverter;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
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
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class CommonFragment extends Fragment {
    protected Spinner InSpinner;
    protected Spinner OutSpinner;
    protected EditText edFirst;
    protected EditText edSecond;
    protected int index1;
    protected int index2;
    protected int counter; /*Считает количество вызовов функций записи в поле. Нужна для избежания ошибки переполнения стека.
                            События изменения текста вызываюит события изменения второго текста. И вызовы становятся бесконечыми.
                            Переменная считает сколько раз была вызвана функция.*/




    public CommonFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.com_lay, container, false);
        InSpinner = (Spinner) rootView.findViewById(R.id.spinIn_moment);
        OutSpinner = (Spinner) rootView.findViewById(R.id.spinOut_moment);
        edFirst = (EditText) rootView.findViewById(R.id.edMoment);
        edSecond = (EditText) rootView.findViewById(R.id.edSecond);

        InSpinner.setAdapter(MainActivity.adapter);
        OutSpinner.setAdapter(MainActivity.adapter);



        Bitmap plSp = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.spin4);
        Bitmap plEd = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.edt2);

        BitmapDrawable sp = new BitmapDrawable(plSp);
        BitmapDrawable ed = new BitmapDrawable(plEd);


        InSpinner.setBackgroundDrawable(sp);
        OutSpinner.setBackgroundDrawable(sp);
        edFirst.setBackgroundDrawable(ed);
        edSecond.setBackgroundDrawable(ed);



        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "NotoSans-Bold.ttf");
        edSecond.setTypeface(font);
        edFirst.setTypeface(font);

        InSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                index1 = i;
                edFirst.setText(edFirst.getText());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        OutSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                index2 = i;
                edFirst.setText(edFirst.getText());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        edFirst.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String calc1 = (calculate(index1, index2, edFirst.getText().toString(), MainActivity.coeff));
                counter++;
                if (counter <= 1)
                {
                    edSecond.setText(calc1);
                } else counter = 0;


            }
        });

        edSecond.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String calc2 = calculate(index2, index1, edSecond.getText().toString(), MainActivity.coeff);
                counter++;
                if (counter <= 1)
                {

                    edFirst.setText(calc2);
                } else counter = 0;


            }
        });




        return rootView;
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        edFirst.setText(MainActivity.txtEdit);
       // Toast.makeText(getActivity(), "restor", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStop() {
        super.onStop();
        MainActivity.txtEdit = edFirst.getText().toString();
    }

    protected String calculate(int i1, int i2, String inData, String[] choose) {
        double comUnit;


        try {
            comUnit = Double.parseDouble(inData);
            comUnit = comUnit * Double.parseDouble(choose[i2]) / (Double.parseDouble(choose[i1]));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        double RoundComUnit = new BigDecimal(comUnit).setScale(9, RoundingMode.UP).doubleValue();
        RoundComUnit = new BigDecimal(RoundComUnit).setScale(8, RoundingMode.DOWN).doubleValue();

        if (RoundComUnit == 0)
        {
            return String.valueOf(comUnit);
        }


        return String.valueOf(RoundComUnit);
    }


}
