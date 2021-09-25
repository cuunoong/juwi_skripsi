package juwio.skripsweet;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import juwio.skripsweet.models.Data;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "Main Activity";

    TextView nilaiPh, status;
    boolean powerStatus = false;
    ImageButton power;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("data");
    ValueEventListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    void init() {

        nilaiPh = findViewById(R.id.nilai_ph);
        status = findViewById(R.id.status_ph);
        power = findViewById(R.id.power);

        listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Data value = dataSnapshot.getValue(Data.class);

                nilaiPh.setText(String.valueOf(value.getPh()));
                status.setText(value.getStatus());

            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        };
    }

    void getData() {
        myRef.addValueEventListener(listener);
    }

    public void keluar(View view) {
        finish();
    }

    public void informasi(View view) {
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(R.layout.informasi);
        dialog.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setPower(View view) {
        if (powerStatus) {
            myRef.removeEventListener(listener);
            nilaiPh.setText("-.--");
            status.setText("pH Saat Ini");
            power.setImageTintList(getColorStateList(R.color.green));

        } else {
            getData();
            power.setImageTintList(getColorStateList(R.color.red));
        }
        powerStatus = !powerStatus;
    }

    public void history(View view) {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }
}