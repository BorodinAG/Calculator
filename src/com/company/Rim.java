package com.company;

public enum Rim {
    I(1), II(2), III(3), IV(4), IX(5), V(6), VI(7), VII(8), VIII(9), X(10);

    private Integer chislo;

    Rim (Integer chislo){
        this.chislo = chislo;
    }
    public Integer getChislo(){
        return chislo;
    }

}
