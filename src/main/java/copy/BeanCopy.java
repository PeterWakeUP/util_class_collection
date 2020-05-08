package copy;

import java.util.ArrayList;
import java.util.List;

public class BeanCopy {

    public static void main(String[] args){
        Bean bean = new Bean(1,"2");
        BeanDTO beanDTO = new BeanDTO();
        //copy(bean, beanDTO);
        CglibBeanCopierUtils.copyProperties(bean, beanDTO);
        System.out.println(beanDTO);
        System.out.println(bean);
        bean.setId(2);
        System.out.println("----------------------");
        System.out.println(beanDTO);
        System.out.println(bean);

        List<Bean> list = new ArrayList<>();
        for (int i=0; i<5; i++){
            list.add(new Bean(i, "bean"));
        }
        System.out.println(list);
        System.out.println("-------------------------");
        List<BeanDTO> list1 = CglibBeanCopierUtils.copyPropertiesOfList(list, BeanDTO.class);
        System.out.println(list1);
    }

    /*public static void copy(Object source, Object target){
        BeanCopier copier = BeanCopier.create(source.getClass(), target.getClass(), false);
        copier.copy(source, target, null);
    }*/
}
