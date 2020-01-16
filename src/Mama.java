import java.util.concurrent.Semaphore;

public class Mama implements Runnable{

    Semaphore mutex;
    Semaphore noHayGalletas;
    Semaphore hayGalletas;

    public Mama(Semaphore mutex, Semaphore noHayGalletas, Semaphore hayGalletas) {
        this.mutex = mutex;
        this.noHayGalletas = noHayGalletas;
        this.hayGalletas = hayGalletas;
    }

    @Override
    public void run() {

        while (true){
            try {
//                System.out.println("*mama espera que no haya galletas");
                noHayGalletas.acquire();
                mutex.acquire();
                if( Main.galletas==0 ){
                    System.out.println("Mama: Tomad galletas pesaos");
                    System.out.println("--------------------------------");
                }
                Main.galletas = Main.capacidadTarro;
                mutex.release();
//                System.out.println("*mama libera turno");
                hayGalletas.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
