/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelFuncionario;

/**
 *
 * @author Anderson
 */
public class SessaoFuncionario extends Funcionario{
    
    private SessaoFuncionario(){
        
    }
    
    private static SessaoFuncionario instancia = null;
    
    public static SessaoFuncionario getInstancia(){
        if(instancia == null){
            instancia = new SessaoFuncionario();
        }
        return instancia;
    }
    
}
