package juwio.skripsweet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import juwio.skripsweet.adapters.LogAdapter;
import juwio.skripsweet.models.Log;

public class HistoryActivity extends AppCompatActivity {
    RecyclerView listLog;
    LogAdapter logAdapter;
    
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference("logs");
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        listLog = findViewById(R.id.list_view);


        List<Log> logs = new ArrayList<>();
        listLog.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        logAdapter = new LogAdapter(logs);
        listLog.setAdapter(logAdapter);
        
        reference.orderByChild("tanggal").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull  DataSnapshot snapshot, @Nullable  String previousChildName) {
                logs.add(snapshot.getValue(Log.class));
                Collections.sort(logs);
                logAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull  DataSnapshot snapshot, @Nullable  String previousChildName) {
                for (int i = 0; i < logs.size(); i++) {
                    Log log = logs.get(i);
                    if(String.valueOf(log.getTanggal()).equals(snapshot.getKey())) {
                        logs.set(i, snapshot.getValue(Log.class));
                    }
                }
                Collections.sort(logs);
                logAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull  DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull  DataSnapshot snapshot, @Nullable  String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });
    }

    public void kembali(View view) {
        finish();
    }
}