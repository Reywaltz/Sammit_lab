package Application;

import java.io.*;
import java.util.ArrayList;

public class ObjectStream
{

    public static void send_to_file(ArrayList<Leader> leaders)
    {
        try
        {
            FileOutputStream fos = new FileOutputStream(new File("F:\\Java projects\\test\\data.dat"));
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            // Запись объектов в файл
            oos.writeObject(leaders.get(leaders.size()-1));
            oos.close();
            fos.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public static void read_from_file(ArrayList<Leader> leaders)
    {
        try{
            ArrayList<Leader> leaders1 = new ArrayList<Leader>();
            FileInputStream fis = new FileInputStream(new File("F:\\Java projects\\test\\data.dat"));
            ObjectInputStream in = new ObjectInputStream(fis);
            Leader lead = (Leader) in.readObject();
            System.out.println(lead.getName());
            fis.close();
            in.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
