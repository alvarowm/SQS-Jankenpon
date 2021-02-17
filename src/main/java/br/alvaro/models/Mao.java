package br.alvaro.models;

import io.quarkus.runtime.annotations.RegisterForReflection;

import java.util.Objects;

@RegisterForReflection
public class Mao {

    String mao;

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Mao)) {
            return false;
        }

        Mao outroObj = (Mao) obj;

        return Objects.equals(outroObj.mao, this.mao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.mao);
    }

    public  Mao (String mao){
        this.mao = mao;
    }

    public String getMao() {
        return mao;
    }

    public void setMao(String mao) {
        this.mao = mao;
    }
}
