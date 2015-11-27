package de.uni_mannheim.informatik.wdi.identityresolution.similarity.wrapper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class CountryCodeWrapper {

    private static CountryCodeWrapper instance;
    private HashMap<String, String> alpha2ToAlpha3 = new HashMap<String, String>();
    private HashMap<String, String> alpha3ToAlpha2 = new HashMap<String, String>();
    
    private final String path = "util/isocodes.csv";
    
    
    private CountryCodeWrapper () {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            
            String line = reader.readLine();
            while(line!= null){
                String[] codes = line.split(",");
                alpha2ToAlpha3.put(codes[0], codes[1]);
                alpha3ToAlpha2.put(codes[1], codes[0]);
                
                line = reader.readLine();
            }
            
            reader.close();
            
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
    }
    
    public String getThreeLetterCode(String twoLetterCode){
        if(twoLetterCode!= null && twoLetterCode.length() == 2){
            String result = this.alpha2ToAlpha3.get(twoLetterCode);
            if(result != null){
                return result;
            }else{
                return "";
            }
        }else{
            return "";
        }
    }
    
    public String getTwoLetterCode(String threeLetterCode){
        if(threeLetterCode!= null && threeLetterCode.length() == 3){
            String result = this.alpha3ToAlpha2.get(threeLetterCode);
            if(result != null){
                return result;
            }else{
                return "";
            }
        }else{
            return "";
        }
    }
    
    
    public static CountryCodeWrapper getInstance () {
      if (CountryCodeWrapper.instance == null) {
          CountryCodeWrapper.instance = new CountryCodeWrapper ();
      }
      return CountryCodeWrapper.instance;
    }
    
}
