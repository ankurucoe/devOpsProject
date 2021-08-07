public class singleTon extends Object{
    private static singleTon instance = null;
    private singleTon(){

    }

    /**
     *
     * @return class instance
     */
    public static singleTon getInstance(){
        if(instance == null){
            instance = new singleTon();
            return instance;
        }
        return instance;
    }

    @Override
    public String toString() {
        return getClass().getName();
    }
}
