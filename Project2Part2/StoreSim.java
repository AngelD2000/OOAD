public class StoreSim {
    public static void main(String[] args) {
        int days = 10;
        Store store = new Store();
        for(int i = 0; i < days; i++){
            store.runDay(i + 1);
        }
        store.finalSummary();
    }

}