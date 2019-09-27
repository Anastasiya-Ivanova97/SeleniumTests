import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

class Company {
    private int id;
    private String code;
    private String name_full;
    private String name_short;
    private String inn;
    private CompanyType company_type;
    private String ogrn;
    private Date eqrul_date;
    private Country country;
    private String fio_head;
    private String address;
    private String phone;
    private String e_mail;
    private String www;
    private boolean is_resident;
    private List<Security> securities;

    Company(int id, String code, String name_full, String name_short, String inn, CompanyType company_type,
            String ogrn, Date eqrul_date, Country country, String fio_head, String address,
            String phone, String e_mail, String www, boolean is_resident) {
        this.id = id;
        this.code = code;
        this.name_full = name_full;
        this.name_short = name_short;
        this.inn = inn;
        this.company_type = company_type;
        this.ogrn = ogrn;
        this.eqrul_date = eqrul_date;
        this.country = country;
        this.fio_head = fio_head;
        this.address = address;
        this.phone = phone;
        this.e_mail = e_mail;
        this.www = www;
        this.is_resident = is_resident;
    }

    void setSecurities(List<Security> securities) {
        this.securities = securities;
    }

    void printInfo() {
        System.out.println("Краткое название: " + name_short + "\nДата основания: "
                + new SimpleDateFormat("dd/mm/yyyy").format(eqrul_date));
    }

    void printFinishedSecurities() {
        securities.forEach(security -> {
            if (security.isExpired()) {
                security.printInfo();
            }
        });
    }

    int getFinishedSecuritiesCount() {
        return (int)securities.stream()
                .filter(Security::isExpired)
                .count();
    }

    boolean hasCreatedAfterDate(Date date) {
        return eqrul_date.after(date);
    }

    void printInfoByCode(String code) {
        securities.stream()
                .filter(security -> security.getCurrencyCode().equals(code))
                .forEach(Security::printSecurityInfo);
    }
}
