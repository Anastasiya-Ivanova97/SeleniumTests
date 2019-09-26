import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Company {
    private int id;
    private String code;
    private String name_full;
    private String name_short;
    private String inn;
    private CompanyType company_type;
    private String ogrn;
    private LocalDateTime eqrul_date;
    private Country country;
    private String fio_head;
    private String address;
    private String phone;
    private String e_mail;
    private String www;
    private boolean is_resident;
    private List<Security> securities;

    public Company(int id, String code, String name_full, String name_short, String inn, CompanyType company_type,
                   String ogrn, LocalDateTime eqrul_date, Country country, String fio_head, String address,
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

    public void setSecurities(List<Security> securities) {
        this.securities = securities;
    }

    public void printInfo() {
        System.out.println("Краткое название: " + name_short + "\nДата основания: "
                + eqrul_date.format(DateTimeFormatter.ofPattern("dd/MM/yy")));
    }

    public void printFinishedSecurities() {
        securities.forEach(security -> {
            if (security.isExpired()) {
                security.printInfo();
            }
        });
    }

    public int getFinishedSecuritiesCount() {
        int count = 0;
        for (Security security : securities) {
            if (security.isExpired()) {
                count++;
            }
        }
        return count;
    }

    public boolean hasCreatedAfterDate(LocalDateTime date) {
        return eqrul_date.isAfter(date);
    }

    public void printInfoByCode(String code) {
        for (Security security : securities) {
            if (security.getCurrencyCode().equals(code)) {
                security.printSecurityInfo();
            }
        }
    }
}
