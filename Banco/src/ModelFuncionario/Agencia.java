/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelFuncionario;

import Recursos.FormatarDinheiro;

/**
 *
 * @author Anderson
 */
public class Agencia {
    private int ID_Agencia;
    private String Nome;
    private double Saldo;
    private String saldoFormatado;

    public Agencia() {
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public int getID_Agencia() {
        return ID_Agencia;
    }

    public void setID_Agencia(int ID_Agencia) {
        this.ID_Agencia = ID_Agencia;
    }

    public double getSaldo() {
        return Saldo;
    }

    public void setSaldo(double Saldo) {
        this.Saldo = Saldo;
    }

    public String getSaldoFormatado() {
        return FormatarDinheiro.formatarDinheiro(Saldo);
    }
}
