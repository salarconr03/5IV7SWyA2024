/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.aes;

/**
 *
 * @author Alumno
 */
import javax.crypto.*;
import java.security.*;
import java.io.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class App {
    static String texto = "habia una vez un patito que decía miau miau";
    static String llave = "qwertyuiopasdfgh";
    static String vector = "zxcvbnmasdfghjkl";
    
    public static void main(String[] args) throws Exception{
        System.out.println("Ejemplo de AES");
        System.out.println("El texto a cifrar es: " + texto);
        
        //declarar un arreglo de bytes
        byte[] cifrado = encript(texto, llave);
        
        System.out.println("Texto cifrado:");
        
        for(int i = 0; i  < cifrado.length; i++){
            System.out.println(new Integer(cifrado[i]));
        }
        System.out.println();
        
        String decifrar = decrypt(cifrado, llave);
        
        System.out.println("El texto descifrado es: " + decifrar);
    }

    public static byte[] encript(String texto, String llave) throws Exception{
        //definimos la instancia
        Cipher cifradoaes = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
        
        //generamos las llaves
        SecretKeySpec key = new SecretKeySpec(llave.getBytes("UTF-8"), "AES");
        
        //inicializamos el modo
        cifradoaes.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(vector.getBytes("UTF-8")));
        
        return cifradoaes.doFinal(texto.getBytes("UTF-8"));
    }

    public static String decrypt(byte[] cifrado, String llave) throws Exception{
        //definimos la instancia
        Cipher cifradoaes = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
        
        //generamos las llaves
        SecretKeySpec key = new SecretKeySpec(llave.getBytes("UTF-8"), "AES");
        
        //inicializamos el modo
        cifradoaes.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(vector.getBytes("UTF-8")));
        
        return new String(cifradoaes.doFinal(cifrado), "UTF-8");
    }
}
