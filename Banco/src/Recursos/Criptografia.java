/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recursos;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Anderson
 */
public class Criptografia {
    
    public static String MD5(String senha){
        return stringHexa(gerarHash(senha));
    }
    
    //Gerar Hash - Passagem do valor por parâmetro
    public static byte[] gerarHash(String senha){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(senha.getBytes());
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
    
    //Gerar Hash
    public static String stringHexa(byte[] bytes){
        StringBuilder s = new StringBuilder();
        
        for(int i = 0; i < bytes.length; i++){
            int  parteAlta = ((bytes[i] >> 4) & 0xf) << 4;
            int parteBaixa = bytes[i] & 0xf;
            
            if(parteAlta == 0) s.append('0');
            
            s.append(Integer.toHexString(parteAlta | parteBaixa));
        }
        return s.toString();
    }
}
