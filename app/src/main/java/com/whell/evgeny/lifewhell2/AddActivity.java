package com.whell.evgeny.lifewhell2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import utils.MyDBHandler;
import utils.categoryItem;
import yuku.ambilwarna.AmbilWarnaDialog;

public class AddActivity extends AppCompatActivity {

    private MyDBHandler db;
    private Button mButton;
    private ImageButton iButton;
    private EditText nameEdit;
    private EditText valueEdit;
    private EditText descriptionEdit;
    private EditText colorEdit;
    private AmbilWarnaDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Add new element");
        mButton = (Button) findViewById(R.id.btnAdd); // Button for adding element to graph
        iButton = (ImageButton) findViewById(R.id.wheelButton); // image button to select color
        // embedded color piker

        nameEdit = (EditText) findViewById(R.id.txtName);
        valueEdit = (EditText) findViewById(R.id.txtValue);
        descriptionEdit = (EditText) findViewById(R.id.txtDescription);
        colorEdit = (EditText) findViewById(R.id.txtColor);

        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                if (dataBaseProtection() == true) {
                    categoryItem newlyCreated = new categoryItem(1,
                            Integer.parseInt(valueEdit.getText().toString()),
                            nameEdit.getText().toString(),
                            descriptionEdit.getText().toString(),
                            colorEdit.getText().toString()
                    );
                    db = new MyDBHandler(view.getContext(), null, null, 1);
                    db.addCategoryItem(newlyCreated);
                    Intent intent = new Intent(view.getContext(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                    return;
                } else // else finish without entry in DB
                {
                    return;
                }
            }

        });


        iButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                callForColorPicker(); // call a function that will give user a colorPicker
            }
        });
    }

    private Boolean dataBaseProtection() {
        if(!nameEdit.getText().toString().isEmpty()
                && !valueEdit.getText().toString().isEmpty()
                && !descriptionEdit.getText().toString().isEmpty()
                && !colorEdit.getText().toString().isEmpty()) //if all fields are field, add element to db and close activity
        {

            if(Integer.parseInt(valueEdit.getText().toString())>=99) // integer should be at most 100
            {
                Toast.makeText(getApplicationContext(),
                    "PercentageValue field should be at most 100%",
                    Toast.LENGTH_LONG).show();
                return false;
            }
            return true;
        }
        else // if some field is empty
        {
            Toast.makeText(getApplicationContext(),
                    "Make sure that all fields are filled!",
                    Toast.LENGTH_LONG).show();
            return false;
        }
    }

    private void callForColorPicker() {

        dialog = new AmbilWarnaDialog(this, 0xff000000, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onOk(AmbilWarnaDialog dialog, int color)
            {
                // color is the color selected by the user.

                String colorText;
//                if(!(colorEdit.getText().toString().isEmpty()))
//                {
//                    colorText = colorEdit.getText().toString();
//                }
                Integer temp = color*-1;// convert color selected to integer
                colorText = temp.toString(); // convert color to string
                CharSequence cs = colorText; // create a charSequence from color
                colorEdit.setText(cs); // assign charSequence to field
            }

            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
                // cancel was selected by the user
            }

        });
        dialog.show();
    }
}
