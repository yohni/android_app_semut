package id.sisemut.apps.Model;

public class MemberGroup {

    private int id;
    private String member_name;

    public MemberGroup(String member_name) {
        this.member_name = member_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

}
