package calculate;

import java.util.ArrayList;
import java.util.concurrent.*;

public class EdgeGetter implements Future<ArrayList<Edge>> {

    private Job job;
    private ExecutorService pool;

    public EdgeGetter (Job job, ExecutorService pool){
        this.job = job;
        this.pool = pool;
    }



    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public ArrayList<Edge> get() {
        Future<ArrayList<Edge>> future = pool.submit(job);

        try {
            if (future.isDone()){
                return future.get();
            }
        } catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ArrayList<Edge> get(long timeout, TimeUnit unit) {
        return null;
    }
}
