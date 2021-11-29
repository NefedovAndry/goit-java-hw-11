package numbers_equivalent;

class ThreadB implements Runnable {
    final NumberToString input;

    public ThreadB(NumberToString input) {
        this.input = input;
    }

    @Override
    public void run() {
        while (!input.isDone) {
            input.buzz();
            if (input.isBuzz) {
                synchronized (input) {
                    notifyAll();
                }
            }
            synchronized (input.newIteration) {
                while (input.isNewIteration) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
