package uz.invinsible.layouts;

import android.os.Bundle;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import uz.invinsible.layouts.fragments.MyFragment;

public class SpinnerAc extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Spinner spinner = new Spinner(this);
        spinner.setSelection(0);


        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("")
                .add(new MyFragment(), "");
    }
}
