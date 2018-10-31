package calculate;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class Job implements Callable <ArrayList<Edge>> {

    private EdgeType edgeType;
    private KochFractal fractal;

    public Job(EdgeType edgeType, KochFractal fractal) {
        this.edgeType = edgeType;
        this.fractal = fractal;
    }

    @Override
    public ArrayList<Edge> call(){

        if (edgeType == EdgeType.RIGHT) {
            fractal.generateRightEdge();
        }
        if (edgeType == EdgeType.LEFT) {
            fractal.generateLeftEdge();

        }
        if (edgeType == EdgeType.BOTTOM) {
            fractal.generateBottomEdge();
        }

        return null;
    }
}
