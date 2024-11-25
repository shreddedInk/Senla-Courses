public class Engine implements ILineStep {
    @Override
    public IProductPart buildProductPart() {
        return new VAZPart("Engine (8 valve)");
    }
}
