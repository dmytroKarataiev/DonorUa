package ua.com.kathien.donorua.models;

/**
 * Created by kathien on 8/11/16.
 */
public class City {

    private int id;
    private String nameUA;
    private String nameEN;
    private String nameRU;
    private int regionID;

    public City(int id, String nameUA, String nameEN, String nameRU, int regionID) {
        this.id = id;
        this.nameUA = nameUA;
        this.nameEN = nameEN;
        this.nameRU = nameRU;
        this.regionID = regionID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameUA() {
        return nameUA;
    }

    public void setNameUA(String nameUA) {
        this.nameUA = nameUA;
    }

    public String getNameEN() {
        return nameEN;
    }

    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
    }

    public String getNameRU() {
        return nameRU;
    }

    public void setNameRU(String nameRU) {
        this.nameRU = nameRU;
    }

    public int getRegionID() {
        return regionID;
    }

    public void setRegionID(int regionID) {
        this.regionID = regionID;
    }
}
