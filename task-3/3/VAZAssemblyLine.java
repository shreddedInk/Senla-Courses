public class VAZAssemblyLine implements IAssemblyLine {
    private ILineStep firstStep;
    private ILineStep secondStep;
    private ILineStep thirdStep;

    public VAZAssemblyLine(ILineStep firstStep, ILineStep secondStep, ILineStep thirdStep) {
        this.firstStep = firstStep;
        this.secondStep = secondStep;
        this.thirdStep = thirdStep;
    }

    @Override
    public IProduct assembleProduct(IProduct product) {
        System.out.println("VAZ began its assembly at the factory...");
        product.installFirstPart(firstStep.buildProductPart());
        product.installSecondPart(secondStep.buildProductPart());
        product.installThirdPart(thirdStep.buildProductPart());
        System.out.println("The assembly is complete!");
        return product;
    }
}
