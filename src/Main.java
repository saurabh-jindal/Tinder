import service.Service;

public class Main {

    public static void main(String[] args) {
        Service service = Service.getInstance();
        service.createUser("userA", 2, 3, 23, "MALE");
        service.createUser("userB", 3, 4, 45, "FEMALE");
        service.createUser("userC", 4, 6, 32, "FEMALE");
        service.createUser("userD", 7, 8, 23, "MALE");
        service.createUser("userE", 8, 12, 23, "FEMALE");
        service.potentialMatches("userA");
        service.like("userA", "userB");
        service.like("userA", "userC");
        service.potentialMatches("userB");
        service.like("userB", "userA");
        service.showMatches("userA");
        service.potentialMatches("userE");
        service.like("userE", "userD");
        service.potentialMatches("userD");
        service.like("userD", "userE");
        service.showAllMatches();
        service.potentialMatches("userC");
        service.like("userC", "userA");
        service.showAllMatches();
        service.deleteAccount("userA");
        service.showAllMatches();
    }
}