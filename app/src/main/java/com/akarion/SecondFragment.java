package com.akarion;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.time.LocalDateTime;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Created by Ed Ssemuwemba on 4/28/20.
 * esseme@gmail.com
 */
public class SecondFragment extends Fragment {

    private Context context;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        context = getActivity().getApplicationContext();
        preferences = context.getSharedPreferences("MyPrefs", 0);

        TextView item = view.findViewById(R.id.itemName);
        item.setText(preferences.getString("ITEM_NAME", " "));
        TextView price = view.findViewById(R.id.itemPrice);
        price.setText(preferences.getString("ITEM_PRICE", " "));
        TextView qty = view.findViewById(R.id.qty);
        qty.setText(preferences.getString("ITEM_QTY", " "));
        TextView date = view.findViewById(R.id.itemDate);
        //TODO: Date needs to be formated
//        date.setText(String.format(LocalDateTime(preferences.getString("ITEM_DATE", " "))));
        date.setText(preferences.getString("ITEM_DATE", " "));
        return view;
    }
}
