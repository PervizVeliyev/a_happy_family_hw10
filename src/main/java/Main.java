import java.util.Arrays;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {

        Dog dog = new Dog("Danny", 6, 56, new HashSet<>(Arrays.asList("eat", "run", "sleep")));
        DomesticCat cat = new DomesticCat("Garfield", 8, 90, new HashSet<>(Arrays.asList("sleep", "watch Tv", "eat")));

        Human mother1 = new Human("Arzu","Ismayilova","21/04/1975", 90, null);
        Human father1 = new Human("Senan","Ismayilov","12/12/1975", 94, null);
        Human mother2 = new Human("Rima", "Quliyeva", "11/12/1980");
        Human father2 = new Human("Rasim", "Quliyev", "10/02/1980");
        Human mother3 = new Human("Esmira", "Eliyeva", "05/05/1981");
        Human father3 = new Human("Azer", "Eliyev", "12/12/1981");
        Human child1 = new Human("Elnur","Ismayilov","12/02/2000",94, null);
        Human child2 = new Human("Emil","Ismayilov","11/12/1996",92, null);
        Human child3 = new Human("Elvin","Quliyev","06/06/1995",92, null);

        Human childAdopted = new Human("Asim", "Hemidov", "21/10/2021", 65);    // For the usage of adopted constructor

        FamilyController familyController = new FamilyController();

        familyController.createNewFamily(mother1, father1);
        familyController.createNewFamily(mother2, father2);
        familyController.createNewFamily(mother3, father3);

        familyController.getFamilyById(0).addChild(child1);
        familyController.getFamilyById(0).addChild(child2);
        familyController.getFamilyById(1).addChild(child3);
        familyController.bornChild(familyController.getFamilyById(1), "Asim", "Valide");
        familyController.adoptChild(familyController.getFamilyById(1), childAdopted);
        familyController.addPet(dog,0);
        familyController.addPet(cat,0);

        familyController.displayAllFamilies();

        System.out.println(mother1);
        System.out.println(mother1.describeAge());
    }
}
