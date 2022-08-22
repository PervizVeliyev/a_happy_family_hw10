import java.util.Iterator;
import java.util.Map;

public final class Man extends Human{
    public Man(String name, String surname, String date, int iq, Map<String,String> schedule) {
        super(name, surname, date, iq, schedule);
    }

    @Override
    public void greetPets() {
        Iterator<Pet> iterator = this.getFamily().getPets().iterator();
        while(iterator.hasNext()) {
            Pet element = iterator.next();
            System.out.printf("Hello, %s! I'm your male owner.\n", element.getNickname());
        }
    }

    public void repairCar() {
        System.out.println("I repaired my car");
    }
}
