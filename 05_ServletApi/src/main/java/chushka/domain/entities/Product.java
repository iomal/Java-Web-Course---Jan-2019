package chushka.domain.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    /*
       Name – a String.
       Description – a String.
       Type – can be one of the following values – (“Food”, “Domestic”, “Health”, “Cosmetic”, “Other”)
     */

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private String id;
    @Column
    private String name;
    @Column
    private String description;
    @Enumerated(EnumType.STRING)
    private Type type;

    public Product() {
    }

    public Product(String name, String description, Type type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Type getType() {
        return this.type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
