package convert.oxbao.ru.cenverter;

public class TemperFragment extends CommonFragment {
    @Override
    protected String calculate(int i1, int i2, String inData, String[] choose) {
        double comUnit;
        try {
            comUnit = Double.parseDouble(inData);
            if (i1!=i2)
            {
                switch (i1) { //Все в градусы цельсия
                    case 1:
                        comUnit = comUnit - 274.15;
                        break;
                    case 2:
                        comUnit = (5.0/9.0)*(comUnit-32);
                        break;
                    case 3:
                        if (comUnit == 0)
                        {
                            comUnit = -273.15;
                            break;
                        }
                        comUnit = (comUnit - 491.67)*(5.0/9.0);
                        break;
                    case 4:
                        comUnit = 0.8 * comUnit;
                        break;
                }

                switch (i2)
                {
                    case 1:
                        comUnit = comUnit - 274.15;
                        break;
                    case 2:
                        comUnit = (comUnit*(9.0/5)) + 32;
                        break;
                    case 3:
                        comUnit = (comUnit + 273.16) * (9.0/5);
                        break;
                    case 4:
                        comUnit = comUnit/0.8;
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return getResources().getString(R.string.EnterVal);
        }
        return String.valueOf(comUnit);
    }
}
