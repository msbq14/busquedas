package grafo;

import java.util.ArrayList;

public class Nodo {
    private int id;
    private String nombre;
    private double peso;
    private ArrayList<Arista> hijos=new ArrayList<>();
    

    public Nodo( String nombre, double peso){
        this.nombre=nombre;
        this.peso=peso;
        
    }

    public void agregarHijo(double peso, Nodo destino){
        Arista miArista=new Arista( peso, destino);
        hijos.add(miArista);
    }

    public ArrayList<Arista> gethijos() {
        return hijos;
    }

    public void sethijos(ArrayList<Arista> hijos) {
        this.hijos = hijos;
    }
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Nodo [nombre=").append(nombre)
                .append(", peso=").append(peso)
                .append(", lista de aristas=");
            for (Arista arista : hijos) {
                builder.append(arista.getNodo().getNombre()).append(", ").append(arista.getPeso()).append(";");
            }
        builder.append("]");
        return builder.toString();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void modificarNodo(String nombre, double peso) {
        this.setNombre(nombre);
        this.setPeso(peso);
    }

    
    
}
