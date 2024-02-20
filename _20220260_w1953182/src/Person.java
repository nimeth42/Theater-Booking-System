public class Person {
    // Task 9 adding the attributes
    private String name;
    private String surname;
    private  String email;
    public Person(){}
    public Person(String name,String surname, String email){
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
    public  String getName(){return  name;}
    public  String getSurname(){return surname;}
    public String getEmail(){return email;}

}
