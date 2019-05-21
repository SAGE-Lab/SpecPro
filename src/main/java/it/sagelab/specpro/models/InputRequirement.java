package it.sagelab.specpro.models;

public class InputRequirement {

    /** The requirement ID **/
    private String reqId = null;

    /** Input Text **/
    private String text = null;

    /**
     * Gets the requirement id
     *
     * @return the reqId
     */
    public String getReqId() { return reqId; }

    /**
     * Sets the psp id
     *
     * @param reqId the new requirement id
     */
    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    /**
     * Gets the text representation of the requirement
     * @return the requirement in text format
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the requirement text
     * @param text the text of the requirement
     */
    public void setText(String text) {
        this.text = text;
    }

}
