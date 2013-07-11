package org.idris.rts;

/**
 * Idris data types with a constructor function id and references in them.
 * 
 * @author Jan
 */
public class IdrisObject {
    private final int constructorId;
    private final Object data[];
    
    /**
     * Create a new Idris data object.
     * 
     * @param constructorId id of the chosen constructor
     * @param data member data
     */
    public IdrisObject(final int constructorId, final Object ... data) {
	this.constructorId = constructorId;
	this.data = data;
    }
    
    /**
     * Get the constructor id.
     *
     * @return id of the constructor chosen for this object
     */
    public int getConstructorId() {
	return constructorId;
    }

    /**
     * Get the data stored in this object.
     * 
     * @return data passed to the constructor function
     */
    public Object[] getData() {
	return data;
    }
}
