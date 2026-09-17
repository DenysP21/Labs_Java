import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {
        Person originalPerson = new Person("Шевченко", "Тарас", 47);
        System.out.println("Початковий об'єкт: " + originalPerson);

        Gson gson = new Gson();
        String jsonPerson = gson.toJson(originalPerson);
        System.out.println("Формат JSON: " + jsonPerson);

        Person restoredPerson = gson.fromJson(jsonPerson, Person.class);
        System.out.println("Відновлений об'єкт: " + restoredPerson);

        boolean isEqual = originalPerson.equals(restoredPerson);
        System.out.println("\nЧи ідентичні об'єкти (equals)? " + isEqual);
    }
}