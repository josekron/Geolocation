package main.java.com.jose.geolocation.vo;

/**
 * The Class ResponseVo.
 */
public class ResponseVo implements java.io.Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1932941273930947931L;

	/**
     * Instantiates a new response vo.
     */
    public ResponseVo()
    {
    }

    /**
     * Instantiates a new response vo.
     *
     * @param errorCode the error code
     * @param errorMessage the error message
     */
    public ResponseVo(Integer errorCode, java.lang.String errorMessage)
    {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    /**
     * Copies constructor from other ResponseVo.
     *
     * @param otherBean the other bean
     */
    public ResponseVo(ResponseVo otherBean)
    {
        this(otherBean.getErrorCode(), otherBean.getErrorMessage());
    }

    /**
     * Copies all properties from the argument value object into this value object.
     *
     * @param otherBean the other bean
     */
    public void copy(ResponseVo otherBean)
    {
        if (otherBean != null)
        {
            this.setErrorCode(otherBean.getErrorCode());
            this.setErrorMessage(otherBean.getErrorMessage());
        }
    }

    /** The error code. */
    private Integer errorCode = 0;

    /**
     * Gets the error code.
     *
     * @return the error code
     */
    public Integer getErrorCode()
    {
        return this.errorCode;
    }

    /**
     * Sets the error code.
     *
     * @param errorCode the new error code
     */
    public void setErrorCode(Integer errorCode)
    {
        this.errorCode = errorCode;
    }

    /** The error message. */
    private java.lang.String errorMessage;

    /**
     * Gets the error message.
     *
     * @return the error message
     */
    public java.lang.String getErrorMessage()
    {
        return this.errorMessage;
    }

    /**
     * Sets the error message.
     *
     * @param errorMessage the new error message
     */
    public void setErrorMessage(java.lang.String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ResponseWsVo [errorCode=" + errorCode + ", errorMessage="
				+ errorMessage + "]";
	}
	
}
