public class Person {
    private  String name;
    private  String surname;
    private  String email;

    public Person(String name,String surname,String email){
        this.name=name;
        this.surname=surname;
        this.email=email;
    }
    public Person(){

    }

    public String getname(){ //this is getter for name
        return name;
    }
    public void setname(String user_name){
        name=user_name;
    }

    public String getsurname(){ //this is getter for surname
        return surname;
    }
    public void setsurname(String user_surname){
        surname=user_surname;
    }

    public String getemail(){ //this is getter for email
        return email;
    }
    public void setemail(String user_email){
        email=user_email;
    }
    public void print_person(){
        System.out.println("           •Name: "+getname()+" "+getsurname());
        System.out.println("           •Email: "+getemail());
    }
}


