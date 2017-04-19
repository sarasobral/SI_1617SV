package serie3.java;

import serie3.java.entities.*;

/**
 * Created by Utilizador on 15/01/2017.
 */
public class PEP {
    public static void main(String[] args) {

        Objecto o1 = new Objecto("File1");
        Objecto o2 = new Objecto("File2");
        Objecto o3 = new Objecto("File3");
        Objecto o4 = new Objecto("Program1");

        PDP.oldmain();
        User alice = PDP.alice;
        User bob = PDP.bob;
        User charlie = PDP.charlie;
        Permission pr = PDP.pr;
        Permission pw = PDP.pw;
        Permission pe = PDP.pe;

        Autorization aut1 = new Autorization(alice, pr, o1);
        Autorization aut2 = new Autorization(alice, pw, o1);
        Autorization aut3 = new Autorization(alice, pr, o2);
        Autorization aut4 = new Autorization(alice, pw, o2);
        Autorization aut5 = new Autorization(alice, pe, o4);
        Autorization aut6 = new Autorization(bob, pr, o1);
        Autorization aut7 = new Autorization(bob, pr, o3);
        Autorization aut8 = new Autorization(bob, pw, o3);
        Autorization aut9 = new Autorization(charlie, pr, o1);
        Autorization aut10 = new Autorization(charlie, pr, o2);
        Autorization aut11 = new Autorization(charlie, pw, o4);
        Autorization aut12 = new Autorization(charlie, pe, o4);
    }
}