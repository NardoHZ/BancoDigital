/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelCliente;

import Recursos.FormatarDinheiro;

/**
 *
 * @author gugaa
 */
public class Conta extends FormatarDinheiro{
    private int ID_Conta;
    private int ID_Cliente;
    private String Agencia;
    private String Conta;
    private String Digito;
    private double Saldo;
    private double saldoFormatado;

    public Conta() {
    }

    public int getID_Conta() {
        return ID_Conta;
    }

    public void setID_Conta(int ID_Conta) {
        this.ID_Conta = ID_Conta;
    }

    public int getID_Cliente() {
        return ID_Cliente;
    }

    public void setID_Cliente(int ID_Cliente) {
        this.ID_Cliente = ID_Cliente;
    }

    public String getAgencia() {
        return Agencia;
    }

    public void setAgencia(String Agencia) {
        this.Agencia = Agencia;
    }

    public String getConta() {
        return Conta;
    }

    public void setConta(String Conta) {
        this.Conta = Conta;
    }

    public String getDigito() {
        return Digito;
    }

    public void setDigito(String Digito) {
        this.Digito = Digito;
    }

    public double getSaldo() {
        return Saldo;
    }

    public void setSaldo(double Saldo) {
        this.Saldo = Saldo;
    }
    
    public String getSaldoFormatado() {
        return formatarDinheiro(Saldo);
    }
}
