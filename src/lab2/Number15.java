package lab2;

import java.util.*;

/**
 * Класс, реализует задание 15 варианта. принимает последовательность чисел, проверяет ее на возрастание
 * повторяющиеся элементы,
 * знакочередование
 */
public class Number15 implements Result {

    private String masString;
    private List<Integer> list = new ArrayList<>();
    private String result;
    private boolean isSequence = false;

    Number15(String masString) {
        this.masString = masString;
        Scanner scanner = new Scanner(masString);
        while (scanner.hasNextInt()) {
            list.add(scanner.nextInt());
        }
        String newString = "";
        for (int Int: list) {
            if (newString.equals("")) {
                newString += String.valueOf(Int);
            }
            else {
                newString += " " + String.valueOf(Int);
            }
        }
        if (newString.equals(masString)) {
            isSequence = true;
            result = "Возрастающая: " + increasing() + ",\r\nповторяющиеся элементы: " + tearing_elements() + ",\r\nзнакочередующаяся: " + alternating();
        }
    }

    private boolean increasing() {
        for (int i=0; i<list.size() - 1; i++) {
            if (list.get(i) >= list.get(i+1))
                return  false;
        }
        return true;
    }

    private boolean tearing_elements() {
        Map<Integer, Integer> map = new HashMap<>();
        for (int number: list) {
            if (map.containsKey(number)) {
                return true;
            } else {
                map.put(number, 1);
            }
        }
        return false;
    }

    private boolean alternating() {
        int i;
        for (i = 0; i<list.size() - 1; i++) {
            System.out.println(i);
            if ( ((list.get(i) >0) && (list.get(i+1) > 0)) || ((list.get(i) <0) && (list.get(i+1) < 0))) {
                return false;
            }

        }
        return true;
    }

    @Override
    public String getResult() {
        if (isSequence) {
            return result;
        }
        return masString;
    }
}
