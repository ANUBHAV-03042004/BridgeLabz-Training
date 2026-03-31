package smartuniversitylibrarymanagementsystem;

class UserFactory {
    public static User createUser(String type, String name) {
        if (type.equalsIgnoreCase("STUDENT")) return new Student(name);
        if (type.equalsIgnoreCase("FACULTY")) return new Faculty(name);
        return null;
    }
}