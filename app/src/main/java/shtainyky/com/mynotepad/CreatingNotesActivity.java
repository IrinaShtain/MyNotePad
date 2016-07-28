package shtainyky.com.mynotepad;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class CreatingNotesActivity extends AppCompatActivity {
    private EditText inputText;
    private TextView fileName;
    private String titleFileName, text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creating_notes);
        initToolbar();

        final Intent intent = getIntent();
        titleFileName = intent.getStringExtra("FILENAME");
        text = intent.getStringExtra("TEXT");
        inputText = (EditText)findViewById(R.id.saved_text);
        inputText.setText(text);
        fileName = (TextView)findViewById(R.id.file_name);
        String fullFileName = getString(R.string.theme) + titleFileName;
        fileName.setText(fullFileName);

        ImageButton buttonSave = (ImageButton)findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveFile(titleFileName);
                finish();

            }
        });
    }

    @Override
    public void onBackPressed() {
        saveFile(titleFileName);
        super.onBackPressed();
    }

    private void initToolbar() {
        final Toolbar toolbar = (Toolbar)findViewById(R.id.toolBar);
        toolbar.setTitle(R.string.app_name);
        toolbar.inflateMenu(R.menu.menu);
        toolbar.setNavigationIcon(R.mipmap.ic_book_open_page_variant);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                finish();
                return false;
            }
        });

    }
    // Метод для сохранения файла
    private void saveFile(String fileName) {
        try {
            OutputStream outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(outputStream);
            osw.write(inputText.getText().toString());
            osw.close();
            String text = getString(R.string.file) + " " + fileName + " " + getString(R.string.saved);
            Toast.makeText(getApplicationContext(),text, Toast.LENGTH_LONG).show();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

}
