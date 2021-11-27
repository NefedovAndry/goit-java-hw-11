/*Задание 2
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

import static java.lang.Thread.sleep;

public class NumbersEquivalent {
    public static void main(String[] args) {
        NumberToString numberToString = new NumberToString(15);
        new Thread(() -> {
            while (!numberToString.isDone) {
                try {
                    numberToString.number();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
        new Thread(() -> {
            while (!numberToString.isDone) {
                try {
                    numberToString.fizzbuzz();
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
        new Thread(() -> {
            while (!numberToString.isDone) {
                try {
                    numberToString.fizz();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(() -> {
//            new NumberToString();
            while (!numberToString.isDone) {
                try {
                    numberToString.buzz();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
    }

}

class NumberToString {
    Integer value;
    Integer bufferValue;
    boolean isFizzBuzz = false;
    boolean isFizz = false;
    boolean isBuzz = false;
    boolean isManaged = false;
    boolean isDone;
    StringBuilder result;
    final Object monitorA = new Object();
    final Object monitorB = new Object();

    public NumberToString(int value) {
        this.value = value;
        this.bufferValue = 1;
        this.isDone = false;
    }

    public void number() throws InterruptedException {
        this.result = new StringBuilder(bufferValue);
        isManaged = false;
        isFizzBuzz = false;
        isFizz = false;
        isBuzz = false;
        synchronized (monitorA) {
            wait();
        }
//        while (!isFizzBuzz && !isFizz && !isBuzz) {
////            wait();
//        }
        if (!Objects.equals(bufferValue, value)){
            result.append(", ");
        } else {
            isDone = true;
        }
        System.out.print(result);
        bufferValue++;
        isManaged = true;
    }

    public void fizzbuzz() throws InterruptedException {
        synchronized (monitorA) {
            if (bufferValue % 3 == 0 && bufferValue % 5 == 0) {
                this.result = new StringBuilder("fizzbuzz");
            }
            isFizzBuzz = true;
        }
        synchronized (monitorB) {
            notifyAll();
        }
        synchronized (monitorA) {
            notify();
        }
    }

    public void fizz() throws InterruptedException {
        synchronized (monitorB) {
            wait();
        }
        if (bufferValue % 3 == 0) {
            this.result = new StringBuilder("fizz");
        }
//        isFizz = true;
    }

    public void buzz() throws InterruptedException {
        synchronized (monitorB) {
            wait();
        }
        if (bufferValue % 5 == 0) {
            this.result = new StringBuilder("buzz");
        }
//        isBuzz = true;
    }


}
