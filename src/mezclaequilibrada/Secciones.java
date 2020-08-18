/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mezclaequilibrada;
import java.util.ArrayList;
/**
 *
 * @author Admin
 */
public class Secciones {
    private ArrayList<Integer> ListValues;
    public Secciones(){
        ListValues = new ArrayList<Integer>();
    
}
    public void addValue(int Value) {
        this.ListValues.add(Value);
    }
    public void Imprime(){
        int n = this.ListValues.size();
        for(int i=0; i<n; i++){
            System.out.println(this.ListValues.get(i)+"\t");
        }
        
    }
    public String ObtenerValoresConcatenados(boolean BANDCambioSeccion){
        int n = this.ListValues.size();
        String salida = "";
        for(int i=0; i<n; i++){
            salida += ((salida=="") ? "" : " ")+this.ListValues.get(i).toString()+((BANDCambioSeccion) ? "" : "'");
        }
        return salida;
        
    }
    public  boolean EstaVacio(){
        return this.ListValues.isEmpty();
    }
    public  int ObtenerNumeroElementos(){
        return this.ListValues.size();
    }
    public int ObtenerValor(int indice){
        return this.ListValues.get(indice);
    }
    public void EliminarElemento(int indice){
        this.ListValues.remove(indice);
    }
    
}
