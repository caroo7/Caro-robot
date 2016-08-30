package DTO;

/**
 * Created by grzegorz_sledz on 25.08.16.
 */
public class BookDetails {

    private String title;
    private String author;
    private String price;
    private String percentageDiscount;
    private String genre;
    private String description;


    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPercentageDiscount() {
        return percentageDiscount;
    }

    public void setPercentageDiscount(String percentageDiscount) {
        this.percentageDiscount = percentageDiscount;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookDetails that = (BookDetails) o;

        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (author != null ? !author.equals(that.author) : that.author != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (percentageDiscount != null ? !percentageDiscount.equals(that.percentageDiscount) : that.percentageDiscount != null)
            return false;
        if (genre != null ? !genre.equals(that.genre) : that.genre != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (percentageDiscount != null ? percentageDiscount.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BookDetails [title=" + title + ", author=" + author + ", price=" + price + ", percentageDiscount=" + percentageDiscount + ", genre=" + genre + ", description=" + description + "]";
    }
}
