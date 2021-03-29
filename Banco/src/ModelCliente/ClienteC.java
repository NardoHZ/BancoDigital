/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelCliente;

import Recursos.FormatarData;
import Recursos.FormatarDinheiro;

/**
 *
 * @author gugaa
 */
public class ClienteC extends Pessoa{
    private int ID_Cliente;
    private String Renda;
    private String dataNascimentoFormatada;

    public ClienteC() {
    }
    
    FormatarDinheiro format = new FormatarDinheiro();
    
    public int getID_Cliente() {
        return ID_Cliente;
    }

    public void setID_Cliente(int ID_Cliente) {
        this.ID_Cliente = ID_Cliente;
    }

    public String getRenda() {
        return Renda;
    }

    public void setRenda(String Renda) {
        this.Renda = Renda;
    }
    
    public String getDataNascimentoFormatada() {
        return FormatarData.formatarData(getDataNascimento(), dataNascimentoFormatada);
    }
}
