package com.akarion;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Created by Ed Ssemuwemba on 4/28/20.
 * esseme@gmail.com
 */
public class MainFragment extends Fragment {

    private Context context;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    DatePickerDialog pickerDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        context = getActivity().getApplicationContext();
        preferences = context.getSharedPreferences("MyPrefs", 0);
        editor = preferences.edit();

        EditText item = view.findViewById(R.id.editItemName);
        EditText price = view.findViewById(R.id.editItemPrice);
        EditText qty = view.findViewById(R.id.editQty);
        final EditText date = view.findViewById(R.id.editItemDate);
        Button submit = view.findViewById(R.id.btnSubmit);

        final String itemBought  = item.getText().toString().trim();
        final String priceItem = price.getText().toString().trim();
        final String itemQty = qty.getText().toString().trim();


        date.setInputType(InputType.TYPE_NULL);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                pickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                editor.putString("ITEM_DATE",dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                editor.apply();
                            }
                        }, year, month, day);
                pickerDialog.show();
            }
        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("ITEM_NAME", itemBought);
                editor.putString("ITEM_QTY", itemQty);
                editor.putString("ITEM_PRICE", priceItem);
                editor.apply();
                SecondFragment secondFragment = new SecondFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainView, secondFragment).
                        addToBackStack(null).commit();
            }
        });
        return view;
    }
}
