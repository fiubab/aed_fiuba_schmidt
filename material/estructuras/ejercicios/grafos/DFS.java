package material.estructuras.ejercicios.grafos;

import java.util.*;

import material.estructuras.grafos.*;


public class DFS {

	public boolean contains(Grafo<Integer, Integer> g, Integer value) {
		Set<Vertice<Integer, Integer>> visitados = new HashSet<>();
		return dfs(visitados, g.getVertices().stream().toArray()[0], true);
	}

	public void traverse(Grafo<Integer, Integer> g, Integer value) {
		Set<Vertice<Integer, Integer>> visitados = new HashSet<>();
		Vertice<Integer, Integer> origen = g.getVertice(value);
		if (origen != null) { dfs(visitados, origen, false); }
	}

	public void dfs(Set<Vertice<Integer, Integer>> visitados, Vertice<Integer, Integer> origen, boolean search) {
		visitados.add(origen);
		if (!search) { System.out.println(origen.getValor()); }
		if (search) {

		}
		/*
		recorremos todos los vertices conectados a origen
		origen<----arista---->destino
		*/
		for (Arista<Integer, Integer> camino : origen.getAdyacencias()) {
			Vertice<Integer, Integer> destino = camino.getDestino();
			if (!visitados.contains(destino)) {
				recursiveBFS(visitados, destino);
			}
		}
	}
}
