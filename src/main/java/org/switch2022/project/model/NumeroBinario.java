package org.switch2022.project.model;

import java.util.Objects;

public class NumeroBinario extends Numero{
    int numero;
    String numeroStr;

    public NumeroBinario(String valor){
        this.numeroStr = valor;

        int j = valor.length() -1;
        for(int i = 0; i<valor.length(); i++) {
            if(valor.charAt(j)=='1')
                numero = numero + (int) Math.pow(2,i);
            j--;
        }
    }

    public NumeroBinario(int num) {
        this.numero = num;

        if(num == 0)
            this.numeroStr = "0";
        else {
            String aux = "";
            while (num != 0) {
                aux = num % 2 + aux;
                num = num / 2;
            }
            numeroStr = aux;
        }
    }

    public int getValueDec() {
        return numero;
    }

    public Numero duplica() {
        return new NumeroBinario( this.numero);
    }

    @Override
    public String toString() {
        return numeroStr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NumeroBinario)) return false;
        NumeroBinario that = (NumeroBinario) o;
        return numero == that.numero;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, numeroStr);
    }
}
