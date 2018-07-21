package com.krokoklemme.shrekdroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getSimpleName();
    private AlertDialog managingDialog;
    private Menu menu;

    private Spinner categorySelection;
    private EditText input;
    private Spinner unitSelection;
    private TextView result;
    private CheckBox shrekMode;

    private boolean inShrekMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categorySelection = findViewById(R.id.categorySelection);
        input = findViewById(R.id.input);
        unitSelection = findViewById(R.id.unitSelection);
        result = findViewById(R.id.result);
        shrekMode = findViewById(R.id.shrekMode);

        final ArrayAdapter<Category> categoryArrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item);
        final ArrayAdapter<Category.Unit> unitArrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item);

        categoryArrayAdapter.setNotifyOnChange(true);
        unitArrayAdapter.setNotifyOnChange(true);

        categorySelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long index) {
                Category category = categoryArrayAdapter.getItem(Math.toIntExact(index));

                if (category != null) {
                    unitArrayAdapter.clear();
                    unitArrayAdapter.addAll(category.getUnits());
                    unitArrayAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        unitSelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long index) {
                Category.Unit unit = unitArrayAdapter.getItem(Math.toIntExact(index));
                if (!inShrekMode) {
                    input.setHint(String.format("# %s", unit.getName()));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        categorySelection.setAdapter(categoryArrayAdapter);
        unitSelection.setAdapter(unitArrayAdapter);

        Resources resources = getResources();

        Category time = new Category(resources.getString(R.string.time));
        time.addUnit(resources.getString(R.string.ms), "ms", 5700000.0);
        time.addUnit(resources.getString(R.string.sec), "sec", 5700.0);
        time.addUnit(resources.getString(R.string.min), "min", 95.0);
        time.addUnit(resources.getString(R.string.hours), "h", 1.58333);
        time.addUnit(resources.getString(R.string.days), "d", 0.0659722);
        time.addUnit(resources.getString(R.string.weeks), "w", 0.0094246);

        Category distance = new Category(resources.getString(R.string.distance));
        distance.addUnit(resources.getString(R.string.inch), "'", 96.0);
        distance.addUnit(resources.getString(R.string.foot), "\"", 8.0);
        distance.addUnit(resources.getString(R.string.yard), "yd", 2.66667);
        distance.addUnit(resources.getString(R.string.mile), "m", 0.00151515);
        distance.addUnit(resources.getString(R.string.mm), "mm", 2438.4);
        distance.addUnit(resources.getString(R.string.cm), "cm", 243.84);
        distance.addUnit(resources.getString(R.string.meter), "m", 2.4384);
        distance.addUnit(resources.getString(R.string.km), "km", 0.0024384);

        categoryArrayAdapter.addAll(time, distance);

        managingDialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle(R.string.manage)
                .setIcon(R.drawable.shrek)
                .setAdapter(categoryArrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, categoryArrayAdapter.getItem(i).getName(), Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(R.string.back, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                .setPositiveButton(R.string.add_new, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "add new category", Toast.LENGTH_SHORT).show();
                    }
                })
                .create();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        this.menu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.menuManage: {
                if (!managingDialog.isShowing()) {
                    managingDialog.show();
                }
                break;
            }
            case R.id.menuShrekMode: {
                onModeToggled(null);

                shrekMode.setChecked(inShrekMode);
                break;
            }
            case R.id.menuAbout: {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.menuGithub: {
                break;
            }
        }

        return true;
    }

    public void onConvertClick(View view) {
        Toast.makeText(MainActivity.this, "not yet", Toast.LENGTH_LONG).show();
    }

    public void onModeToggled(View view) {
        inShrekMode = !inShrekMode;
        menu.findItem(R.id.menuShrekMode).setChecked(inShrekMode);
    }
}
