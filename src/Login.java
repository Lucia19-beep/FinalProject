import java.io.*;
import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;

public class Login {
    static Scanner sc = new Scanner(System.in);
    static Random rnd = new Random();


    public static String HashingPassword(String password) {
        StringBuilder newPassword = new StringBuilder();
        char[] passwordParts =  password.toCharArray();

        for(char c : passwordParts) {
            c += (char)(c+rnd.nextInt(1,20)); // El random provocaria un error ya que nunca pordria ser la misma contraseña si se comparan con la introducida convertida
            newPassword.append(c);
        }

        return newPassword.toString();
    }
    public static boolean checkPassword(String hashedPassword) {
        boolean correct = false;
        for (int i=0;i!=3 && !correct;i++)
        {
            System.out.print("Enter Your Password: ");
            String hashedPassword2 = HashingPassword(sc.next());
            correct = hashedPassword2.equals(hashedPassword);
        }
        return correct;
    }
    public static int GetNumberID()
    {
        int counterID = 0;
        File file = new File("users.txt");
        if (file.exists())
        {
            try(BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {
                String line;
                while((line = br.readLine()) != null)
                {
                    counterID++;
                }
            }
            catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return counterID+1;
    }

    public static void SignUser(String line)
    {
        try(PrintWriter out = new PrintWriter(new FileWriter("users.txt",true))) {
            out.println(line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } ;
    }
    public static String[] SignData()
    {
        String[] data = new String[3];
        System.out.print("Enter Your Name: ");
        data[0] = sc.nextLine();
        System.out.print("Enter Your Address: ");
        data[1] = sc.nextLine();
        System.out.print("Enter Password: ");
        data[2] = HashingPassword(sc.nextLine());

        return data;
    }
    public static User SignUpCustumer()
    {
        Customer cs = null;

        String[] data = SignData();
        String name = data[0];
        String address = data[1];
        String password = data[2];

        int id = GetNumberID();
        int riskLevel = 0;
        LocalDate linkingDate = LocalDate.now();
        double balance = 0.0;
        String line = "Customer," +name+','+id+','+address+','+riskLevel+','+linkingDate+','+balance+','+password;
        SignUser(line);
        cs = new Customer(name, id, address, riskLevel, linkingDate, balance);
        return cs;
    }
    public static User SignUpAdmin()
    {
        Administrator admin = null;
        String[] data = SignData();
        String name = data[0];
        String address = data[1];
        String password = data[2];
        int id = GetNumberID();
        System.out.print("Acces Level: ");
        int accessLevel = 0;
        if(Integer.parseInt(sc.nextLine())<0)
        {
            accessLevel = Integer.parseInt(sc.nextLine());;
        }
        String line = "Admin,"+name+','+id+','+address+','+accessLevel+','+password;
        SignUser(line);
        admin = new Administrator(name, id, address, accessLevel);
        return admin;
    }
    public static User SignUpEmployee()
    {
        Employee employee = null;
        String[] data = SignData();
        String name = data[0];
        String address = data[1];
        String password = data[2];
        int id = GetNumberID();
        System.out.print("Position: ");
        String position = sc.nextLine();
        System.out.print("Salary: ");
        String salary = sc.nextLine();
        Double salaryDouble = Double.parseDouble(salary);
        System.out.print("Permits: Yes/No ");
        String permission = sc.nextLine();
        boolean accessPermit = false;
        if (permission.equalsIgnoreCase("Yes"))
        {
            accessPermit = true;
        }
        String line = "Employee,"+name+','+id+','+address+','+position+','+salaryDouble+','+accessPermit+','+password;
        SignUser(line);
        employee = new Employee(name,id,address,position,salaryDouble,accessPermit);
        return employee;
    }

    public static User Login()
    {
        User cs = null;
        try(BufferedReader bf = new BufferedReader(new FileReader("users.txt")))
        {
            System.out.print("Enter Your Name: ");
            String name = sc.nextLine();
            boolean found = false;
            String[] user = null;

            String line;
            while((line = bf.readLine()) != null && !found)
            {
                String[] data = line.split(",");
                if(data[1].equalsIgnoreCase(name.trim()))
                {
                    found = true;
                    user = data;
                }
            }
            if (found)
            {
                switch(user[0].trim())
                {
                    case "Customer":
                        cs = LoginCustomer(user,sc);
                    break;
                    case "Admin":
                        cs = LoginAdmin(user,sc);
                    break;
                    case "Employee":
                        cs = LoginEmployee(user,sc);
                    break;
                }
            }
            else
            {
                System.out.println("Invalid Username");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return cs;
    }

    public static User LoginAdmin(String[] user,Scanner sc)
    {
        User cs = null;
        boolean correct = checkPassword(user[5]);
        if (correct)
        {
            cs = new Administrator(user[1],Integer.parseInt(user[2]),user[3],Integer.parseInt(user[4]));
        }
        return cs;
    }
    public static User LoginCustomer(String[] user,Scanner sc)
    {
        User cs = null;
        boolean correct = checkPassword(user[7]);
        if (correct)
        {
            cs = new Customer(user[1],Integer.parseInt(user[2]),user[3],Integer.parseInt(user[4]),LocalDate.parse(user[5]),Double.parseDouble(user[6]));
        }
        return cs;
    }
    public static User LoginEmployee(String[] user,Scanner sc)
    {
        User cs = null;
        boolean correct = checkPassword(user[7]);
        if (correct)
        {
            cs = new Employee(user[1],Integer.parseInt(user[2]),user[3],user[4],Double.parseDouble(user[5]),Boolean.parseBoolean(user[6]));
        }
        return cs;
    }
}
