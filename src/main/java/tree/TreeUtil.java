package tree;


import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * 将｛id:1, name:"shw", pid:0｝的Map数据转化成tree层级嵌套数据
 */
public class TreeUtil {

    public static List<TreeNode> getTree(Map<Long, TreeNode> map){
        //根据id pid 转成树形结构
        //转换算法核心:由叶子节到根节点进行处理，也即由下到上处理
        List<TreeNode> nodes = new ArrayList<>();
        for (Map.Entry<Long, TreeNode> en : map.entrySet()) {
            TreeNode node = en.getValue();
            TreeNode pTreeNode = map.get(node.getPid());
            //含有父节点就是子节点
            if (Objects.nonNull(pTreeNode)) {
                List<TreeNode> sons = pTreeNode.getChildren();
                if (Objects.isNull(sons)) {
                    sons = new ArrayList<>();
                    pTreeNode.setChildren(sons);
                }

                int i;
                for(i=0; i<sons.size(); i++){
                    //按顺序插入
                    if(sons.get(i).getId() > node.getId()){
                        break;
                    }
                }
                sons.add(i, node);

            } else {//不是子节点就是父节点
                nodes.add(node);
            }
        }
        return nodes;
    }


    public static void main(String[] args) {

        // 存储: id => node
        // id 主键 唯一
        Map<Long, TreeNode> map = new HashMap<>();
        TreeNode node14 = new TreeNode(14,"龙华小卖部", 9);
        map.put(node14.getId(), node14);
        TreeNode node13 = new TreeNode(13,"hengli", 12);
        map.put(node13.getId(), node13);
        TreeNode node12 = new TreeNode(12,"hengli", 1);
        map.put(node12.getId(), node12);
        TreeNode node1 = new TreeNode(1,"东莞", 0);
        map.put(node1.getId(), node1);
        TreeNode node2 = new TreeNode(2,"东坑", 1);
        map.put(node2.getId(), node2);
        TreeNode node3 = new TreeNode(3,"黄屋村", 2);
        map.put(node3.getId(), node3);
        TreeNode node4 = new TreeNode(4,"茶山", 1);
        map.put(node4.getId(), node4);
        TreeNode node5 = new TreeNode(5,"东城", 1);
        map.put(node5.getId(), node5);
        TreeNode node6 = new TreeNode(6,"南城", 1);
        map.put(node6.getId(), node6);
        TreeNode node7 = new TreeNode(7,"深圳", 0);
        map.put(node7.getId(), node7);
        TreeNode node8 = new TreeNode(8,"宝安", 7);
        map.put(node8.getId(), node8);
        TreeNode node9 = new TreeNode(9,"龙华", 7);
        map.put(node9.getId(), node9);
        TreeNode node10 = new TreeNode(10,"福田", 7);
        map.put(node10.getId(), node10);
        TreeNode node11 = new TreeNode(11,"莞城", 1);
        map.put(node11.getId(), node11);

        System.out.println(JSON.toJSONString(TreeUtil.getTree(map)));

    }
}
