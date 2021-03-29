/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelFuncionario;

import ModelCliente.Pessoa;
import Recursos.FormatarData;
import Recursos.FormatarDinheiro;

/**
 *
 * @author Anderson
 */
public class Funcionario extends Pessoa{
    private int ID_Funcionario;
    private int ID_Departamento;
    private int ID_EnderecoFuncionario;
    private double Salario;
    private String dataNascimentoFormatado;
    private String Logradouro;
    private String Bairro;
    private String CEP;
    private String UF;
    private String Departamento;
    private String salarioFormatado;

    public Funcionario() {
    }

    public String getSalarioFormatado() {
        return FormatarDinheiro.formatarDinheiro(Salario);
    }

    public String getDepartamento() {
        return Departamento;
    }

    public void setDepartamento(String Departamento) {
        this.Departamento = Departamento;
    }

    public String getLogradouro() {
        return Logradouro;
    }

    public void setLogradouro(String Logradouro) {
        this.Logradouro = Logradouro;
    }

    public String getBairro() {
        return Bairro;
    }

    public void setBairro(String Bairro) {
        this.Bairro = Bairro;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }


    public int getID_EnderecoFuncionario() {
        return ID_EnderecoFuncionario;
    }

    public void setID_EnderecoFuncionario(int ID_EnderecoFuncionario) {
        this.ID_EnderecoFuncionario = ID_EnderecoFuncionario;
    }


    public int getID_Funcionario() {
        return ID_Funcionario;
    }

    public void setID_Funcionario(int ID_Funcionario) {
        this.ID_Funcionario = ID_Funcionario;
    }

    public int getID_Departamento() {
        return ID_Departamento;
    }

    public void setID_Departamento(int ID_Departamento) {
        this.ID_Departamento = ID_Departamento;
    }

    public double getSalario() {
        return Salario;
    }

    public String getDataNascimentoFormatado() {
        return FormatarData.formatarData(getDataNascimento(), dataNascimentoFormatado);
    }

    public void setSalario(double Salario) {
        this.Salario = Salario;
    }
}
