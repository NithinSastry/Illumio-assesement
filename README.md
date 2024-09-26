# How to run

Download "Illumio-assesement.jar"

## In your terminal run the .jar file as following:

```java -jar Illumio-assesement.jar <absolute path to protocol numbers.csv file> <absolute path to lookup table.csv file> <absolute path to flow logs.txt file>```

## assumptions made
1. The protocol numbers .csv should be as per https://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml 
2. The lookup table .csv file
3. First line in both file should be headers
4. The flow logs file is .txt file without headers
5. The port in Port/protocol combination is assumed as source port address

## code review
1. The count for tags and source port address + protocol combination are Long variables. As the count can cross integer max value for large files
2. All the file resources that are accessed for reads and writes are properly closed
3. Exceptions are caught if the files are not read or written properly
4. If the flow logs are not properly passed as per the examples shared, exceptions are raised
