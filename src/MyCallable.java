import java.util.Random;
import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {

    String name = randomName();

    @Override
    public Integer call() throws InterruptedException {
        int count = 0;

        while (count != 5) { //ограничил количество выводов сообщений
            Thread.sleep(1000);
            System.out.println("Я " + name + ". Всем привет!");
            count++;
            Thread.sleep(2000);
        }

        System.out.printf("%s завершен \n", name);

        return count;
    }

    public String randomName() {
        Random random = new Random();
        String name = "поток " + random.nextInt(1000);
        return name;
    }
}
