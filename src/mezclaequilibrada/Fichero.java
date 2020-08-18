/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mezclaequilibrada;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import jdk.jfr.events.FileWriteEvent;




/**
 *
 * @author Admin
 */
public class Fichero {
    static String getDatos(String Nombre){
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        String linea = null;
        try{
            archivo = new File ("C:\\Users\\Admin\\Documents\\Organizacion\\MezclaEquilibrada\\"+Nombre);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            linea = br.readLine();
        } catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if( null != fr)
                    fr.close();
                }catch (Exception e2){
                        e2.printStackTrace();
                        }
            }
            return linea;
        }
        static void setDatos(String NombreFichero, String Datos){
            FileWriter fichero = null;
            PrintWriter pw = null;
            try{
                fichero = new FileWriter("C:\\Users\\Admin\\Documents\\Organizacion\\MezclaEquilibrada\\"+NombreFichero);
                pw = new PrintWriter(fichero);
                
                pw.print(Datos);
                
            } catch (Exception e){
                e.printStackTrace();
            }finally {
                try{
                    if (null != fichero)
                        fichero.close();
                    
                } catch (Exception e2) {
                e2.printStackTrace();
            }
            }
        }

    
    }
    

