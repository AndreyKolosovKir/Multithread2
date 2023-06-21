import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        List<Callable<Integer>> callables = new ArrayList<>();
        System.out.println("Создаю потоки...\n");
        final ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            callables.add(new MyCallable());
        }

        int result = 0;
        for (Future<Integer> future : threadPool.invokeAll(callables)) {
            result += future.get();
        }

        int resultAny = threadPool.invokeAny(callables);

        System.out.println("Результат первой завершенной задачи " + resultAny + " в " + new Date(System.currentTimeMillis()));
        System.out.println("Общее количество сообщений, выведенных в консоль: " + result);
        System.out.println("Завершаю все потоки");

        threadPool.shutdown();
    }
}