package devtitans.batscanapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView homeSeachIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //homeSeachIcon = findViewById(R.id.imvTelaPrincipal);
    }

    public void deviceList(View view) {
        Intent intent = new Intent(this, MacVendorListActivity.class);
        startActivity(intent);
    }

}