package dao;

import org.springframework.stereotype.Component;

@Component("dao")//instancier cette classe sous le nom "dao" (un objet "dao")
public class IDaoImpl implements IDao {
    @Override
    public double getData() {

        /*
         * Se connecter a la BD pour recuperer la temperature
         * */
        System.out.println("Version base de donnees");
        double temp = Math.random() * 40;

        return temp;
    }
}
