/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package grafo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 *
 * @author mari1
 */
public class Main {
    
    
    public static void main(String[] args) {
        
        menu();
        //System.out.println("Grafo:"+grafo);
        
       
        
        // if(!Busquedas.porProfundidadIterativa(origen, destino, grafo)){
        //     System.out.println("El nodo no se encontró");
        // }
        
    }

    public static void menu(){
        ArrayList<Nodo> grafo=leerArchivo();
        Scanner scanner=new Scanner(System.in);
        String origen, destino="";
        Scanner leerEntero=new Scanner(System.in);
        int op=0;
        do{
            System.out.println("==============================================================");
            System.out.println("\t\t\tMENU");
            System.out.println("==============================================================");
            System.out.println("1) Busqueda por amplitud");
            System.out.println("2) Busqueda por profundidad");
            System.out.println("3) Cambiar de archivo");
            System.out.println("4) Todas las busquedas");
            System.out.println("5) Ver grafo");
            System.out.println("6) Salir");
            System.out.print("Escriba su opción:");
            op=leerEntero.nextInt();
            switch(op){
                case 1: //! no se esta imprimiendo bien falta el encontradooooo
                System.out.println("Ingrese el origen:");
                origen=scanner.nextLine();
                System.out.println("Ingrese el destino:");
                destino=scanner.nextLine();
                    System.out.println("\n---------BUSQUEDA POR AMPLITUD---------");
                    if(!Busquedas.porAmplitud(origen, destino, grafo)){
                        System.out.println("El nodo no se encontró");
                    }
                    break;
                case 2: 
                System.out.println("Ingrese el origen:");
                origen=scanner.nextLine();
                System.out.println("Ingrese el destino:");
                destino=scanner.nextLine();
                System.out.println("\n---------BUSQUEDA POR PROFUNDIDAD---------");
                    if(!Busquedas.porProfundidad(origen, destino, grafo)){
                        System.out.println("El nodo no se encontró");
                    }
                    break;
            }
        }while(op!=6);
        scanner.close();
        leerEntero.close();
    }

    public static ArrayList<Nodo> leerArchivo(){
        ArrayList<Nodo> nodos=new ArrayList<Nodo>();
        try{
            File file=new File("src/files/ejemplo2.csv");
            String line="";
        
            try(BufferedReader bufferedReader=new BufferedReader(new FileReader(file))){
                //!ignorar cabeceras
                //bufferedReader.readLine(); 
                while((line=bufferedReader.readLine())!=null){
                    String[] campos=line.split(","); 
                    String origen=campos[0];
                    String destino=campos[1];
                    double pesoOrigen=Double.parseDouble(campos[2]);
                    double pesoDestino=Double.parseDouble(campos[3]);
                    double arista=Double.parseDouble(campos[4]);
                    nodos=crearGrafo(origen, destino, pesoOrigen, pesoDestino, arista, nodos);
                        
                }
                return nodos;
            }catch(IOException e){
                System.err.println("Error"+e.getMessage());
            }
        }catch(Exception e){
            System.err.println("Archivo no encontrado"+e.getMessage());
        }
        
        return null;
        
    
        
    }
    public static int getPosicion(String nombre, ArrayList<Nodo> nodos){
        for(int i=0; i<nodos.size(); i++){
            if(nodos.get(i).getNombre().equals(nombre)){
                return i;
            }
        }
        return -1;
    }
    
    public static ArrayList<Nodo> crearGrafo(String origen, String destino, double pesoOrigen, double pesoDestino, double arista, ArrayList<Nodo> nodos) {
        
        Nodo nodoOrigen=obtenerOCrearPorPosicion(origen, nodos,  pesoOrigen);
        Nodo nodoDestino=obtenerOCrearPorPosicion(destino, nodos,  pesoDestino);
        nodoOrigen.agregarHijo(arista, nodoDestino);
    
        return nodos;
    }

    private static Nodo obtenerOCrearPorPosicion(String nombre, ArrayList<Nodo> nodos, double peso){
        Nodo nodo=null;
        int posicion=getPosicion(nombre, nodos);
        if(posicion!=-1){
            nodo=getNodo(nombre, nodos, posicion);
        }else{
            nodo=crearNodo(nombre, peso, nodos);
        }
        return nodo;
    }
    private static Nodo getNodo(String nombre,  ArrayList<Nodo> nodos, int posicion){
        Nodo nodo=null;
        nodo=nodos.get(posicion);
        return nodo;
    }

    private static Nodo crearNodo(String nombre, double peso, ArrayList<Nodo> nodos){
        Nodo nodo=null;
        Arista arista=new Arista();
        nodo=arista.agregarNodo(nombre, peso);
        nodos.add(nodo);
        return nodo;
    }
    
    
}
