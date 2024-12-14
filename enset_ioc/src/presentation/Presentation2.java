package presentation;

import dao.IDao;
import metier.IMetier;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Presentation2 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(new File("src/config.txt"));
        String daoClassName = scanner.nextLine();
        Class cDao = Class.forName(daoClassName);
        IDao dao = (IDao) cDao.newInstance();
        System.out.println(dao.getData());

        String metierClassName = scanner.nextLine();
        Class cmetier = Class.forName(metierClassName);
        IMetier metier = (IMetier) cmetier.newInstance();

        Method method = cmetier.getMethod("setDao", IDao.class);
        //metier.setDao(dao)
        method.invoke(metier, dao);
        System.out.println("Resultat=>"+metier.calcul());


    }
}

