import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Security {
    private int id;
    private String code;
    private String name_full;
    private String cfi;
    private LocalDateTime date_to;
    private LocalDateTime state_reg_date;
    private State state;
    private Currency currency;

    public Security(int id, String code, String name_full, String cfi, LocalDateTime date_to,
                    LocalDateTime state_reg_date, State state, Currency currency) {
        this.id = id;
        this.code = code;
        this.name_full = name_full;
        this.cfi = cfi;
        this.date_to = date_to;
        this.state_reg_date = state_reg_date;
        this.state = state;
        this.currency = currency;
    }

    @Override
    public String toString() {
        return id + code + name_full + cfi + date_to + state_reg_date;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(date_to);
    }

    public void printInfo() {
        System.out.println("Код: " + code);
        System.out.println("Дата истечения: " + date_to.format(DateTimeFormatter.ofPattern("dd/MM/yy")));
        System.out.println("Название: " + name_full);
    }

    public String getCurrencyCode() {
        return currency.getCode();
    }

    public void printSecurityInfo() {
        System.out.println("id: " + id);
        System.out.println("code: " + code);
    }
}

