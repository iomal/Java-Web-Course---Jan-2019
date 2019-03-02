package exam.domain.models.binding;

public class ScheduleCreateBindingModel {
    private String title;
    private String content;

    public ScheduleCreateBindingModel() {
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

}
