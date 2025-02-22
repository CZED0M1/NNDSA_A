package DataStructures;

import lombok.Getter;

import java.util.*;


@Getter
public abstract class Graph<KEdge, VEdge, KVertex, VVertex>{
    private final HashMap<KVertex, Vertex<KVertex, VVertex>> vertices;
    private final HashMap<KEdge, Edge<KEdge, VEdge>> edges;

    public Graph(){
        vertices = new HashMap<>();
        edges = new HashMap<>();
    }

    public void addVertex(Vertex<KVertex, VVertex> vertex){
        if(vertices.containsKey(vertex.getKey())){
            throw new IllegalArgumentException("Vertex with key " + vertex.getKey() + " already exists.");
        }
        vertices.put(vertex.getKey(), vertex);
    }

    public void addEdge(KVertex startKey, KVertex endKey, Edge<KEdge, VEdge> edge){
        Map.Entry<KVertex,KVertex> key = new AbstractMap.SimpleEntry<>(startKey, endKey);
        //noinspection SuspiciousMethodCalls
        if(edges.containsKey(key)){
            throw new IllegalArgumentException("Edge with start key " +edge.getKey()+ " already exists.");
        }
        if(!vertices.containsKey(startKey)){
            throw new IllegalArgumentException("Vertex with key " + startKey + " does not exist.");
        }
        if(!vertices.containsKey(endKey)){
            throw new IllegalArgumentException("Vertex with key " + endKey + " does not exist.");
        }
        edges.put(edge.getKey(), edge);
    }
    public void addEdge(KEdge key, Edge<KEdge, VEdge> edge){
        if(edges.containsKey(key)){
            throw new IllegalArgumentException("Edge with start key " +edge.getKey()+ " already exists.");
        }
        edges.put(edge.getKey(), edge);
    }

    public void removeVertex(KVertex key){
        if(!vertices.containsKey(key)){
            throw new IllegalArgumentException("Vertex with key " + key + " does not exist.");
        }
        vertices.remove(key);
    }

    public void removeEdge(KVertex startKey, KVertex endKey){
        Map.Entry<KVertex,KVertex> key = new AbstractMap.SimpleEntry<>(startKey, endKey);
        //noinspection SuspiciousMethodCalls
        if(!edges.containsKey(key)){
            throw new IllegalArgumentException("Vertex with key " + key + " does not exist.");
        }
        //noinspection SuspiciousMethodCalls
        edges.remove(key);
    }

    public Vertex<KVertex, VVertex> getVertex(KVertex key){
        if(!vertices.containsKey(key)){
            throw new IllegalArgumentException("Vertex with key " + key + " does not exist.");
        }
        return vertices.get(key);
    }

    public Edge<KEdge, VEdge> getEdge(KVertex startKey, KVertex endKey){
        Map.Entry<KVertex,KVertex> key = new AbstractMap.SimpleEntry<>(startKey, endKey);
        Map.Entry<KVertex,KVertex> OppositeKey = new AbstractMap.SimpleEntry<>(endKey, startKey);
        //noinspection SuspiciousMethodCalls
        if(!edges.containsKey(key) && !edges.containsKey(OppositeKey)){
            throw new IllegalArgumentException("Edge with start vertex key " + key + " does not exist.");
        }
        //noinspection SuspiciousMethodCalls
        return (edges.containsKey(key)) ? edges.get(key) : edges.get(OppositeKey);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vertices:\n");
        for(Vertex<KVertex, VVertex> vertex : vertices.values()){
            sb.append(vertex.toString()).append("\n");
        }
        sb.append("Edges:\n");
        for(Edge<KEdge, VEdge> edge : edges.values()){
            sb.append(edge.toString()).append("\n");
        }
        return sb.toString();
    }

    public void clear() {
        vertices.clear();
        edges.clear();
    }

}