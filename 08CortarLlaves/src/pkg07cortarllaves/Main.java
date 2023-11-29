/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkg07cortarllaves;

/**
 *
 * @author Usuario
 */
import java.io.*;
import javax.crypto.*;
import java.security.*;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class Main {

    private static Cipher rsa;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        
        /*Determinar la cración de las llaves de Rsa para public y private*/
        
        KeyPairGenerator generadollaves = KeyPairGenerator.getInstance("RSA");
        
        //calculamos las llaves publica y privada
        KeyPair llavesrsa = generadollaves.generateKeyPair();
        
        PublicKey llavepublica = llavesrsa.getPublic();
        PrivateKey llaveprivada = llavesrsa.getPrivate();
        
        //crea los metodos para guardar cargar las llaves
        saveKey(llavepublica, "public.key");
        
        llavepublica = loadpublickey("public.key");
        
        saveKey(llaveprivada, "private.key");
        
        llaveprivada = loadprivatekey("private.key");
        
        //hacemos la instancia de rsa
        
        rsa = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        
        String texto = "Había una ves un pato que decía miau miau";
        
        //ciframos
        
        rsa.init(Cipher.ENCRYPT_MODE, llavepublica);
        
        //ciframos
        byte[] cifrado = rsa.doFinal(texto.getBytes());
        
        for(byte b : cifrado) {
            System.out.println(Integer.toHexString(0xFF & b));
        }
        System.out.println("");
        
        //descifrar
        rsa.init(Cipher.DECRYPT_MODE, llaveprivada);
        byte[] bytesdescifrados = rsa.doFinal(cifrado);
        String textodescifrado = new String(bytesdescifrados);
        System.out.println("Mensaje descifrado es: " + textodescifrado);
        
        
    }

    private static void saveKey(Key llave, String archivo) throws Exception{
        byte[] llavespubpriv = llave.getEncoded();
        //generamos el fichero
        FileOutputStream fos = new FileOutputStream(archivo);
        fos.write(llavespubpriv);
        fos.close();
        
        
    }

    private static PublicKey loadpublickey(String archivo) throws Exception{
        FileInputStream fis = new FileInputStream(archivo);
        int numbytes = fis.available();
        byte[] bytes = new byte[numbytes];
        
        fis.read(bytes);
        fis.close();
        
        //verificamos el formato de la clave
        KeyFactory fabricallaves = KeyFactory.getInstance("RSA");
        //comparamos las llaves
        KeySpec keyspec = new X509EncodedKeySpec(bytes);
        PublicKey llavedelarchivo = fabricallaves.generatePublic(keyspec);
        return llavedelarchivo;
    }

    private static PrivateKey loadprivatekey(String archivo) throws Exception {
        FileInputStream fis = new FileInputStream(archivo);
        int numbytes = fis.available();
        byte[] bytes = new byte[numbytes];
        
        fis.read(bytes);
        fis.close();
        
        //verificamos el formato de la clave
        KeyFactory fabricallaves = KeyFactory.getInstance("RSA");
        //comparamos las llaves
        KeySpec keyspec = new PKCS8EncodedKeySpec(bytes);
        PrivateKey llavedelarchivopriv = fabricallaves.generatePrivate(keyspec);
        return llavedelarchivopriv;
    }
    
}
