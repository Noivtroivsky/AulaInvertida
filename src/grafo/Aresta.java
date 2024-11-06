/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafo;

// Classe que representa uma aresta em um grafo
public class Aresta<TIPO> {
    private double peso; // Peso da aresta, que pode representar a distância ou o custo
    private Vertice<TIPO> inicio; // Vértice de início da aresta
    private Vertice<TIPO> fim; // Vértice de fim da aresta

    // Construtor da classe Aresta
    public Aresta(double peso, Vertice<TIPO> inicio, Vertice<TIPO> fim) {
        this.peso = peso; // Inicializa o peso da aresta
        this.inicio = inicio; // Inicializa o vértice de início
        this.fim = fim; // Inicializa o vértice de fim
    }

    // Método para obter o peso da aresta
    public double getPeso() {
        return peso; // Retorna o peso da aresta
    }

    // Método para obter o vértice de início da aresta
    public Vertice<TIPO> getInicio() {
        return inicio; // Retorna o vértice de início
    }

    // Método para obter o vértice de fim da aresta
    public Vertice<TIPO> getFim() {
        return fim; // Retorna o vértice de fim
    }
}
