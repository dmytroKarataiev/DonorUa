package ua.com.kathien.donorua.models;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

import java.net.URL;

/**
 * Created by kathien on 8/11/16.
 */
public class Center implements Parcelable{

    private String street;
    private int cityId;
    private Gender gender;
    private boolean ignoreRegion;
    private String name;
    private String description;
    private String about;
    private URL website;
    private int id;
    private String email;
    private String phone;
    private Location loc;
    private String cityName;

    public Center(String street, int cityId, String cityName, Gender gender, boolean ignoreRegion, String name, String description, String about,
                  URL website, int id, String email, String phone, Location loc){
        this.street = street;
        this.cityId = cityId;
        this.gender = gender;
        this.ignoreRegion = ignoreRegion;
        this.name = name;
        this.description = description;
        this.about = about;
        this.website = website;
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.loc = loc;
        this.cityName = cityName;

    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public boolean isIgnoreRegion() {
        return ignoreRegion;
    }

    public void setIgnoreRegion(boolean ignoreRegion) {
        this.ignoreRegion = ignoreRegion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public URL getWebsite() {
        return website;
    }

    public void setWebsite(URL website) {
        this.website = website;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(street);
        dest.writeInt(cityId);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(about);
        dest.writeInt(id);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeString(cityName);
        gender.writeToParcel(dest, flags);
        dest.writeByte((byte)(ignoreRegion ? 1 : 0)); //Writing boolean into parcel. If ignoreRegion == true, byte == 1
        dest.writeValue(website);
        loc.writeToParcel(dest, flags);
    }

    private Center(Parcel in) {
        street = in.readString();
        cityId = in.readInt();
        name = in.readString();
        description = in.readString();
        about = in.readString();
        id = in.readInt();
        email = in.readString();
        phone = in.readString();
        cityName = in.readString();
        gender = Gender.CREATOR.createFromParcel(in);
        ignoreRegion = in.readByte() != 0; //Getting boolean from parcel
        website = (URL) in.readValue(URL.class.getClassLoader());
        loc = Location.CREATOR.createFromParcel(in);
    }

    public static final Parcelable.Creator<Center> CREATOR = new Parcelable.Creator<Center>() {
        public Center createFromParcel(Parcel in) {
            return new Center(in);
        }

        public Center[] newArray(int size) {
            return new Center[size];
        }
    };




    public enum Gender implements Parcelable {
        ANYONE,
        MALE,
        FEMALE;

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(final Parcel dest, final int flags) {
            dest.writeInt(ordinal());
        }

        public static final Creator<Gender> CREATOR = new Creator<Gender>() {
            @Override
            public Gender createFromParcel(final Parcel source) {
                return Gender.values()[source.readInt()];
            }

            @Override
            public Gender[] newArray(final int size) {
                return new Gender[size];
            }
        };

    }

    @Override
    public String toString() {
        return "Center{\n" +
                "id=" + id + '\n' +
                "Address=" + street + '\n' +
                "Name=" + name + '\n' +
                "}\n";
    }
}
