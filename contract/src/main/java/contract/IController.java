package contract;

/**
 * The Interface IController.
 *
 * @author Tyreel
 */
public interface IController {
	/**
	 * @throws InterruptedException
	 */
	void play();

	/**
	 * Order perform.
	 *
	 * @param controllerOrder
	 *          the controller order
	 */
	public void orderPerform(ControllerOrder controllerOrder);

	IController getOrderPerformer();

}
