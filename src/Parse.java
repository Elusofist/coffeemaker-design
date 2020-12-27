import java.util.ArrayList;
import java.util.List;

class Parse {

    static List<String> parser(String string) {
        List<String> output = new ArrayList<>();
        String temp = "";
        if (string.length() != 0) {
            if (string.charAt(0) == '{') {
                string = string.substring(1);
                string = string.substring(0, string.length() - 1);
                for (int i = 0; i < string.length(); i++) {
                    if (string.charAt(i) != ',') {
                        temp += string.substring(i, i + 1);
                    } else {
                        output.add(temp);
                        temp = "";
                    }
                }
            } else {
                for (int i = 0; i < string.length(); i++) {
                    if (string.charAt(i) != '='
                            && string.charAt(i) != '+'
                            && string.charAt(i) != '|'
                            && string.charAt(i) != '^')
                        temp += string.substring(i, i + 1);
                    else {
                        output.add(temp);
                        output.add(string.substring(i, i + 1));
                        temp = "";
                    }
                }
            }
            output.add(temp);
        } else output = null;
        return output;
    }

    static public void structurePunctuationRemover(TreeNode root) {
        for (int i = 0; i < root.getChildren().size(); i++) {

            if (root.getChildren().get(i).getFeature().equals("=")
                    || root.getChildren().get(i).getFeature().equals("+")
                    || root.getChildren().get(i).getFeature().equals("|")
                    || root.getChildren().get(i).getFeature().equals("^")) {
                root.getChildren().remove(i);
                i--;
            }
        }

        for (int i = 0; i < root.getChildren().size(); i++) {
            if (root.getChildren().get(i) != null)
                structurePunctuationRemover(root.getChildren().get(i));
        }
    }

    static public void questionMarkRemover(TreeNode treeNode) {
        String feature = treeNode.getFeature().substring(1);
        treeNode.setFeature(feature);
    }
}
