public class StoreSim {
    public static void main(String[] args) {
        int days = 10;
        Store store = new Store();
        for(int i = 0; i < days; i++){
            System.out.println("DAY " + String.valueOf(i+1));
            store.runDay();
        }
        store.finalSummary();
    }

}