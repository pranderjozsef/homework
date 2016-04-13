package hu.codingmentor.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "findBookByPublisher",
        query = "SELECT b FROM Book b WHERE b.publisher = :p")
public class Book extends Item{
    
    private String publisher;
    
    @Column(name = "NUMBER_OF_PAGES")
    private Integer nbOfPage;

    public Book() {
        //Empty
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getNbOfPage() {
        return nbOfPage;
    }

    public void setNbOfPage(Integer nbOfPage) {
        this.nbOfPage = nbOfPage;
    }

    @Override
    public String toString() {
        return super.toString() + ", publisher=" + publisher + ", nbOfPage=" + nbOfPage + '}';
    }
}
