package id.hardian.deviceidutil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvGenerateID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvGenerateID = findViewById(R.id.tvGenerateID);

        String uuid = DeviceIDUtil.getUniqueID();

        tvGenerateID.setText(uuid);
    }
}
