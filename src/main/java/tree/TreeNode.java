package tree;

import java.util.List;

public class TreeNode {

    private Long id;
    private String name;
    private Long pid;
    private List<TreeNode> children;

    public TreeNode() {
    }

    public TreeNode(long id, String name, long pid) {
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

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "TreeNode [id=" + id + ", name=" + name + ", pid=" + pid + ", children=" + children + "]";
    }
}
