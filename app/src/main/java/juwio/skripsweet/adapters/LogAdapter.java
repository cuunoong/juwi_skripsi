package juwio.skripsweet.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import juwio.skripsweet.R;
import juwio.skripsweet.models.Log;

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.Holder> {

    private List<Log> logs = new ArrayList<>();

    public LogAdapter(List<Log> logs) {
        this.logs = logs;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(LogAdapter.Holder holder, int position) {
        holder.gantiText(logs.get(position));
    }

    @Override
    public int getItemCount() {
        return logs.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        TextView ph, status, tanggal;

        public Holder(View itemView) {
            super(itemView);
            ph = itemView.findViewById(R.id.ph);
            status = itemView.findViewById(R.id.status);
            tanggal = itemView.findViewById(R.id.tanggal);
        }

        public void gantiText(Log log) {
            ph.setText(String.valueOf(log.getPh()));
            status.setText(log.getStatus());
            tanggal.setText(log.getFormattedDate());
        }

    }
}
