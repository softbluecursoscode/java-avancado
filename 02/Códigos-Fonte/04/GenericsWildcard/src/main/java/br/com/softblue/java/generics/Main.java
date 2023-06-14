package br.com.softblue.java.generics;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<Bebida> bebidas = new ArrayList<Bebida>();
		
		bebidas.add(new Cafe());
		bebidas.add(new Cha());
		
		prepararBebidas(bebidas);
		
		List<Cha> chas = new ArrayList<Cha>();
		chas.add(new Cha());
		prepararBebidas(chas);
		
		List<Cafe> cafes = new ArrayList<Cafe>();
		cafes.add(new Cafe());
		prepararBebidas(cafes);
		
		prepararBebidas2(chas);
		prepararBebidas2(bebidas);
	}
	
	private static void prepararBebidas(List<? extends Bebida> bebidas) {
		for (Bebida b : bebidas) {
			b.preparar();
		}
	}
	
	private static void prepararBebidas2(List<? super Cha> bebidas) {
		//bebidas.add(new Cha());
		
		for (Object obj : bebidas) {
			Cha cha = (Cha) obj;
			cha.preparar();
		}
	}
	
	@SuppressWarnings("unused")
	private static void prepararBebidas3(List<?> bebidas) {
		for (Object obj : bebidas) {
			Cha cha = (Cha) obj;
			cha.preparar();
		}
	}
}
