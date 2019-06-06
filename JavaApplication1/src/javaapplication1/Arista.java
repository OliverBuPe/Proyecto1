package javaapplication1;
public class Arista {
    private Nodo A;
    private Nodo B;
    private float peso;
    boolean dirigido;
public Arista(){
    this.A=new Nodo();
    this.B=new Nodo();
    this.peso=0;
    this.dirigido=false;
}
public Arista(Nodo A,Nodo B){
    this.A=A;
    this.B=B;
    this.peso=0;
    this.dirigido=false;
}
public Arista(Nodo A,Nodo B, boolean d){
    this.A=A;
    this.B=B;
    this.peso=0;
    this.dirigido=d;
}
public Arista(Nodo A,Nodo B, float peso,boolean d){
    this.A=A;
    this.B=B;
    this.peso=peso;
    this.dirigido=d;
}

public String plot(){
    String p="";
    if(dirigido){
        p=p+A.obtenerNombre()+"->"+B.obtenerNombre();       
    }else{
        p=p+A.obtenerNombre()+"--"+B.obtenerNombre();
    }
    return p;
}
public Nodo getA(){
    return A;
}

public Nodo getB(){
    return B;

}
public String toString() {    
    return (getA().obtenerNombre()).concat ("-").concat(getB().obtenerNombre());
    
}
public String Impresion(){
           return (getA().obtenerNombre()).concat ("-").concat(getB().obtenerNombre())+" "+getPeso();
}
public float getPeso(){
    return peso;
}
public void setPeso(float p){
    peso=p;

}
public boolean getDir(){
    return dirigido;
}
    

}
    


