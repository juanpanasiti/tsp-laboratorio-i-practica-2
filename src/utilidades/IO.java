/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.util.Scanner;

/**
 *
 * @author juan
 */
public class IO {
    public static String setColor(String color, String msg){
        color = color.toLowerCase(); //Cambia la palabra a minuscula
        String colorCode;
        switch(color){
            case "negro": colorCode = "\033[30m" ;
                break;
            case "rojo": colorCode = "\033[31m" ;
                break;
            case "verde": colorCode = "\033[32m" ;
                break;
            case "amarillo": colorCode = "\033[33m" ;
                break;
            case "azul": colorCode = "\033[34m" ;
                break;
            case "magenta": colorCode = "\033[35m" ;
                break;
            case "cyan": colorCode = "\033[36m" ;
                break;
            case "blanco": colorCode = "\033[37m" ;
                break;
            default: colorCode = "\033[30m";
                break;
        }
            return colorCode + msg;
    } // FIN setColor()
    
    public static void printInColor(String color, String msg){
        System.out.print(setColor(color,msg));
    } // FIN printInColor
    
    public static void printlnInColor(String color, String msg){
        System.out.println(setColor(color,msg));
    } // FIN printlnInColor  
    
    public static int solicitarEnteroEntre(String mensaje, int minimo, int maximo){
        //Devuelve un entero que se debe ingresar por teclado y debe estar 
        //entre los límites pasados por parámetro
        Scanner key;
        int entero;
        if(minimo>maximo){
            int aux = minimo;
            minimo = maximo;
            maximo = aux;
        }
        while(true){
            printInColor("azul",mensaje);
            key = new Scanner(System.in);
            if (key.hasNextInt()){
                entero = key.nextInt();
                if ((entero >= minimo)&&(entero <= maximo)){
                    break;
                } else{
                printlnInColor("rojo","Error! El valor ingresado está fuera de los límites.");
                continue;
                    
                }
            } else {
                printlnInColor("rojo","Error! no ingresó un número válido. Intente de nuevo.");
                continue;
            }
        } // FIN While
        return entero;
        
    }// FIN solicitarEnteroEntre()
    
    public static int difHora(int horaInicio, int minInicio,int horaFin, int minFin){
        //Devuelve la diferencia en minutos de dos hora pasadas como parámetro
        boolean dosDias = false;
        int diferencia;
        if(horaInicio > horaFin){
            dosDias = true;
        }
        else{
            if((horaInicio == horaFin)&&(minInicio > minFin)){
                dosDias = true;
            }
        }
        if(dosDias){
            diferencia = difHora(horaInicio, minInicio,24, 00) + difHora(00, 00, horaFin, minFin);
        }else {
            diferencia = (horaFin - horaInicio) * 60 - minInicio + minFin;
        }
        return diferencia;
    } // FIN difHora()
    
    public static String fPesos(float monto){
        return "$" + String.format("%.2f", monto);
    } // FIN fPesos
    
    public static void continuar(){
        Scanner key;
        printInColor("rojo","Presione [ENTER] para continuar...");
        key = new Scanner(System.in);
        key.nextLine();
    }// FIN continuar
    
    public static void saltoLinea(int cantSaltos){
        for(int i = 0; i < cantSaltos; i++){
            System.out.println("");
        }
    } // FIN saltoLinea
    
    public static String formatoHora(int hora, int minutos){
        String sHora, sMin;
        if (hora < 10)
            sHora = "0" + hora;
        else
            sHora = "" + hora;
        
        if (minutos < 10)
            sMin = "0" + minutos;
        else
            sMin = "" + minutos;
        return (sHora + ":" + sMin);
    }
} // FIN Clase IOUtils


