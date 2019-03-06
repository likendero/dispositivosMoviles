package org.likendero.mainactivity;

import android.util.Base64;
import android.util.Log;

import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class CifrarDescifrar  {

    /**
     * encriptar
     * @param mensaje
     * @param clave
     * @return
     */
    public String cifrarMensajes(String mensaje,String clave){
        byte[] salida = null;
        try{
            byte[] clavebit = Arrays.copyOf(clave.getBytes("UTF8"),32);
            byte[] mensajeBit = mensaje.getBytes("UTF8");
            SecretKey key = new SecretKeySpec(clavebit,"AES");
            Cipher ciper = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            ciper.init(Cipher.ENCRYPT_MODE,key);
            salida = ciper.doFinal(mensajeBit);
        }catch(Exception ex){
            Log.d("error",ex.getMessage());
        }

        return Base64.encodeToString(salida,Base64.DEFAULT);
    }
    public String descifrarMensajes(String mensaje,String clave){
        byte[] salida = null;
        try{
            byte[] mensajeBit =  Base64.decode(mensaje,Base64.DEFAULT);
            byte[] clavebit = Arrays.copyOf(clave.getBytes("UTF8"),32);

            SecretKey key = new SecretKeySpec(clavebit,"AES");
            Cipher ciper = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            ciper.init(Cipher.DECRYPT_MODE,key);
            salida = ciper.doFinal(mensajeBit);
        }catch(Exception ex){
            Log.d("error",ex.getMessage());
        }

        return new String(salida);
    }


}
