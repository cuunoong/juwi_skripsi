package juwio.skripsweet.models;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class Log implements Comparable<Log> {
    float ph;
    long tanggal;
    String status;

    public Log() {
    }

    public Log(float ph, long tanggal, String status) {
        this.ph = ph;
        this.tanggal = tanggal;
        this.status = status;
    }

    public float getPh() {
        return ph;
    }

    public void setPh(float ph) {
        this.ph = ph;
    }

    public long getTanggal() {
        return tanggal;
    }

    public String getFormattedDate(){
        Date date = new Date((tanggal - 25200)*1000);

        SimpleDateFormat format = new SimpleDateFormat("dd/MMM HH:mm", Locale.getDefault());
        return format.format(date);
    }

    public void setTanggal(long tanggal) {
        this.tanggal = tanggal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int compareTo(Log o) {
        return (int)(o.getTanggal() - tanggal);
    }
}
