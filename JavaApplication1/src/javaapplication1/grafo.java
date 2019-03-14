package javaapplication1;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap; 
import java.util.Map; 



public class grafo{
    private  ArrayList<Nodo> nodos = new ArrayList<Nodo>();
    private ArrayList<Arista> aristas = new ArrayList<Arista>();
    private HashMap<String, String> map;
    private int L;


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {  
        grafo a = new grafo();
        Scanner input = new Scanner(System.in);
        input.useLocale(Locale.US);
        int opc,nodos,aristas,val;
        boolean dirigido,ciclo;
        double p;

        
        System.out.print("\tSeleciona modelo\n"
                + "1.- Modelo de Erdos y Renyi\n"
                + "2.- Modelo de Gilbert\n"
                + "3.- Modelo Barabasi-Albert\n"
                + "4.- Modelo de Geografico\n"
                + "Eleccion:");
        opc = input.nextInt();
        
        switch (opc) {
            case 1:{                
                System.out.println (" \nModelo de Erdos y Renyi");
                System.out.print("Numero de nodos: ");
                nodos=input.nextInt();
                System.out.print("\nNumero de aristas: ");
                aristas=input.nextInt();
                System.out.print("\nEs dirigido"
                        + "\n1.-true"
                        + "\n2.-false"
                        + "\neleccion: ");
                val=input.nextInt();
                if(val==1){
                    dirigido=true;
                }else{
                    dirigido=false;
                }
                System.out.print("\npermite autociclo:"
                        + "\n1.-true"
                        + "\n2.-false"
                        + "\neleccion: ");
                
                val=input.nextInt();
                if(val==1){
                    ciclo=true;
                }else{
                    ciclo=false;
                }
                a.ModeloErdosRenyi(nodos,aristas,dirigido, ciclo);            

                break;
            }
            case 2:{
                System.out.println (" \nModelo de Gilbert");
                System.out.print("Numero de nodos: ");
                nodos=input.nextInt();
                System.out.print("\nProbabilidad: "); 
                p=input.nextDouble();                        
                System.out.print("\nEs dirigido"
                        + "\n1.-true"
                        + "\n2.-false"
                        + "\neleccion: ");
                val=input.nextInt();
                if(val==1){
                    dirigido=true;
                }else{
                    dirigido=false;
                }
                System.out.print("\npermite autociclo:"
                        + "\n1.-true"
                        + "\n2.-false"
                        + "\neleccion: ");
                
                val=input.nextInt();
                if(val==1){
                    ciclo=true;
                }else{
                    ciclo=false;
                }
                a.ModeloGilbert(nodos,p,dirigido, ciclo);
                break;
            }
            case 3:{
                System.out.println (" \nModelo Barabasi-Albert");
                System.out.print("Numero de nodos: ");
                nodos=input.nextInt();
                System.out.print("\nNumero de aristas para cada nodo: ");
                aristas=input.nextInt();
                System.out.print("\nEs dirigido"
                        + "\n1.-true"
                        + "\n2.-false"
                        + "\neleccion: ");
                val=input.nextInt();
                if(val==1){
                    dirigido=true;
                }else{
                    dirigido=false;
                }
                System.out.print("\npermite autociclo:"
                        + "\n1.-true"
                        + "\n2.-false"
                        + "\neleccion: ");
                
                val=input.nextInt();
                if(val==1){
                    ciclo=true;
                }else{
                    ciclo=false;
                }
                
                a.ModeloGilbert(nodos,aristas,dirigido,ciclo);
                break;
            }
            case 4:{
                System.out.println (" \nModelo de geografico");
                System.out.print("Numero de nodos: ");
                nodos=input.nextInt();
                System.out.print("\nDistancia minima: ");
                p=input.nextDouble();
                System.out.print("\nEs dirigido"
                        + "\n1.-true"
                        + "\n2.-false"
                        + "\neleccion: ");
                val=input.nextInt();
                if(val==1){
                    dirigido=true;
                }else{
                    dirigido=false;
                }
                System.out.print("\npermite autociclo:"
                        + "\n1.-true"
                        + "\n2.-false"
                        + "\neleccion: ");
                
                val=input.nextInt();
                if(val==1){
                    ciclo=true;
                }else{
                    ciclo=false;                }
                a.ModeloGeografico(nodos,p,dirigido,ciclo);
                break;
            }            
            default:{ 
                System.out.println ("opcion no valida");
                break;
            }

        }

       
    }
    public Nodo CrearNodo(String nombre){
        Nodo A =new Nodo(nombre);
        return A;
        
    }
    public Arista CrearArista(Nodo a,Nodo b){
        Arista Ari = new Arista(a,b);
        return Ari;
        
    }
    public void ModeloErdosRenyi(int n, int m, boolean dirigido,boolean ciclo ){
        L=0;
        HashMap<String, String> map = new HashMap<>();
        nodos.clear();
        aristas.clear();   
        Random rand = new Random();
        double num;
        int na,nb,i,j,numAristas=0;
        Arista aux;        
        
        for(i=0;i<n;i++){
            nodos.add(new Nodo(Integer.toString(i+1)));          
        }      
        System.out.println("n= "+n+"size "+nodos.size());
        while(numAristas<m){
            na=rand.nextInt(n);
            nb=rand.nextInt(n);
            if(na!=nb){
                num= (Math.random() * 1);
                    if(num>=0.5){
                        aux = new Arista(nodos.get(na),nodos.get(nb),dirigido); 
                        if(BuscaArista(aux,map,dirigido)){
                            map.put(aux.toString(),aux.toString());
                            aristas.add(aux);
                            numAristas+=1;
                        }
                    }
            }
            
        }    
        String name = "ErdosRenyi"+Integer.toString(n);
        Graf(name);  
    
    }
    
    public void ModeloGilbert(int v,double p,boolean dirigido,boolean ciclos){
        nodos.clear();
        aristas.clear();
        L=0;
        map = new HashMap<>();
        double num;
        int i,j;
        Arista aux;        
        for(i=0;i<v;i++){
            nodos.add(new Nodo(Integer.toString(i+1)));                    
        }
        for(i=0;i<v;i++){
            for(j=i;j<v;j++){
                if(i!=j){
                    num=(Math.random()*1);
                    if(num>p){
                        aux = new Arista(nodos.get(i),nodos.get(j),dirigido);             
                        if(BuscaArista(aux,map,dirigido)){
                            map.put(aux.toString(),aux.toString());
                            nodos.get(i).anadirEnlace(j+1);
                            nodos.get(j).anadirEnlace(i+1);
                            L = L+2;
                            aristas.add(aux);
                        }
                    }
                }
            }
        }
        String name = "Gilbert"+Integer.toString(v);
        Graf(name);
              
    }
  
    public void ModeloBarabasiAlbert(int m0, int iteraciones,boolean dirigido,boolean ciclo){ 
        int j,i;
        nodos.clear();
        aristas.clear();
        map = new HashMap<>();
        double p,num;
        Arista aux;            
        for(i=0;i<m0;i++){                
            nodos.add(new Nodo(Integer.toString(i+1)));                   
        }
        for(i = 0; i<m0; i++){
            for(j=0;j<m0;j++){                
                if(i==j||nodos.get(i).obtenerEnlaces().size()>iteraciones-1){                   
                    break;
                }                  
                p=probabilidadEnlace(nodos.get(j),iteraciones);
                num= (Math.random() * 1);
                aux = new Arista(nodos.get(i),nodos.get(j),dirigido);                         
                if(p>num&&BuscaArista(aux,map,false) &&(nodos.get(i).obtenerEnlaces().size()<iteraciones-1)){                            
                    map.put(aux.toString(),aux.toString());
                    nodos.get(i).anadirEnlace(j+1);
                    nodos.get(j).anadirEnlace(i+1);
                    aristas.add(aux);                       
                }
            }         
        }
        String name = "BarabasiAlbert"+Integer.toString(m0);
        Graf(name);   
    }
    public void ModeloGeografico(int num,double dis,boolean dirigido,boolean ciclo){
        nodos.clear();
        aristas.clear();
        map = new HashMap<>();
        int i,j;
        double x,y,d;
        for(i=0;i<num;i++){
            x=Math.random();
            y=Math.random();
            nodos.add(new Nodo(Integer.toString(i+1),x,y));
        }        
        for(i=0;i<nodos.size();i++){
            for(j=0;j<nodos.size();j++){
                if(i!=j){
                    d=distancia(nodos.get(i).obtenerX(),nodos.get(i).obtenerY(),nodos.get(j).obtenerX(),nodos.get(j).obtenerY());
                    Arista aux = new Arista(new Nodo(Integer.toString(i+1)),new Nodo(Integer.toString(j+1)),dirigido);   
                    if(d<dis&&BuscaArista(aux,map,dirigido)){                        
                            map.put(aux.toString(),aux.toString());
                            aristas.add(aux);
                    }               
                    
                }
            }
        }
        String name = "Geografico"+Integer.toString(num);
        Graf(name);
          

    }
    public double probabilidadEnlace(Nodo nodo, int g) {
        
		int k = nodo.obtenerEnlaces().size(); //Obtenemos el grado del nodo en cuestion
		return 1-(k/g);
		
	}
    public double distancia(double x1,double y1,double x2,double y2){
        return(Math.sqrt( ((x2-x1)*(x2-x1))+ ((y2-x1)*(x2-x1)) ) );
    }
    
    public boolean BuscaArista(Arista l,HashMap map,boolean dir){
        
        if(dir){
            if(map.containsKey(l.toString())){
                return false;
            }else{
                return true;
            }     
            
        }else{
            Arista a=new Arista(l.getB(),l.getA());
            if(map.containsKey(l.toString())||map.containsKey(a.toString())){
                return false;
            }else{
                return true;
            }
        }

    }
  public void Graf(String name){
    String nombre=name+".gdf";
    File archivo;
    Scanner sc;
    FileWriter fw;
    BufferedWriter bw;
    try{
       archivo=new File(nombre);
       fw=new FileWriter(archivo);
    }catch (IOException ex){


    }
    
    try{
        
        archivo=new File(nombre);
        fw=new FileWriter(archivo);
        bw=new BufferedWriter(fw);               
        bw.write("nodedef>name VARCHAR\n");
        for(int i=0;i<nodos.size();i++){
            bw.write(nodos.get(i).obtenerNombre()+"\n");            
        }
        
        bw.write("edgedef>node1 VARCHAR, node2 VARCHAR\n");      
        for(int i=0;i<aristas.size();i++){    
             bw.write(aristas.get(i).getA().obtenerNombre()+","+aristas.get(i).getB().obtenerNombre()+"\n");      
        }
        
        bw.flush(); }

     catch (IOException io)  
    {
        
    }
  }
    
}
    
    
