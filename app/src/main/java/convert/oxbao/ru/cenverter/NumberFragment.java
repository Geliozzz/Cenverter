package convert.oxbao.ru.cenverter;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

public class NumberFragment extends CommonFragment {


    @Override
    /** Override need for change input type editText */
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.com_lay, container, false);
        InSpinner = (Spinner) rootView.findViewById(R.id.spinIn_moment);
        OutSpinner = (Spinner) rootView.findViewById(R.id.spinOut_moment);
        edFirst = (EditText) rootView.findViewById(R.id.edMoment);
        edSecond = (EditText) rootView.findViewById(R.id.edSecond);

        InSpinner.setAdapter(MainActivity.adapter);
        OutSpinner.setAdapter(MainActivity.adapter);

        edFirst.setInputType(1);
        edSecond.setInputType(1);

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
                if (counter <= 1) {
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
                if (counter <= 1) {

                    edFirst.setText(calc2);
                } else counter = 0;
            }
        });

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


        return rootView;
    }

    @Override
    protected String calculate(int i1, int i2, String inData, String[] choose) {
        String ret = "";

        try {
            int tmp = Integer.parseInt(inData, Integer.parseInt(choose[i1]));
            ret = Integer.toString(tmp, Integer.parseInt(choose[i2]));

        } catch (Exception e) {
            e.printStackTrace();
            return "";

        }

        return ret;
    }
}
