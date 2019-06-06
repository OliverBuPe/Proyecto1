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
    private HashSet<Nodo> nodosArbol = new HashSet<Nodo>();
     private HashMap<Nodo, HashSet<Arista>> incidencia =new HashMap<Nodo, HashSet<Arista>>();
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
        HashMap<String, String> map = new HashMap<>();
        nodos.clear();
        aristas.clear();   
        Random rand = new Random();
        double num;
        int na,nb,i,j,numAristas=0;
        Arista aux;       
        int answer = rand.nextInt(10) + 1;        
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
         EdgeValues(1,10);
        String name = "ErdosRenyi"+Integer.toString(n);
        Graf(name);     
               /*
        DFSRecursivo(nodos.get(0));
        GrafArbol("ErdosRenyiDFSRecursivo"+Integer.toString(n));
        BFS(nodos.get(0));
        GrafArbol("ErdosRenyiBFS"+Integer.toString(n));
        DFSIterativo(nodos.get(0));
        GrafArbol("ErdosRenyiDFSIterativo"+Integer.toString(n));
        
        Dijkstra(0);
        GrafArbol("DijkstraErdosRenyi"+Integer.toString(n));*/
       
       Kruskal();
       GrafArbol("KruscalErdosRenyi"+Integer.toString(n));
       KruskalInverso();
       GrafArbol("KruscalInversoErdosRenyi"+Integer.toString(n));      
       Prim(nodos.get(1));
       GrafArbol("PrimErdosRenyi"+Integer.toString(n));
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
        EdgeValues(1,10);
        String name = "Gilbert"+Integer.toString(v);
        Graf(name);
        /*
        DFSRecursivo(nodos.get(0));
        GrafArbol("GilbertDFSRecursivo"+Integer.toString(v));
        BFS(nodos.get(0));
        GrafArbol("GilbertBFS"+Integer.toString(v));
        DFSIterativo(nodos.get(0));
        GrafArbol("GilbertDFSIterativo"+Integer.toString(v));*/
        //Dijkstra(0);
        //GrafArbol("DijkstraGilbert"+Integer.toString(v));
        Kruskal();
       GrafArbol("KruscalGilbert"+Integer.toString(v));
       KruskalInverso();
       GrafArbol("KruscalInversoGilbert"+Integer.toString(v));      
       Prim(nodos.get(1));
       GrafArbol("PrimGilbert"+Integer.toString(v));   
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
//                      System.out.println("conecta "+i+" "+j);
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
        
        EdgeValues(1,10);                    
        String name = "BarabasiAlbert"+Integer.toString(v);
        Graf(name); /*
        DFSRecursivo(nodos.get(2));
        GrafArbol("BarabasiAlbertDFSRecursivo"+Integer.toString(v));
        BFS(nodos.get(2));
        GrafArbol("BarabasiAlbertBFS"+Integer.toString(v));
        DFSIterativo(nodos.get(2));
        GrafArbol("BarabasiAlbertDFSIterativo"+Integer.toString(v));*/
        //Dijkstra(0);
        //GrafArbol("DijkstraBarabasi"+Integer.toString(v));
       Kruskal();
       GrafArbol("KruscalBarabasiAlbert"+Integer.toString(v));
       KruskalInverso();
       GrafArbol("KruscalInversoBarabasiAlbert"+Integer.toString(v));      
       Prim(nodos.get(1));
       GrafArbol("PrimBarabasiAlbert"+Integer.toString(v));   
        
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
                    Arista aux = new Arista(nodos.get(i),nodos.get(j),dirigido);   
                    if(d>dis&&BuscaArista(aux,map,dirigido)){                        
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
        EdgeValues(1,10);
        String name = "Geografico"+Integer.toString(num);
        Graf(name);/*
        DFSRecursivo(nodos.get(0));
        System.out.println("DFS Recursico");
        GrafArbol("GeograficoDFSRecursivo"+Integer.toString(num));
        BFS(nodos.get(0));
         System.out.println("BFS");
        GrafArbol("GeograficoBFS"+Integer.toString(num));
        DFSIterativo(nodos.get(0));
         System.out.println("DFS Iterativo");
        GrafArbol("GeograficoDFSIterativo"+Integer.toString(num));*/
       // Dijkstra(0);
        //GrafArbol("DijkstraGeografico"+Integer.toString(num));
       Kruskal();
       GrafArbol("KruscalGeografico"+Integer.toString(num));
       KruskalInverso();
       GrafArbol("KruscalInversoGeografico"+Integer.toString(num));      
       Prim(nodos.get(1));
       GrafArbol("PrimGeografico"+Integer.toString(num));   
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
        bw.write("edgedef>node1 VARCHAR,node2 VARCHAR,directed BOOLEAN,weight DOUBLE\n");      
        for(int i=0;i<aristas.size();i++){    
             bw.write(aristas.get(i).getA().obtenerNombre()+","+aristas.get(i).getB().obtenerNombre()+",false,"+aristas.get(i).getPeso()+"\n");      
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
        
        bw.write("nodedef>name VARCHAR,label VARCHAR\n");
        Iterator<Nodo> j = nodosArbol.iterator(); 
        while (j.hasNext()){
            Nodo nodo =j.next();
            String n=nodo.obtenerNombre();
            bw.write(n+","+n+"("+nodo.obtenerDistancia()+")"+"\n");
        }
        /*for(int i=0;i<nodosArbol.size();i++){
            bw.write(nodosArbol.get(i).obtenerNombre()+"\n");            
        }*/
        
        bw.write("edgedef>node1 VARCHAR,node2 VARCHAR,directed BOOLEAN,weight DOUBLE\n");      
        for(int i=0;i<arbol.size();i++){    
             bw.write(arbol.get(i).getA().obtenerNombre()+","+arbol.get(i).getB().obtenerNombre()+",false,"+arbol.get(i).getPeso()+"\n");      
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
        arbol.clear();
        int pos;
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
 public void Dijkstra(int  pos){    
     Nodo aux1=nodos.get(0);
     arbol.clear();     
     CrearGrafo(true);
     int posicion;
     nodos = new ArrayList<Nodo>(nodosArbol);
     //pos =  Posicion(nodos,aux1);
     float  inf = (float)Double.MAX_VALUE;
     int tam = nodosArbol.size();
     Integer[] padres = new Integer[tam];     
     for (int i = 0; i < tam; i++) {
          nodos.get(i).establecerDistancia(inf);

          padres[i] = null;
    }
     nodos.get(pos).establecerDistancia(0);
     padres[pos] = pos;
     PriorityQueue<Nodo> distPQ = new PriorityQueue<>(vertexDistanceComp);
      for (int i = 0; i < tam; i++) {
        distPQ.add(nodos.get(i));
    }

    while (distPQ.peek() != null) {  // se revisa que no esté vacía la cola
      Nodo nodo = distPQ.poll(); // elemento de la cola de prioridad
      //System.out.println("cola "+nodo+"  "+nodo.obtenerDistancia());
      HashSet<Arista> aristas = obtenerArista(nodo);
      //System.out.println("aristas::::");
      for (Arista e : aristas) { 
          float Da = e.getB().obtenerDistancia();
          float Db = nodo.obtenerDistancia()+e.getPeso();  
          //padres[Posicion(nodos,e.getB())]=Posicion(nodos,nodo);
         //System.out.println("idice "+Posicion(nodos,e.getB())+" valor "+Posicion(nodos,nodo));
         // System.out.println("a "+Da+" "+Db);
          if (Da>Db){
             posicion=Posicion(nodos,e.getB()) ;
            // System.out.println("Actualiza a "+ nodos.get(posicion));
             distPQ.add(nodos.get(posicion));
             nodos.get(posicion).establecerDistancia(Db);       
             padres[posicion]=Posicion(nodos,nodo);
          }          
      }
    }
    nodosArbol.clear();
      for (int i = 0; i < tam; i++) {
         // System.out.println(" "+i+" "+padres[i]);
          if(padres[i]!=null){
              if(padres[i]!=i){
                  nodosArbol.add(nodos.get(padres[i]));
                  nodosArbol.add(nodos.get(i));
           //System.out.println("padres: "+padres[i]+" tam "+tam);
                float peso = obtenerPeso(nodos.get(padres[i]),nodos.get(i));
               Arista aux= new Arista(nodos.get(padres[i]),nodos.get(i),peso,true);
               arbol.add(aux);}
          }
    }
      
 }
   Comparator<Nodo> vertexDistanceComp = new Comparator<Nodo>() {
              @Override
              public int compare(Nodo n1, Nodo n2) {
                  return Float.compare(n1.obtenerDistancia(), n2.obtenerDistancia());
              }
          };
  public HashSet<Arista> obtenerArista(Nodo n){
      return incidencia.get(n);
  }
  public void CrearGrafo(boolean dir){
      int i,j;
      ArrayList<Nodo> nod = new ArrayList<Nodo>();
      nodosArbol.clear();      
      for( i=0;i<nodos.size();i++){
          for(j=0;j<aristas.size();j++){              
              if(aristas.get(j).getA().obtenerNombre()==nodos.get(i).obtenerNombre()||aristas.get(j).getB().obtenerNombre()==nodos.get(i).obtenerNombre()){
                  nodosArbol.add(nodos.get(i));
              }             
          }  
      }
 nod=new ArrayList<Nodo>(nodosArbol);
 ArrayList<HashSet<Arista>> aux = new ArrayList< HashSet<Arista>>();
HashSet<Arista> aris = new  HashSet<Arista>();
 for( i=0;i<nod.size();i++){
           aris.clear();
          for(j=0;j<aristas.size();j++){              
              if(aristas.get(j).getA().obtenerNombre()==nod.get(i).obtenerNombre()){
                  aris.add(aristas.get(j));
              }
              
          }
          aux.add(new  HashSet<Arista>());
          for(Arista e:aris){
                  aux.get(i).add(e);
              } 
      }
 
 for(i=0;i<nod.size();i++){
     incidencia.put(nod.get(i),aux.get(i));
 }
 
 if(!dir){
     int w;
      boolean val;
      for( w=0;w<nodos.size();w++){
           HashSet<Arista> aristas2 = obtenerArista(nodos.get(w));           
           for (Arista ar : aristas2){
                Nodo d =ar.getB();     
                Arista aris2=new Arista(ar.getB(),ar.getA(),ar.getPeso(),false);
                 HashSet<Arista> aristas3 = incidencia.get(d);
                // System.out.println("Esta "+ aris2+" "+incidencia.get(d).contains(aris2));
                 val=false;
                 for(Arista a:aristas3){
                     if(a.getA()==aris2.getA()&a.getB()==aris2.getB()){
                         val=true;
                     }
                     //System.out.println(a);
                 }
                 
                if(!val)
                    incidencia.get(d).add(aris2);               
           }           
      }
 }
 
 
 
 Iterator <Nodo> it = incidencia.keySet().iterator();
while(it.hasNext()){
  Nodo key = it.next();
 // System.out.println("Clave: " + key + " -> Valor: ");
          for(Arista e:incidencia.get(key)){
              //System.out.println(e.Impresion());
          }
}

}
  public int Posicion(ArrayList<Nodo> nod,Nodo n){
      int posicion=0;
      for(int i=0;i<nod.size();i++){
          if(nod.get(i)==n){
              posicion= i;
          }
      }
     return posicion;
  }
  public float obtenerPeso(Nodo a,Nodo b){
      float p=0;
      HashSet<Arista> aristas = obtenerArista(a);
      for(Arista e:aristas){
          if(e.getA()==a&&e.getB()==b){
              p=e.getPeso();         
          }
      }
  return p;
  }
  public void EdgeValues(float min,float max){
      Random rand = new Random();
      float num;
      for(int i=0;i<aristas.size();i++){
          
          num=(float)((min+(max-min)*rand.nextDouble()));
          aristas.get(i).setPeso(num);
      }
      
  }
  public void Prim(Nodo Inicial){
      Queue<Arista> cola = new PriorityQueue<>(CompararPeso);
      System.out.println("Algoritmo de Prim");
      int l,w,tam2 = incidencia.size();
      int i=0;
      double costo=0;  
      nodosArbol.clear();
      arbol.clear();      
      ArrayList<Nodo> U = new ArrayList<Nodo>();
      CrearGrafo(false);         
     U.add(Inicial);      
     Nodo aux = new Nodo("a",1); 
     Arista aristaf=new Arista(aux,aux,20);
     ArrayList<String> padres = new ArrayList<String>();        
     for(w=0;w<nodos.size();w++){
         padres.add(nodos.get(w).obtenerNombre());
     }
     //hacer mientras conjuntos sean diferentes
     while(!(Comparar(U,nodos))){         
         cola = new PriorityQueue<>(CompararPeso);  
         for(int n=0;n<U.size();n++){
             HashSet<Arista> aristas = obtenerArista(U.get(n)); 
             for (Arista e : aristas) {
                  cola.add(e);
       }
         }
          while(!cola.isEmpty()){
           aristaf= cola.remove(); 
            if(Find(aristaf.getA(),padres)==Find(aristaf.getB(),padres)){
                continue;
            }else{
                if(!Repetida(aristaf)){
                arbol.add(aristaf);
                U.add(aristaf.getB());
                nodosArbol.add(aristaf.getB());
                 padres.set(nodos.indexOf(aristaf.getB()),aristaf.getA().obtenerNombre());
                break;
                }
            }          
       }
     } 
     for (Arista e : arbol) {
         costo=costo+e.getPeso();
     }
     System.out.println("Costo "+costo);
  }
void Kruskal(){
    System.out.println("Kruscal ");
    Queue<Arista> cola = new PriorityQueue<>(CompararPeso);
    int i;
    double costo=0;
    nodosArbol.clear();
    arbol.clear();
    CrearGrafo(true);    
    ArrayList<String> padres = new ArrayList<String>();        
     for(i=0;i<nodos.size();i++){
         padres.add(nodos.get(i).obtenerNombre());
     }
    for(i=0;i<nodos.size();i++){
        HashSet<Arista> aristas = obtenerArista(nodos.get(i));
          for (Arista e1 : aristas) {
                cola.add(e1);
            }
    }
     ArrayList<Nodo> V = new ArrayList<Nodo>();
     ArrayList<Nodo> U = new ArrayList<Nodo>();
     Arista a = cola.remove();
     V.add(a.getA());
     U.add(a.getB());
     padres.set(nodos.indexOf(a.getB()),a.getA().obtenerNombre());      
     arbol.add(a);     
    while(arbol.size()<nodos.size()-1){
        if(!cola.isEmpty()){
             Arista b = cola.remove();              
                if(Find(b.getA(),padres)!=Find(b.getB(),padres)){
                    arbol.add(b);   
                    nodosArbol.add(b.getA());
                    nodosArbol.add(b.getB());
                    Union(b.getA(),b.getB(),padres);             
           }
        }else{
            break;
        }      
    }
    costo=0;
  for (Arista e : arbol) {
         costo=costo+e.getPeso();
     }
System.out.println("costo: "+costo);
}
boolean Comparar( ArrayList<Nodo> n1, ArrayList<Nodo> n2){
    int tam = 0;
    if(n1.size()==n2.size()){
        for(int i=0;i<n1.size();i++){
            for(int j=0;j<n2.size();j++){
                if(n1.get(i).obtenerNombre() ==n2.get(j).obtenerNombre()){
                    tam=tam+1;
                    break;
            }
            }            
        }
        if(tam==n1.size()){
        return true;
        }else{
            return false;
        }
    }else{
        return false;
    }
}
  Comparator<Arista> CompararPeso = new Comparator<Arista>() {
              @Override
              public int compare(Arista a1, Arista a2) {
                  return Float.compare(a1.getPeso(), a2.getPeso());
              }
          };
 Comparator<Arista> CompararPeso2 = new Comparator<Arista>() {
    @Override
    public int compare(Arista a1, Arista a2) {
        if(a1.getPeso()==a2.getPeso()){
            return 0;
        }
        if(a1.getPeso()>a2.getPeso()){
            return -1;
        }else{
            return 1;
        }

    }
};

Nodo Find( Nodo x,ArrayList<String> p ){    
    int pos=nodos.indexOf(x);        
    if(x.obtenerNombre()==p.get(pos)){        
        return x;        
    }    
    else{   
        int pos2 = Integer.parseInt(p.get(pos));
        return Find( nodos.get(pos2-1) ,p); 
    }
    }
void Union( Nodo x ,Nodo y,ArrayList<String> p  ){
    Nodo xRoot = Find( x ,p);  
    Nodo yRoot = Find( y,p );    
    p.set(nodos.indexOf(xRoot),yRoot.obtenerNombre());
}
void KruskalInverso(){
   System.out.println("kruskal inverso");
   CrearGrafo(false);    
   double costo=0;
    int i,a; 
    Arista aux;
   nodosArbol.clear();
   arbol.clear();   
   Queue<Arista> cola = new PriorityQueue<Arista>(CompararPeso2);      
    for(i=0;i<nodos.size();i++){
        HashSet<Arista> aristas = obtenerArista(nodos.get(i));
        for (Arista e1 : aristas) {
                cola.add(e1);
        }
    }
    System.out.println("cola llena");
    while(!cola.isEmpty()){      
        Arista b = cola.remove();    
         //System.out.println(" "+b);
        incidencia.get(b.getA()).remove(b);
       a=conexion();        
        if(a==0){           
            aux = new Arista(b.getB(),b.getA(),b.getPeso(),b.getDir());
            if(!arbol.contains(aux)&!arbol.contains(b)){
                arbol.add(b);           
                 incidencia.get(b.getA()).add(b);
            }   
        }            
    }
        for (Arista e1 : arbol) {
            nodosArbol.add(e1.getA());
            nodosArbol.add(e1.getB());
            //System.out.println(e1);
            costo=costo+e1.getPeso();                            
        }    
     System.out.println("Costo: "+costo);    
}
int conexion(){
    int control=0;   
    int i,numAristas,visitas=0;
    ArrayList<Arista> aristas2 = new ArrayList<Arista>();
    ArrayList<Nodo> nodosVisitados = new ArrayList<Nodo>();
    Iterator <Nodo> it = incidencia.keySet().iterator();
    Nodo key;
    while(it.hasNext()){
         key = it.next();      
        for(Arista e:incidencia.get(key)){
            aristas2.add(e);         
        }
    }
    numAristas=aristas.size();    
    it = incidencia.keySet().iterator();
    key = it.next();
    nodosVisitados.add(key);    
    //System.out.println("nodos que se visitan:");
    while(control<nodosVisitados.size()){
       // System.out.print(" "+nodosVisitados.get(control));
        for(Arista e:incidencia.get(nodosVisitados.get(control))){            
            if(!nodosVisitados.contains(e.getB())){
                nodosVisitados.add(e.getB());     
            }else{
               // System.out.println("Ya contiene "+e.getB());
            }
                        
    }
    control=control+1;
    }
    if(nodosVisitados.size()==nodos.size()){
        return 1;
    }else{
        return 0;
    }    
}
boolean Repetida(Arista a){    
    for(Arista b:arbol){
        if(a.getA()==b.getA()&a.getB()==b.getB()){
            return true;        
        }
        if(a.getB()==b.getA()&a.getA()==b.getB()){
            return true;        
        }
       
    }
    return false;
}

}
    
    
