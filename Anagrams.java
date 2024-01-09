/******************************************************************************
 *
 * MAC0122 PRINCÍPIOS DE DESENVOLVIMENTO DE ALGORITMOS
 * Aluno: Carolina Tavares Duarte
 * Numero USP: 12690963
 * Tarefa: E08
 * Data: 17/12/2023
 * 
 * Baseado em ... (breve e se aplicável)
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS 
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO  
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE, 
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS 
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/
import java.util.Arrays;

public class Anagrams {

    public static void main(String[] args) {
        int k = 2; // Valor padrão se nenhum argumento for fornecido
        if (args.length > 0) {
            k = Integer.parseInt(args[0]);
        }

        // Ler palavras da entrada padrão
        String[] words = StdIn.readAllStrings();

        // Organizar palavras em conjuntos de anagramas usando uma tabela de símbolos
        ST<String, SET<String>> anagramSets = new ST<>();
        for (String word : words) {
            char[] charArray = word.toCharArray();
            Arrays.sort(charArray);
            String sortedWord = new String(charArray);

            if (!anagramSets.contains(sortedWord)) {
                anagramSets.put(sortedWord, new SET<>());
            }
            anagramSets.get(sortedWord).add(word);
        }

        // Filtrar conjuntos com pelo menos k anagramas e ordenar a saída
        ST<String, SET<String>> newAnagrams = new ST<>();
        for (String key : anagramSets.keys()) {
            SET<String> anagramSet = anagramSets.get(key);
            if (anagramSet.size() >= k) {
                newAnagrams.put(key, anagramSet);
            }
        }

        // Ordenar a saída conforme especificado
        String[] outputLines = new String[newAnagrams.size()];
        int i = 0;
        for (String key : newAnagrams.keys()) {
            SET<String> anagramSet = newAnagrams.get(key);
            String[] anagramsArray = new String[anagramSet.size()];
            int j = 0;
            for (String anagram : anagramSet) {
                anagramsArray[j++] = anagram;
            }
            Arrays.sort(anagramsArray);
            outputLines[i++] = "+ " + String.join(" ", anagramsArray);
        }

        // Imprimir a saída ordenada
        Arrays.sort(outputLines);
        for (String line : outputLines) {
            StdOut.println(line);
        }
    }
}