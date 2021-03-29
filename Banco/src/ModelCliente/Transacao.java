/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelCliente;

import Recursos.FormatarData;
import static Recursos.FormatarDinheiro.formatarDinheiro;

/**
 *
 * @author gugaa
 */
public class Transacao{
    private int ID_Tranferencia;
    private int ID_Conta;
    private int Agencia;
    private int Conta;
    private int Digito;
    private String Data;
    private String dataFormatada;
    private double Valor;
    private double valorFormatado;
    private double Transferencia;

    public Transacao() {
    }

    public int getID_Tranferencia() {
        return ID_Tranferencia;
    }

    public void setID_Tranferencia(int ID_Tranferencia) {
        this.ID_Tranferencia = ID_Tranferencia;
    }

    public int getID_Conta() {
        return ID_Conta;
    }

    public void setID_Conta(int ID_Conta) {
        this.ID_Conta = ID_Conta;
    }

    public int getAgencia() {
        return Agencia;
    }

    public void setAgencia(int Agencia) {
        this.Agencia = Agencia;
    }

    public int getConta() {
        return Conta;
    }

    public void setConta(int Conta) {
        this.Conta = Conta;
    }

    public int getDigito() {
        return Digito;
    }

    public void setDigito(int Digito) {
        this.Digito = Digito;
    }

    public String getData() {
        return Data;
    }

    public void setData(String Data) {
        this.Data = Data;
    }

    public String getDataFormatada() {
        return FormatarData.formatarData(Data, dataFormatada);
    }

    public double getValor() {
        return Valor;
    }

    public void setValor(double Valor) {
        this.Valor = Valor;
    }

    public String getValorFormatado() {
        return formatarDinheiro(Valor);
    }

    public double getTransferencia() {
        return Transferencia;
    }

    public void setTransferencia(double Transferencia) {
        this.Transferencia = Transferencia;
    }
}