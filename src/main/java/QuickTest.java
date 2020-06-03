public class QuickTest {
    public static void main(String[] args) {
        ThreadGroup tg = new ThreadGroup("main");
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
