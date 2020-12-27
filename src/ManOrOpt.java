abstract class ManOrOpt implements ChildType {
    ChildrenType type = null;

    @Override
    public abstract boolean isType(TreeNode treeNode);
}
