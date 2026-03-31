package smartuniversitylibrarymanagementsystem;

class Faculty implements User {
    private String name;
    public Faculty(String name) { this.name = name; }
    @Override public void update(String msg) { System.out.println("[Faculty " + name + "] Notification: " + msg); }
    @Override public void showRole() { System.out.println("Role: Faculty (30-day limit)"); }
}

