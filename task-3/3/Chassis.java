public class Chassis implements ILineStep {
    @Override
    public IProductPart buildProductPart() {
        return new VAZPart("Chassis");
    }
}
