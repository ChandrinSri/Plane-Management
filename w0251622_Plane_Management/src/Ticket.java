import java.io.FileWriter;
import java.io.IOException;

public class Ticket {
    private String row;
    private int seat;
    private double price;
    private Person info;

    public void setRow(String row){
        this.row=row;
    }
    public void setSeat(int seat){
        this.seat=seat;
    }
    public void setPrice(double price){
        this.price=price;
    }

    public String getRow(){
        return row;
    }
    public int getseat(){
        return seat;
    }
    public double getprice(){
        return price;
    }



    public void printTicketInfo(){
        System.out.println("\n \n                      Requested Ticket Info\n");
        System.out.printf("           •Ticket No:%s%d \n",getRow().toUpperCase(),getseat());
        Plane_Main.info.print_person();
        System.out.printf("           •Total Price= %f\n\n",ticketPrice(seat));
    }

    public   double ticketPrice(int SeatNumber){
        if (0<=SeatNumber && SeatNumber<=5) {
            double ticket_price=200.00;
            return ticket_price;
        }else if (6<=SeatNumber && SeatNumber<=9) {
            double ticket_price=150.00;
            return ticket_price;
        }else if (10<=SeatNumber && SeatNumber<=14) {
            double ticket_price=180.00;
            return ticket_price;
        }
        return -1;
    }
    public  void save(){
        try {
            String directory_path = "/Users/chandrin_09/Downloads/20230302-W2051622/Sold Tickets/Ticket-"; //creates the relevent directory to save the text file
            String fileName = row.toUpperCase()+seat+".txt";
            FileWriter file = new FileWriter(directory_path + fileName);
            file.write("             Ticket-Info"+"\n\n");
            file.write("        • Ticket-"+row+seat+"\n");
            file.write("        • Name: "+Plane_Main.info.getname()+" "+Plane_Main.info.getsurname()+"\n"); // writes the relevant information for the text file
            file.write("        • Email: "+Plane_Main.info.getemail()+"\n");
            file.write("        • Price: "+ticketPrice(seat));
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
