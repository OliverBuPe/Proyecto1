
package javaapplication1;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Nodo {
    private String nombre;
    private int id;
    private double x;
    private double y;
    private boolean visitado;
    private List<Nodo> vecinos= new ArrayList<>();    
    private ArrayList<Integer> enlaces = new ArrayList<Integer>();
    private float distancia;
    public Nodo() {
     this.nombre="null";
     this.id = 0;
 }
 public Nodo(String nombre,int id) {
     this.nombre=nombre;
     this.id = id;
     this.id=0;
     this.y=0;
 }
 public Nodo(String nombre) {
     this.nombre=nombre;
     this.id = 0;
     this.x=0;
     this.id=0;
 }
 public Nodo(String nombre,double x,double y) {
     this.nombre=nombre;
     this.id = 0;
     this.x=x;
     this.y=y;
 }
public void anadirEnlace(int j){
        enlaces.add(j);
}
 public void estableceNombre(String nombre){
     this.nombre=nombre;
 }
 public void establecerId(int id) {
     this.id = id;
 }
 public void establecerX(double x) {
     this.x = x;
 } 
 public void establecerY(double y) {
     this.y = y;
 }
 public void establecerVisitado(boolean b){
     visitado=b;
 }
 public void establecerVecinos(List<Nodo> v){
     vecinos=v;
 }
 public void establecerDistancia(float d){
     distancia=d;
 }
 public String obtenerNombre() {
     return nombre;
 } 
 public int obtenerId() {
     return id;
 } 
 public double obtenerX(){
     return x;
 }
  public double obtenerY(){
     return y;
 }
 public boolean obtenerVisitado(){
     return visitado;
 }
 public List<Nodo> obtenerVecinos(){
     return vecinos;
 }
 public void nuevoVecino(Nodo vecino){
   vecinos.add(vecino);
   }
 public ArrayList<Integer> obtenerEnlaces(){
		return enlaces;
}
 public String toString() {    
    return (obtenerNombre());
}
 public float obtenerDistancia(){
     return distancia;
 }
 
}


