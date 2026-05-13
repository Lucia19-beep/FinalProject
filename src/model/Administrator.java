package model;

public class Administrator extends User{
    int accessLevel;

    public Administrator(String name, int id, String address,int accessLevel) {
        super(name, id, address);
        this.accessLevel=accessLevel;
    }
    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }
}
