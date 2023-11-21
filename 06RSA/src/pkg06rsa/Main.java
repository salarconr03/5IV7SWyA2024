/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkg06rsa;

/**
 *
 * @author Alumno
 */
import java.io.InputStream;
import java.security.*;
import javax.crypto.*;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
/*Esta librería sirve para poder realizar el calculo de primos más grandes pero a costo de que el bloque debe ser constante
BC es un */


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        //Tenemos que añadir el nuebo provedor
        Security.addProvider(new BouncyCastleProvider());
        System.out.println("1. Vamos a crear las llaves publica y privada");
        
        //tenemos que inicializar la clase KeyPairGenerator
        KeyPairGenerator generadorclaves = KeyPairGenerator.getInstance("RSA", "BC");
        
        //tenemos que definir el tamaño de la clave
        generadorclaves.initialize(1024);
        
        //creamos las llaves publica y privada
        KeyPair clavesRSA = generadorclaves.genKeyPair();
        //privada
        PrivateKey clavePrivada = clavesRSA.getPrivate();
        //pública
        PublicKey clavePublica = clavesRSA.getPublic();
        
        System.out.println("2. Escribe el texto que quieres cifrar");
        
        byte[] bufferplano = leerLinea(System.in);
        
        /*
        BC no funcian en modo ECB no divide el mensaje en bloques
        nosotres tenemos que realizar esa operacción
        y solo puede cifrar máximo 64 caracteres el bloqu es constante
        */
        
        //vamos a cifrar
        
        Cipher cifrador = Cipher.getInstance("RSA", "BC");
        
        //cifrar con publica
        
        cifrador.init(Cipher.ENCRYPT_MODE, clavePublica);
        
        //tenemos que leer el buffer
        System.out.println("Ciframos con pública");
        byte[] bufferCifrado = cifrador.doFinal(bufferplano);
        
        System.out.println("Texto cifrado");
        //crear un método para leer el boloque cifrado
        mostrarBytes(bufferCifrado);
        System.out.println("\n**************");
        
        //desciframos con privada
        cifrador.init(Cipher.ENCRYPT_MODE, clavePrivada);
        
        byte[] bufferDescifrado = cifrador.doFinal(bufferCifrado);
        
        System.out.println("Texto descifrado");
        mostrarBytes(bufferDescifrado);
        System.out.println("\n**************");
        
        //cifrar con privada
        
        cifrador.init(Cipher.ENCRYPT_MODE, clavePrivada);
        
        //tenemos que leer el buffer
        System.out.println("Ciframos con privada");
        bufferCifrado = cifrador.doFinal(bufferplano);
        
        System.out.println("Texto cifrado");
        //crear un método para leer el boloque cifrado
        mostrarBytes(bufferCifrado);
        System.out.println("\n**************");
        
        //desciframos con pública
        cifrador.init(Cipher.ENCRYPT_MODE, clavePublica);
        
        bufferDescifrado = cifrador.doFinal(bufferCifrado);
        
        System.out.println("Texto d-escifrado");
        mostrarBytes(bufferDescifrado);
        System.out.println("\n**************");


    }

    public static byte[] leerLinea(InputStream in) throws Exception{
        //un bufffer
        byte[] buffer1 = new byte[1000];
        
        int i = 0;
        
        byte c;
        
        c = (byte)in.read();
        
        while((c!='\n') && (i < 1000)){
            buffer1[i] = c;
            c = (byte)in.read();
            i++;
        }
        
        //asignar
        byte[] buffer2 = new byte[i];
        for(int j = 0; j < i; j++){
            buffer2[j] = buffer1[j];
        }
        return (buffer2);
    }

    public static void mostrarBytes(byte[] buffer) throws Exception{
        System.out.write(buffer,0,buffer.length);
    }
    
}
