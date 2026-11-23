package com.epn.edii.model;

/** Opciones del menú principal. Para agregar una nueva basta con añadirla aquí. */
public enum OpcionMenu {
    EFICIENCIA_ALGORITMICA("1. Eficiencia Algorítmica",
            "Comparación Suma Consecutiva vs. Método de Gauss"),
    ANALISIS_COMPLEJIDAD("2. Análisis de Complejidad",
            "Reducción polinómica: A ≤p B"),
    P_NP("3. P, NP, NP-Hard, NP-Completo",
            "Un ejercicio por cada clase"),
    GRAFOS("4. Grafos",
            "Orden topológico, árbol de expansión mínima, Prim, Kahn, Kruskal, Dijkstra");

    private final String titulo;
    private final String descripcion;

    OpcionMenu(String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public String getTitulo() { return titulo; }
    public String getDescripcion() { return descripcion; }
}
