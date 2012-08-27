package cz.urbo.dto


public enum FeedbackState {


    /**
     * when feedback is first created by user
     */
    CREATED("nový"),

    /**
     * Ready to send to authority
     */
    READY_TO_SEND("k odeslání"),

    /**
     * when government authority (ward's council...etc.) was informed about citizen's feedback
     */
    SENT_TO_AUTHORITY("odesláno"),

    /**
     * reported issue (feedback) is fixed
     */
    FIXED("opraveno")

    private FeedbackState(String description) {
        this.description = description;
    }

    private final String description;
}
