package ptvobjects;


import java.util.Date;

public class PtvDisruptionInformation implements PtvBasicObject {

    private String distuptionId;
    private String title;
    private String url;
    private String description;
    private String status;
    private String type;
    private Date publishedOn;
    private Date lastUpdated;
    private Date fromDate;
    private Date toDate;
    private String serviceTime;


    public String getDistuptionId() {
        return distuptionId;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public Date getPublishedOn() {
        return publishedOn;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public String getServiceTime() {
        return serviceTime;
    }

}
