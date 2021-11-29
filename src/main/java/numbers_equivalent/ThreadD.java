package numbers_equivalent;

class ThreadD implements Runnable {
    final NumberToString input;

    public ThreadD(NumberToString input) {
        this.input = input;
    }

    @Override
    public void run() {
        while (!input.isDone) {
            synchronized (input) {
                while (input.isFizzBuzz) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            input.number();
            synchronized (input.newIteration) {
                notifyAll();
            }
        }
    }
}
