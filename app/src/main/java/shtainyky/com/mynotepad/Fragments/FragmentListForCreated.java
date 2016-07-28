package shtainyky.com.mynotepad.Fragments;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;

import shtainyky.com.mynotepad.CreatingNotesActivity;
import shtainyky.com.mynotepad.MainActivity;
import shtainyky.com.mynotepad.R;

public class FragmentListForCreated extends ListFragment {

    private static final int LAYOUT = R.layout.fragment_created;
    private View view;
    private String[] listTitles;
    private ArrayAdapter<String> adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        {
            view = inflater.inflate(LAYOUT, container, false);
            setNotifiedAdapter();
            initFloatingButton();
            return view;
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        TextView textView = (TextView) v;
        String fileName = textView.getText().toString();
        showAlertDialog(fileName);

    }
    @Override
    public void onResume() {
        setNotifiedAdapter();
        super.onResume();
    }
    private void initFloatingButton() {
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.show();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.showCreatingTab(0);
            }
        });
    }
    private void setNotifiedAdapter()
    {
        listTitles  = getActivity().fileList();
        adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, listTitles);
        setListAdapter(adapter);
        adapter.notifyDataSetChanged();


    }
    private void showAlertDialog(final String title)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.text_for_AD)
                .setMessage(title);
        builder.setPositiveButton(R.string.positive_btn_text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                openFile(title);
                setNotifiedAdapter();
            }
        });
        builder.setNegativeButton(R.string.negative_btn_text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                getActivity().deleteFile(title);
                setNotifiedAdapter();
                String text = getString(R.string.file) + " " + title + " " + getString(R.string.deleted);
                Toast.makeText(getActivity(),text, Toast.LENGTH_LONG).show();
            }
        });
        builder.setCancelable(true);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    private void openFile(String file) {
        FileInputStream fis;
        String content = "";
        try {
            fis = getActivity().openFileInput(file);  // открываем файл для чтения
            byte[] input = new byte[fis.available()];
            while (fis.read(input) != -1) {
                content += new String(input);
            }
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(getContext(), CreatingNotesActivity.class);
        intent.putExtra("FILENAME", file);
        intent.putExtra("TEXT", content);
        startActivity(intent);



    }
}