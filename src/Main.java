
import java.util.*;


public class Main {

    public static void isValid(List<List<String>> testCases, TreeNode root) {
        for (List<String> aCase : testCases) {
            if (Check.containParent(root, aCase)
                    && Check.validFeature(root, aCase)
                    && Check.containChildrenMatchingTypeRule(root, aCase))
                System.out.println("Valid");
            else System.out.println("Invalid");
        }
    }

    static List<List<String>> getStructure(List<List<String>> allData) {
        List<List<String>> structure = new ArrayList<>();
        int i = 0;
        while (!(allData.get(i).get(0).equals("#"))) {
            structure.add(allData.remove(i));
        }
        allData.remove(i);
        return structure;
    }

    static List<List<String>> getTestCase(List<List<String>> data) {
        List<List<String>> testCase = new ArrayList<>();
        int i = 0;
        while (!(data.get(i).get(0).equals("##"))) {
            testCase.add(data.remove(i));
        }
        data.remove(i);
        return testCase;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String nextLine = scan.nextLine();
        List<List<String>> allData = new ArrayList<>();
        while (!nextLine.equals("###")) {
            allData.add(Parse.parser(nextLine.trim().replaceAll("\\s+", "")));
            nextLine = scan.nextLine();
        }
        scan.close();

        List<List<String>> structure;
        List<List<String>> testCase;

        while (allData.size() != 0) {
            structure = getStructure(allData);
            testCase = getTestCase(allData);

            InputTree tree = new InputTree();
            TreeNode root = tree.toTree(structure);

            isValid(testCase, root);

            System.out.println("+++");

            structure.clear();
            testCase.clear();
        }
    }
}