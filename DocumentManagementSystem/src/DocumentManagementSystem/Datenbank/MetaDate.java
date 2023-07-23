package DocumentManagementSystem.Datenbank;

public class MetaDate {
    private String text;
    private int id;
    private String doctorName;
    private String date;
    private String prescribedMedications;

    public MetaDate(String text, int id, String doctorName, String date, String prescribedMedications) {
        this.text = text;
        this.id = id;
        this.doctorName = doctorName;
        this.date = date;
        this.prescribedMedications = prescribedMedications;
    }


    public String getText() {
        return text;
    }

    public int getId() {
        return id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getDate() {
        return date;
    }

    public String getPrescribedMedications() {
        return prescribedMedications;
    }


    public void setText(String text) {
        this.text = text;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPrescribedMedications(String prescribedMedications) {
        this.prescribedMedications = prescribedMedications;
    }
    public String toString() {
        return "MetaDate{" +
                "text='" + text + '\'' +
                ", id=" + id +
                ", doctorName='" + doctorName + '\'' +
                ", date='" + date + '\'' +
                ", prescribedMedications='" + prescribedMedications + '\'' +
                '}';
    }
}

