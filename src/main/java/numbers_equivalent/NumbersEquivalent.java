package numbers_equivalent;/*Задание 2
Напишите программу, которая выводит в консоль строку, состоящую из чисел от 1 до n, но с заменой некоторых значений:

если число делится на 3 - вывести "fizz"
если число делится на 5 - вывести "buzz"
если число делится на 3 и на 5 - вывести "fizzbuzz"

Например, для n = 15, ожидаемый результат:
1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz.
Программа должна быть многопоточной, работать с 4 потоками:
Поток A вызывает fizz(), чтобы проверить делимость на 3 и вывести fizz.
Поток B вызывает buzz(), чтобы проверить делимость на 5 и вывести buzz.
Поток C вызывает fizzbuzz(), чтобы проверить делимость на 3 и 5 и вывести fizzbuzz.
Поток D вызывает number(), чтобы вывести число.*/

import java.util.Objects;

public class NumbersEquivalent {
    public static void main(String[] args) {

        NumberToString numberToString = new NumberToString(15);

        Thread threadA = new Thread(new ThreadA(numberToString), "A");
        Thread threadB = new Thread(new ThreadB(numberToString), "B");
        Thread threadC = new Thread(new ThreadC(numberToString), "C");
        Thread threadD = new Thread(new ThreadD(numberToString), "D");

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();

    }
}

