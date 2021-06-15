package tree.extend;


import com.alibaba.fastjson.JSON;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 将｛id:1, name:"shw", pid:0｝的Map数据转化成tree层级嵌套数据
 */
public class TreeUtilExtend {

    /**
     * 根据id pid 转成树形结构
     * 转换算法核心: 利用对象引用，遍历时将树拼接好，然后将根节点加入到nodes中
     * @param map
     * @param <T>
     * @return
     */
    public static <T extends TreeNodeExtend> List<T> getTree(Map<Long, T> map){

        List<T> nodes = new ArrayList<>();
        for (Map.Entry<Long, T> en : map.entrySet()) {
            T node = en.getValue();
            T pTreeNodeExtend = map.get(node.getPid());

            if (Objects.nonNull(pTreeNodeExtend)) { //含有父节点就是子节点
                List<T> sons = pTreeNodeExtend.getChildren();
                if (Objects.isNull(sons)) {
                    sons = new ArrayList<>();
                    pTreeNodeExtend.setChildren(sons);
                }

                int i;
                for(i=0; i<sons.size(); i++){
                    //按顺序插入
                    if(sons.get(i).getSortValue() > node.getSortValue()){
                        break;
                    }
                }
                sons.add(i, node);

            } else { //不是子节点就是父节点
                int i;
                for(i=0; i<nodes.size(); i++){
                    //按顺序插入
                    if(nodes.get(i).getSortValue() > node.getSortValue()){
                        break;
                    }
                }
                nodes.add(i, node);
            }
        }
        return nodes;
    }

    /**
     * 根据id pid 转成树形结构
     * 转换算法核心: 利用对象引用，遍历时将树拼接好，然后将根节点加入到nodes中
     * @param list
     * @param <T>
     * @return
     */
    public static <T extends TreeNodeExtend> List<T> getTree(List<T> list){
        List<T> nodes = new ArrayList<>();

        Map<Long, T> map = list.stream().collect(Collectors.toMap(x->x.getId(), x->x));
        for (Map.Entry<Long, T> en : map.entrySet()) {
            T node = en.getValue();
            T pTreeNodeExtend = map.get(node.getPid());

            if (Objects.nonNull(pTreeNodeExtend)) { //含有父节点就是子节点
                List<T> sons = pTreeNodeExtend.getChildren();
                if (Objects.isNull(sons)) {
                    sons = new ArrayList<>();
                    pTreeNodeExtend.setChildren(sons);
                }

                int i;
                for(i=0; i<sons.size(); i++){
                    //按顺序插入
                    if(sons.get(i).getSortValue() > node.getSortValue()){
                        break;
                    }
                }
                sons.add(i, node);

            } else { //不是子节点就是父节点
                int i;
                for(i=0; i<nodes.size(); i++){
                    //按顺序插入
                    if(nodes.get(i).getSortValue() > node.getSortValue()){
                        break;
                    }
                }
                nodes.add(i, node);
            }
        }
        return nodes;
    }

    public static void main(String[] args) {

        // 存储: id => node
        // id 主键 唯一
        Map<Long, TreeNodeExtend> map = new HashMap<>();
        TreeNodeExtend node14 = new TreeNodeExtend(14,"龙华小卖部", 9);
        map.put(node14.getId(), node14);
        TreeNodeExtend node13 = new TreeNodeExtend(13,"hengli", 12);
        map.put(node13.getId(), node13);
        TreeNodeExtend node12 = new TreeNodeExtend(12,"hengli", 1);
        map.put(node12.getId(), node12);
        TreeNodeExtend node2 = new TreeNodeExtend(2,"东坑", 1);
        map.put(node2.getId(), node2);
        TreeNodeExtend node4 = new TreeNodeExtend(4,"茶山", 1);
        map.put(node4.getId(), node4);
        TreeNodeExtend node9 = new TreeNodeExtend(9,"龙华", 7);
        map.put(node9.getId(), node9);
        TreeNodeExtend node5 = new TreeNodeExtend(5,"东城", 1);
        map.put(node5.getId(), node5);
        TreeNodeExtend node8 = new TreeNodeExtend(8,"宝安", 7);
        map.put(node8.getId(), node8);
        TreeNodeExtend node6 = new TreeNodeExtend(6,"南城", 1);
        map.put(node6.getId(), node6);
        TreeNodeExtend node7 = new TreeNodeExtend(7,"深圳", 0);
        map.put(node7.getId(), node7);
        TreeNodeExtend node10 = new TreeNodeExtend(10,"福田", 7);
        map.put(node10.getId(), node10);
        TreeNodeExtend node11 = new TreeNodeExtend(11,"莞城", 1);
        map.put(node11.getId(), node11);
        TreeNodeExtend node3 = new TreeNodeExtend(3,"黄屋村", 2);
        map.put(node3.getId(), node3);
        TreeNodeExtend node1 = new TreeNodeExtend(1,"东莞", 0);
        map.put(node1.getId(), node1);

        System.out.println(JSON.toJSONString(TreeUtilExtend.getTree(map)));

    }
}
