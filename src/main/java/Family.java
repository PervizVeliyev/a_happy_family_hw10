import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Family implements HumanCreator{
    private Human mother;
    private Human father;
    private Set<Pet> pets;
    private List<Human> children;

    static {
        System.out.println("A new family is being loaded");
    }

    {
        System.out.println("A new family object is created");
    }

    public Human getMother() {
        return mother;
    }

    public void setMother(Human mother) {
        this.mother = mother;
    }

    public Human getFather() {
        return father;
    }

    public void setFather(Human father) {
        this.father = father;
    }

    public Set<Pet> getPets() {
        if(pets == null)  pets = new HashSet<>();
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }

    public List<Human> getChildren() {
        if(children == null) children = new ArrayList<>();
        return children;
    }

    public void setChildren(List<Human> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Family{" +
                "mother=" + mother +
                ", father=" + father +
                ", pet=" + pets +
                ", children=" + children +
                '}';
    }

    public Family(Human mother, Human father) {
        this.mother = mother;
        this.father = father;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Family family = (Family) o;
        return mother.equals(family.mother) && father.equals(family.father);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mother, father);
    }

    @Override
    protected void finalize() {
        System.out.println("Family object is destroyed");
    }

    Random random = new Random();

    public void addChild(Human child){
        this.getChildren().add(child);
        child.setFamily(this);
    }

    public boolean deleteChild(int index) {
        try {Human deletedOne = this.getChildren().get(index);
            this.getChildren().remove(index);
            deletedOne.setFamily(null);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public void deleteChild(Human child){
        List<Human> tempList = new ArrayList<>();
        for(Human deleted: this.getChildren()){
            if(!deleted.equals(child)) {
                tempList.add(deleted);
            }
            else deleted.setFamily(null);
        }
        this.setChildren(tempList);
    }

    public int countFamily() {
        return this.getChildren().size() + 2;
    }

    public List<String> maleNames = Arrays.asList("Elsen","Azer","Rasim","Veli","Senan","Mehemmed","Eldar","Elvin","Rauf","Terlan","Rafael",
            "Nebi","Letif","Ferid","Vusal","Eli","Cemil","Behruz","Cavad","Teymur","Cavid","Seymur","Perviz");

    public List<String> femaleNames = Arrays.asList("Nigar","Aytac","Aygun","Ruqiyye","Gulsen","Fatime","Inci","Fidan","Aydan","Dilsad","Benovse",
            "Firuze","Nilufer","Gulare","Gulay","Leman","Xeyale","Meryem","Nezrin","Arzu","Pervane","Elmira");

    @Override
    public Human bornChild() {
        Human childBorned = null;
        int choice  = random.nextInt(0, 2);
        LocalDate localDate = LocalDate.now();

        if(choice == 1){
            childBorned = new Man(maleNames.get(random.nextInt(0, maleNames.size())), this.getFather().getSurname(), localDate.format(DateTimeFormatter. ofPattern("dd/MM/yyyy")),
                    (this.getFather().getIq() + this.getMother().getIq()) / 2, null);
        }
        if(choice == 0){
            childBorned = new Woman(femaleNames.get(random.nextInt(0, femaleNames.size())), this.getMother().getSurname(), localDate.format(DateTimeFormatter. ofPattern("dd/MM/yyyy")),
                    (this.getFather().getIq() + this.getMother().getIq()) / 2, null);
        }
        this.addChild(childBorned);

        return childBorned;
    }
}
