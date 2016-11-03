package ua.com.kathien.donorua.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.net.URL;
import java.util.Date;


public class News implements Parcelable {

    private int id;
    private Date datePublished;
    private Date lastUpdated;
    private boolean isPublished;
    //TODO: decide whether to add these fields to the News object if i don't use them in my UI and code
    private int contentTypeId;
    private URL image;
    private URL bigImage;
    private int userProfileId;
    private String shortDescription;
    private String title;
    private String description;

    public News(int id, Date datePublished, Date lastUpdated, boolean isPublished, int contentTypeId,
                URL image, URL bigImage, int userProfileId, String shortDescription,
                String title, String description) {
        this.id = id;
        this.datePublished = datePublished;
        this.lastUpdated = lastUpdated;
        this.isPublished = isPublished;
        this.contentTypeId = contentTypeId;
        this.image = image;
        this.bigImage = bigImage;
        this.userProfileId = userProfileId;
        this.shortDescription = shortDescription;
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        String news = "News #" + id + ": " + title + "\n" + shortDescription + "\n";
        return news;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(id);
        dest.writeLong(datePublished != null ? datePublished.getTime() : -1); //writing date as long. if date is null, write -1
        dest.writeLong(lastUpdated != null ? lastUpdated.getTime() : -1); //writing date as long. if date is null, write -1
        dest.writeByte((byte)(isPublished ? 1 : 0)); //Writing boolean into parcel. If isPublished == true, byte == 1*/
        dest.writeInt(contentTypeId);
        dest.writeValue(image);
        dest.writeValue(bigImage);
        dest.writeInt(userProfileId);
        dest.writeString(shortDescription);
        dest.writeString(title);
        dest.writeString(description);

    }

    private News(Parcel in) {
        id = in.readInt();
        long tmpDate = in.readLong();
        datePublished = tmpDate == -1 ? null : new Date(tmpDate); //reading long from parcel to create new date object
        tmpDate = in.readLong();
        lastUpdated = tmpDate == -1 ? null : new Date(tmpDate); //reading long from parcel to create new date object
        isPublished = in.readByte() != 0; //Getting boolean from parcel
        contentTypeId = in.readInt();
        image = (URL) in.readValue(URL.class.getClassLoader());
        bigImage = (URL) in.readValue(URL.class.getClassLoader());
        userProfileId = in.readInt();
        shortDescription = in.readString();
        title = in.readString();
        description = in.readString();
    }

    public static final Parcelable.Creator<News> CREATOR = new Parcelable.Creator<News>() {
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        public News[] newArray(int size) {
            return new News[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    public int getContentTypeId() {
        return contentTypeId;
    }

    public void setContentTypeId(int contentTypeId) {
        this.contentTypeId = contentTypeId;
    }

    public URL getImage() {
        return image;
    }

    public void setImage(URL image) {
        this.image = image;
    }

    public URL getBigImage() {
        return bigImage;
    }

    public void setBigImage(URL bigImage) {
        this.bigImage = bigImage;
    }

    public int getUserProfileId() {
        return userProfileId;
    }

    public void setUserProfileId(int userProfileId) {
        this.userProfileId = userProfileId;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
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
}
