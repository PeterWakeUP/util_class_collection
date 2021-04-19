package xml;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XMLUtil {

    public static void main(String[] args){
        Person person = new Person("1", "老四");
        String xmlStr = obj2Xml("person", person, Person.class);
        System.out.println(xmlStr);
    }

    public static <T> String obj2Xml(String alias, T obj, Class<T> clazz){
        // 先创建一下XStream对象
        XStream xStream = new XStream(new DomDriver());
        // 设置Person类的别名
        xStream.alias(alias, clazz);
        // 将Java对象序列化成XML
        String xmlStr = xStream.toXML(obj);

        return xmlStr;
    }

    static class Person{
        private String id;
        private String name;

        public Person(String id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
