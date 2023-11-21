/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkg03des;

/**
 *
 * @author Alumno
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.crypto.*;
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        //vamos a cargar un archivo para cifrar
        if(args.length != 1){
            mensajeAyuda();
            System.exit(1);
        }
        
        System.out.println("1. Generar la clavae Des");
        //vamos a ocupar la clase KeyGenerator
        KeyGenerator generadorDES = KeyGenerator.getInstance("DES");
        //ahora debo definir el tamaño de la clave
        generadorDES.init(56); //56 bits
        
        //generamos la clave secreta
        SecretKey clave = generadorDES.generateKey(); //se generan las 16 subllaves
        
        System.out.println("Veamos la clave: " + clave);
        //para poder ver la clave necesito un método para darle formato
        mostrarBytes(clave.getEncoded());
        System.out.println("");
        
        //paso 2 es momento de definir los elementos para cifrar
        /*
        Des es un cifrado por bloques, tenemos que dar reglas de 
        como se va a manejar el bloque Modo Cifrado ECB (Electronic Code Book) 
        Estandar PKCS5Padding
        */
        
        System.out.println("2. Cifrar con DES el archivo " + args[0] + " , generamos el resultado en " + args[0] + ".cifrado");
        
        //lo feo a cifrar
        Cipher cifrador = Cipher.getInstance("DES/ECB/PKCS5Padding");
        //ciframos
        cifrador.init(Cipher.ENCRYPT_MODE, clave);
        
        //leer el archivo y definir de cuanto en cuanto bytes de lectura
        byte[] buffer = new byte[1000]; //se leen cada mil bits
        
        byte[] bufferCifrado;
        
        FileInputStream in = new FileInputStream(args[0]);
        FileOutputStream out = new FileOutputStream(args[0] + ".cifrado");
        
        //leo cada archivo
        int bytesleidos = in.read(buffer, 0, 1000);
        while(bytesleidos != -1){
            //que no he terminado de leer el archivo
            bufferCifrado = cifrador.update(buffer, 0, bytesleidos);
            out.write(bufferCifrado);
            bytesleidos = in.read(buffer, 0, bytesleidos);
        }
        bufferCifrado = cifrador.doFinal();
        out.write(bufferCifrado);
        
        in.close();
        out.close();
        
        System.out.println("Vamos a descifrar el archivo " + args[0] + ".cifrado" + " , y el resultado esta en " + args[0] + ".descifrado");
        
        //desciframos
        cifrador.init(Cipher.DECRYPT_MODE, clave);
        
        //leer el archivo y definir de cuanto en cuanto bytes de lectura
        
        byte[] bufferPlano;
        
        in = new FileInputStream(args[0] + ".cifrado");
        out = new FileOutputStream(args[0] + ".descifrado");
        
        //leo cada archivo
        bytesleidos = in.read(buffer, 0, 1000);
        while(bytesleidos != -1){
            //que no he terminado de leer el archivo
            bufferPlano = cifrador.update(buffer, 0, bytesleidos);
            out.write(bufferPlano);
            bytesleidos = in.read(buffer, 0, bytesleidos);
        }
        bufferPlano = cifrador.doFinal();
        out.write(bufferPlano);
        
        in.close();
        out.close();
    }

    public static void mensajeAyuda() {
            System.out.println("Ejemplo de cifrado DES");
            System.out.println("Debe tener afuerzar un archivo cargado");
    }
    
    public static void mostrarBytes(byte[] buffer){
        System.out.write(buffer, 0, buffer.length);
    }
    
}
