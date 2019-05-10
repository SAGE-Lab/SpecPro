package it.sagelab.specpro.fe.psp.similarity;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.util.StringUtils;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Text {

    private String text;
    private List<String> tokens;
    private Map<String, Integer> tf;
    private double score = 0;

    public Text(String text) {
        this.text = text;
    }

    public List<String> getTokens() {
        if(tokens == null)
            tokens = tokenize(text);
        return tokens;
    }

    public Map<String, Integer> getTf() {
        if(tf == null)
            tf = computeTf(getTokens());
        return tf;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public static Map<String, Integer> computeTf(List<String> tokens) {
        HashMap<String,Integer> df = new HashMap<>();
        for(String t: tokens) {
            if(StringUtils.isAlpha(t))
                df.put(t.toLowerCase(), df.getOrDefault(t, 0) + 1);
        }
        return df;
    }

    public static List<String> tokenize(String text) {
        ArrayList<String> tokens = new ArrayList<>();

        PTBTokenizer<CoreLabel> ptbt = new PTBTokenizer<>(new StringReader(text), new CoreLabelTokenFactory(), "");
        while (ptbt.hasNext()) {
            CoreLabel label = ptbt.next();
            tokens.add(label.toString());
        }

        return tokens;
    }

    @Override
    public String toString() {
        return text;
    }

}
