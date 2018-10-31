package calculate;

import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EdgeGenerator extends Task implements Observer {

    private KochManager manager;
    private int nxt;
    private ArrayList<Edge> edges;

    EdgeGenerator(KochManager manager) {
        this.edges = new ArrayList<>();
        this.manager = manager;
        KochFractal fractal = new KochFractal(manager);
        fractal.addObserver(this);
    }

    public ArrayList<Edge> generateEdges() throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        CountDownLatch latch = new CountDownLatch(3);

        for (EdgeType edgeType : EdgeType.values()) {
            KochFractal fractal = new KochFractal(this.manager);
            fractal.setLevel(nxt);

            Job job = new Job(edgeType, fractal);
            EdgeGetter edgeGetter = new EdgeGetter(job, pool);
            edges.addAll(edgeGetter.get());
        }
        latch.await();
        return edges;

    }


    @Override
    protected ArrayList<Edge> call() {
        return this.edges;
    }

    public void setNxt(int nxt) {
        this.nxt = nxt;
    }

    public ArrayList<Edge> getEdges() {
        return this.edges;
    }

    @Override
    public void update(Observable o, Object arg) {
         this.edges.add((Edge) arg);
    }
}
