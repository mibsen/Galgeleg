package com.galgeleg.mibsen.galgeleg;

import android.support.v4.content.res.TypedArrayUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

public class Galgelogik {
    /** AHT afprøvning er muligeOrd synlig på pakkeniveau */
    ArrayList<String> muligeOrd = new ArrayList<String>();
    private String ordet;
    private ArrayList<String> brugteBogstaver = new ArrayList<String>();
    private String synligtOrd;
    private int antalForkerteBogstaver;
    private boolean sidsteBogstavVarKorrekt;
    private boolean spilletErVundet;
    private boolean spilletErTabt;
    private final String muligeBogstaver= "qwertyuiopåasdfghjklæøzxcvbnm";
    private Character[] tilladteBogstaver;
    private int level;
    private int antalForkerteMuligeBogstaver = 7;

    public ArrayList<String> getBrugteBogstaver() {
        return brugteBogstaver;
    }

    public String getSynligtOrd() {
        return synligtOrd;
    }

    public String getOrdet() {
        return ordet;
    }

    public Character[] muligeBogstaver() {

        String total = "";
        for (Character b : tilladteBogstaver){
            total = total + b;
        }

        return tilladteBogstaver;
    }

    public int getAntalForkerteBogstaver() {
        return antalForkerteBogstaver;
    }

    public boolean erSidsteBogstavKorrekt() {
        return sidsteBogstavVarKorrekt;
    }

    public boolean erSpilletVundet() {
        return spilletErVundet;
    }

    public boolean erSpilletTabt() {
        return spilletErTabt;
    }

    public int getLevel(){
        return level;
    }

    public void setLevel(int level){
        this.level = level;

        if(level < 5){
            this.antalForkerteMuligeBogstaver = 8;
        } else if(level < 10){
            this.antalForkerteMuligeBogstaver = 10;
        } else if(level < 12){
            this.antalForkerteMuligeBogstaver = 12;
        } else if(level < 14){
            this.antalForkerteMuligeBogstaver = 15;
        } else if(level < 16){
            this.antalForkerteMuligeBogstaver = 20;
        } else {
            this.antalForkerteMuligeBogstaver = this.muligeBogstaver.length();
        }
    }

    public boolean erSpilletSlut() {
        return spilletErTabt || spilletErVundet;
    }


    public Galgelogik() {
        muligeOrd.add("bil");
        muligeOrd.add("computer");
        muligeOrd.add("programmering");
        muligeOrd.add("motorvej");
        muligeOrd.add("busrute");
        muligeOrd.add("gangsti");
        muligeOrd.add("skovsnegl");
        muligeOrd.add("solsort");
        muligeOrd.add("seksten");
        muligeOrd.add("sytten");
        muligeOrd.add("atten");
        nulstil();
    }

    public void nulstil() {
        brugteBogstaver.clear();
        antalForkerteBogstaver = 0;
        spilletErVundet = false;
        spilletErTabt = false;
        ordet = muligeOrd.get(new Random().nextInt(muligeOrd.size()));

        ArrayList<Character> tilladteBogstaver =  new ArrayList<Character>();

        String bogstaver = muligeBogstaver;

        for (char bogstav : ordet.toLowerCase().toCharArray()) {
                bogstaver =  bogstaver.replace(Character.toString(bogstav), "");
            tilladteBogstaver.add(bogstav);
        }

        // Udvælg nogle andre mulige bogstaver - Her kan der tages højde for sværhedsgrad.

        int andreMuligheder = this.antalForkerteMuligeBogstaver;

        for (int i = 0; i < andreMuligheder; i++){
            if(bogstaver.length() == 0){
                break;
            }
            int  n = new Random().nextInt(bogstaver.length());
            char v = bogstaver.charAt(n);
            tilladteBogstaver.add(v);
            bogstaver = bogstaver.replace(Character.toString(v), "");
        }

        this.tilladteBogstaver = tilladteBogstaver.toArray(new Character[tilladteBogstaver.size()]);


        opdaterSynligtOrd();
    }


    private void opdaterSynligtOrd() {
        synligtOrd = "";
        spilletErVundet = true;
        for (int n = 0; n < ordet.length(); n++) {
            String bogstav = ordet.substring(n, n + 1);
            if (brugteBogstaver.contains(bogstav)) {
                synligtOrd = synligtOrd + bogstav;
            } else {
                synligtOrd = synligtOrd + "*";
                spilletErVundet = false;
            }
        }
    }

    public void gætBogstav(String bogstav) {
        if (bogstav.length() != 1) return;
        System.out.println("Der gættes på bogstavet: " + bogstav);
        if (brugteBogstaver.contains(bogstav)) return;
        if (spilletErVundet || spilletErTabt) return;

        brugteBogstaver.add(bogstav);

        if (ordet.contains(bogstav)) {
            sidsteBogstavVarKorrekt = true;
            System.out.println("Bogstavet var korrekt: " + bogstav);
        } else {
            // Vi gættede på et bogstav der ikke var i ordet.
            sidsteBogstavVarKorrekt = false;
            System.out.println("Bogstavet var IKKE korrekt: " + bogstav);
            antalForkerteBogstaver = antalForkerteBogstaver + 1;
            if (antalForkerteBogstaver > 6) {
                spilletErTabt = true;
            }
        }
        opdaterSynligtOrd();
    }

    public void logStatus() {
        System.out.println("---------- ");
        System.out.println("- ordet (skult) = " + ordet);
        System.out.println("- synligtOrd = " + synligtOrd);
        System.out.println("- forkerteBogstaver = " + antalForkerteBogstaver);
        System.out.println("- brugeBogstaver = " + brugteBogstaver);
        if (spilletErTabt) System.out.println("- SPILLET ER TABT");
        if (spilletErVundet) System.out.println("- SPILLET ER VUNDET");
        System.out.println("---------- ");
    }


    public static String hentUrl(String url) throws IOException {
        System.out.println("Henter data fra " + url);
        BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
        StringBuilder sb = new StringBuilder();
        String linje = br.readLine();
        while (linje != null) {
            sb.append(linje + "\n");
            linje = br.readLine();
        }
        return sb.toString();
    }


    public void hentOrdFraDr() throws Exception {
        String data = hentUrl("https://dr.dk");
        //System.out.println("data = " + data);

        data = data.substring(data.indexOf("<body")). // fjern headere
                replaceAll("<.+?>", " ").toLowerCase(). // fjern tags
                replaceAll("&#198;", "æ"). // erstat HTML-tegn
                replaceAll("&#230;", "æ"). // erstat HTML-tegn
                replaceAll("&#216;", "ø"). // erstat HTML-tegn
                replaceAll("&#248;", "ø"). // erstat HTML-tegn
                replaceAll("&oslash;", "ø"). // erstat HTML-tegn
                replaceAll("&#229;", "å"). // erstat HTML-tegn
                replaceAll("[^a-zæøå]", " "). // fjern tegn der ikke er bogstaver
                replaceAll(" [a-zæøå] "," "). // fjern 1-bogstavsord
                replaceAll(" [a-zæøå][a-zæøå] "," "); // fjern 2-bogstavsord

        System.out.println("data = " + data);
        System.out.println("data = " + Arrays.asList(data.split("\\s+")));
        muligeOrd.clear();
        muligeOrd.addAll(new HashSet<String>(Arrays.asList(data.split(" "))));

        System.out.println("muligeOrd = " + muligeOrd);
        nulstil();
    }
}