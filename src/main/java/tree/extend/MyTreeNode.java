package tree.extend;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

public class MyTreeNode extends TreeNodeExtend{

    private String detail;

    public MyTreeNode(long id, String name, long pid) {
        super(id, name, pid);
        this.detail = "没钱";
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }


    public static void main(String[] args){
        // 存储: id => node
        // id 主键 唯一
        Map<Long, MyTreeNode> map = new HashMap<>();
        MyTreeNode node14 = new MyTreeNode(14,"龙华小卖部", 9);
        map.put(node14.getId(), node14);
        MyTreeNode node13 = new MyTreeNode(13,"hengli", 12);
        map.put(node13.getId(), node13);
        MyTreeNode node12 = new MyTreeNode(12,"hengli", 1);
        map.put(node12.getId(), node12);
        MyTreeNode node2 = new MyTreeNode(2,"东坑", 1);
        map.put(node2.getId(), node2);
        MyTreeNode node4 = new MyTreeNode(4,"茶山", 1);
        map.put(node4.getId(), node4);
        MyTreeNode node9 = new MyTreeNode(9,"龙华", 7);
        map.put(node9.getId(), node9);
        MyTreeNode node5 = new MyTreeNode(5,"东城", 1);
        map.put(node5.getId(), node5);
        MyTreeNode node8 = new MyTreeNode(8,"宝安", 7);
        map.put(node8.getId(), node8);
        MyTreeNode node6 = new MyTreeNode(6,"南城", 1);
        map.put(node6.getId(), node6);
        MyTreeNode node7 = new MyTreeNode(7,"深圳", 0);
        map.put(node7.getId(), node7);
        MyTreeNode node10 = new MyTreeNode(10,"福田", 7);
        map.put(node10.getId(), node10);
        MyTreeNode node11 = new MyTreeNode(11,"莞城", 1);
        map.put(node11.getId(), node11);
        MyTreeNode node3 = new MyTreeNode(3,"黄屋村", 2);
        map.put(node3.getId(), node3);
        MyTreeNode node1 = new MyTreeNode(1,"东莞", 0);
        map.put(node1.getId(), node1);

        System.out.println(JSON.toJSONString(TreeUtilExtend.getTree(map)));
    }
}
