package it.sagelab.specpro.models.psp;

/**
 * The Class Time.
 *
 * @author Simone Vuotto
 */
public class Time {
    /** The value. */
    private final float value;
    
    /** The unit. */
    private final String unit;

    /**
     * Instantiates a new time.
     *
     * @param value the value
     * @param unit the unit
     */
    public Time(float value, String unit) {
        this.value = value;
        this.unit = unit;
    }

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public float getValue() {
		return value;
	}

	/**
	 * Gets the unit.
	 *
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}

}
