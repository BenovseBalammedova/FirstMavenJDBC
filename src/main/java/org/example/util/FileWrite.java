package org.example.util;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class FileWrite {
    public static void fileWriteFromService(String fileName, String info) {

        try {

            FileWriter filewiter = new FileWriter(fileName, true);

            BufferedWriter bufferedWriter = new BufferedWriter(filewiter);

            bufferedWriter.write(info);

            bufferedWriter.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
