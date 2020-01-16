import java.util.concurrent.Semaphore;

public class Hijo implements Runnable{

    String id;
    Semaphore mutex;
    Semaphore noHayGalletas;
    Semaphore hayGalletas;

    public Hijo(String id, Semaphore mutex, Semaphore noHayGalletas, Semaphore hayGalletas) {
        this.id = id;
        this.mutex = mutex;
        this.noHayGalletas = noHayGalletas;
        this.hayGalletas = hayGalletas;
    }

    @Override
    public void run() {
        while (true){
            try {
//                System.out.println("*hijo espera su turno1");
                mutex.acquire();
                if( Main.galletas <= 0 ){

                    System.out.println(id+": No quedan galletas");
                    System.out.println("-------------------------------------");
                    noHayGalletas.release();
                    mutex.release();
//                    System.out.println("*hijo espera a que haya galletas");

                    hayGalletas.acquire();
//                    System.out.println("*hijo espera su turno2");

                    mutex.acquire();

                    cogerGalleta();
                    mutex.release();
                }else{
                    cogerGalleta();
                    mutex.release();

                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void cogerGalleta(){
        try {
//            Thread.sleep(1000);
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(id+": Coge 1 galleta");
        Main.galletas--;
        System.out.println("Quedan: "+Main.galletas+" galletas");
        System.out.println("-------------------------------------");

    }

}
