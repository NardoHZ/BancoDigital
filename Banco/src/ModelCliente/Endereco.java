/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelCliente;

/**
 *
 * @author gugaa
 */
public class Endereco {
    private int ID_Endereco;
    private int ID_Cliente;
    private String Logradouro;
    private String Bairro;
    private String CEP;
    private String UF;

    public Endereco() {
    }

    public int getID_Endereco() {
        return ID_Endereco;
    }

    public void setID_Endereco(int ID_Endereco) {
        this.ID_Endereco = ID_Endereco;
    }

    public int getID_Cliente() {
        return ID_Cliente;
    }

    public void setID_Cliente(int ID_Cliente) {
        this.ID_Cliente = ID_Cliente;
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
}
