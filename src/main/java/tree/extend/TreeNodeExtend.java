package tree.extend;

import java.util.List;

public class TreeNodeExtend<T extends TreeNodeExtend> {

    private Long id;
    private String name;
    private Long pid;
    private List<T> children;

    public TreeNodeExtend() {
    }

    public TreeNodeExtend(long id, String name, long pid) {
        this.id = id;
        this.name = name;
        this.pid = pid;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public List<T> getChildren() {
        return children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "TreeNode [id=" + id + ", name=" + name + ", pid=" + pid + ", children=" + children + "]";
    }
}
