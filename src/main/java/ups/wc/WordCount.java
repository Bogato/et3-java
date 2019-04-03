package ups.wc;

import java.util.*;

public class WordCount {
    private Map<String, Long> freqs;
    private final boolean caseSensitive;

    public WordCount(boolean caseSensitive) {
        freqs = new HashMap<String, Long>();
        this.caseSensitive = caseSensitive;
    }

    private String sanitize(String text) {
        if (caseSensitive) {
            return text;
        }
        return text.toLowerCase();
    }

    public long words(String text) {
        String [] words = (sanitize(text)).split("\\W+");
        for (String w : words) {
            long v = freqs.getOrDefault(w, 0L);
            freqs.put(w, v + 1);
        }
        return words.length;
    }

    public long getFrequenceMot(String w) {
        return freqs.getOrDefault(w, 0L);
    }

    public ArrayList<Map.Entry<String, Long>> sort() {
        ArrayList<Map.Entry<String, Long>> sorted =
                new ArrayList<>(freqs.entrySet());

        sorted.sort(new Comparator<Map.Entry<String, Long>>() {
            public int compare(Map.Entry<String, Long> e1, Map.Entry<String, Long> e2) {
               return e2.getValue().compareTo(e1.getValue());
            }
        });

        return sorted;
    }

    public ArrayList<Map.Entry<String, Long>> lambdaSort() {
        ArrayList<Map.Entry<String, Long>> sorted =
                new ArrayList<>(freqs.entrySet());

        sorted.sort((e1, e2) ->
                e2.getValue().compareTo(e1.getValue()));

        return sorted;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Long> e : freqs.entrySet()) {
            sb.append(e.getKey()).append(": ")
                    .append(e.getValue()).append('\n');
        }
        return sb.toString();
    }


    public static void main(String [] args) {
        String text = "Un matin un lapin a tué un lapin !";
        WordCount wc = new WordCount(false);

        long w = wc.words(text);

        System.out.println(w + " words\n" + wc);
        System.out.println(wc.sort());
        System.out.println(wc.lambdaSort());
    }
}
