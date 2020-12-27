import java.util.LinkedList;
import java.util.List;

class InputTree {

    public static void setOptional(List<List<TreeNode>> treeNodeStructure) {
        for (List<TreeNode> treeNodes : treeNodeStructure) {
            for (TreeNode treeNode : treeNodes) {
                if (treeNode.getFeature().substring(0, 1).equals("?")) {
                    Parse.questionMarkRemover(treeNode);
                    treeNode.optional = true;
                }
            }
        }
    }

    public static void insertFeatures(List<List<String>> structure, List<List<TreeNode>> treeNodeStructure) {
        for (List<String> list : structure) {
            treeNodeStructure.add(TreeNode.toTreeNode(list));
        }
    }

    public static void insertParents(List<List<TreeNode>> treeNodeStructure) {
        for (List<TreeNode> treeNodes : treeNodeStructure) {
            for (int j = 1; j < treeNodes.size(); j++) {
                treeNodes.get(j).setParent(treeNodes.get(0));
            }
        }
    }

    public static void setChildren(List<List<TreeNode>> treeNodeStructure, List<TreeNode> treeNodes, int x, int i) {
        List<TreeNode> output = new LinkedList<>();
        for (int j = 1; j < treeNodeStructure.get(i).size(); j++) {
            output.add(treeNodeStructure.get(i).get(j));
        }
        treeNodes.get(x).setChildren(output);
    }

    public static void insertChildren(List<List<TreeNode>> treeNodeStructure) {
        for (int i = 1; i < treeNodeStructure.size(); i++) {
            for (List<TreeNode> treeNodes : treeNodeStructure) {
                int x = TreeNode.indexOf(treeNodes, treeNodeStructure.get(i).get(0));
                if (x > 0) {
                    setChildren(treeNodeStructure, treeNodes, x, i);
                    break;
                }
            }
        }
    }


    public static void insertTypes(TreeNode root) {
        if (!root.getFeature().equals("+")
                || !root.getFeature().equals("=")
                || !root.getFeature().equals("|")
                || !root.getFeature().equals("^"))
            root.setChildrenType(TreeNode.defineType(root));
        for (int i = 0; i < root.getChildren().size(); i++) {
            if (root.getChildren().get(i) != null) {
                insertTypes(root.getChildren().get(i));
            }
        }
    }

    public static void traverseAndRemove(TreeNode root) {
        Parse.structurePunctuationRemover(root);
        for (int i = 0; i < root.getChildren().size(); i++) {
            if (root.getChildren().get(i) != null) {
                traverseAndRemove(root.getChildren().get(i));
            }
        }
    }

    public TreeNode toTree(List<List<String>> structure) {

        List<List<TreeNode>> treeNodeStructure = new LinkedList<>();

        insertFeatures(structure, treeNodeStructure);

        setOptional(treeNodeStructure);

        insertParents(treeNodeStructure);

        insertChildren(treeNodeStructure);

        insertTypes(treeNodeStructure.get(0).get(0));

        traverseAndRemove(treeNodeStructure.get(0).get(0));

        return treeNodeStructure.get(0).get(0);
    }

}
