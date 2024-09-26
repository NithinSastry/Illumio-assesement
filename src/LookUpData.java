import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class LookUpData {
    
    public static Map<String, String> loadData(String filePath) {
        Map<String, String> lookupTable = new HashMap<>();
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            
            br.readLine(); // skipping one line for the header
            while ((line = br.readLine()) != null) {
                String fields[] = line.split(",");
                
                if (fields.length != 3) {
                    br.close();
                    throw new Exception("Incorrect format for look up table. 3 columns required");
                }

                String dstport = fields[0];
                String protocol = fields[1].toLowerCase();
                String tag = fields[2].toLowerCase();
                String key = dstport + " " + protocol;

                lookupTable.put(key, tag);
            }
            
            br.close();
        } catch (Exception e) {
            System.out.println("error reading lookup table file");
            e.printStackTrace();
        }

        return lookupTable;
    }
}