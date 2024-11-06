package grafo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import javax.swing.JOptionPane;

public class ExemploGrafo {
    public static void main(String[] args) {
        // Cria um novo grafo
        Grafo<String> grafo = new Grafo<>();

        // Adicionando os vértices ao grafo
        String[] vertices = {
            "Teatro Municipal", 
            "Galeria do Rock", 
            "Praça das Artes", 
            "Shopping Light", 
            "CCBB SP", 
            "Edifício Martinelli/Farol Santander", 
            "Mosteiro de São Bento", 
            "Patio do Collegio", 
            "Catedral da Sé", 
            "Casa da Imagem",
            "Largo São Francisco"
        };

        for (String vertice : vertices) {
            grafo.adicionarVertice(vertice); // Adiciona cada vértice ao grafo
        }

        // Adicionando as arestas com seus respectivos pesos
        grafo.adicionaAresta(1, "Teatro Municipal", "Galeria do Rock");
        grafo.adicionaAresta(1, "Teatro Municipal", "Praça das Artes");
        grafo.adicionaAresta(1, "Galeria do Rock", "Praça das Artes");
        grafo.adicionaAresta(1, "Teatro Municipal", "Shopping Light");
        grafo.adicionaAresta(3, "Shopping Light", "CCBB SP");
        grafo.adicionaAresta(2, "Praça das Artes", "Edifício Martinelli/Farol Santander");
        grafo.adicionaAresta(3, "Shopping Light", "Largo São Francisco"); // Peso maior
        grafo.adicionaAresta(1, "Patio do Collegio", "Catedral da Sé");
        grafo.adicionaAresta(1, "Edifício Martinelli/Farol Santander", "Mosteiro de São Bento");
        grafo.adicionaAresta(2, "Patio do Collegio", "Casa da Imagem"); // Peso maior
        grafo.adicionaAresta(2, "Casa da Imagem", "Catedral da Sé");
        grafo.adicionaAresta(1, "Edifício Martinelli/Farol Santander", "CCBB SP");
        grafo.adicionaAresta(3, "Edifício Martinelli/Farol Santander", "Patio do Collegio");
        grafo.adicionaAresta(1, "Patio do Collegio", "CCBB SP");
        grafo.adicionaAresta(2, "Catedral da Sé", "Largo São Francisco");
        // Direção oposta
        grafo.adicionaAresta(1, "Galeria do Rock", "Teatro Municipal");
        grafo.adicionaAresta(1, "Praça das Artes", "Teatro Municipal");
        grafo.adicionaAresta(1, "Praça das Artes", "Galeria do Rock");
        grafo.adicionaAresta(1, "Shopping Light", "Teatro Municipal");
        grafo.adicionaAresta(3, "CCBB SP", "Shopping Light");
        grafo.adicionaAresta(2, "Edifício Martinelli/Farol Santander", "Praça das Artes");
        grafo.adicionaAresta(3, "Largo São Francisco", "Shopping Light"); // Peso maior
        grafo.adicionaAresta(1, "Catedral da Sé", "Patio do Collegio");
        grafo.adicionaAresta(1, "Mosteiro de São Bento", "Edifício Martinelli/Farol Santander");
        grafo.adicionaAresta(2, "Casa da Imagem", "Patio do Collegio"); // Peso maior
        grafo.adicionaAresta(2, "Catedral da Sé", "Casa da Imagem");
        grafo.adicionaAresta(1, "CCBB SP", "Edifício Martinelli/Farol Santander");
        grafo.adicionaAresta(3, "Patio do Collegio", "Edifício Martinelli/Farol Santander");
        grafo.adicionaAresta(1, "CCBB SP", "Patio do Collegio");
        grafo.adicionaAresta(2, "Largo São Francisco", "Catedral da Sé");
        // Solicita ao usuário que escolha um vértice de origem
        Object origem = JOptionPane.showInputDialog(null, "Escolha uma origem:", "Calcula rota", JOptionPane.WARNING_MESSAGE, null, vertices, vertices);
        // Solicita ao usuário que escolha um vértice de destino
        Object destino = JOptionPane.showInputDialog(null, "Escolha um destino:", "Calcula rota", JOptionPane.WARNING_MESSAGE, null, vertices, vertices);
        
        // Calcula as distâncias a partir da origem usando o algoritmo de Dijkstra
        Map<Vertice<String>, Double> distancias = dijkstra(grafo, origem.toString());
        System.out.println("Caminho mais curto de " + origem + " para " + destino + ":");
        
        // Obtém o caminho mais curto a partir das distâncias calculadas
        ArrayList<Vertice<String>> caminho = obterCaminhoMaisCurto(grafo, origem.toString(), destino.toString(), distancias);
        for (Vertice<String> vertice : caminho) {
            System.out.print(vertice.getDado() + " -> "); // Exibe o caminho
        }
        // Exibe o tempo total do caminho mais curto
        System.out.println("\nTempo total: " + distancias.get(grafo.getVertice(destino.toString())) + " minutos");
    }

    // Método para calcular as distâncias usando o algoritmo de Dijkstra
    public static <TIPO> Map<Vertice<TIPO>, Double> dijkstra(Grafo<TIPO> grafo, TIPO origem) {
        Map<Vertice<TIPO>, Double> distancias = new HashMap<>(); // Mapa para armazenar as distâncias
        PriorityQueue<Vertice<TIPO>> filaPrioridade = new PriorityQueue<>((v1, v2) -> Double.compare(distancias.get(v1), distancias.get(v2)));

        // Inicialização: todas as distâncias são infinitas, exceto a origem
        for (Vertice<TIPO> vertice : grafo.getVertices()) {
            distancias.put(vertice, Double.POSITIVE_INFINITY); // Define distância como infinita
            filaPrioridade.add(vertice); // Adiciona vértice à fila de prioridade
        }
        distancias.put(grafo.getVertice(origem), 0.0); // Distância da origem para ela mesma é 0

        // Algoritmo de Dijkstra
        while (!filaPrioridade.isEmpty()) {
            Vertice<TIPO> vertice = filaPrioridade.poll(); // Remove o vértice com a menor distância
            for (Aresta<TIPO> aresta : vertice.getArestaSaida()) { // Itera sobre as arestas de saída
                Vertice<TIPO> vizinho = aresta.getFim(); // Obtém o vizinho
                double novaDistancia = distancias.get(vertice) + aresta.getPeso(); // Calcula nova distância
                // Se a nova distância for menor, atualiza
                if (novaDistancia < distancias.get(vizinho)) {
                    filaPrioridade.remove(vizinho); // Remove vizinho da fila para atualização
                    distancias.put(vizinho, novaDistancia); // Atualiza a distância
                    filaPrioridade.add(vizinho); // Adiciona o vizinho atualizado na fila
                }
            }
        }
        return distancias; // Retorna o mapa de distâncias
    }

    // Método para obter o caminho mais curto a partir das distâncias calculadas
    public static <TIPO> ArrayList<Vertice<TIPO>> obterCaminhoMaisCurto(Grafo<TIPO> grafo, TIPO origem, TIPO destino, Map<Vertice<TIPO>, Double> distancias) {
        ArrayList<Vertice<TIPO>> caminho = new ArrayList<>(); // Lista para armazenar o caminho
        Vertice<TIPO> atual = grafo.getVertice(destino); // Começa pelo destino

        // Reconstrói o caminho
        while (atual != null && !atual.getDado().equals(origem)) { // Enquanto não chegar à origem
            caminho.add(atual); // Adiciona o atual ao caminho
            atual = encontrarPredecessor(grafo, atual, distancias); // Encontra o predecessor
        }
        
        if (atual != null) {
            caminho.add(grafo.getVertice(origem)); // Adiciona a origem se encontrada
        }

        Collections.reverse(caminho); // Inverte o caminho para a ordem correta
        return caminho; // Retorna o caminho
    }

    // Método auxiliar para encontrar o predecessor
    private static <TIPO> Vertice<TIPO> encontrarPredecessor(Grafo<TIPO> grafo, Vertice<TIPO> atual, Map<Vertice<TIPO>, Double> distancias) {
        for (Aresta<TIPO> aresta : atual.getArestasEntrada()) {
            Vertice<TIPO> predecessor = aresta.getInicio();
            if (distancias.get(predecessor) + aresta.getPeso() == distancias.get(atual)) {
                return predecessor;
            }
        }
        return null; // Se não encontrar, retorna null
    }
}
    
