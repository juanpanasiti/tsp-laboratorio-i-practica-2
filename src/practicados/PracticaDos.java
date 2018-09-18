/*
 * Ejericicios del documento "Practica 2.pdf" del campus virtual
 * Laboratorio de Computación I
 * 04-09-2018
 */
package practicados;

import java.util.Scanner;
//import static utils.formats.*;
import static utilidades.IO.*;
/**
 *
 * @author Juan Marcelo Panasiti
 */
public class PracticaDos {

    public static void main(String[] args) {
        int option;
        boolean exit = false;
        Scanner key;
        while(true){
            printlnInColor("verde","Seleccione una opción:");
            printlnInColor("azul","1) Ejericicio 1");
            printlnInColor("azul","2) Ejericicio 2");
            printlnInColor("azul","3) Ejericicio 3");
            printlnInColor("azul","4) Ejericicio 4");
            printlnInColor("rojo","5.Salir");
            System.out.println("");
            System.out.print("$ ");
            
            key = new Scanner(System.in);
            if(key.hasNextInt()){
                option = key.nextInt();
            }else {
                key.nextLine();
                option = 99; //No importa el valor, en el switch saldrá por default
            }
            switch(option){
                case 1: EjercicioUno();
                    break;
                case 2: EjercicioDos();
                    break;
                case 3: EjercicioTres();
                    break;
                case 4: EjercicioCuatro();
                    break;
                case 5: exit = true;
                    break;
                default: printlnInColor("rojo","Opción incorrecta, intente de nuevo.");
                    break;
            } //Fin Switch
            if(exit){
                break;
            } // Fin If
        } //Fin While
    } // Fin método Main
    
    public static void EjercicioUno(){
        /*
        * Muestra los números del 1 al 100 (ambos incluidos) divisibles entre 2 y 3. 
        * Utiliza el bucle que for.
        */
        boolean primerNum = true;
        String respuesta = "";
        for(int i = 0; i <= 100; i++){
            if((i % 2 == 0) && (i % 3 == 0)){
                if(!primerNum){
                    respuesta += "; ";
                }else {
                    primerNum = false;
                }
                respuesta += i;
            }
        }
        printlnInColor("verde",respuesta);
        continuar();
        saltoLinea(15);
    }
    
    public static void EjercicioDos(){
        /*
                Codifique un método denominado esPar que reciba como parámetro
            un valor numérico entero y retorne true en caso de que el numero
            sea par, caso contrario retorne false. Compruebe el funcionamiento
            del método solicitando al usuario del programa que ingrese números
            aleatorios. 
        */
        Scanner key = new Scanner(System.in);
        printlnInColor("azul","Ingrese un número para saber si es par");
        if(esPar(key.nextInt())){
            printlnInColor("verde","Es par!");
        }else{
            printlnInColor("rojo","No es par!");
        }
        continuar();
        saltoLinea(15);
    }
    public static boolean esPar(int numero){
        boolean par = false;
        if(numero % 2 == 0)
            par = true;
        return par;
    }
    
    public static void EjercicioTres(){
        /*
            Programar un algoritmo recursivo que en cada recursión multiplique 
        a si mismo un número ingresado por el usuario hasta que el valor 
        resultante sea mayor 100.000.000.
        Valide que el número ingresado por el usuario sea mayor a 1, ejemplo:
            Se ingresa el valor 6
                1 iteración => 6 * 6 = 36
                2 iteración => 36 * 36 = 1296
                3 iteración => 1296 * 1296 = 1679616
                4 iteración => 1679616 * 1679616 = 2821109907456
            Fin Recursión   
        */
        long numero;
        Scanner key = new Scanner(System.in);
        do {
            printInColor("azul","Ingrese un número: ");
            numero = key.nextLong();
            if(numero < 1){
                printlnInColor("rojo","ERROR! Tiene que ser un número mayor a 1!");
                printlnInColor("rojo","Va de nuevo..");
            }
        }while(numero < 1);
        multiplicate(numero,1);
        continuar();
        saltoLinea(15);
    }
    public static void multiplicate(long numero, long nIteracion){
        if(numero > 1000000){
            return;
        }
        long cuadrado = numero * numero;
        printlnInColor("verde",nIteracion + " iteración => " + numero + " * " + numero + " = " + cuadrado);
        nIteracion++;
        multiplicate(cuadrado,nIteracion);
    }
    
    public static void EjercicioCuatro(){
        /*
            Cree las funciones necesarias para Calcular el sueldo de los 
        trabajadores dependiendo de las horas trabajadas. El valor de la hora en
        horario diurnos (8:00 a 20:00) es de $10/hora, si el horario es
        nocturno (20:00 a 8:00) se incrementa en un 50%. El programa debe pedir
        al usuario en que horario trabajo el empleado (hora ingreso, minuto 
        ingreso, hora salida, minuto salida), validar que la hora/minuto de 
        ingreso no sea mayor a la hora/minuto de salida, validar que la 
        cantidad de horas trabajadas no supere las 8 horas, calcular cuantas
        horas trabajo en total y cuanto corresponde pagarle. Nota: Se
        recomienda calcular el tiempo laboral en minutos
        */
        final float precioHora = 10.0f;
        float sueldoDia = 0.0f;
        int horaEntrada, minutosEntrada, horaSalida, minutosSalida, minutosTrabajados;
        while(true){
            horaEntrada = solicitarEnteroEntre("Ingrese la hora de entrada [0-23]: ",0,23);
            minutosEntrada = solicitarEnteroEntre("Ingrese los minutos de entrada [0-59]: ",0,59);
            horaSalida = solicitarEnteroEntre("Ingrese la hora de salida [0-23]: ",0,23);
            minutosSalida = solicitarEnteroEntre("Ingrese los minutos de salida [0-59]: ",0,59);
            minutosTrabajados = difHora(horaEntrada,minutosEntrada,horaSalida,minutosSalida);
            if( minutosTrabajados > 480){
                printlnInColor("rojo","El horario ingresado supera las 8hs máximas permitidas. " + minutosTrabajados);
                continue;
            } else{
                break;
            }
        } // FIN While()
        if (esHorarioDiurno(horaEntrada) == esHorarioDiurno(horaSalida)){
            //Pregunta si tanto la hora de entrada como la de salida son parte
            //Del mismo turno, es decir, si todo el tiempo trabajó en el turno
            //diurno o en el nocturno
            if(esHorarioDiurno(horaEntrada)){
                sueldoDia = calcularSueldo(horaEntrada, minutosEntrada, horaSalida, minutosSalida, precioHora);
            } else{
                sueldoDia = calcularSueldo(horaEntrada, minutosEntrada, horaSalida, minutosSalida, precioHora * 1.5f);
            }
        }else{
            //El turno de la hora de entrada es distinto al turno de la hora de salida
            if(esHorarioDiurno(horaEntrada)){
                sueldoDia += calcularSueldo(horaEntrada, minutosEntrada, 20, 00, precioHora);
                sueldoDia += calcularSueldo(20, 00, horaSalida, minutosSalida, precioHora * 1.5f);
            } else {
                sueldoDia += calcularSueldo(horaEntrada, minutosEntrada, 8, 00, precioHora * 1.5f);
                sueldoDia += calcularSueldo(8, 00, horaSalida, minutosSalida, precioHora);
            }
        }
        printInColor("azul","El sueldo a pagar por el día de trabajo desde las " + 
                formatoHora(horaEntrada, minutosEntrada) + " hasta las " + 
                formatoHora(horaSalida, minutosSalida) + " es de ");
        printlnInColor("verde",fPesos(sueldoDia));
        continuar();
        saltoLinea(15);
    }// FIN EjercicioCuatro()
    public static boolean esHorarioDiurno(int hora){
        if ((hora >= 8) && (hora < 20 ))
            return true;
        else
            return false;
    }// FIN esHoraioDiurno()
    public static float calcularSueldo(int horaInicial, int minutoInicial, int horaFinal, int minutoFinal, float precioHora){
        float sueldo;
        sueldo = ((float)(difHora(horaInicial,minutoInicial,horaFinal,minutoFinal) / 60)) * precioHora;
        return sueldo;
    }
    
    
    
}// Fin CLASS PracticaDos
