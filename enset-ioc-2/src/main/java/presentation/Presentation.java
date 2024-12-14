package presentation;

import dao.IDaoImpl;
import extension.IDaoImp2;
import metier.MetierImpl;

public class Presentation {
    public static void main(String[] args) {
        /*
        * Injection des dependances par instanciation statique => new
        * */
        IDaoImpl dao = new IDaoImpl();
        MetierImpl metier = new MetierImpl(dao);
        //metier.setDao(dao);
        System.out.println("Resultat="+metier.calcul());
    }
}
