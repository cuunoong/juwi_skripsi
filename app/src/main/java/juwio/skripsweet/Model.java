package juwio.skripsweet;

public class Model {
    float ph;
    String status;

    public Model() {
    }

    public Model(float ph, String status) {
        this.ph = ph;
        this.status = status;
    }

    public float getPh() {
        return ph;
    }

    public void setPh(float ph) {
        this.ph = ph;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Model{" +
                "ph=" + ph +
                ", status='" + status + '\'' +
                '}';
    }
}
