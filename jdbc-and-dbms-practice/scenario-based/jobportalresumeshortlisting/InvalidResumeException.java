package jobportalresumeshortlisting;

class InvalidResumeException extends Exception {

    private final String field;
    private final String value;

    public InvalidResumeException(String field, String value, String reason) {
        super(String.format("Invalid resume — field='%s', value='%s': %s", field, value, reason));
        this.field = field;
        this.value = value;
    }

    public String getField() { return field; }
    public String getValue() { return value; }
}
