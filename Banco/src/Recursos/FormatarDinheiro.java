/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recursos;

import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author Anderson
 */
public class FormatarDinheiro {
    
    public static String formatarDinheiro(double valor){
        Locale l = new Locale("pt", "BR");
        NumberFormat nf = NumberFormat.getCurrencyInstance(l);
        return nf.format(valor);
    }
}
