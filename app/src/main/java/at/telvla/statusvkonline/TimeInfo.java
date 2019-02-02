package at.telvla.statusvkonline;

public class TimeInfo {
    private String Id;
    private String Date;

    TimeInfo (String id, String date) {
        this.Id = id;
        this.Date = date;
    }


    public void setId (String id) {
        Id = id;
    }

    public void setDate (String date) {
        Date = date;
    }

    public String getId() {
        return Id;
    }

    public String getDate() {
        return Date;
    }
}
