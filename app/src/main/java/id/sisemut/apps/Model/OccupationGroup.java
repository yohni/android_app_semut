package id.sisemut.apps.Model;

public class OccupationGroup {

    private int id;
    private String occupation_name;

    public OccupationGroup(String occupation_name) {
//        this.id = id;
        this.occupation_name = occupation_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOccupation_Name() {
        return occupation_name;
    }

    public void setOccupation_Name(String occupation_name) {
        this.occupation_name = occupation_name;
    }

}
