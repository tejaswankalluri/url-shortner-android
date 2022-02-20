package com.example.helloworld;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipboardManager;
import android.content.ClipData;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Getting elements from the dom
        EditText nameInput = findViewById(R.id.nameInput);
        Button button = findViewById(R.id.submitButton);
        TextView shortLinkText = (TextView) findViewById(R.id.shortlinkText);
        // Clipboard
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        // Event Listener for click me button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameInput.onEditorAction(EditorInfo.IME_ACTION_DONE);
                String name = nameInput.getText().toString();
                if (!name.isEmpty() && URLUtil.isValidUrl(name)) {

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://owo.vc/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
                    DataModal model = new DataModal(name);
                    Call<DataModal> call = retrofitAPI.createPost(model);
                    call.enqueue(new Callback<DataModal>() {
                        @Override
                        public void onResponse(Call<DataModal> call, Response<DataModal> response) {
                            DataModal responseFromAPI = response.body();

                            shortLinkText.setText("https://" + responseFromAPI.getResult());
                            ClipData clip = ClipData.newPlainText("url", "https://" + responseFromAPI.getResult());
                            clipboard.setPrimaryClip(clip);
                            Toast.makeText(MainActivity.this, "Copied to clipboard", Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onFailure(Call<DataModal> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "error occurred", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(MainActivity.this, "Please Enter valid url", Toast.LENGTH_SHORT).show();
                }
            }
        });
        shortLinkText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipData clip = ClipData.newPlainText("url", shortLinkText.getText());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(MainActivity.this, "Copied to clipbord", Toast.LENGTH_SHORT).show();
            }
        });
    }
}