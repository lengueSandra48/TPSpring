package presentation;

import dao.IDaoImpl;
import extension.IDaoImp2;
import metier.MetierImpl;

public class Presentation {
    public static void main(String[] args) {
        /*
        * Injection des dependances par instanciation statique => new
        * */

        MetierImpl metier = new MetierImpl();
        IDaoImp2 dao = new IDaoImp2();
        metier.setDao(dao);
        System.out.println("Resultat="+metier.calcul());
    }
}
