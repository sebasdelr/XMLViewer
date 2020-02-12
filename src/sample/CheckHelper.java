package sample;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class CheckHelper {
    private boolean officeCoord;
    private boolean subCoord;
    private FileInputStream inLines = null;
    private FileOutputStream outLines = null;
    private Scanner scannerLines = null;
    private PrintWriter printWriter = null;
    private int[] settings = {0,0};
    final private String CONFIGINI = "config.ini";

    public CheckHelper(){
        loadData();


    }

    public void writeData(){

        try{
            outLines = new FileOutputStream(CONFIGINI);
            printWriter = new PrintWriter(outLines);

            //maybe create a settings class with variable and value

            String lineString = this.settings[0] + "," + this.settings[1];

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

                String[] itemPieces = scannerLines.nextLine().split(",");
                settings[0] = parseInt(itemPieces[0]);
                settings[1] = parseInt(itemPieces[1]);


            }
            inLines.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }



    }


    public void setOfficeCoord(int flag) {
        this.settings[0] = flag;
    }

    public void setSubCoord(int flag) {
        this.settings[1] = flag;
    }

    public int getOfficeCoord() {
        return this.settings[0];
    }

    public int getSubCoord() {
        return this.settings[1];
    }

}
