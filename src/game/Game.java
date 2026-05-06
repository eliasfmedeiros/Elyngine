package game;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.function.Consumer;

/** @author EliasFM */
public class Game implements Runnable {
    
    public interface Tickable { boolean tickRepeating(); }

    private final LinkedList<Tickable> world=new LinkedList<>();
    public final Comparator<Tickable> relevance;
    
    protected float tickPerSecond;

    private Thread gameLooping=new Thread();
    private boolean isRunning=false;
    private double delta=0; // getLoopingLag()

    /**  @param tickPerSecond */
    public Game(float tickPerSecond) {
        this.tickPerSecond=tickPerSecond;
        this.relevance=(Tickable a, Tickable b) -> { return 0; };
    }
    /**
            * @param tickPerSecond
            * @param relevance
            */
    public Game(float tickPerSecond,Comparator<Tickable> relevance) {
        this.tickPerSecond=tickPerSecond;
        this.relevance=relevance;
    }
    
    private synchronized void withWorldLock( Runnable action ){
        action.run();
    }
    
    public void addObject(Game.Tickable o){
        this.withWorldLock( ()-> { world.add( o ); });
    }
    
    protected void forEachObject(Consumer<? super Tickable> action ) { 
        this.withWorldLock( ()-> { world.forEach( action ); });
    }
    
    protected void tick()  { // lembrete: java.util.ConcurrentModificationException é o nome    // faz o tick e, em seguida, realoca o Objeto de acordo com a relevância. Faz isso para cada um dos objetos no world.
        this.withWorldLock( ()-> {
            if(!world.isEmpty()){
                Tickable mainObj=world.getFirst();
                ListIterator<Tickable> li=world.listIterator();
                while( li.hasNext() ) {
                    Tickable auxObj=mainObj;
                    if( (mainObj=li.next()).tickRepeating() ) { 
                        int nextIndex=li.nextIndex();
                        if(relevance.compare(auxObj, mainObj )>0){ // tem que organizar
                            while(li.previous()!=auxObj);
                            while(li.hasPrevious())
                                if(relevance.compare( li.previous(), mainObj )<1){
                                    li.next();
                                    break;
                                }
                            while(li.nextIndex()<nextIndex){
                                auxObj=li.next();
                                li.set( mainObj );
                                mainObj=auxObj;
                            }
                        }
                    } else li.remove();
                }
            }
        });
    }
    
    @Override
    public void run() { // do Game 
        long lastTime=System.nanoTime();
        while( isRunning ) {
            long currentTime=System.nanoTime();
            delta+=(currentTime-lastTime)/nanoTimePerFrame(tickPerSecond ); // Não precisa verificar se tickPerSecond > 0, porque só quem modifica é uma subclasse
            if( delta>=1 ) {
                this.tick();
                delta--;
            }
            lastTime=currentTime;
        }
    }

    public synchronized void stop() {
        isRunning=false;
        try {
            gameLooping.join();
        }catch( InterruptedException e ) {
            javax.swing.JOptionPane.showMessageDialog( null, e);
        }
    }

    public synchronized void start() { // deve ser possível após o stop() ? Está sendo
        if( gameLooping.isAlive() ) stop(); // para não perder a referência da thread ativo
        isRunning=true;
        gameLooping=new Thread( this, getClass().getName() + " looping" );
        gameLooping.start(); // run
    }
    
    public double getLoopingLag(){
        return delta-1;
    }

    public static double nanoTimePerFrame( double fps ) {
        return fps==0 ? Double.NaN : 1000000000/fps;
    }
}
