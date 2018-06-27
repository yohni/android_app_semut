package id.sisemut.apps.Model;

public class EthnicGroup {

    private int id;
    private String ethnic_name;

    public EthnicGroup(String ethnic_name) {
//        this.id = id;
        this.ethnic_name = ethnic_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEthnic_name() {
        return ethnic_name;
    }

    public void setEthnic_name(String ethnic_name) {
        this.ethnic_name = ethnic_name;
    }
}
