package gsn.http.datarequest;

public class LimitCriterion extends AbstractCriterion{
	
	private Integer offset,size	;
	
	/**
	 * <p>
	 * Create a new Limit Criterion from a serialized Criterion description.
	 * The description must follow the syntax:<br />
	 * <code><offset>:<size></code>.
	 * </p>
	 * @param inlinecrits
	 * @return
	 */
	public LimitCriterion (String inlinecrits) throws DataRequestException {
		
		String[] crits = inlinecrits.split(":");

		if (crits.length != 2) throw new DataRequestException (GENERAL_ERROR_MSG + " >" + inlinecrits + "<.") ;

		offset	= Integer.parseInt(crits[0]);
		size	= Integer.parseInt(crits[1]);
	}
	
	public Integer getOffset() {
		return offset;
	}
	
	public Integer getSize() {
		return size;
	}
}
