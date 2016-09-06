package converter;

public class BookToDTOConverter {


//    public void method(){
//        ModelMapper modelMapper=new ModelMapper();
//
//        BookDTO bookDTO = modelMapper.map(order, BookDTO.class);
//    }
//
//
//
//
//
//    private String concatenateBy(String s1,String s2,String separator){
//        return s1+", "+s2;
//    }
//
//    private String retrieveAuthors(Book book) {
//        return book.getAuthors().stream().map(author -> author.getName()).reduce((s, s2) -> concatenateBy(s,s2,",")).get();
//    }
//
//    private String retrieveTags(Book book) {
//        Set<Tag> tags=book.getTags();
//        System.out.println("tags: "+tags);
//        if(tags==null||tags.size()==0) {
//            //FIXME: why would I log that like it's noteworthy event? :-)
//           return "";
//        }
//        return book.getTags().stream().map(tag -> tag.getName()).reduce((s, s2) -> concatenateBy(s, s2, ",")).get();
//    }
//
//    private String retrieveGenres(Book book) {
//        return book.getGenres().stream().map(genre -> genre.getName()).reduce((s, s2) -> concatenateBy(s,s2,",")).get());
//    }
//
//    private BookDTO convertToDTO(Book book) {
////        return BookDTO.builder()
////                .title(book.getTitle())
////                .authors(retrieveAuthors(book))
////                .coverUrl(book.getCoverUrl())
////                .url(book.getUrl())
////                .description(book.getDescription())
////                .discount(book.getDiscount())
////                .price(book.getPrice())
////                .library(book.getLibrary())
////                .tags(retrieveTags(book))
////                .genres(retrieveGenres(book))
////                .timestamp(book.getTimestamp())
////                .build();
//
//            ModelMapper modelMapper=new ModelMapper();
//
//            BookDTO bookDTO = modelMapper.map(order, BookDTO.class);
//
//
//    }
//
//    public List<BookDTO> convertAll(List<Book> books) {
//        return books.stream().map(book -> convertToDTO(book)).collect(Collectors.toList());
//    }

}
