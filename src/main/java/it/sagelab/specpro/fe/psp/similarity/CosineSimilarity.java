package it.sagelab.specpro.fe.psp.similarity;

import java.util.*;

public class CosineSimilarity {

    private ArrayList<Text> documents;
    private HashMap<String, Integer> df;

    public CosineSimilarity() {
        documents = new ArrayList<>();
    }

    public void addDocument(Text t) {
        documents.add(t);
    }

    public List<Text> computeRanking(Text t) {
        ArrayList<Text> rankedDocuments = new ArrayList<>();

        for(Text doc: documents) {
            doc.setScore(cosineSimilarity(t, doc));
            rankedDocuments.add(doc);
        }

        rankedDocuments.sort((t1, t2) -> -Double.compare(t1.getScore(), t2.getScore()));

        return rankedDocuments;
    }

    public double cosineSimilarity(Text a, Text b) {
        double score = 0;
        for(String t: a.getTf().keySet())
            score += tfidf(t, a) * tfidf(t, b);
        score /= computeNorm(a);
        score /= computeNorm(b);

        return score;
    }

    private double computeNorm(Text text) {
        double norm = 0;
        for(String t: text.getTf().keySet())
            norm += Math.pow(tfidf(t, text), 2);

        return Math.sqrt(norm);
    }

    private double tfidf(String t, Text text) {
        if(df == null)
            computeDf();
        return text.getTf().getOrDefault(t, 0) * Math.log((double) documents.size() / (df.getOrDefault(t, 0) + 1));
    }

    private void computeDf() {
        df = new HashMap<>();
        for(Text pattern : documents) {
            for(String k: pattern.getTf().keySet()) {
                df.put(k, df.getOrDefault(k, 0) + 1);
            }
        }
    }

}
