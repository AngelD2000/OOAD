abstract class Employee {
    public String name;
    public String type;
    void setName(String newName){
        name = newName;
    }
    void setType(String newType){
        type = newType;
    }
    /**
     * Gets the name and type as a string of this employee
     */
    public String identifier(){
        return name + " the " + type;
    }
    /**
     * Has a cashier report that they are doing the action
     */
    public void report(String action){
        System.out.println(identifier() + " " + action);
    }
    public void report(String action, int day){
        System.out.println(identifier() + " " + action);
    }
}