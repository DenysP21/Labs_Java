import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        String[] inputStrings = {"Java", "Programming", "Lab", "JDK", "Development", "Code", "IDE"};

        System.out.println("Вхідний масив: " + Arrays.toString(inputStrings));

        double average = calculateAverageLength(inputStrings);
        System.out.printf("Середня довжина: %.2f символів\n\n", average);

        String[] shorterStrings = getShorterStrings(inputStrings, average);
        System.out.println("Рядки, довжина яких МЕНША за середню:");
        System.out.println(Arrays.toString(shorterStrings));

        String[] longerStrings = getLongerStrings(inputStrings, average);
        System.out.println("\nРядки, довжина яких БІЛЬША за середню:");
        System.out.println(Arrays.toString(longerStrings));
    }

    public static double calculateAverageLength(String[] arr) {
        double sum = 0;
        for (String s : arr) {
            sum += s.length();
        }
        return sum / arr.length;
    }

    public static String[] getShorterStrings(String[] arr, double average) {
        int count = 0;
        for (String s : arr) {
            if (s.length() < average) {
                count++;
            }
        }

        String[] result = new String[count];
        int index = 0;
        for (String s : arr) {
            if (s.length() < average) {
                result[index] = s;
                index++;
            }
        }
        return result;
    }

    public static String[] getLongerStrings(String[] arr, double average) {
        int count = 0;
        for (String s : arr) {
            if (s.length() > average) {
                count++;
            }
        }

        String[] result = new String[count];
        int index = 0;
        for (String s : arr) {
            if (s.length() > average) {
                result[index] = s;
                index++;
            }
        }
        return result;
    }
}