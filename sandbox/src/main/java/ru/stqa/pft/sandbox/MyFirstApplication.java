package ru.stqa.pft.sandbox;

public class MyFirstApplication{
	public static void main(String[] args){
		Point p1 = new Point(22, 72);
		Point p2 = new Point(29, 11);

		System.out.println("Расстояние между p1(" + p1.x + ", " + p1.y + ") и p2(" + p2.x + ", " + p2.y + ") = " + p1.distance(p2));
	}
}