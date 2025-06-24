package ru.vsu.cs.course1.model;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private Board board;
    private List<Node> edges;
    private Probability probability;

    public Node(Board board) {
        this.board = board;
        this.edges = new ArrayList<>();
        this.probability = null;
    }

    public Board getBoard() {
        return board;
    }

    public List<Node> getEdges() {
        return edges;
    }

    public Probability getProbability() {
        return probability;
    }

    public void setProbability(Probability probability) {
        this.probability = probability;
    }

    public void addEdge(Node node) {
        edges.add(node);
    }
}