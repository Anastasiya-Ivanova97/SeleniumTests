import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Security {
    private int id;
    private String code;
    private String name_full;
    private String cfi;
    private Date date_to;
    private Date state_reg_date;
    private State state;
    private Currency currency;

    Security(int id, String code, String name_full, String cfi, Date date_to,
             Date state_reg_date, State state, Currency currency) {
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

    boolean isExpired() {
        return new Date(System.currentTimeMillis()).after(date_to);
    }

    void printInfo() {
        System.out.println("Код: " + code);
        System.out.println("Дата истечения: " +new SimpleDateFormat("dd/MM/yy").format(date_to));
        System.out.println("Название: " + name_full);
    }

    String getCurrencyCode() {
        return currency.getCode();
    }

    void printSecurityInfo() {
        System.out.println("id: " + id);
        System.out.println("code: " + code);
    }
}
