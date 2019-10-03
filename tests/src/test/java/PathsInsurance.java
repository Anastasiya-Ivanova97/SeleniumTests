public class PathsInsurance {
    private String path1 = "[href=\"/products/private_person/health/index.wbp\"]";
    private String dmsPath = "[href=\"/products/private_person/health/dms/generalinfo/index.wbp\"]";
    private String form = "//*[@id=\"form-insurance-application-gtm-processing-config\"]/following-sibling::a";
    private String findSurname = "LastName";
    private String findName = "FirstName";
    private String findMiddleName = "MiddleName";
    private String findRegion = "Region";
    private String titleForm = "//b[text()='Заявка на добровольное медицинское страхование']";

    public String getTitleForm() {
        return titleForm;
    }

    private String phone = "//label[text()='Телефон']/following-sibling::input";
    private String mailField = "//label[text()='Эл. почта']/following-sibling::input";
    private String comment = "//*[@id=\"applicationForm\"]/descendant::textarea";
    private String check = "//*[@type=\"checkbox\"]";
    private String date = "//label[text()='Предпочитаемая дата контакта']/following-sibling::input";
    private String send = "button-m";
    private String errorPath = "//span[text()='Введите адрес электронной почты']";

    public String getPath1() {
        return path1;
    }

    public String getDmsPath() {
        return dmsPath;
    }

    public String getForm() {
        return form;
    }

    public String getFindSurname() {
        return findSurname;
    }

    public String getFindName() {
        return findName;
    }

    public String getFindMiddleName() {
        return findMiddleName;
    }

    public String getFindRegion() {
        return findRegion;
    }

    public String getPhone() {
        return phone;
    }

    public String getMailField() {
        return mailField;
    }

    public String getComment() {
        return comment;
    }

    public String getCheck() {
        return check;
    }

    public String getDate() {
        return date;
    }

    public String getSend() {
        return send;
    }

    public String getErrorPath() {
        return errorPath;
    }
}
