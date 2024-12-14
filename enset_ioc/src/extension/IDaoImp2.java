package extension;

import dao.IDao;

public class IDaoImp2 implements IDao {
    @Override
    public double getData() {
        System.out.println("Version Capteurs");
        double temp = 1000;
        return temp;
    }
}
