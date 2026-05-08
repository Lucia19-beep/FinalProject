public class Employee extends User{
    String position;
    double salary;
    boolean accessPermits;

    public Employee(String name, int id, String address,String position,
                    double salary,boolean accessPermits) {
        super(name, id, address);
        this.position=position;
        this.salary=salary;
        this.accessPermits=accessPermits;
    }
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public boolean isAccessPermits() {
        return accessPermits;
    }

    public void setAccessPermits(boolean accessPermits) {
        this.accessPermits = accessPermits;
    }
}
