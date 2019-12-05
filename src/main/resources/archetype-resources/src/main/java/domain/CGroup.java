package ${package}.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class CGroup {
    @Id
    @GeneratedValue

    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CGroup)) return false;
        CGroup CGroup = (CGroup) o;
        return Objects.equals(id, CGroup.id) &&
                Objects.equals(name, CGroup.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
