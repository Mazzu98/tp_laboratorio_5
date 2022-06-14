package com.example.peliculas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class Search implements View.OnClickListener {

    @Override
    public void onClick(View view) {
        Context context = view.getContext();
        Activity activity = (Activity) context;
        Intent i = new Intent(context,RecycleActivity.class);

        String search = ((EditText) activity.findViewById(R.id.etSearch)).getText().toString();
        Spinner spFilters = (Spinner) activity.findViewById(R.id.sp_filter);

        if(!"".equals(search)){
            String type = spFilters.getSelectedItem().toString();

            i.putExtra("search",search);
            i.putExtra("type",type);

            activity.startActivity(i);
        }
    }
}
