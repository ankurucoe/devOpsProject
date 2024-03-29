public class apiPojo {

    private String first_name;
    private String last_name;
    private String gender;
    private String email;
    private String status;

    /**
     * Create api POJO Object
     * @param first_name
     * @param last_name
     * @param gender
     * @param email
     * @param status
     */
    public apiPojo(String first_name, String last_name, String gender, String email, String status) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.email = email;
        this.status = status;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "apiPojo{" +
                "first_name='" + this.first_name + '\'' +
                ", last_name='" + this.last_name + '\'' +
                ", gender='" + this.gender + '\'' +
                ", email='" + this.email + '\'' +
                ", status='" + this.status + '\'' +
                '}';
    }
}
