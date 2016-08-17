package ua.com.kathien.donorua.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.net.URL;
import java.util.Date;

/**
 * Created by kathien on 8/18/16.
 */
public class Event implements Parcelable {

    private int id;
    private String title;
    private String description;
    private String venueName;
    private int cityId;
    private String cityName;
    private Date startDate;
    private Date endDate;
    private String body;
    private String conditions;

    public Event(int id, String title, String description, String venueName, int cityId,
                 String cityName, Date startDate, Date endDate, String body, String conditions) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.venueName = venueName;
        this.cityId = cityId;
        this.cityName = cityName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.body = body;
        this.conditions = conditions;
    }

    @Override
    public String toString() {
        String event = "Event: " + title + "\n" + description + "\n";
        return event;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(venueName);
        dest.writeInt(cityId);
        dest.writeString(cityName);
        dest.writeLong(startDate != null ? startDate.getTime() : -1);//writing date as long. if date is null, write -1
        dest.writeLong(endDate != null ? endDate.getTime() : -1);
        dest.writeString(body);
        dest.writeString(conditions);
    }

    private Event(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        venueName = in.readString();
        cityId = in.readInt();
        cityName = in.readString();
        long tmpDate = in.readLong();
        startDate = tmpDate == -1 ? null : new Date(tmpDate);
        tmpDate = in.readLong();
        endDate = tmpDate == -1 ? null : new Date(tmpDate);
        body = in.readString();
        conditions = in.readString();
    }

    public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>() {
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }
}
