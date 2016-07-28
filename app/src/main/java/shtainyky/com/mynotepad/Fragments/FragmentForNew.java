package shtainyky.com.mynotepad.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import shtainyky.com.mynotepad.CreatingNotesActivity;
import shtainyky.com.mynotepad.R;

public class FragmentForNew extends Fragment implements View.OnClickListener {
   private View view;
   private EditText editText;
   private Button buttonCreate;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_new, container,false);
        editText = (EditText) view.findViewById(R.id.editText_title);

        buttonCreate = (Button) view.findViewById(R.id.buttonCreate);
        buttonCreate.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View view) {
        if (TextUtils.isEmpty(editText.getText().toString())) return;
        String FILENAME = editText.getText().toString();
        Intent intent = new Intent(getContext(), CreatingNotesActivity.class);
        intent.putExtra("FILENAME", FILENAME);
        intent.putExtra("TEXT", " ");
        startActivity(intent);
        editText.setText("");
    }


}
