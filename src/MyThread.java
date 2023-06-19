public class MyThread extends Thread {
    public MyThread(ThreadGroup group1, String name) {
        super(group1, name);
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                Thread.sleep(2500);
                System.out.println("Я " + getName() + ". Всем привет!");
            }
        } catch (InterruptedException err) {

        } finally {
            System.out.printf("%s завершен \n", getName());
        }
    }
}
