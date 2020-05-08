package copy;

import lombok.Data;

@Data
public class BeanDTO {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BeanDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
