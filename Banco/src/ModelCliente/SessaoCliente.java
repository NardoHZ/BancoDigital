/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelCliente;

/**
 *
 * @author Anderson
 */
public class SessaoCliente extends ClienteC{
    
    private SessaoCliente(){
        
    }
    
    private static SessaoCliente instancia = null;
    
    public static SessaoCliente getInstancia(){
        if(instancia == null){
            instancia = new SessaoCliente();
        }
        return instancia;
    }
    
}
