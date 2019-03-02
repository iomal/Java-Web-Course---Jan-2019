package chushka.domain.models;

import chushka.domain.entities.Type;

import javax.persistence.Column;

public class ProductServiceModel {
    private String id;
    private String name;
    private String description;
    private String type;

    public ProductServiceModel() {
    }

    public ProductServiceModel(String name, String description, String type) {
        this.name = name;
        this.description = description;
        this.type=type;
    }

    public ProductServiceModel(String name, String description) {
        this.name = name;
        this.description = description;
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

    public String getType() {
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

    public void setType(String type) {
        this.type = type;
    }
}
