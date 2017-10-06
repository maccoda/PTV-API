package ptvobjects;


import java.util.List;

public class PtvDisruptionInformation implements PtvBasicObject {

    private int disruption_id;
    private String title;
    private String url;
    private String description;
    private String status;
    private String type;
    private String publishedOn;
    private String lastUpdated;
    private String fromDate;
    private String toDate;
    private String serviceTime;
    private List<PtvLine> lines;


    public int getDistuptionId() {
        return disruption_id;
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

    public String getPublishedOn() {
        return publishedOn;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public String getFromDate() {
        return fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public String getServiceTime() {
        return serviceTime;
    }

    public List<PtvLine> getLines() {
        return lines;
    }
}
