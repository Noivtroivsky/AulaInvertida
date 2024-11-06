package grafo;

import java.util.ArrayList;

// Classe que representa um vértice
public class Vertice<TIPO> {
    private TIPO dado; // Dado armazenado no vértice
    private ArrayList<Aresta<TIPO>> arestaEntrada; // Lista de arestas que entram neste vértice
    private ArrayList<Aresta<TIPO>> arestaSaida; // Lista de arestas que saem deste vértice

    // Construtor da classe Vertice
    public Vertice(TIPO valor) {
        this.dado = valor; // Inicializa o dado do vértice
        this.arestaEntrada = new ArrayList<>(); // Inicializa a lista de arestas de entrada
        this.arestaSaida = new ArrayList<>(); // Inicializa a lista de arestas de saída
    }

    // Método para obter o dado armazenado no vértice
    public TIPO getDado() {
        return dado;
    }

    // Método para adicionar uma aresta à lista de arestas de entrada
    public void adicionarArestaEntrada(Aresta<TIPO> aresta) {
        this.arestaEntrada.add(aresta);
    }

    // Método para adicionar uma aresta à lista de arestas de saída
    public void adicionaArestaSaida(Aresta<TIPO> aresta) {
        this.arestaSaida.add(aresta);
    }

    // Método para obter a lista de arestas de entrada
    public ArrayList<Aresta<TIPO>> getArestasEntrada() {
        return arestaEntrada;
    }

    // Método para obter a lista de arestas de saída
    public ArrayList<Aresta<TIPO>> getArestaSaida() {
        return arestaSaida;
    }
}
