class Or implements ChildType {
    ChildrenType type = null;

    @Override
    public ChildrenType getType() {
        return ChildrenType.OR;
    }

    @Override
    public void setType(ChildrenType childrenType) {
        this.type = childrenType;
    }

    @Override
    public boolean isType(TreeNode treeNode) {
        for (int i = 0; i < treeNode.getChildren().size(); i++) {
            if (treeNode.getChildren().get(i).getFeature().equals("|"))
                return true;
        }
        return false;
    }
}
