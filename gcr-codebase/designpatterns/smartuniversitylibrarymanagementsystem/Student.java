package smartuniversitylibrarymanagementsystem;

class Student implements User {
    private String name;
    public Student(String name) { this.name = name; }
    @Override public void update(String msg) { System.out.println("[Student " + name + "] Notification: " + msg); }
    @Override public void showRole() { System.out.println("Role: Student (14-day limit)"); }
}
