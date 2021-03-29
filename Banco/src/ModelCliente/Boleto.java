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
public class Boleto{
    private int ID_Boleto;
    private int ID_Cliente;
    private double Valor;
    private String valorFormatado;
    private String Codigo;
    private String dataEmissao;
    private String dataEmissaoFormatada;
    private String Validade;
    private String validadeFormatada;
    private String Status;
    private String Pagamento;

    public Boleto() {
    }

    public double getValor() {
        return Valor;
    }

    public void setValor(double Valor) {
        this.Valor = Valor;
    }
    
    public String getValorFormatado() {
        return FormatarDinheiro.formatarDinheiro(Valor);
    }

    public int getID_Cliente() {
        return ID_Cliente;
    }

    public void setID_Cliente(int ID_Cliente) {
        this.ID_Cliente = ID_Cliente;
    }

    public int getID_Boleto() {
        return ID_Boleto;
    }

    public void setID_Boleto(int ID_Boleto) {
        this.ID_Boleto = ID_Boleto;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(String dataEmissao) {
        this.dataEmissao = dataEmissao;
    }
    
    public String getDataEmissaoFormatada() {
        return FormatarData.formatarData(dataEmissao, dataEmissaoFormatada);
    }
    
    public String getValidadeFormatada() {
        return FormatarData.formatarData(Validade, validadeFormatada);
    }

    public String getValidade() {
        return Validade;
    }

    public void setValidade(String Validade) {
        this.Validade = Validade;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getPagamento() {
        return Pagamento;
    }

    public void setPagamento(String Pagamento) {
        this.Pagamento = Pagamento;
    }
}
