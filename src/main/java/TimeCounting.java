/*
Задание 1.
Напишите программу, которая каждую секунду отображает на экране данные о времени, прошедшем от начала
сессии (запуска программы).
Другой ее поток каждые 5 секунд выводит сообщение: "Прошло 5 секунд".
Предусмотрите возможность ежесекундного оповещения потока, воспроизводящего сообщение о пяти секундах, потоком,
отсчитывающим время.
*/

import static java.lang.Thread.sleep;

public class TimeCounting {
    static int time = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            int count = 0;
            while (true) {
                setTime(count++);
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Counter").start();

        new Thread(() -> {
            while (true) {
                if (getTime() % 5 == 0) {
                    System.out.println("5 seconds left");
                }
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Messenger").start();

    }

    public static synchronized void setTime(int time) {
        TimeCounting.time = time;
        notify();
    }

    public static synchronized int getTime() {
        return time;
    }
}
