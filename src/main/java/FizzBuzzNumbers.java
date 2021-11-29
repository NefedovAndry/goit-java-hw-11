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

public class FizzBuzzNumbers {
    public static void main(String[] args) {

        MyIterator myIterator = new MyIterator(16);

        new Thread(() -> {
            while (myIterator.isInWork()) {
                try {
                    myIterator.fizz();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            while (myIterator.isInWork()) {
                try {
                    myIterator.buzz();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            while (myIterator.isInWork()) {
                try {
                    myIterator.fizzbuzz();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread(() -> {
            while (myIterator.isInWork()) {
                try {
                    myIterator.number();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();

    }

    static class MyIterator {
        private final Integer maxValue;
        private Integer result;
        private boolean inWork;

        public MyIterator(Integer maxValue) {
            this.result = 1;
            this.maxValue = maxValue;
            this.inWork = true;
        }

        public void next() {
            result++;
        }

        public void done() {
            this.inWork = false;
        }

        public boolean hasNext() {
            return !Objects.equals(result, maxValue);
        }

        public boolean isInWork() {
            return inWork;
        }

        public synchronized void fizz() throws InterruptedException {
            if (result % 3 == 0 && result % 5 != 0) {
                if (hasNext()) {
                    System.out.print("fizz, ");
                    next();
                } else {
                    System.out.print("fizz");
                    done();
                }
                notifyAll();
            } else {
                wait();
            }
        }

        public synchronized void buzz() throws InterruptedException {
            if (result % 5 == 0 && result % 3 != 0) {
                if (hasNext()) {
                    System.out.print("buzz, ");
                    next();
                } else {
                    System.out.print("buzz");
                    done();
                }
                notifyAll();
            } else {
                wait();
            }
        }

        public synchronized void fizzbuzz() throws InterruptedException {
            if (result % 3 == 0 && result % 5 == 0) {
                if (hasNext()) {
                    System.out.print("fizzbuzz, ");
                    next();
                } else {
                    System.out.print("fizzbuzz");
                    done();
                }
                notifyAll();
            } else {
                wait();
            }
        }

        public synchronized void number() throws InterruptedException {
            if (result % 3 != 0 && result % 5 != 0){
                if (hasNext()) {
                    System.out.print(result + ", ");
                    next();
                } else {
                    System.out.print(result);
                    done();
                }
                notifyAll();
            } else {
                wait();
            }
        }
    }
}