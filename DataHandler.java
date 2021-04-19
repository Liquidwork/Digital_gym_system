/**
 *This is a static class
 * that provide static functions for all data transaction between local file and JVM
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class DataHandler {
    /**
     * @Descrption This is a function that can read from local file by its path.
     * @param path The file path you want to access
     * @return ArrayList The text in file will be separated into list by line
     */
    public static ArrayList<String> read(String path){
        String line ;
        ArrayList<String> data =new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((line = br.readLine()) != null) {
                data.add(line);
            }
        }
        catch (IOException e) {
            return data;
        }
     
        return data;          
    }

    /**
     * @Descrption This is a function that can over-write the data in local file
     * @param list the data you want store in file, of course a csv style.
     * @param path  the path of the local file
     * @return boolean to tell you if the local file changed successfully
     */
    public static boolean write(ArrayList<String> list, String path){
        String line;
        boolean result = false;
        try{
            File csv = new File(path);
            csv.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(csv,false));
            Iterator<String> iterator =list.iterator();
            while (iterator.hasNext()){
                line = (String)iterator.next();
                bw.write(line);
                bw.newLine();
            }
            bw.close();
            result =true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * @descrption This is a function that allow you to append the data in local file
     * You do not need re-write the old data, which can improve the File-IO speed
     * But you can only add one line string into file.
     * @param data the data you want to append in local file
     * @param path  the path of local file
     * @return boolean to tell you if the local file changed successfully
     */
    public static boolean append(String data, String path){
        boolean result = false;
        try{
            File csv = new File(path);
            csv.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(csv,true));
            bw.write(data);
            bw.newLine();
            bw.close();
            result =true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
