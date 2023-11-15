public class NewEntVistor implements Visitor {

    @Override
    public int visit(User user) {
        return 1;
    }

    @Override
    public int visit(Group userGroup) {
        return 1;
    }
}