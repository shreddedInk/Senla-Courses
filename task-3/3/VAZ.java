public class VAZ implements IProduct {
    @Override
    public void installFirstPart(IProductPart part) {
        System.out.println("Part 1 is installed: " + ((VAZPart) part).getPartName());
    }

    @Override
    public void installSecondPart(IProductPart part) {
        System.out.println("Part 2 is installed: " + ((VAZPart) part).getPartName());
    }

    @Override
    public void installThirdPart(IProductPart part) {
        System.out.println("Part 3 is installed: " + ((VAZPart) part).getPartName());
    }
}
