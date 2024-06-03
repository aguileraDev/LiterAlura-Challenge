package com.aluracursos.literalura.models;

/**
 * @author Manuel Aguilera / @aguileradev
 */
public enum Languages {
    EN(1),
    ES(2),
    PT(3),
    FR(4),
    DE(5);

    private final int option;

    Languages(int option){
        this.option = option;
    }

    public int getOption() {
        return option;
    }

    public static Languages fromOption(int option){

        for(Languages lang: Languages.values()){
            if (lang.getOption() == option){
                return lang;
            }
        }
      throw new IllegalArgumentException("Opcion de lenguaje no encontrado");
    }
}
