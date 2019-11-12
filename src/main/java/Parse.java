<<<<<<< HEAD
=======
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.util.Span;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

>>>>>>> 179e0eda13ed5a81ab840e34d34c9ddcbc7e6a4b
/**
 * A class that disassembles each document into terms.
 */
public class Parse {

<<<<<<< HEAD





=======
    public void parseTextFile(String document){
        int startIndex = document.indexOf("<TEXT>");
        int endIndex = document.indexOf("</TEXT>");
        String textOnly = document.substring(startIndex+6,endIndex);//gets the text written between the <TEXT> tags
        try {
            String [][] listOfPersons = tokenizingAccordingToSubject(textOnly, "/models/en-ner-person.bin");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private String[][] tokenizingAccordingToSubject(String text,String modelPath)
            throws Exception {

        SimpleTokenizer tokenizer = SimpleTokenizer.INSTANCE;
        String[] tokens = tokenizer.tokenize(text);

        InputStream inputStreamNameFinder = getClass().getResourceAsStream(modelPath);
        TokenNameFinderModel model = new TokenNameFinderModel(inputStreamNameFinder);
        NameFinderME nameFinderME = new NameFinderME(model);
        Span nameSpans[] = nameFinderME.find(tokens);
        String[][] result = new String[nameSpans.length][4];
        for(int i= 0;i < nameSpans.length;i++){
            String spanString = nameSpans[i].toString();
            //each cell of result array contains an array of Strings representing the term found.
            //the format of the array: [the term][start index][end index][type of word]

            result[i][1] = spanString.substring(1,spanString.indexOf('.'));//0
            int startIndex = Integer.parseInt(result[i][1]);

            //end index -> the last index in which the current span is located (included)
            String tempEndIndex = spanString.substring(spanString.lastIndexOf('.')+1,spanString.indexOf(')'));
            int endIndex = Integer.parseInt(tempEndIndex)-1;
            result[i][2] = String.valueOf(endIndex);//1

            result[i][3] = spanString.substring(spanString.indexOf(')')+2);//person

            //get the full term - even if it contains more than one word
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < endIndex-startIndex +1; j++) {
                if (j > 0)
                    sb.append(" ");
                sb.append(tokens[nameSpans[i+j].getStart()]);//for example - John Smith
            }
            result[i][0] = sb.toString();
        }
        return result;
    }
>>>>>>> 179e0eda13ed5a81ab840e34d34c9ddcbc7e6a4b

}
