package fr.eni.lokacar.ui;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import fr.eni.lokacar.R;

public class AssociateClientActivity extends AppCompatActivity {

    private EditText etDateDebut, etDateFin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_associate_client);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etDateDebut = (EditText) findViewById(R.id.editText_DateDebut);
        etDateFin = (EditText) findViewById(R.id.editText_DateFin);
    }

    public void seeCalendar(View view) {

        final Calendar calendar = Calendar.getInstance();

        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                String date = String.valueOf(dayOfMonth) + "/" + String.valueOf(monthOfYear) + "/" + String.valueOf(year);
                if(etDateDebut.hasFocus()) {
                    etDateDebut.setText(date);
                }else if (etDateFin.hasFocus()){
                    etDateFin.setText(date);
                }
            }
        }, yy, mm, dd);

        datePicker.show();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.home :
                finish();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
