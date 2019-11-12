import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import com.mmsindia.OpenNLPTester;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.stemmer.PorterStemmer;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.Span;

public class Main {

    public static void main(String[] args) throws Exception {

        System.out.println("Hello World!");
        //*************************Test og ReadFile************************************************************************//
        //ReadFile();
        //********************************opennlp models test******************************************/
        //opennlpModels(");
        //********************************stemmer test******************************************/
        //stemmer();

    }
    //*************************Test og ReadFile************************************************************************//
    public static void ReadFile(){
        ReadFile readFileTest = new ReadFile("C:\\testShir");
        //ReadFile readFileTest = new ReadFile("C:\\corpus");
        readFileTest.openFolder();
    }
    //********************************opennlp test******************************************/
    public static void opennpl(){
        try {
        com.mmsindia.OpenNLPTester tester = new OpenNLPTester();
        String example = "6% 10.6 percent 10.6 percentage.";
        tester.testParse(tester, example, 1, true);
        
        } catch (IOException e) {
        e.printStackTrace();
        }
    }

    //********************************stemmer test******************************************/

    public static void stemmer(){
        PorterStemmer stemmer = new PorterStemmer();
        String stem = stemmer.stem("loves");
        System.out.println(stem);
    }


    //********************************givenEnglishModel_whenTokenize_thenTokensAreDetected test******************************************/
    public static void givenEnglishModel_whenTokenize_thenTokensAreDetected()
            throws Exception {

        InputStream inputStream = Main.class
                .getResourceAsStream("en-token.bin");
        TokenizerModel model = new TokenizerModel(inputStream);
        TokenizerME tokenizer = new TokenizerME(model);
        String[] tokens = tokenizer.tokenize("Baeldung is a Spring Resource.");
        System.out.println("1: " + tokens[0] + " " +tokens[1]+" " +tokens[2] +" " +tokens[3] +" " +tokens[4] +" " +tokens[5]);
    }
    public static void givenWhitespaceTokenizer_whenTokenize_thenTokensAreDetected()
            throws Exception {

        WhitespaceTokenizer tokenizer = WhitespaceTokenizer.INSTANCE;
        String[] tokens = tokenizer.tokenize("Baeldung is a Spring Resource.");
        System.out.println("2: "  + tokens[0] +" " +tokens[1]+" " +tokens[2] +" " +tokens[3] +" " +tokens[4] );


    }

    public static void givenEnglishPersonModel_whenNER_thenPersonsAreDetected()
            throws Exception {

        SimpleTokenizer tokenizer = SimpleTokenizer.INSTANCE;
        String[] tokens = tokenizer.tokenize("John is 26 years old."
                        + "name is Leonard. He has a sister named Penny." + "6% 10.6 percent 10.6 percentage.");

        InputStream inputStreamNameFinder = Main.class.getResourceAsStream("/models/en-ner-person.bin");
        TokenNameFinderModel model = new TokenNameFinderModel(inputStreamNameFinder);
        NameFinderME nameFinderME = new NameFinderME(model);
        Span nameSpans[] = nameFinderME.find(tokens);
        for(Span s: nameSpans)
            System.out.println(s.toString()+"  "+tokens[s.getStart()]);
        //List<Span> spans = Arrays.asList(nameFinderME.find(tokens));
        //System.out.println(spans);
    }

}
