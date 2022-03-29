import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<String> array1 = new ArrayList<>();
    static ArrayList<String> array2 = new ArrayList<>();
    static ArrayList<String> array3 = new ArrayList<>();

    //коэффицент сходства
    static double Koff=0.45;

    //Метод сходства строк (Левенштейн расстояние)
    public static int computeEditDistance(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        int[] costs = new int[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            int lastValue = i;
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0) {
                    costs[j] = j;
                } else {
                    if (j > 0) {
                        int newValue = costs[j - 1];
                        if (s1.charAt(i - 1) != s2.charAt(j - 1)) {
                            newValue = Math.min(Math.min(newValue, lastValue),
                                    costs[j]) + 1;
                        }
                        costs[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }
            if (i > 0) {
                costs[s2.length()] = lastValue;
            }
        }
        return costs[s2.length()];
    }

    public static double compareString (String s1, String s2) {
        double similarityOfStrings = 0.0;
        int editDistance = 0;
        if (s1.length() < s2.length()) {
            String swap = s1;
            s1 = s2;
            s2 = swap;
        }
        int bigLen = s1.length();
        editDistance = computeEditDistance(s1, s2);
        if (bigLen == 0) {
            similarityOfStrings = 1.0;
        } else {
            similarityOfStrings = (bigLen - editDistance) / (double) bigLen;
        }

        return similarityOfStrings;
    }


    public static void main(String[] args) {

        //Добавляем значения в первую коллекцию(множество)
        Scanner in = new Scanner(System.in);
        System.out.print("Введите количество строк в 1 множестве: ");
        int num = in.nextInt();

        for (int i = 1; i <= num; i++) {
            Scanner znachenie = new Scanner(System.in);
            System.out.println("Введите " + i + " значение первого множества");
            String value = znachenie.nextLine();
            array1.add(value);
        }

        //Добавляев значения во вторую коллекцию (множество)
        Scanner in2 = new Scanner(System.in);
        System.out.print("Введите количество строк во 2 множестве: ");
        int num2 = in.nextInt();

        for (int i = 1; i <= num2; i++) {
            Scanner znachenie2 = new Scanner(System.in);
            System.out.println("Введите " + i + " значение второго множества");
            String value = znachenie2.nextLine();
            array2.add(value);
        }

        boolean found=false;

        for(int i = 0;i<array1.size();i++) {
            for ( int j = 0; j < array2.size(); j++) {
                if (compareString(array1.get(i), array2.get(j)) >= Koff) {
                    array3.add(array1.get(i) + ":" + array2.get(j));
                    found=true;
                }
            }
            if(!found){
                array3.add(array1.get(i) + "?");
            }
        }


        // Выводим полученное множество после сравнения строк
        for(String s:array3) {
            System.out.println(s);
        }

    }
}

