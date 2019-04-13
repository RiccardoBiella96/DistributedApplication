import url_connection.Connection;

import java.io.IOException;

public class Main {
    /*
    * 1. visualizzare a console il contenuto di supsi.ch
    * 2. visualizzare headers richiesta + risposta
    * 3. contare quante risorse recuperate
    * 4. calcolare overhead totale
    * 5. plugin browser traffico HTTP
    * */
    public static void main(String[] args){

        Connection connection = new Connection("http://www.supsi.ch/");
        try {
            //1. visualizzare a console il contenuto di supsi.ch
            connection.showContentPage();
            //2. visualizzare headers richiesta + risposta
            connection.showHeaderRequest();
            connection.showHeaderResponse();
            //3. contare quante risorse recuperate
            int numInternalResources=connection.countInternalResource();
            System.out.println("Risorse Interne: "+numInternalResources);
            //4. calcolare overhead totale
            int overheadTotale=850*numInternalResources;//850 traffico medio di una connesione http tra andata e ritorno
            System.out.println("Overhead totale: "+overheadTotale);
            //5. plugin browser traffico HTTP
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
