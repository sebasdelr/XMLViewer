package sample.xmlviewer.helpers;

import sample.xmlviewer.data.ViewerManager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class CheckHelper {

    private FileInputStream inLines = null;
    private FileOutputStream outLines = null;
    private Scanner scannerLines = null;
    private PrintWriter printWriter = null;

    final private String CONFIGINI = "config.ini";

    public CheckHelper(){
        loadData();
    }

    public void writeData(){

        try{
            outLines = new FileOutputStream(CONFIGINI);
            printWriter = new PrintWriter(outLines);
            String lineString = "";


            lineString = "xsd" + "=" + ViewerManager.getXsdFilePath();

            printWriter.println(lineString);
            System.out.println(lineString);

            printWriter.flush();
            outLines.close();
        }
        catch (IOException e){
            e.printStackTrace();

        }

    }

    public void loadData() {
        try{
            inLines = new FileInputStream(CONFIGINI);
            scannerLines = new Scanner(inLines);


            while (scannerLines.hasNextLine()) {



                String setting = "";
                String tempFlag;

                String[] itemPieces = scannerLines.nextLine().split("=");
                setting = itemPieces[0];
                tempFlag = itemPieces[1];


                switch (setting) {
                    case "xsd":
                        ViewerManager.setXsdFilePath(tempFlag);
                }

                if(setting == "xsd") {
                    System.out.println("wha happen");
                }


            }
            inLines.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

}
