package java17.ex02;

import java.util.List;

import org.junit.Test;

import java17.data.Data;
import java17.data.Person;

/**
 * Exercice 02 - Redéfinition
 */
public class Method_02_Test {

    // tag::IDao[]
    interface IDao {
        List<Person> findAll();

        //  créer une méthode String format()
        //  la méthode retourne une chaîne de la forme [<nb_personnes> persons]
        //  exemple de résultat : "[14 persons]", "[30 persons]"
        default String format() {
            int nbPersonnes = findAll().size();
            return "[" + nbPersonnes + " persons]";
        }
    }
    // end::IDao[]

    // tag::DaoA[]
    class DaoA implements IDao {

        List<Person> people = Data.buildPersonList(20);

        @Override
        public List<Person> findAll() {
            return people;
        }

        //  redéfinir la méthode String format()
        //  la méthode retourne une chaîne de la forme DaoA[<nb_personnes> persons]
        //  exemple de résultat : "DaoA[14 persons]", "DaoA[30 persons]"
        //  l'implémentation réutilise la méthode format() de l'interface

        @Override
        public String format() {
//            int nbPersonnes = findAll().size();
//            return "DaoA[" + nbPersonnes + " persons]";
            return getClass().getSimpleName() + IDao.super.format(); // réutilise le nom de la classe & récupère methode d'une interface mère
        }
    }
    // end::DaoA[]

    @Test
    public void test_daoA_format() throws Exception {

        DaoA daoA = new DaoA();

        //  invoquer la méthode format() pour que le test soit passant
        String result = daoA.format();

        assert "DaoA[20 persons]".equals(result);
    }
}
