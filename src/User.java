public class User {
    String name;
    String id;
    String address;

    public User(String name, String id, String address) {
        this.name = name;
        this.id = id;
        this.address = address;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

     public String HashingPassword(String password) {
        StringBuilder newPassword = new StringBuilder();
        char[] passwordParts =  password.toCharArray();

        for(char c : passwordParts) {
            Random rnd = new Random();
            c += (char)(c+rnd.nextInt(1,20));
            newPassword.append(c);
        }

        return newPassword.toString();
    }
}
