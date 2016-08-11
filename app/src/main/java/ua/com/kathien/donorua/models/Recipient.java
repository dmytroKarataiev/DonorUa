package ua.com.kathien.donorua.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.net.URL;
import java.util.Date;

/**
 * Created by kathien on 8/11/16.
 */
public class Recipient implements Parcelable {


    protected int id;
    protected String email;
    protected String phone;
    protected String firstName;
    protected String lastName;
    protected String middleName;
    protected Date dateOFBirth;
    protected String center;
    protected URL photoImage;
    protected String contactPerson;
    protected boolean isUrgent;
    protected Date dateAdded;
    protected String bloodType;
    protected String disease;
    protected String description;
    protected int neededDonorsCount;
    protected String city;
    protected int cityId;
    protected int centerId;
    protected String centerAddress;
    protected URL url;

    public Recipient(int id, String email, String phone, String firstName, String lastName, String middleName, Date dateOFBirth, String center,
                     URL photoImage, String contactPerson, boolean isUrgent, Date dateAdded, String bloodType, String disease, String description,
                     int neededDonorsCount, String city, int cityId, int centerId, String centerAddress, URL url) {

        this.id = id;
        this.email = email;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.dateOFBirth = dateOFBirth;
        this.center = center;
        this.photoImage = photoImage;
        this.contactPerson = contactPerson;
        this.isUrgent = isUrgent;
        this.dateAdded = dateAdded;
        this.bloodType = bloodType;
        this.disease = disease;
        this.description = description;
        this.neededDonorsCount = neededDonorsCount;
        this.city = city;
        this.cityId = cityId;
        this.centerId = centerId;
        this.centerAddress = centerAddress;
        this.url = url;

    }

    @Override
    public String toString() {
        return "Recipient{\n" +
                "id: " + id + '\n' +
                "Name: " + firstName + ' ' + lastName + '\n' +
                "Date of birth: " + dateOFBirth + '\n' +
                "Date added: " + dateAdded + '\n' +
                "BloodType: " + bloodType + '\n' +
                "Disease: " + disease + '\n' +
                "Description: " + description + '\n' +
                "}\n";
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(middleName);
        dest.writeString(center);
        dest.writeString(contactPerson);
        dest.writeString(bloodType);
        dest.writeString(disease);
        dest.writeString(description);
        dest.writeInt(neededDonorsCount);
        dest.writeString(city);
        dest.writeInt(cityId);
        dest.writeInt(centerId);
        dest.writeString(centerAddress);
        dest.writeLong(dateOFBirth != null ? dateOFBirth.getTime() : -1); //writing date as long. if date is null, write -1
        dest.writeLong(dateAdded != null ? dateAdded.getTime() : -1); //writing date as long. if date is null, write -1
        dest.writeValue(photoImage);
        dest.writeValue(url);
        dest.writeByte((byte)(isUrgent ? 1 : 0)); //Writing boolean into parcel. If isUrgent == true, byte == 1
    }

    private Recipient(Parcel in) {
        id = in.readInt();
        email = in.readString();
        phone = in.readString();
        firstName = in.readString();
        lastName = in.readString();
        middleName = in.readString();
        center = in.readString();
        contactPerson = in.readString();
        bloodType = in.readString();
        disease = in.readString();
        description = in.readString();
        neededDonorsCount = in.readInt();
        city = in.readString();
        cityId = in.readInt();
        centerId = in.readInt();
        centerAddress = in.readString();
        long tmpDate = in.readLong();
        dateOFBirth = tmpDate == -1 ? null : new Date(tmpDate); //reading long from parcel to create new date object
        tmpDate = in.readLong();
        dateAdded = tmpDate == -1 ? null : new Date(tmpDate); //reading long from parcel to create new date object
        photoImage = (URL) in.readValue(URL.class.getClassLoader());
        url = (URL) in.readValue(URL.class.getClassLoader());
        isUrgent = in.readByte() != 0; //Getting boolean from parcel
    }

    public static final Parcelable.Creator<Recipient> CREATOR = new Parcelable.Creator<Recipient>() {
        public Recipient createFromParcel(Parcel in) {
            return new Recipient(in);
        }

        public Recipient[] newArray(int size) {
            return new Recipient[size];
        }
    };


}