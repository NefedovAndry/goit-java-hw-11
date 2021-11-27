/*
Напишите программу, которая выводит в консоль строку, состоящую из чисел от 1 до n, но с заменой некоторых значений:

если число делится на 3 - вывести "fizz"
если число делится на 5 - вывести "buzz"
если число делится на 3 и на 5 - вывести "fizzbuzz"

Например, для n = 15, ожидаемый результат:
1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz.

Это задание 2 в однопоточном исполнении.
*/

import java.util.Objects;

public class NumbersChanger {
    public static void main(String[] args) {
        new NumberToString1(15);
    }
}

class NumberToString1 {
    Integer value;
    Integer bufferValue;
    StringBuilder result;

    public NumberToString1(int value) {
        this.value = value;
        this.bufferValue = 1;
        changer();
    }

    public void changer() {
        for (int i = 0; i < value; i++) {
            this.result = new StringBuilder(bufferValue.toString());
            fizz();
            buzz();
            fizzbuzz();
            number();
            bufferValue++;
        }
    }

    public void number() {
        if (!Objects.equals(bufferValue, value)) {
            result.append(", ");
        }
        System.out.print(result);
    }

    public void fizzbuzz() {
        if (bufferValue % 3 == 0 && bufferValue % 5 == 0) {
            this.result = new StringBuilder("fizzbuzz");
        }
    }

    public void fizz() {
        if (bufferValue % 3 == 0) {
            this.result = new StringBuilder("fizz");
        }
    }

    public void buzz() {
        if (bufferValue % 5 == 0) {
            this.result = new StringBuilder("buzz");
        }
    }
}