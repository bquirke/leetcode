package leetcode.graphs.bfs.helpers;

public class ColourState {
    public int node;
    public int steps;

    public Colour colour;

    public ColourState(int node, int steps, Colour colour){
        this.node = node;
        this.steps = steps;
        this.colour = colour;
    }
}
