import java.util.LinkedList;
import java.util.List;

class TreeNode {
    private List<TreeNode> children;
    private String feature;
    private ChildrenType childrenType;
    private TreeNode parent;
    boolean optional = false;

    public TreeNode(String featureName) {
        this.feature = featureName;
        this.children = new LinkedList<>();
        this.childrenType = null;
        this.parent = null;
    }

    public TreeNode() {
        this.feature = null;
        this.children = new LinkedList<>();
        this.childrenType = null;
        this.parent = null;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public void setChildren(List<TreeNode> tree) {
        this.children = tree;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public ChildrenType getChildrenType() {
        return this.childrenType;
    }

    public void setChildrenType(ChildrenType childrenType) {
        this.childrenType = childrenType;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public TreeNode getParent() {
        return this.parent;
    }

    public static List<TreeNode> toTreeNode(List<String> line) {
        List<TreeNode> treeNodized = new LinkedList<>();
        for (String s : line) {
            treeNodized.add(new TreeNode(s));
        }
        List<TreeNode> t = new LinkedList<>();
        for (int i = 1; i < treeNodized.size(); i++)
            t.add(treeNodized.get(i));
        treeNodized.get(0).setChildren(t);
        return treeNodized;
    }

    static public ChildrenType defineType(TreeNode treeNode) {
        Or or = new Or();
        Alternative alt = new Alternative();
        Mandatory man = new Mandatory();
        if (or.isType(treeNode)) {
            return ChildrenType.OR;
        } else if (alt.isType(treeNode)) {
            return ChildrenType.ALT;
        } else if (man.isType(treeNode)) {
            return ChildrenType.MAN;
        }
        return null;
    }

    public static int indexOf(List<TreeNode> list, TreeNode searchItem) {
        int index = 0;
        for (TreeNode item : list) {
            if (item.getFeature().equals(searchItem.getFeature()))
                return index;
            index += 1;
        }
        return -1;
    }
}
