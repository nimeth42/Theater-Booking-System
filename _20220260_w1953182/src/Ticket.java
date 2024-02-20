public class Ticket {
    //Task 10 adding the attributes
    private int row;
    private int seat;
    private double price;
    private Person person;
    public Ticket(){

    }

    public int getRow(){return row;}
    public int getSeat(){return seat;}
    public double getPrice(){return price;}
    public Person getPerson(){return person;}
    public void setRow(int row){this.row = row;}
    public void setSeat(int seat){this.seat = seat;}
    public void setPrice(double price){this.price = price;}
    public  void setPerson(Person person){this.person = person;}
    public Ticket(int row,int seat,Person person){
        this.row = row;
        this.seat = seat;
        this.person = person;
        switch (row){
            case 1 -> price = 10;
            case 2 -> price = 20;
            case 3 -> price = 30;
        }
    }
    public void print(){
        System.out.println("\tName: " + person.getName() + "\n" +
                "\tSurname: " + person.getSurname() + "\n" +
                "\tEmail: " + person.getEmail() + "\n" +
                "\tRow: " + row + "\n" +
                "\tSeat: " + seat + "\n" +
                "\tPrice: " + price + "\n");

    }
}
