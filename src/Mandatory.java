class Mandatory extends ManOrOpt {

    @Override
    public ChildrenType getType() {
        return ChildrenType.MAN;
    }

    @Override
    public void setType(ChildrenType childrenType) {
        this.type = childrenType;
    }

    @Override
    public boolean isType(TreeNode treeNode) {
        if (treeNode.getParent() == null
                && treeNode.getChildren() == null) return true;
        if (treeNode.getChildren().size() == 2) return true;
        for (TreeNode child : treeNode.getChildren()) {
            if (child.getFeature().equals("+"))
                return true;
        }
        return treeNode.getChildren().size() == 0;
    }
}
