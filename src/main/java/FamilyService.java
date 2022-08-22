import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class FamilyService {
    private static FamilyDao familyDao = new CollectionFamilyDao();

    List<Family> getAllFamilies(){
        return familyDao.getAllFamilies();
    }

    void displayAllFamilies(){
        familyDao.getAllFamilies().forEach
                (family -> System.out.printf("%d) %s\n",familyDao.getAllFamilies().indexOf(family), family));
    }

    //I could display the information with starting index of 1 for database point of view.
    //But, I didn't for indexing principe of Java language. So, I hope it won't be a problem seeing our data starting from 0 index.

    List<Family> getFamiliesBiggerThan(int number){
        List<Family> helper = new ArrayList<>();
        for(int i = 0; i< familyDao.getAllFamilies().size(); i++) {
            if (familyDao.getFamilyByIndex(i).countFamily() > number) helper.add(familyDao.getFamilyByIndex(i));
        }
        return helper;
    }

    List<Family> getFamiliesLessThan(int number){
        List<Family> helper = new ArrayList<>();
        for(int i = 0; i< familyDao.getAllFamilies().size(); i++) {
            if (familyDao.getFamilyByIndex(i).countFamily() < number) helper.add(familyDao.getFamilyByIndex(i));
        }
        return helper;
    }

    int countFamiliesWithMemberNumber(int number){
        int count = 0;
        for(int i = 0; i < familyDao.getAllFamilies().size();i++){
            if(familyDao.getFamilyByIndex(i).countFamily() == number) count++;
        }
        return count;
    }

    void createNewFamily(Human mother, Human father){
        Family familyCreated = new Family(mother, father);
        familyDao.saveFamily(familyCreated);
    }

    boolean deleteFamilyByIndex(int index){
        return familyDao.deleteFamily(index);
    }

    Family bornChild(Family family, String masculine, String feminine){
        Human bornedChild = family.bornChild();
        if(bornedChild instanceof Man) bornedChild.setName(masculine);
        else bornedChild.setName(feminine);
        familyDao.saveFamily(family);
        return family;
    }

    Family adoptChild(Family family, Human child){
        family.addChild(child);
        if(child instanceof Man) family.getChildren().get(family.getChildren().size() - 1).setSurname(family.getFather().getSurname());
        else if(child instanceof Woman) family.getChildren().get(family.getChildren().size() - 1).setSurname(family.getMother().getSurname());
        familyDao.saveFamily(family);
        return family;
    }

    void deleteAllChildrenOlderThan(int age){
        for(int i = 0; i < familyDao.getAllFamilies().size(); i++){
            Iterator<Human> iterator = familyDao.getFamilyByIndex(i).getChildren().iterator();
            while(iterator.hasNext()){
                Human element = iterator.next();
                LocalDate localDate = Instant.ofEpochMilli(element.getBirthDate()).atZone(ZoneId.systemDefault()).toLocalDate().plusDays(1);
                if(LocalDate.now().getYear() - localDate.getYear() > age) familyDao.getFamilyByIndex(i).deleteChild(element);
            }
            familyDao.saveFamily(familyDao.getFamilyByIndex(i));
        }
    }

    int count(){
        return familyDao.getAllFamilies().size();
    }

    Family getFamilyById(int index){
        return familyDao.getFamilyByIndex(index);
    }

    Set<Pet> getPets(int index){
        return familyDao.getFamilyByIndex(index).getPets();
    }

    void addPet(Pet pet, int index){
        familyDao.getFamilyByIndex(index).getPets().add(pet);
        familyDao.saveFamily(familyDao.getFamilyByIndex(index));
    }
}
