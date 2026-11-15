
//https://github.com/JadanEbb/Lab-7.git

import java.io.*;

public class Main {

    public static void main(String[] args) {

        // Creates a File object using the location of students.txt
        File studentFile = new File("UPDATE WITH FILE LOCATION");

        // Displays the full location of the file
        System.out.println("File location: "
                + studentFile.getAbsolutePath());

        // Checks whether the file exists before trying to open it
        if (!studentFile.exists()) {
            System.out.println("ERROR: students.txt not found!");
            return;
        }

        // Opens the file in read and write mode
        // "rw" allows the program to read and update the file
        try (RandomAccessFile file =
                     new RandomAccessFile(studentFile, "rw")) {

            // Displays the size of the file in bytes
            System.out.println("File size: "
                    + file.length() + " bytes");

            String line;
            boolean found = false;

            // Continues reading records until the end of the file
            while (true) {

                // Saves the current position in the file
                long position = file.getFilePointer();

                // Reads the next line from the file
                line = file.readLine();

                // Stops reading when there are no more lines
                if (line == null) {
                    break;
                }

                // Displays the student record currently being read
                System.out.println("Reading: " + line);

                // Splits the line into separate pieces using commas
                String[] data = line.split(",");

                // Skips records that do not contain at least three fields
                if (data.length < 3) {
                    continue;
                }

                // Gets the student ID from the first field
                String studentID = data[0].trim();

                // Checks whether the student ID matches 1002
                if (studentID.equals("1002")) {

                    // Creates the updated student record
                    String updated =
                            "1002,Alice Brown,3.9";

                    // Makes sure the replacement has the same length
                    // as the original record to avoid overwriting
                    // the next record in the file
                    if (updated.length() != line.length()) {
                        System.out.println(
                                "ERROR: Record length mismatch!");
                        break;
                    }

                    // Moves the file pointer back to the beginning
                    // of the student record we want to update
                    file.seek(position);

                    // Writes the updated information into the file
                    file.writeBytes(updated);

                    System.out.println("Record updated!");

                    // Records that the matching student was found
                    found = true;
                    break;
                }
            }

            // Displays a message if student ID 1002 was not updated
            if (!found) {
                System.out.println(
                        "Student ID 1002 was not updated.");
            }

            // Handles errors that may occur while reading or writing the file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


//The program uses Java file handling to read and update student information stored in a file called students.txt. It begins by locating the file and checking whether it exists. If the file cannot be found, the program displays an error message and stops.
//If the file exists, the program opens it using RandomAccessFile in read and write mode. It reads the student records one line at a time and separates each record into individual fields using the split() method. The program checks the student ID to find the record belonging to student 1002.
//Once the matching student is found, the program replaces the original information with the updated record "1002,Alice Brown,3.9." Before writing the new information, it checks that the updated record has the same length as the original record. It then uses seek() to return to the original position and writeBytes() to replace the information.
//Finally, the program displays a message indicating whether the student record was successfully updated. It also uses exception handling to catch file-related errors.


//expected output:
//File location: [your file location]
//File size: [file size] bytes
//Reading: 1001,John Smith,3.5
//Reading: 1002,Alice Brown,3.7
//Record updated!