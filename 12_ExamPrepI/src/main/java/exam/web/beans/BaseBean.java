package exam.web.beans;

import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;

public abstract class BaseBean implements Serializable {
    protected void redirect(String url) {
        try {
            FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .redirect("/views" + url + ".jsf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
