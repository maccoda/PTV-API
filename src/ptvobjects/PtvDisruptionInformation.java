package model.ptvobjects;

import java.util.Date;

import org.json.simple.JSONObject;

public class PtvDisruptionInformation extends PtvObject {
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

  public void setDistuptionId(String distuptionId) {
    this.distuptionId = distuptionId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Date getPublishedOn() {
    return publishedOn;
  }

  public void setPublishedOn(Date publishedOn) {
    this.publishedOn = publishedOn;
  }

  public Date getLastUpdated() {
    return lastUpdated;
  }

  public void setLastUpdated(Date lastUpdated) {
    this.lastUpdated = lastUpdated;
  }

  public Date getFromDate() {
    return fromDate;
  }

  public void setFromDate(Date fromDate) {
    this.fromDate = fromDate;
  }

  public Date getToDate() {
    return toDate;
  }

  public void setToDate(Date toDate) {
    this.toDate = toDate;
  }

  public String getServiceTime() {
    return serviceTime;
  }

  public void setServiceTime(String serviceTime) {
    this.serviceTime = serviceTime;
  }

  @Override
  public void populateFields(JSONObject object) {
    // TODO Auto-generated method stub

  }

}
