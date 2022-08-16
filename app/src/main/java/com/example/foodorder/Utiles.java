package com.example.foodorder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utiles {

    boolean mail_validado;

    public boolean getMail_validado() {
        return mail_validado;
    }

    public void setMail_validado(boolean mail_validado) {
        this.mail_validado = mail_validado;
    }

    public boolean ValidarMail(String email) {
        // Patron para validar el email
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher mather = pattern.matcher(email);
        return mather.find();
    }

    public String GetItemList(String Lista, int NumItem, String separador) {
        String Cadena;
        int Posic;
        int i;
        int lenSeparador;
        int LastPosic;


        Cadena = Lista;
        Posic = 1;
        lenSeparador = separador.length();

        for (i = 1; (i <= (NumItem - 1)); i++) {

            Posic = Cadena.indexOf(separador);
            Cadena = Mid(Cadena, Posic + lenSeparador);
        }


        if ((Posic == 0)) {
            return "";


        }


        LastPosic = (Cadena.indexOf(separador) + 1);
        if ((LastPosic == 0)) {

            return Cadena;
        } else {
            return Cadena.substring(0, (LastPosic - 1));
        }

    }

    public String Mid(String param, int startIndex) {
        String result = param.substring(startIndex);
        return result;
    }

    public int getnumlist(String Lista, String separador) {
        int i;
        int Inicio;

        Inicio = 0;

        if ((separador != " ")) {
            Lista = Lista.trim();
        }
        if ((Lista != "")) {
            i = 1;
        } else {
            i = 0;
        }


        if ((separador != "")) {
            for (i = 1; (i < Lista.length());
            ) {
                Inicio = (Lista.indexOf(separador, Inicio) + 1);
                if ((Inicio == 0)) ;
                {
                    break;
                }

                //i = (i + 1);
            }

        } else {
            i = 1;
        }

        return i + 1;
    }

}
