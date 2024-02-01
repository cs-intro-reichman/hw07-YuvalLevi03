
public class SpellChecker {


	public static void main(String[] args) {
	     String word = args[0];
	     int threshold = Integer.parseInt(args[1]);
	     String[] dictionary = readDictionary("dictionary.txt");
	     String correction = spellChecker(word, threshold, dictionary);
	     System.out.println(correction);
	}

	public static String tail(String str) {
	     if (str.length() <= 1 ) {
		 return "";
	     } else {
		 return str.substring(1,str.length());
	     }
	}

	public static int levenshtein(String word1, String word2) {
	     word1 = word1.toLowerCase();
	     word2 = word2.toLowerCase();
	     if (word1.length() == 0) {
		 return word2.length();
	     }
	     if (word2.length() == 0) {
		 return word1.length();
	     }
	     if (word1.substring(0, 1).equals(word2.substring(0, 1))) {
		 return levenshtein(tail(word1), tail(word2));
	     } else {
		 return
		        Math.min(levenshtein(tail(word1), tail(word2)),
			Math.min(levenshtein(tail(word1),word2),
		                 levenshtein(word1, tail(word2)))) + 1;
	     }
	}

	public static String[] readDictionary(String fileName) {
	     String[] dictionary = new String[3000];
	     In in = new In(fileName);
	     for (int i = 0 ; i < 3000 ; i++) {
		  dictionary[i] = in.readLine();
	     }
	     return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
	     String similarWord = "";
	     for(int i = 0 ; i < dictionary.length ; i++) {
		 if (levenshtein(dictionary[i],word) < levenshtein(similarWord, word)) {
                     similarWord = dictionary[i];
		 }
	     }
	     if (levenshtein(similarWord, word) <= threshold) {
		 return similarWord;
	     } else {
		 return word;
	     }
	}

}
