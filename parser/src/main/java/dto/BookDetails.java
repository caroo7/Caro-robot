package dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;
@Builder
public class BookDetails {

    @Getter
    private String title;
    @Getter
    private Set<String> authors;
    @Getter
    private String price;
    @Getter
    private String percentageDiscount;
    @Getter
    private String genre;
    @Getter
    private String description;
    @Getter
    private Set<String> tags;
    @Getter
    private String url;
    @Getter
    private String coverUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        BookDetails that = (BookDetails) o;

        if (title != null ? !title.equals(that.title) : that.title != null)
            return false;
        if (authors != null ? !authors.equals(that.authors) : that.authors != null)
            return false;
        if (price != null ? !price.equals(that.price) : that.price != null)
            return false;
        if (percentageDiscount != null ? !percentageDiscount.equals(that.percentageDiscount) : that.percentageDiscount != null)
            return false;
        if (genre != null ? !genre.equals(that.genre) : that.genre != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null)
            return false;
        if (tags != null ? !tags.equals(that.tags) : that.tags != null)
            return false;
        if (url != null ? !url.equals(that.url) : that.url != null)
            return false;
        return coverUrl != null ? coverUrl.equals(that.coverUrl) : that.coverUrl == null;

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (authors != null ? authors.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (percentageDiscount != null ? percentageDiscount.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (coverUrl != null ? coverUrl.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BookDetails [title=" + title + ", authors=" + authors + ", price=" + price + ", percentageDiscount=" + percentageDiscount + ", genre=" + genre + " tags " + tags + ", description=" + description + ", url=" + url + ", coverUrl= " + coverUrl + "]";
    }
}
