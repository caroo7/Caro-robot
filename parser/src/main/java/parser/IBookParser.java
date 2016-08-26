package parser;

import org.jsoup.nodes.Document;
import parser.DTO.Book;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by grzegorz_sledz on 25.08.16.
 */
public interface IBookParser  {
    Book parse(Document document) ;
}
