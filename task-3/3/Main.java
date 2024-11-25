public class Main {
    public static void main(String[] args) {
        ILineStep bodyStep = new Body();
        ILineStep chassisStep = new Chassis();
        ILineStep engineStep = new Engine();

        IAssemblyLine vazAssemblyLine = new VAZAssemblyLine(bodyStep, chassisStep, engineStep);

        IProduct vaz = new VAZ();

        vazAssemblyLine.assembleProduct(vaz);
    }
}
