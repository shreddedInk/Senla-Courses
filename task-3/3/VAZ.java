public class VAZ implements IProduct {
    private IProductPart body;
    private IProductPart chassis;
    private IProductPart engine;

    @Override
    public void installFirstPart(IProductPart part) {
        setBody(part);
        System.out.println("Installed part 1: " + ((VAZPart) part).getPartName());
    }

    @Override
    public void installSecondPart(IProductPart part) {
        setChassis(part);
        System.out.println("Installed part 2: " + ((VAZPart) part).getPartName());
    }

    @Override
    public void installThirdPart(IProductPart part) {
        setEngine(part);
        System.out.println("Installed part 3: " + ((VAZPart) part).getPartName());
    }

    public void setBody(IProductPart body) {
        this.body = body;
    }

    public void setChassis(IProductPart chassis) {
        this.chassis = chassis;
    }

    public void setEngine(IProductPart engine) {
        this.engine = engine;
    }

    public void displayParts() {
        System.out.println("VAZ configuration:");
        System.out.println("Body: " + ((VAZPart) body).getPartName());
        System.out.println("Chassis: " + ((VAZPart) chassis).getPartName());
        System.out.println("Engine: " + ((VAZPart) engine).getPartName());
    }
}
