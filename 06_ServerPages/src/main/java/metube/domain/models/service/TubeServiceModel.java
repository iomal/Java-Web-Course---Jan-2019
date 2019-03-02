package metube.domain.models.service;

public class TubeServiceModel {
    private String id;
    private String name;
    private String description;
    private String link;
    private String uploader;

    public TubeServiceModel() {
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

    public String getLink() {
        return this.link;
    }

    public String getUploader() {
        return this.uploader;
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

    public void setLink(String link) {
        this.link = link;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }
}
