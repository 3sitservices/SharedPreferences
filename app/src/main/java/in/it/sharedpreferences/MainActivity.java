package in.it.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText edt_name,edt_email;
    private AppCompatButton btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_name = findViewById(R.id.edt_name);
        edt_email = findViewById(R.id.edt_email);
        btn_save = findViewById(R.id.btn_save);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creating a shared pref object with a file name "MySharedPref" in private mode
                SharedPreferences sharedPreferences = getSharedPreferences("Test", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                // write all the data entered by the user in SharedPreference and apply
                myEdit.putString("name", edt_name.getText().toString());
                myEdit.putString("email", edt_email.getText().toString());
                myEdit.apply();
                Toast.makeText(MainActivity.this, "Data Saved", Toast.LENGTH_SHORT).show();
            }
        });
    }
    // Fetch the stored data in onResume() Because this is what will be called when the app opens again
    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(MainActivity.this, "Data Present in SharedPreference", Toast.LENGTH_SHORT).show();
        // Fetching the stored data from the SharedPreference
        SharedPreferences sh = getSharedPreferences("Test", MODE_PRIVATE);
        String name = sh.getString("name", "");
        String email = sh.getString("email", "");
        // Setting the fetched data in the EditTexts
        edt_name.setText(name);
        edt_email.setText(email);
    }
}