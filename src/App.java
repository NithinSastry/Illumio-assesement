import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        /* load protocol mapping as per https://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml */
        Map<String, String> protocolMap = ProtocolData.loadData();

        /* load look up table data */
        Map<String, String> lookupMap = LookUpData.loadData();

        /* count of matches for each tag */
        Map<String, Long> tagCount = new HashMap<>(); 

        /* read and start analyzing the flow logs */
        final String flowLogFilePath = "/home/ns/Illumio-assesement/flow_logs.txt";
        try {
            BufferedReader br = new BufferedReader(new FileReader(flowLogFilePath));
            String line;

            while ((line = br.readLine()) != null) {
                String fields[] = line.split(" ");

                if (fields.length < 14) {
                    br.close();
                    throw new Exception("Invalid format for flow log file. atleast 14 fields expected as per example.");
                }

                String dstport = fields[6];
                String protocolNumber = fields[7];
                String protocol = protocolMap.get(protocolNumber);
                String lookupKey = dstport + " " + protocol;
                String tag = lookupMap.get(lookupKey);
                
                tagCount.put(tag, tagCount.getOrDefault(tag, 0L) + 1);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("error while reading / processing flow log file.");
            e.printStackTrace();
        }
    }
}
