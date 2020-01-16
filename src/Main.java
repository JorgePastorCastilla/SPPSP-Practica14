import java.util.concurrent.Semaphore;

public class Main {

    public static final int capacidadTarro = 5;
    public static int galletas = capacidadTarro;

    public static void main(String[] args) {

        Semaphore noHayGalletas = new Semaphore(0,true);
        Semaphore hayGalletas = new Semaphore(0,true);
        Semaphore mutex = new Semaphore(1,true);

        Hijo hijo1 = new Hijo("Hijo1",mutex,noHayGalletas,hayGalletas);
        Hijo hijo2 = new Hijo("Hijo2",mutex,noHayGalletas,hayGalletas);
        Hijo hijo3 = new Hijo("Hijo3",mutex,noHayGalletas,hayGalletas);

        Thread t1 = new Thread(hijo1);
        Thread t2 = new Thread(hijo2);
        Thread t3 = new Thread(hijo3);

        Mama mama = new Mama(mutex,noHayGalletas,hayGalletas);

        Thread tm = new Thread(mama);

        t1.start();
        t2.start();
        t3.start();
        tm.start();

/*        hijo1.run();
        hijo2.run();
        hijo3.run();

        mama.run();*/

    }

}
