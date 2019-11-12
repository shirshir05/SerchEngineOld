import opennlp.tools.stemmer.PorterStemmer;

public class Stemmer {

    public static String stemmer(String word){
        PorterStemmer stemmer = new PorterStemmer();
        String stem = stemmer.stem(word);
        return stem;
    }

}
