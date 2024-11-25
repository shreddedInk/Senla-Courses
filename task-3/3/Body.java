public class Body implements ILineStep {
    @Override
    public IProductPart buildProductPart() {
        return new VAZPart("Body");
    }
}
