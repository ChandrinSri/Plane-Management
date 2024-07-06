
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Plane_Main{
    //(below varibales used here becaused usable in every method)
    public static int seatNum;
    public static final String ANSI_RESET = "\u001B[0m";// for resetting a ansi values
    public static int option;// variable that use for switch through cases
    public static String seatRow;//variable which saves users Row
    public static double Total_price;//variable which saves total price of tickets bought
    public static int count;//counts the times of tickets bought(doesnt let buy more than 54)
    public static String[] A=new String[14];
    public static String[] B=new String[12];
    public static String[] C=new String[12];
    public static String[] D=new String[14];
    public static String[][] Plane={A,B,C,D};
    public static String[][] Tickets={};//where all bought ticket information are saved
    public static Scanner Buyer=new Scanner(System.in);
    static Plane_Main Method=new Plane_Main();
    static Person info=new Person();
    static Ticket getinfo=new Ticket();

    public static void main(String args[]){
        String ANSI_BOLD = "\u001B[1m";
        System.out.println(" \n               "+ANSI_BOLD+"WELCOME TO Air-Ticket TERMINAL");

        boolean Controller=true;
        Arrays.fill(A,"O ");Arrays.fill(B,"O ");Arrays.fill(C,"O ");Arrays.fill(D,"O ");
        while(Controller){
            //below prints out the menu
            //******************************************************************************************* */
            String MenuStar="* ";
            String StarLine=MenuStar.repeat(30);
            String Space=" ";
            String MidLine=Space.repeat(21);
            String OptionSpace=Space.repeat(11);
            System.out.print(StarLine);
            System.out.println("\n");
            System.out.printf("* %s MENU OPTIONS %s*",MidLine,MidLine);
            System.out.println("\n");
            System.out.print(StarLine+"\n\n");
            System.out.printf("%s 1.Buy a Seat\n\n",OptionSpace);
            System.out.printf("%s 2.Cancel a Seat\n\n",OptionSpace);
            System.out.printf("%s 3.Find First Available Seat\n\n",OptionSpace);
            System.out.printf("%s 4.Show Seating plan\n\n",OptionSpace);
            System.out.printf("%s 5.Print ticket information and total Sales\n\n",OptionSpace);
            System.out.printf("%s 6.Search Ticket\n\n",OptionSpace);
            System.out.printf("%s 0.Quit\n\n",OptionSpace);
            System.out.print(StarLine+"    ");
            System.out.print("Option Number: ");
            //******************************************************************************************* */
            switch (options()) {
                case 0://for program terminaation
                    System.out.print("Thank You For Selecting Our Service!!!-");
                    Controller=false;
                    break;
                case 1://buy seat happens here
                    Method.buySeat();

                    System.out.println("");
                    Controller=true;
                    break;

                case 2://cancel seat happens here
                    cancelSeat();
                    Controller=true;
                    break;

                case 3:// checking the first seat available happens here
                    find_first_available(A);
                    if (find_first_available(A)!=-1) {
                        thread();
                        System.out.println("  The Seat 'A-"+(find_first_available(A)+1)+"' is Avalable");
                        //return;
                    }else{
                        find_first_available(B);
                        if (find_first_available(B)!=-1) {
                            thread();
                            System.out.println("  The Seat 'B-"+(find_first_available(B)+1)+"' is Avalable");
                            //return;
                        }else {
                            find_first_available(C);
                            if (find_first_available(C)!=-1) {
                                thread();
                                System.out.println("  The Seat 'C-"+(find_first_available(C)+1)+"' is Avalable");
                                //return;
                            }else {
                                find_first_available(D);
                                if (find_first_available(D)!=-1) {
                                    thread();
                                    System.out.println("  The Seat 'D-"+(find_first_available(D)+1)+"' is Avalable");
                                    //return;
                                }

                            }

                        }

                    }


                    Controller=true;
                    break;

                case 4:// showing the planes current booking pattern happens here
                    Method.show_seating_plan();
                    Controller=true;
                    break;

                case 5:// printing all bought ticket info though session happens here
                    print_tickets_info();
                    System.out.printf("\nTotal Ticket price: %.2f",Total_price);
                    System.out.println();
                    Controller=true;
                    break;

                case 6:// searching for seat availabitlity happens here
                    getinfo.setRow(seatRow);
                    getinfo.setSeat(seatNum);
                    search_ticket();
                    break;

                default:// Users wrong input dumps here
                    System.out.println("User Numbers Between 1-6 Only!!!0");
                    break;
            }


        }
    }

    public  void buySeat(){

        String ANSI_BOLD = "\u001B[1m";
        String ANSI_RED="\u001B[31m"; // color pallete
        String ANSI_GREEN = "\u001B[32m";
        System.out.println(ANSI_BOLD+"\n\n                              --Ticket Purchase--"+ANSI_RESET);

        if (count<=54) {
            seatRow();  //gets users inut for Seat-Row
            if (seatRow.equals("0")) {
                return;
            }
            seatNum(seatRow);//gets Users input for Seat-Number
            if (seatNum==0) {
                return;
            }

            System.out.println();
            if (seatRow.toUpperCase().equals("A")) {
                for (int i = 0; i < A.length; i++) {
                    if (seatNum==i+1) {
                        if (A[i]=="O ") {
                            thread();//deylayed output method in line 591(to give a feel for user)
                            System.out.print(ANSI_GREEN+" Seat Available! "+ANSI_RESET+"Proceed Enter to Continue Purchasing:");
                            String book=Buyer.nextLine();//if user wants to terminate the process
                            if (book.equals("0")) {
                                return;
                            }else{
                                person();//taking information of user(Name/surname/Email) in line 500
                                Total_price+=getinfo.ticketPrice(seatNum);
                                thread2();//deylayed output method in line 602(to give a feel for user)
                                Tickets=addTicketinfo(Tickets);//Filles the Ticket array with relevent information
                                getinfo.save();//saves to a text file about the seat booking info
                                A[i]=ANSI_RED+"1 "+ANSI_RESET;
                                count=count+1;




                            }

                            System.out.println(ANSI_GREEN+"Seat Booked"+ANSI_RESET);

                        }else{
                            thread();
                            System.out.println(ANSI_RED+"Seat Booked-ALREADY"+ANSI_RESET);

                        }

                    }

                }

            }else if (seatRow.toUpperCase().equals("B")) {
                for (int i = 0; i < B.length; i++) {
                    if (seatNum==i+1) {
                        if (B[i]=="O ") {
                            thread();
                            System.out.print(ANSI_GREEN+" Seat Available! "+ANSI_RESET+"Proceed Enter to Continue Purchasing:");
                            String book=Buyer.nextLine();
                            if (book.equals("0")) {
                                return;
                            }else{
                                person();
                                thread2();
                                Tickets=addTicketinfo(Tickets);
                                Total_price+=getinfo.ticketPrice(seatNum);
                                B[i]=ANSI_RED+"1 "+ANSI_RESET;
                                getinfo.save();
                                count=count+1;
                            }
                            System.out.println(ANSI_GREEN+"Seat Booked"+ANSI_RESET);
                        }else{
                            thread();
                            System.out.println(ANSI_RED+"Seat Booked-ALREADY"+ANSI_RESET);

                        }
                    }
                }
            }else if (seatRow.toUpperCase().equals("C")) {
                for (int i = 0; i < C.length; i++) {
                    if (seatNum==i+1) {
                        if (C[i]=="O ") {
                            thread();
                            System.out.print(ANSI_GREEN+" Seat Available! "+ANSI_RESET+"Proceed Enter to Continue Purchasing:");
                            String book=Buyer.nextLine();
                            if (book.equals("0")) {
                                return;
                            }else{
                                person();
                                thread2();
                                Tickets=addTicketinfo(Tickets);
                                Total_price+=getinfo.ticketPrice(seatNum);
                                getinfo.save();
                                C[i]=ANSI_RED+"1 "+ANSI_RESET;
                                count=count+1;
                            }
                        }else{
                            thread();
                            System.out.println(ANSI_RED+"Seat Booked-ALREADY"+ANSI_RESET);

                        }

                    }
                }
            }else if (seatRow.toUpperCase().equals("D")) {
                for (int i = 0; i < D.length; i++) {
                    if (seatNum==i+1) {
                        if (D[i]=="O ") {
                            thread();
                            System.out.print(ANSI_GREEN+" Seat Available! "+ANSI_RESET+"Proceed Enter to Continue Purchasing:");
                            String book=Buyer.nextLine();
                            if (book.equals("0")) {
                                return;
                            }else{
                                person();
                                thread2();
                                Tickets=addTicketinfo(Tickets);
                                Total_price+=getinfo.ticketPrice(seatNum);
                                getinfo.save();
                                D[i]=ANSI_RED+"1 "+ANSI_RESET;
                                count=count+1;
                            }
                            System.out.println(ANSI_GREEN+"Seat Booked"+ANSI_RESET);
                        }else{
                            thread();
                            System.out.println(ANSI_RED+"Seat Booked-ALREADY"+ANSI_RESET);

                        }

                    }
                }

            }else{
                System.out.println("All Seats Booked!!!");
            }

        }
    }
    public static void cancelSeat(){
        String ANSI_BOLD = "\u001B[1m";
        String ANSI_RED="\u001B[31m";        //color pallete
        String ANSI_GREEN = "\u001B[32m";
        System.out.println(ANSI_BOLD+"\n\n                              --Cancel Ticket Purchase--"+ANSI_RESET);
        seatRow();          //gets users inut for Seat-Row
        if (seatRow.equals("0")) {
            return;
        }
        seatNum(seatRow);           //gets Users input for Seat-Number
        if (seatNum==0) {
            return;
        }
        if (seatRow.toUpperCase().equals("A")) {
            for (int i = 0; i < A.length; i++) {
                if (seatNum==i+1) {
                    //checks if the seat already booked
                    if (A[i].equals(ANSI_RED+"1 "+ANSI_RESET  )|| A[i].equals(ANSI_RED+"X "+ANSI_RESET)) {
                        A[i]="O ";
                        count=count-1;
                        Tickets=removeTicketinfo(Tickets);// removes the relevent ticket info from the Tickets array
                        Total_price-=getinfo.ticketPrice(seatNum);
                        deleteTicket();//deletes created text file for relevant ticket
                        thread();
                        System.out.println(ANSI_GREEN+"Seat Successfully Cancelled"+ANSI_RESET);

                    }else{
                        thread();
                        System.out.println(ANSI_RED+"ALERT!!!-->Seat Cancelation not succesfull"+ANSI_RESET);
                    }


                }

            }

        }else if (seatRow.toUpperCase().equals("B")) {
            for (int i = 0; i < B.length; i++) {
                if (seatNum==i+1) {
                    if (B[i].equals(ANSI_RED+"1 "+ANSI_RESET  )|| B[i].equals(ANSI_RED+"X "+ANSI_RESET)) {
                        B[i]="O ";
                        count=count-1;
                        Tickets=removeTicketinfo(Tickets);
                        Total_price-=getinfo.ticketPrice(seatNum);
                        thread();
                        System.out.println(ANSI_GREEN+"Seat Successfully Cancelled"+ANSI_RESET);
                        deleteTicket();

                    }else{
                        thread();
                        System.out.println(ANSI_RED+"ALERT!!!-->Seat Cancelation not succesfull"+ANSI_RESET);
                    }
                }
            }

        }else if (seatRow.toUpperCase().equals("C")) {
            for (int i = 0; i < C.length; i++) {
                if (seatNum==i+1) {
                    if (C[i].equals(ANSI_RED+"1 "+ANSI_RESET  )|| C[i].equals(ANSI_RED+"X "+ANSI_RESET)) {
                        C[i]="O ";
                        count=count-1;
                        Tickets=removeTicketinfo(Tickets);
                        Total_price-=getinfo.ticketPrice(seatNum);
                        deleteTicket();
                        thread();
                        System.out.println(ANSI_GREEN+"Seat Successfully Cancelled"+ANSI_RESET);

                    }else{
                        thread();
                        System.out.println(ANSI_RED+"ALERT!!!-->Seat Cancelation not succesfull"+ANSI_RESET);
                    }
                }

            }

        }else if (seatRow.toUpperCase().equals("D")) {
            for (int i = 0; i < D.length; i++) {
                if (seatNum==i+1) {
                    if (D[i].equals(ANSI_RED+"1 "+ANSI_RESET  )|| D[i].equals(ANSI_RED+"X "+ANSI_RESET)) {
                        D[i]="O ";
                        count=count-1;
                        Tickets=removeTicketinfo(Tickets);
                        Total_price-=getinfo.ticketPrice(seatNum);
                        deleteTicket();
                        thread();
                        System.out.println(ANSI_GREEN+"Seat Successfully Cancelled"+ANSI_RESET);

                    }else{
                        thread();
                        System.out.println(ANSI_RED+"ALERT!!!-->Seat Cancelation not succesfull"+ANSI_RESET);
                    }
                }

            }
        }

    }
    public static int find_first_available(String array[]){
        int i=0;
        while (i<array.length) {
            //checks for the first 0 element from the array find first avail seat
            if (array[i].equals("O ")) {
                return i;

            }else{
                i=i+1;
            }

        }return -1;
    }
    public void show_seating_plan(){

        String BLUE_BRIGHT = "\033[0;94m";//color pallete
        String ANSI_RED="\u001B[31m";
        System.out.print("   ");
        for(int i=1;i<10;i++){
            System.out.print(i+"  ");
        }
        for(int i=10;i<15;i++){
            System.out.print(i+" ");
        }                              //codes to left are for the beauti of presenting the seat plan
        System.out.println();
        for (int i = 0; i < Plane.length; i++) {
            if (i==0) {
                System.out.print("A: ");
            }else if(i==1){
                System.out.print("B: ");
            }else if(i==2){
                System.out.print("C: ");
            }else if(i==3){
                System.out.print("D: ");
            }
            for (int j = 0; j < Plane[i].length; j++) {
                //checks for seat booked element (booked->1) and makes them as "X "
                if (Plane[i][j].equals(ANSI_RED+"1 "+ANSI_RESET)) {
                    Plane[i][j]=ANSI_RED+"X "+ANSI_RESET;

                }
                System.out.print(BLUE_BRIGHT+Plane[i][j]+ANSI_RESET+" ");
            }
            System.out.println();
        }
    }
    public static void print_tickets_info(){
        System.out.println(     "Sold Ticket-Info");
        //Iterates the Current saved tickets and outputs bought tickets and thier prices
        for (int i=0;i<Tickets.length ;i++) {
            System.out.printf("•"+Tickets[i][0]+"="+Tickets[i][3]);
            System.out.println();
        }

    }
    public static void search_ticket(){
        String ANSI_BOLD = "\u001B[1m";
        System.out.println(ANSI_BOLD+"\n\n                              --Search Ticket--"+ANSI_RESET);
        seatRow();//gets User input for Which Row should check
        if (seatRow.equals("0")) {
            return;
        }
        seatNum(seatRow);//gets User input for Which Seat Number should check
        if (seatNum==0) {
            return;
        }
        String Ticket_=seatRow.toUpperCase()+seatNum;
        boolean found=false;//used this speacial flag to get if the seat is available
        for(int i=0;i<Tickets.length;i++){
            //Itrates through the Tickets-array and check if the user requested Ticket Booked or not
            if (Tickets[i][0].equals(Ticket_)) {
                thread();
                getinfo.printTicketInfo();
                found=true;

            }
        }
        if (found) {
            //
        }else{
            thread();
            System.out.println("Requested Ticket is Available for Boooking");
        }

    }

    public static String seatRow(){
        boolean flag=true;
        String ANSI_RED="\u001B[31m";
        while (flag) {
            System.out.println("\n                                                                                       Enter 0 to Terminate");
            System.out.print("Enter Seat Row: ");
            char Row=Buyer.nextLine().charAt(0);
            seatRow=String.valueOf(Row);     //turns the character to a String
            //below if condtion checks if the user Enters a charater between [a/b/c/d]
            if (seatRow.toUpperCase().equals("A") || seatRow.toUpperCase().equals("B") ||
                    seatRow.toUpperCase().equals("C")|| seatRow.toUpperCase().equals("D")) {
                getinfo.setRow(seatRow.toUpperCase());
                flag=false;

            }else if(seatRow.equals("0")){//uses for if user want to terminate the process
                return seatRow;

            }else{
                System.out.println(ANSI_RED+"              Select Your Choice [A]/[B]/[C]/[D]\n"+ANSI_RESET);//asks again for a valid input
            }
        }
        return seatRow;

    }
    public static int seatNum(String row){
        boolean flag=true;
        String ANSI_RED="\u001B[31m"; //color pallate
        String ANSI_CYAN = "\u001B[36m";
        String Space=" ";
        String lineSpace=Space.repeat(12);

        while (flag) {
            try {
                System.out.println();
                System.out.print("Please Enter the Seat number: ");
                seatNum=Buyer.nextInt();
                Buyer.nextLine();
                //below if condition checks if the user enter a number relevent to the Row's length
                if (row.toUpperCase().equals("A")&&seatNum<=14 ||row.toUpperCase().equals("B")&&seatNum<=12 ||
                        row.toUpperCase().equals("C")&&seatNum<=12 ||row.toUpperCase().equals("D")&&seatNum<=14 ) {
                    getinfo.setSeat(seatNum);
                    flag=false;

                }else{
                    System.out.println(ANSI_RED+"                       FOLLOW BELOW SEAT NUMBERINGS!!!"+ANSI_RESET);
                    System.out.print(lineSpace+ANSI_CYAN+"A: 1-14 Seats"+ANSI_RESET+" ");
                    System.out.print(lineSpace+ANSI_CYAN+"B: 1-12 Seats"+ANSI_RESET+" ");//if user gives wrong number range this outputs
                    System.out.print(lineSpace+ANSI_CYAN+"C: 1-12 Seats"+ANSI_RESET+" ");
                    System.out.print(lineSpace+ANSI_CYAN+"D: 1-14 Seats"+ANSI_RESET+" ");
                }


            } catch (InputMismatchException e) {
                //if user enters a character instead of number this exception works
                System.out.println(ANSI_RED+"     Numbers Only!!!"+ANSI_RESET);
                Buyer.nextLine();

            }


        }
        return seatNum;
    }
    public static void person(){
        //gets the additional iformation from user
        System.out.print("\nFirst Name: ");
        String buyer_Name=Buyer.nextLine();
        info.setname(buyer_Name);//sets value name to a private attribiute in Person class
        System.out.print("\nLast Name: ");
        String buyer_Surname=Buyer.nextLine();
        info.setsurname(buyer_Surname);//sets value surname to a private attribiute in Person class
        System.out.print("\nEmail: ");
        String buyer_Email=Buyer.nextLine();
        info.setemail(buyer_Email);//sets value Email  to a private attribiute in Person class
        //Person info=new Person(buyer_Name,buyer_Surname,buyer_Email);
        System.out.println();
    }

    public static String[][] addTicketinfo(String [][] array){

        String[][] midArray=new String[array.length+1][4];//creates a new array for that lager by 1 than original Tickets array
        for(int o=0;o<array.length;o++){
            midArray[o]=array[o];//Assigns each every value of Tickets Array to the above created arrray
        }
        //Assigens the last element of the array which is null
        midArray[midArray.length-1][0]=seatRow.toUpperCase()+seatNum;
        midArray[midArray.length-1][1]=info.getname()+" "+info.getsurname();
        midArray[midArray.length-1][2]=info.getemail();
        midArray[midArray.length-1][3]=Double.toString(getinfo.ticketPrice(seatNum));
        return midArray;
    }
    public static String[][] removeTicketinfo(String[][] array){

        String[][] midArray=new String[array.length-1][4];//creates a new array for that smaller by 1 than original Tickets array
        for (int i = 0, k=0; i < array.length; i++) {
            //checks if the ticket number is equal to users rquested ticket information
            if (!(seatRow.toUpperCase()+seatNum).equals(array[i][0])) {
                midArray[k]=array[i];//here we assigen every element to the new array except whats equal to user Input ticket
                k++;
            }
        }
        return midArray;
    }

    public static void deleteTicket(){
        //this method deletes wen user cancels a tickets
        String filepath="/Users/chandrin_09/Downloads/20230302-W2051622/Sold Tickets/Ticket-"+seatRow+seatNum+".txt";
        File fileToDelete = new File(filepath);//creates a new file object
        if (fileToDelete.exists()){
            fileToDelete.delete();

        }
        else{
        }
    }
     public static int options(){
        //all below code is to validate the right info for the switch cases
        boolean flag=true;
        while (flag) {
            try {
                option=Buyer.nextInt();
                if (0<=option&&option<7) {
                    Buyer.nextLine();

                    flag=false;
                }else{
                    return -1;
                }
            } catch (InputMismatchException e) {
                String ANSI_RED="\u001B[31m";
                Buyer.nextLine();
                System.out.println(ANSI_RED+"     Numbers Only!!!"+ANSI_RESET);
                return -1;
            }
        }
        return option;
    }


    private static void thread(){
        System.out.print("Checking SeatInfo");
        for(int k=0;k<4;k++){
            System.out.print(".");
            try {
                Thread.sleep(700);//in here created a delayed output to give a user experience
            } catch (InterruptedException e) {

                e.printStackTrace();
            }}
    }
    private static void thread2(){
        System.out.print("Redirecting");
        for(int k=0;k<2;k++){
            System.out.print(".");
            try {
                Thread.sleep(250);//in here created a delayed output to give a user experience. delays 1/4 of a second
            } catch (InterruptedException e) {

                e.printStackTrace();
            }}
        System.out.print("Updating System");
        for(int k=0;k<4;k++){
            System.out.print(".");
            try {
                Thread.sleep(500);//in here created a delayed output to give a user experience.delays 1/2 of a seccond
            } catch (InterruptedException e) {

                e.printStackTrace();
            }}
    }

}
