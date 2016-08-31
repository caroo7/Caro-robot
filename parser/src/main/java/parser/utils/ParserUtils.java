package parser.utils;

import org.jsoup.nodes.Document;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserUtils {

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
