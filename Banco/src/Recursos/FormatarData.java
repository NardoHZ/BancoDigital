/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recursos;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Anderson
 */
public class FormatarData {
    public static String formatarData(String dataAtual, String dataFormatada){
        DateFormat formatUS = new SimpleDateFormat("yyyy-mm-dd");
        Date date = null;
        try {
            date = formatUS.parse(dataAtual);
        } catch (ParseException ex) {
        }
        
        DateFormat formatBR = new SimpleDateFormat("dd-mm-yyyy");
        return dataFormatada = formatBR.format(date);
    }
}
