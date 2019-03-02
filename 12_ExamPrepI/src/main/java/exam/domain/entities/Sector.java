package exam.domain.entities;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;

public enum Sector implements Serializable {
    medicine, car, food, domestic, security;
}
