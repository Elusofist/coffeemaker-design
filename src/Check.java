import java.util.List;

class Check {
    public static TreeNode search(TreeNode root, String feature) {
        if (root.getFeature().equals(feature))
            return root;
        for (int i = 0; i < root.getChildren().size(); i++) {
            if (root.getChildren().get(i) != null
                    && search(root.getChildren().get(i), feature) != null)
                return search(root.getChildren().get(i), feature);
        }
        return null;
    }

    public static boolean containParent(TreeNode root, List<String> testCase) {
        for (int i = 0; i < testCase.size(); i++) {
            TreeNode node = search(root, testCase.get(i));
            if (node != null
                    && node.getParent() != null
                    && !testCase.contains(node.getParent().getFeature()))
                return false;
        }

        return true;
    }

    public static boolean containChildrenMatchingTypeRule(TreeNode root, List<String> testCase) {
        if (root.getParent() == null) {
            if (!testCase.contains(root.getFeature()))
                return false;
        }
        if (root.getChildrenType() == ChildrenType.MAN) {
            for (int i = 0; i < root.getChildren().size(); i++) {
                if (!root.getChildren().get(i).optional) {
                    if (!testCase.contains(root.getChildren().get(i).getFeature())) {
                        return false;
                    }
                    if (!containChildrenMatchingTypeRule(root.getChildren().get(i), testCase)) {
                        return false;
                    }
                } else {
                    if (testCase.contains(root.getChildren().get(i).getFeature())) {
                        if (!containChildrenMatchingTypeRule(root.getChildren().get(i), testCase)) {
                            return false;
                        }
                    }
                }
            }
        } else if (root.getChildrenType() == ChildrenType.OR) {
            boolean or = false;
            for (int i = 0; i < root.getChildren().size(); i++) {
                if (testCase.contains(root.getChildren().get(i).getFeature())) {
                    or = true;
                    if (!containChildrenMatchingTypeRule(root.getChildren().get(i), testCase)) {
                        return false;
                    }
                }
            }
            if (!or) return false;
        } else if (root.getChildrenType() == ChildrenType.ALT) {
            int count = 0;
            for (int i = 0; i < root.getChildren().size(); i++) {
                if (testCase.contains(root.getChildren().get(i).getFeature())) {
                    count++;
                    if (count > 1) return false;
                    if (!containChildrenMatchingTypeRule(root.getChildren().get(i), testCase)) return false;
                }
            }
            if (count == 0) return false;
        }
        return true;
    }

    static public boolean validFeature(TreeNode root, List<String> testCase) {
        boolean thereIs = false;
        for (String s : testCase) {
            if (search(root, s) == null) {
                return false;
            }
            thereIs = true;
        }
        return thereIs;
    }
}
