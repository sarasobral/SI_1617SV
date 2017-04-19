package serie3.java;

import serie3.java.entities.Permission;
import serie3.java.entities.Role;
import serie3.java.entities.User;

public class PDP {

    public static User alice, bob, charlie;
    public static Permission pr, pw, pe;

    //   public static void main(String[] args) {
    public static void oldmain() {
        alice = new User("Alice");
        bob = new User("Bob");
        charlie = new User("Charlie");
        Role projectSupervisor = new Role("Project Supervisor");
        Role testEng = new Role("Test Engineer");
        Role programmer = new Role("Programmer");
     //   Role projectMember = new Role("Project Member");
        pr = new Permission("read");
        pw = new Permission("write");
        pe = new Permission("execute");

        alice.addRole(testEng);
        bob.addRole(programmer);
        charlie.addRole(projectSupervisor);
        testEng.addPermission(pr);
        testEng.addPermission(pw);
        testEng.addPermission(pe);
        programmer.addPermission(pr);
        programmer.addPermission(pw);
        projectSupervisor.addPermission(pr);
        projectSupervisor.addPermission(pw);
        projectSupervisor.addPermission(pe);
    }
}