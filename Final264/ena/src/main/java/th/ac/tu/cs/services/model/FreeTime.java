package th.ac.tu.cs.services.model;

public class FreeTime {
    private int Id;
    private String Date;
    private String FirstName;
    private String LastName;
    private String StartTime;
    private String EndTime;
    private String Annotation;

    public FreeTime(String date, String firstName, String lastName, String startTime, String endTime, String annotation) {
        Date = date;
        FirstName = firstName;
        LastName = lastName;
        StartTime = startTime;
        EndTime = endTime;
        Annotation = annotation;
    }

    public FreeTime(int id, String date, String firstName, String lastName, String startTime, String endTime, String annotation) {
        Id = id;
        Date = date;
        FirstName = firstName;
        LastName = lastName;
        StartTime = startTime;
        EndTime = endTime;
        Annotation = annotation;
    }
    public FreeTime() {

    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getAnnotation() {
        return Annotation;
    }

    public void setAnnotation(String annotation) {
        Annotation = annotation;
    }

    @Override
    public String toString() {
        return "FreeTime{" +
                "Id=" + Id +
                ", Date='" + Date + '\'' +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", StartTime='" + StartTime + '\'' +
                ", EndTime='" + EndTime + '\'' +
                ", Annotation='" + Annotation + '\'' +
                '}';
    }
}
