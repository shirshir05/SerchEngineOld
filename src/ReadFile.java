import java.io.*;


/**Reading documents from the corpus.
 * Identifying the beginning of each file and separating the documents.
  */

public class ReadFile {
    String path;
    Parse parser;

    /**
     * Constractor that gets the path to the corpus.
     * @param PathArg
     */
    public ReadFile(String PathArg)
    {
        path = PathArg;
        parser = new Parse();
    }

    /**
     *
     */
    public void openFolder()
    {
        File directory = new File(path);
        File[] listOfDirectory = directory.listFiles();
        File files;// for file
        BufferedReader reader = null; // for read File

        for (int i = 0; i < listOfDirectory.length; i++) {
             if (listOfDirectory[i].isDirectory()) {//FB396001 for example
                 files= new File(listOfDirectory[i].getPath());//all text file
                 File[] listOfFile= files.listFiles();
                 for (int j = 0; j < listOfFile.length; j++) {
                     if(listOfFile[j].isFile())
                     {
                         try {
                             FileReader fileReader = new FileReader(listOfFile[j]);
                             reader = new BufferedReader(fileReader);
                             String text = null;
                             String dataDoc = null;
                             while ((text = reader.readLine()) != null) {
                                 if(!text.equals("<DOC>")) {
                                     if(dataDoc != null){
                                         dataDoc = dataDoc + text;
                                     }else{
                                         dataDoc = text;
                                     }
                                 }
                                 else{
                                     String toParser = dataDoc;
                                     System.out.println(toParser);
                                     dataDoc = null;
                                 }
                             }
                         } catch (FileNotFoundException e) {
                             e.printStackTrace();
                         } catch (IOException e) {
                             e.printStackTrace();
                         }finally {
                             try {
                                 if (reader != null) {
                                     reader.close();
                                 }
                             } catch (IOException e) {
                             }
                         }
                     }
                 }
            }
        }
    }










}
