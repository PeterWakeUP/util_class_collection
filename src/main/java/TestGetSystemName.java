public class TestGetSystemName {

    private static String getCurrentOperationSystem() {
        String os = System.getProperty("os.name").toLowerCase();
        return os;
    }

    public static void main(String[] args){
        System.out.println(getCurrentOperationSystem());
    }
}
