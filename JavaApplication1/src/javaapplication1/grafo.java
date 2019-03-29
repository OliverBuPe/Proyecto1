package javaapplication1;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;
import java.io.*;
import java.util.HashMap; 
import java.util.List;



public class grafo{
    private  ArrayList<Nodo> nodos = new ArrayList<Nodo>();
    private ArrayList<Arista> aristas = new ArrayList<Arista>();
    private ArrayList<Arista> arbol = new ArrayList<Arista>();
    private ArrayList<Nodo> nodosArbol = new ArrayList<Nodo>();
    private HashMap<String, String> map;
    private int L;
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
                
                a.ModeloBarabasiAlbert(nodos,aristas,dirigido,ciclo);
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
                            if(dirigido){
                                 nodos.get(na).nuevoVecino(nodos.get(nb));
                            }else{
                                nodos.get(na).nuevoVecino(nodos.get(nb));
                                nodos.get(nb).nuevoVecino(nodos.get(na));                                
                            }
                            aristas.add(aux);
                            numAristas+=1;
                        }
                    }
            }
        }        
        String name = "ErdosRenyi"+Integer.toString(n);
        Graf(name);       
        DFSRecursivo(nodos.get(0));
        GrafArbol("ErdosRenyiDFSRecursivo"+Integer.toString(n));
        BFS(nodos.get(0));
        GrafArbol("ErdosRenyiBFS"+Integer.toString(n));
        DFSIterativo(nodos.get(0));
        GrafArbol("ErdosRenyiDFSIterativo"+Integer.toString(n));
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
                            if(dirigido){
                                 nodos.get(i).nuevoVecino(nodos.get(j));
                                 nodos.get(i).anadirEnlace(j+1);
                            }else{
                                nodos.get(i).nuevoVecino(nodos.get(j));
                                nodos.get(i).nuevoVecino(nodos.get(j));          
                                nodos.get(i).anadirEnlace(j+1);
                                nodos.get(j).anadirEnlace(i+1);
                            }
                            aristas.add(aux);
                        }
                    }
                }
            }
        }
        String name = "Gilbert"+Integer.toString(v);
        Graf(name);
        DFSRecursivo(nodos.get(0));
        GrafArbol("GilbertDFSRecursivo"+Integer.toString(v));
        BFS(nodos.get(0));
        GrafArbol("GilbertBFS"+Integer.toString(v));
        DFSIterativo(nodos.get(0));
        GrafArbol("GilbertDFSIterativo"+Integer.toString(v));
              
    }
  
    public void ModeloBarabasiAlbert(int v, int e,boolean dirigido,boolean ciclo){ 
        int i,j;
       Arista aux;
       nodos.clear();
        aristas.clear();
        map = new HashMap<>();
       
       for(i=0;i<v;i++){                
            nodos.add(new Nodo(Integer.toString(i+1)));                   
        }      
       for(i = 0; i < e; i++){
             for( j = 0; j < i; j++) {
                 aux=new Arista(nodos.get(i),nodos.get(j),dirigido);                  
                  if (BuscaArista(aux,map,dirigido)) {
                      System.out.println("conecta "+i+" "+j);
                       map.put(aux.toString(),aux.toString());
                       aristas.add(aux);
                       if(dirigido){
                                    nodos.get(i).nuevoVecino(nodos.get(j));
                                    nodos.get(i).anadirEnlace(j+1);
                              }else{
                                   nodos.get(i).nuevoVecino(nodos.get(j));
                                   nodos.get(j).nuevoVecino(nodos.get(i));          
                                   nodos.get(i).anadirEnlace(j+1);
                                   nodos.get(j).anadirEnlace(i+1);
                                }
                            }
                      }
        }
       Random volado = new Random();       
       for( i = e; i <v;) {
      for( j = 0; j < i; j++) {
        double probabilidad = probabilidadEnlace(nodos.get(j),aristas.size());
                //(double)nodos.get(j).obtenerEnlaces().size()/(double)aristas.size();
        if (volado.nextDouble() <= probabilidad) {
            aux=new Arista(nodos.get(i),nodos.get(j),dirigido);
          if (BuscaArista(aux,map,dirigido)&& (nodos.get(i).obtenerEnlaces().size() < e)) {
            map.put(aux.toString(),aux.toString());
            aristas.add(aux);
             if(dirigido){
                         nodos.get(i).nuevoVecino(nodos.get(j));
                         nodos.get(i).anadirEnlace(j+1);
                   }else{
                        nodos.get(i).nuevoVecino(nodos.get(j));
                        nodos.get(j).nuevoVecino(nodos.get(i));          
                        nodos.get(i).anadirEnlace(j+1);
                        nodos.get(j).anadirEnlace(i+1);
                     }
          }
        }
      }
      if (nodos.get(i).obtenerEnlaces().size()>= e){
          i++;
      }
    }         
        
                            
        String name = "BarabasiAlbert"+Integer.toString(v);
        Graf(name); 
        DFSRecursivo(nodos.get(2));
        GrafArbol("BarabasiAlbertDFSRecursivo"+Integer.toString(v));
        BFS(nodos.get(2));
        GrafArbol("BarabasiAlbertBFS"+Integer.toString(v));
        DFSIterativo(nodos.get(2));
        GrafArbol("BarabasiAlbertDFSIterativo"+Integer.toString(v));
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
                            if(dirigido){
                                 nodos.get(i).nuevoVecino(nodos.get(j));
                                 nodos.get(i).anadirEnlace(j+1);
                            }else{
                                nodos.get(i).nuevoVecino(nodos.get(j));
                                nodos.get(j).nuevoVecino(nodos.get(i));          
                                nodos.get(i).anadirEnlace(j+1);
                                nodos.get(j).anadirEnlace(i+1);
                            }
                            
                    }               
                    
                }
            }
        }
        String name = "Geografico"+Integer.toString(num);
        Graf(name);
        DFSRecursivo(nodos.get(0));
        System.out.println("DFS Recursico");
        GrafArbol("GeograficoDFSRecursivo"+Integer.toString(num));
        BFS(nodos.get(0));
         System.out.println("BFS");
        GrafArbol("GeograficoBFS"+Integer.toString(num));
        DFSIterativo(nodos.get(0));
         System.out.println("DFS Iterativo");
        GrafArbol("GeograficoDFSIterativo"+Integer.toString(num));
          

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
     catch (IOException io) {
    }
  }
  public void GrafArbol(String name){
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
        for(int i=0;i<nodosArbol.size();i++){
            bw.write(nodosArbol.get(i).obtenerNombre()+"\n");            
        }
        
        bw.write("edgedef>node1 VARCHAR, node2 VARCHAR\n");      
        for(int i=0;i<arbol.size();i++){    
             bw.write(arbol.get(i).getA().obtenerNombre()+","+arbol.get(i).getB().obtenerNombre()+"\n");      
        }        
        bw.flush(); }
     catch (IOException io)  {        
    }
  }
  public void iniciarVisitas(){
      for(int i=0;i<nodos.size();i++){
          nodos.get(i).establecerVisitado(false);     
      }
  }
  public void BFS(Nodo nodo){
      nodosArbol.clear();
      arbol.clear();
     // System.out.println("BFS");
      iniciarVisitas();
      LinkedList<Nodo> cola = new LinkedList<Nodo>(); 
      nodo.establecerVisitado(true);
       cola.add(nodo);    
       nodosArbol.add(nodo);
        while (cola.size() != 0) {             
            nodo = cola.poll(); 
           //System.out.print(nodo.obtenerNombre()+" ");  
            List<Nodo> vecinos=nodo.obtenerVecinos();
            for(int i=0;i<vecinos.size();i++){
                Nodo n=vecinos.get(i);
                if(!n.obtenerVisitado()){
                    n.establecerVisitado(true);
                    Arista aux = new Arista(nodo,n,false);
                    arbol.add(aux);       
                    nodosArbol.add(n);
                    cola.add(n);               
                }            
            }
        }      
  }
  public void DFSRecursivo(Nodo nodo){
        iniciarVisitas();
        nodosArbol.clear();
        arbol.clear();
        dfs(nodo);
      
  }
  public  void dfs(Nodo nodo){
        //System.out.print(nodo.obtenerNombre() + " ");
        nodosArbol.add(nodo);
        List<Nodo> vecinos=nodo.obtenerVecinos();
         nodo.establecerVisitado(true);
        for (int i=0; i < vecinos.size(); i++){
	Nodo n=vecinos.get(i);
	if(n!=null && !n.obtenerVisitado()){
                            Arista aux = new Arista(nodo,n,false);
                            arbol.add(aux);                            
                            dfs(n);
	}
	}
}
   public  void DFSIterativo(Nodo nodo){
        nodosArbol.clear();
        arbol.clear();
       iniciarVisitas();
       Stack<Nodo> pila=new  Stack<Nodo>();
       pila.add(nodo);
       nodo.establecerVisitado(true);
       while (!pila.isEmpty()){
               Nodo e=pila.pop();
               //System.out.print(e.obtenerNombre() + " "); 
               nodosArbol.add(e);
               java.util.List<Nodo> vecinos=e.obtenerVecinos();
               for (int i = 0; i < vecinos.size(); i++) {
                       Nodo n=vecinos.get(i);
                       if(n!=null && !n.obtenerVisitado()){
                               pila.add(n);
                               Arista aux = new Arista(e,n,false);
                               arbol.add(aux);
                               n.establecerVisitado(true); 
                       }
               }
       }
}
  
}
    
    

    
    
