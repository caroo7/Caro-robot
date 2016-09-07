package parser.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserUtils {

    public static String moveLastWordOnBeginning(String text){
        if(text==null || !text.contains(" ")){
            return text;
        }

        String result;

        String lastWord = text.substring(text.lastIndexOf(" "));
        text=text.substring(0,text.length()-lastWord.length());

        result=lastWord.substring(1,lastWord.length())+" "+text;

        return result;
    }

    public static String extractDataFromRegex(String regex,String data){
        Pattern patter = Pattern.compile(regex);
        Matcher matchPattern = patter.matcher(data);
        matchPattern.find();
        return matchPattern.group(1);
    }

    public static String calculatePercentageDiscount(float orginalPrice,float salePrice){
        float discount = (1 - salePrice / orginalPrice) * 100;
        return Math.round(discount) + "%";
    }


}
