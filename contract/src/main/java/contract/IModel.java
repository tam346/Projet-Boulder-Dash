package contract;

import entity.element.IMap;
import entity.element.mobile.IMobile;

/**
 * The Interface IModel.
 *
 * @author Tyreel
 */
public interface IModel {
	/**
	 * @return
	 */
	IMap getMap();

	/**
	 * @return
	 */
	IMobile getMyPlayer();
}
