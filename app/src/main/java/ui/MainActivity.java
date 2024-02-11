package ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.urmish.firstapp.R;
import com.urmish.firstapp.databinding.ActivityMainBinding;

import data.MainViewModel;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding variableBinding;
    private MainViewModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model = new ViewModelProvider(this).get(MainViewModel.class);

        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());

//        TextView myText = findViewById(R.id.textview);
//        variableBinding.textview.setText(model.editString);
//        Button mytBtn = findViewById(R.id.mybutton);

        model.editString.observe(this, s ->  {
            variableBinding.textview.setText("Your edit text is: " + s);
            });

        model.isSelected.observe(this,selected -> {
            variableBinding.checkBoxCoffee.setChecked(selected);
            variableBinding.radioButtonCoffeeYes.setChecked(selected);
            variableBinding.switchCoffee.setChecked(selected);
            Toast.makeText(MainActivity.this, "The value is now: " +selected, Toast.LENGTH_SHORT).show();
        });

        variableBinding.checkBoxCoffee.setOnCheckedChangeListener((buttonView, isChecked) -> {
            model.isSelected.postValue(isChecked);
        });

        variableBinding.switchCoffee.setOnCheckedChangeListener((buttonView, isChecked) -> {
            model.isSelected.postValue(isChecked);
        });

        variableBinding.radioButtonCoffeeYes.setOnCheckedChangeListener((group, isChecekd) -> {
            model.isSelected.postValue(isChecekd);
        });
        variableBinding.mybutton.setOnClickListener(click ->
                {
                    model.editString.postValue(variableBinding.myedittext.getText().toString());

                });

        variableBinding.myimagebutton.setOnClickListener(view -> {
            // Get width and height
            int width = view.getWidth();
            int height = view.getHeight();
            // Show a Toast message with the width and height
            Toast.makeText(MainActivity.this, "The width = " + width + " and height = " + height, Toast.LENGTH_SHORT).show();
        });














//        EditText myEdit = findViewById(R.id.myedittext);



//        mytBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String editString = myEdit.getText().toString();
//                myText.setText( "Your edit text has: " + editString);
//            }
//        });
    }
}