import java.util.*;

/**
 * Created by jxzhong on 2018/5/22.
 */
public class WordFrequencyGame {
    public String getResult(String inputStr) {

        if (inputStr.split("\\s+").length == 1) {
            return inputStr + " 1";
        } else {

            Map<String, List<Input>> map = groupInputWord(inputStr);

            List<Word> resultModle = tanslateToModle(map);

            resultModle.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

            StringJoiner resultView = buildResultView(resultModle);
            return resultView.toString();

        }
    }

    private StringJoiner buildResultView(List<Word> resultModle) {
        StringJoiner resultView = new StringJoiner("\n");
        for (Word w : resultModle) {
            String s = w.getValue() + " " + w.getWordCount();
            resultView.add(s);
        }
        return resultView;
    }

    private List<Word> tanslateToModle(Map<String, List<Input>> map) {
        List<Word> resultModle = new ArrayList<>();
        for (Map.Entry<String, List<Input>> entry : map.entrySet()) {
            Word input = new Word(entry.getKey(), entry.getValue().size());
            resultModle.add(input);

        }
        return resultModle;
    }

    private Map<String, List<Input>> groupInputWord(String inputStr) {
        String[] arr = inputStr.split("\\s+");
        List<Input> inputList = new ArrayList<>();
        //split the input string with 1 to n pieces of spaces
        for (String s : arr) {
            Input input = new Input(s, 1);
            inputList.add(input);
        }

        //get the map for the next step of sizing the same word
        return getListMap(inputList);
    }

    private Map<String, List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> map = new HashMap<>();
        for (Input input : inputList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(input.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getValue(), arr);
            } else {
                map.get(input.getValue()).add(input);
            }
        }
        return map;
    }


}
