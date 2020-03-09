package sample.xmlviewer.helpers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class CheckHelper {
    private boolean officeCoord;
    private boolean subCoord;
    private FileInputStream inLines = null;
    private FileOutputStream outLines = null;
    private Scanner scannerLines = null;
    private PrintWriter printWriter = null;
    private int[] settingFlags = {0,0};
    final private String CONFIGINI = "config.ini";
    private List<String> settings = null;

    public CheckHelper(){
        loadData();


    }

    public void writeData(){

        try{
            outLines = new FileOutputStream(CONFIGINI);
            printWriter = new PrintWriter(outLines);
            String lineString = "";

            //maybe create a settings class with variable and value

            lineString += "officecoord" + "=" + this.settingFlags[0] + "\n";
            lineString += "subcoord" + "=" + this.settingFlags[1];

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
                int tempFlag;

                String[] itemPieces = scannerLines.nextLine().split("=");
                tempFlag = parseInt(itemPieces[1]);


                switch (setting) {
                    case "officecoord":
                        settingFlags[0] = tempFlag;
                        break;
                    case "subcoord":
                        settingFlags[1] = tempFlag;
                        break;
                }



            }
            inLines.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }



    }


    public void setOfficeCoord(int flag) {
        this.settingFlags[0] = flag;
    }

    public void setSubCoord(int flag) {
        this.settingFlags[1] = flag;
    }

    public int getOfficeCoord() {
        return this.settingFlags[0];
    }

    public int getSubCoord() {
        return this.settingFlags[1];
    }

}
