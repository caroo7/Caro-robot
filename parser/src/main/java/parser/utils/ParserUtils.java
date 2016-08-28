package parser.utils;

import org.jsoup.nodes.Document;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Grzesiek on 2016-08-26.
 */
public class ParserUtils {

    public static String extractDataFromRegex(String regex,String data){
        Pattern patter = Pattern.compile(regex);
        Matcher matchPattern = patter.matcher(data);
        matchPattern.find();
        return matchPattern.group(1);
    }

}
