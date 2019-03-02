package exam.domain.models.view;

import java.io.Serializable;

public class DocumentDetailViewModel implements Serializable {
    private String id;
    private String title;
    private String content;

    public DocumentDetailViewModel() {
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setId(String id) {
        this.id = id;
    }
}
