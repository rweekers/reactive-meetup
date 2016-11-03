package nl.craftsmen.workshops.reactivemeetup.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Convenience class for constructing/initializing {@link Map} instances via chainable {@link #set} method calls.
 *
 * @param <KeyType>   Type used for the keys of the map.
 * @param <ValueType> Type used for the values of the map.
 */
public class MapBuilder<KeyType, ValueType> {
	
	/** Map which holds the properties that are to be set for the {@link Map} instance that is to be created/initialized. */
	private final Map<KeyType, ValueType> map = new LinkedHashMap<>();
	
	/**
	 * Sets the value for the specified key.
	 *  
	 * @param  key   Key for which the value is to be updated.
	 * @param  value Value that is to be stored for the specified key.
	 * @return       The {@link MapBuilder} itself to support chaining.
	 */
	public MapBuilder<KeyType, ValueType> set(KeyType key, ValueType value) {
		map.put(key, value);
		return this;
	}
	
	/**
	 * Builds a {@link Map} instance that receives the key-value pairs that were defined using the {@link #set} method.
	 *  
	 * @return  The {@link Map} instance that was created.
	 */
	public Map<KeyType, ValueType> build() {
		return new LinkedHashMap<>(map);
	}
	
	/**
	 * Stores the key-value pairs that were defined using the {@link #set} method in the specified map. Use this method instead of
	 * {@link #build()} to update an existing map. Can also be used in case more control is required over the type of {@link Map} that is
	 * needed.
	 * 
	 * @param targetMap         A {@link Map} instance in which the key-value pairs should be stored.
	 * @param <MapType>         Type of the target map.
	 * @return                  The specified target map in which the key-value pairs have been stored.
	 */
	public <MapType extends Map<? super KeyType, ? super ValueType>> MapType applyTo(MapType targetMap) {
		
		targetMap.putAll(map);
		
		return targetMap;
	}

}
