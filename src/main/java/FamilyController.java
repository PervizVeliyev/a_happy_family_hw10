import java.util.List;
import java.util.Set;

public class FamilyController {
    private static FamilyService familyService = new FamilyService();

    List<Family> getAllFamilies() {
        return familyService.getAllFamilies();
    }

    void displayAllFamilies() {
        familyService.displayAllFamilies();
    }

    List<Family> getFamiliesBiggerThan(int number) {
        return familyService.getFamiliesBiggerThan(number);
    }

    List<Family> getFamiliesLessThan(int number) {
        return familyService.getFamiliesLessThan(number);
    }

    int countFamiliesWithMemberNumber(int number){return familyService.countFamiliesWithMemberNumber(number);}

    void createNewFamily(Human father, Human mother) {
        familyService.createNewFamily(father, mother);
    }

    boolean deleteFamilyByIndex(int index) {
        return familyService.deleteFamilyByIndex(index);
    }

    Family bornChild(Family family, String masculine, String feminine) {
        return familyService.bornChild(family, masculine, feminine);
    }

    Family adoptChild(Family family, Human child) {
        return familyService.adoptChild(family, child);
    }

    void deleteAllChildrenOlderThan(int age) {
        familyService.deleteAllChildrenOlderThan(age);
    }

    int count() {
        return familyService.count();
    }

    Family getFamilyById(int index) {
        return familyService.getFamilyById(index);
    }

    Set<Pet> getPets(int index) {
        return familyService.getPets(index);
    }

    void addPet(Pet pet, int index) {
        familyService.addPet(pet, index);
    }
}
