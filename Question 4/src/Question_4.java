import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.*;

public class Question_4 {
    public static void main(String[] args) throws IOException {

        Path path = Path.of("record.csv");
        List<String>lines  = Files.readAllLines(path);
        int totalSalary=0;
        int averageSalary=0;
        int highestSalary=0;
        String highestSalaryEmployee="";
        List<String> increaseSalary=new ArrayList<>();
        increaseSalary.add("id,Name,Salary");
        for(int i=1;i<lines.size();i++){
            String[]line=lines.get(i).split(",");
            int salary=Integer.parseInt(line[2].trim());

            int val=(int)(salary+0.1*salary);
            increaseSalary.add(line[0] + "," + line[1].trim() + "," + val);

            totalSalary+=salary;

            if(highestSalary<salary){
                highestSalary=salary;
                highestSalaryEmployee=line[1];
            }
        }
        averageSalary=totalSalary/(lines.size()-1);
        System.out.println("Average Salary: "+averageSalary);
        System.out.println("Highest Salary Employee is "+highestSalaryEmployee+" that has salary as "+highestSalary);

        Path inputpath=Path.of("processed.csv");
        Files.write(inputpath,increaseSalary);

    }
}