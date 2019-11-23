package recorder.hk.pubgrecorder;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    String[] spinnerTitles;
    String[] spinnerPopulation;
    int[] spinnerImages;
    Spinner mSpinner;
    private boolean isUserInteracting;
    public static final String MyPREFERENCES = "Settings" ;
    public static final String ORIENT = "orientation";
    SharedPreferences sharedpreferences;
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        saveBtn = findViewById(R.id.save);

        mSpinner = (Spinner) findViewById(R.id.spinner_orient);
        spinnerTitles = new String[]{"Landscape", "Potriant"};
        spinnerPopulation = new String[]{"Horizontal","Vertical"};
        spinnerImages = new int[]{R.drawable.ic_stay_primary_landscape_black_24dp
                , R.drawable.ic_stay_primary_portrait_black_24dp};
        CustomAdapter mCustomAdapter = new CustomAdapter(Settings.this, spinnerTitles, spinnerImages, spinnerPopulation);
        mSpinner.setAdapter(mCustomAdapter);
        int selected = sharedpreferences.getInt(ORIENT,-1);
        if (selected!=-1)
        mSpinner.setSelection(selected);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (isUserInteracting) {


                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putInt(ORIENT, (int) mSpinner.getSelectedItemId());
                editor.commit();
                Toast.makeText(Settings.this, "Saved.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        isUserInteracting = true;
    }
}
