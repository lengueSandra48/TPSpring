package metier;

import dao.IDao;
import extension.IDaoImp2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MetierImpl implements IMetier{
    //au moment d'instancier metierImpl, si tu trouve l'objet de type IDao parmis les objets deja crees tu l'injectes dans la variable "dao"
    @Autowired //injection des dependances
    private IDao dao; //couplage faible

    public MetierImpl(IDao dao) {
        this.dao = dao;
    }

    @Override
    public double calcul() {
        double tmp = dao.getData();
        double res = tmp * 540/Math.cos(tmp * Math.PI);
        return res;
    }

    /**
     * @param dao
     *  injecter dans la variable dao un objet d'une classe
     *  qui implemente l'interface IDao
     */
    public void setDao(IDao dao) {
        this.dao = dao;
    }
}
