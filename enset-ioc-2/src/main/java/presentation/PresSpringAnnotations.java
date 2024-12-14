package presentation;

import metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PresSpringAnnotations {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(
                "dao", "metier");
        //donne moi un bean qui implemente l'interface Imetier
        IMetier metier = context.getBean(IMetier.class);
        System.out.println(metier.calcul());
    }
}
