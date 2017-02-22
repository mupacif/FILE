package be.formation.file;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    public final static String FILENAME="myText.dat";
    private TextView textTv;
    private String text;
    private Button submit;
    private Button toast;
    private Button delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textTv = (TextView)findViewById(R.id.et_main_text);
        submit=(Button)findViewById(R.id.bt_main_save);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = textTv.getText().toString();
                if(!text.isEmpty()) {

                    save(text);
                }
            }
        });

        toast = (Button)findViewById(R.id.bt_main_toaster);
        toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast();
            }
        });

        delete =(Button)findViewById(R.id.bt_main_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFile();

            }
        });

    }

    public void save(String text)
    {
        FileOutputStream fos;
        try {
            fos = openFileOutput(FILENAME, MODE_PRIVATE);
            fos.write(text.getBytes());
            fos.close();
            Toast.makeText(MainActivity.this, "File saved", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Show text with toast
     */
    public void showToast()
    {
        FileInputStream fis = null;
        String line;
        try {
            fis = openFileInput(FILENAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            StringBuilder out = new StringBuilder();
            line="";
            while ((line = reader.readLine())!= null)
                out.append(line);

            reader.close();
            fis.close();

            Toast.makeText(MainActivity.this,line,Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public boolean deleteFile()
    {
         File dir = getFilesDir();
        File file = new File(dir,FILENAME);
        return file.delete();
    }


}
