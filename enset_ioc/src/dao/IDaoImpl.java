package dao;

public class IDaoImpl implements IDao{
    @Override
    public double getData() {

        /*
        * Se connecter a la BD pour recuperer la temperature
        * */
        System.out.println("Version base de donnees");
        double temp = Math.random()*40;

        return temp;
    }
}
