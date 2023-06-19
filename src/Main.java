import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        System.out.println("Создаю потоки...\n");
        ThreadGroup group1 = new ThreadGroup("main group1");


        Callable<Integer> myCallable = new MyCallable();
        final ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        final Future<Integer> task = threadPool.submit(myCallable);

        for (int i = 1; i < 5; i++) {
            String name = "Поток " + i;
            final MyThread thread = new MyThread(group1, name);
            thread.setName(name);
            thread.start();
            Thread.sleep(15000);
        }

        int result = task.get();
        System.out.println("Общее количество сообщений, выведенных в консоль: " + result);

        System.out.println("Завершаю все потоки");
        group1.interrupt();
        threadPool.shutdown();
    }

    private static class MyCallable implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            int count = 0;
            for (int i = 0; i < 10; i++){
                Thread.sleep(3000);
                count++;
            }
            return count;
        }
    }
}