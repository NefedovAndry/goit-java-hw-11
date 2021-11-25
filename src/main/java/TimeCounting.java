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

    public static void main(String[] args) {
        Timer timer = new Timer();
        new Thread(() -> {
            int count = 1;
            while (true) {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(count + " seconds passed");
                timer.setTime(count);
                count++;
            }
        }, "Counter").start();

        new Thread(() -> {
            int time = 0;
            while (true) {
                time = timer.getTime();
                if (time != 0 && time % 5 == 0) {
                    System.out.println("5 seconds left");
                }
            }
        }, "Messenger").start();
    }

    static class Timer {
        int time = 0;
        boolean isDelivered = false;

        public synchronized void setTime(int time) {
            this.time = time;
            isDelivered = false;
            notify();
        }

        public synchronized int getTime() {
            while (isDelivered) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            isDelivered = true;
            return time;
        }
    }
}
